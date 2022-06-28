package com.example.nhom15bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom15bookshop.adapter.ListviewCTSPAdapter;
import com.example.nhom15bookshop.models.AuthorDTO;
import com.example.nhom15bookshop.models.BookDTO;

import com.example.nhom15bookshop.models.CategoryDTO;
import com.example.nhom15bookshop.models.CommentDTO;
import com.example.nhom15bookshop.models.CustomerDTO;
import com.example.nhom15bookshop.models.PublisherDTO;
import com.example.nhom15bookshop.ui.trangchu.TrangChuActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView ivProduct;
    TextView tvTitle, tvPrice, tvComment,tvContent,tvAuthor,tvDescription,tvCategory,tvPublisher;
    ListView lvComment;
    Button btnThemVaoGio, btnMua,btnGui;
    ScrollView scrollView;
    ListviewCTSPAdapter listviewCTSPAdapter;
    List<CommentDTO> dataComment = new ArrayList<>();
    BookDTO data = new BookDTO();
    ArrayList<CommentDTO> comment = new ArrayList<>();
    String strUsername = "";
    Intent intent = null;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        setControl();
        if(checkLoginRememeber()<0){
            intent = new Intent(ChiTietSanPhamActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        setEvent();

    }

    private void setEvent() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        getBookDetails(id);
        getComment(id);

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertComment();
            }
        });
        btnThemVaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertCart();
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertOrder();
            }
        });

    }
    public int checkLoginRememeber(){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        boolean chk = sharedPreferences.getBoolean("REMEMBER",false);
        if(chk){
            strUsername = sharedPreferences.getString("USERNAME","");
            return 1;
        }
        return -1;
    }
    private void insertOrder() {
        String url = "http://192.168.182.129:8081/api/order";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", strUsername);
        params.put("idStore", "1");
        params.put("status", "Chờ xác nhận");


        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = Integer.parseInt(response.getString("id"));
                            insertOrderDatail(id);
                            //Toast.makeText(ChiTietSanPhamActivity.this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
    private void insertOrderDatail(int idDt) {
        String url = "http://192.168.182.129:8081/api/orderDetailSave";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(idDt));
        params.put("idBook", String.valueOf(id));
        params.put("number", String.valueOf(1));


        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            Toast.makeText(ChiTietSanPhamActivity.this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
    private void insertCart() {

        String url = "http://192.168.182.129:8081/api/cart";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", strUsername);
        params.put("number", "1");
        params.put("idBook", String.valueOf(id));

        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
    private void insertComment() {
        Toast.makeText(ChiTietSanPhamActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();

        String url = "http://192.168.182.129:8081/api/comment";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", strUsername);
        params.put("content", tvContent.getText().toString());
        params.put("idBook", String.valueOf(id));

        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
    private void getBookDetails(int id) {
        String url = "http://192.168.182.129:8081/api/book/"+id;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null){
                    try {
                        response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                getData(response);
                tvTitle.setText(data.getName());
                tvPrice.setText(data.getPrice()+"đ");
                tvAuthor.setText(data.getAuthors().getName());
                tvCategory.setText(data.getCategory().getName());
                tvDescription.setText(data.getTitle());
                tvPublisher.setText(data.getPublishers().getName());
                Picasso.get().load(data.getUrlImage()).into(ivProduct);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
    public void getData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            //get authors
            JSONObject authorsJson = jsonObject.getJSONObject("authors");
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setName(authorsJson.getString("name"));
            //get NXB
            JSONObject publishersJson = jsonObject.getJSONObject("publishers");
            PublisherDTO publisherDTO = new PublisherDTO();
            publisherDTO.setName(publishersJson.getString("name"));
            //Get category
            JSONObject categoryJson = jsonObject.getJSONObject("category");
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(categoryJson.getString("name"));

            data.setId(jsonObject.getInt("id"));
            data.setName(jsonObject.getString("name"));
            data.setTitle(jsonObject.getString("title"));
            data.setPrice((float) jsonObject.getDouble("price"));
            data.setUrlImage(jsonObject.getString("urlImage"));
            data.setAuthors(authorDTO);
            data.setCategory(categoryDTO);
            data.setPublishers(publisherDTO);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void getComment(int id) {
        String url = "http://192.168.182.129:8081/api/comment/"+id;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null){
                    try {
                        response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                getDataComment(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
    public void getDataComment(String response) {
        comment.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject object;
            JSONArray jsonArray = jsonObject.getJSONArray("listResult");
            for(int i = 0;i < jsonArray.length();i++){
                object = jsonArray.getJSONObject(i);
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(object.getInt("id"));
                commentDTO.setContent(object.getString("content"));
                commentDTO.setConvertDate(object.getString("date"));
                JSONObject customer = object.getJSONObject("customerComment");

                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setName(customer.getString("name"));
                customerDTO.setAvatar(customer.getString("avatar"));
                commentDTO.setCustomerComment(customerDTO);
                //Toast.makeText(ChiTietSanPhamActivity.this, ""+commentDTO.getCustomerComment().getName(), Toast.LENGTH_SHORT).show();


                comment.add(commentDTO);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(comment.isEmpty()){
            Toast.makeText(ChiTietSanPhamActivity.this, "rỗng", Toast.LENGTH_SHORT).show();
        }
        listviewCTSPAdapter = new ListviewCTSPAdapter(ChiTietSanPhamActivity.this,R.layout.listview_chitietsanpham,comment);
        lvComment.setAdapter(listviewCTSPAdapter);

    }
    private void setControl() {
        tvContent = findViewById(R.id.content);
        ivProduct = findViewById(R.id.ivProduct);
        tvTitle = findViewById(R.id.tvTitle);
        tvPrice = findViewById(R.id.tvPrice);
        tvComment = findViewById(R.id.tvComment);
        lvComment = findViewById(R.id.lvComment);
        btnThemVaoGio = findViewById(R.id.btnThemVaoGio);
        btnMua = findViewById(R.id.btnMua);
        scrollView = findViewById(R.id.idScrollView);
        btnGui = findViewById(R.id.btnSend);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvDescription = findViewById(R.id.tvDescription);
        tvPublisher = findViewById(R.id.tvPublisher);
        tvCategory = findViewById(R.id.tvCategory);
    }

}