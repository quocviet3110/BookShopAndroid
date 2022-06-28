package com.example.nhom15bookshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.example.nhom15bookshop.adapter.GioHangAdapter;
import com.example.nhom15bookshop.adapter.ListviewCTSPAdapter;
import com.example.nhom15bookshop.models.BookDTO;
import com.example.nhom15bookshop.models.CartDTO;
import com.example.nhom15bookshop.models.CommentDTO;
import com.example.nhom15bookshop.models.CustomerDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    ListView lvGioHang;
    Button btnMua;
    ScrollView scrollView;
    GioHangAdapter gioHangAdapter;
    List<CartDTO> data = new ArrayList<>();
    List<Integer> dsSanPhamDaChon,ids;
    List<Integer> dsSoLuongSanPham,idBooks,idsNumber;
    int quantity = 1;
    String strUsername = "";
    Intent intent = null;
    int tongTien = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setControl();
        setEvent();
        if(checkLoginRememeber()<0){
            intent = new Intent(GioHangActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        getCart();

    }

    private void setEvent() {


        ids = new ArrayList<Integer>(Collections.nCopies(data.size(), 0));

        //gioHangAdapter = new GioHangAdapter(GioHangActivity.this,R.layout.listview_giohang,data);
        //lvGioHang.setAdapter(gioHangAdapter);
        lvGioHang.setOnItemClickListener(this::lvGioHangOnItemClickEvent);

        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idBooks = new ArrayList<>();
                idsNumber = new ArrayList<>();
                for (int i = 0;i < dsSanPhamDaChon.size();i++) {

                    if (dsSanPhamDaChon.get(i) == 1) {
                        idBooks.add(ids.get(i));
                        idsNumber.add(dsSoLuongSanPham.get(i));
                    }


                }
                insert(idBooks,idsNumber);
            }
        });

    }

    private void setControl() {
        lvGioHang = findViewById(R.id.lvGioHang);
        btnMua = findViewById(R.id.btnMua);
        scrollView = findViewById(R.id.idScrollView);
    }

    private void lvGioHangOnItemClickEvent(AdapterView<?> adapterView, View view, int i, long l) {

    }
    public void insert(List<Integer> idBooks,List<Integer> idsNumber){
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
                            for(int i=0;i<idBooks.size();i++){
                                insertOrderDatail(id,idBooks.get(i),idsNumber.get(i));
                            }


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

    private void insertOrderDatail(int idDt,int idBook,int idNumber) {
        String url = "http://192.168.182.129:8081/api/orderDetailSave";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(idDt));
        params.put("idBook",String.valueOf(idBook) );
        params.put("number", String.valueOf(idNumber));


        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            Toast.makeText(GioHangActivity.this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();
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
    public void sanPhamDaChon(int i,int choose, int id) {
        dsSanPhamDaChon.set(i,choose);
        ids.set(i,id);
        if (choose == 1) {


        } else {
            // tru tong tien

        }
    }

    public void soLuongSanPham(int i,int quantity) {
        dsSoLuongSanPham.set(i,quantity);
    }
    private void getCart() {
        String url = "http://192.168.182.129:8081/api/cart/"+strUsername;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        response = new String(response.getBytes("ISO-8859-1"), "UTF-8");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    getData(response);
                    gioHangAdapter = new GioHangAdapter(GioHangActivity.this,R.layout.listview_giohang,data);
                    lvGioHang.setAdapter(gioHangAdapter);
                    dsSanPhamDaChon = new ArrayList<Integer>(Collections.nCopies(data.size(), 0));
                    dsSoLuongSanPham = new ArrayList<Integer>(Collections.nCopies(data.size(), 1));
                    ids = new ArrayList<Integer>(Collections.nCopies(data.size(), 0));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void getData(String response) {
        data.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject object;
            JSONArray jsonArray = jsonObject.getJSONArray("listResult");
            for(int i = 0;i < jsonArray.length();i++){
                object = jsonArray.getJSONObject(i);
                CartDTO cartDTO = new CartDTO();
                cartDTO.setId(object.getInt("id"));
                cartDTO.setNumber(object.getInt("number"));
                JSONObject book = object.getJSONObject("bookCart");

                BookDTO bookDTO = new BookDTO();
                bookDTO.setId(book.getInt("id"));
                bookDTO.setName(book.getString("name"));
                bookDTO.setUrlImage(book.getString("urlImage"));
                bookDTO.setPrice((float) book.getDouble("price"));

                cartDTO.setBookCart(bookDTO);
                //Toast.makeText(GioHangActivity.this, ""+bookDTO.getName(), Toast.LENGTH_SHORT).show();


                data.add(cartDTO);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    public void xoaSanPham(CartDTO cart) {


    }
}