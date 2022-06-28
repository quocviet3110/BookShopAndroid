package com.example.nhom15bookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom15bookshop.adapter.DonHangAdapter;
import com.example.nhom15bookshop.adapter.RecyclerViewBookAdapter;
import com.example.nhom15bookshop.models.BookDTO;
import com.example.nhom15bookshop.models.CategoryDTO;
import com.example.nhom15bookshop.models.OrderDTO;
import com.example.nhom15bookshop.models.OrderDetailDTO;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DonHangActivity extends AppCompatActivity {
    ListView lvDonHang;
    DonHangAdapter donHangAdapter;
    List<OrderDetailDTO> data = new ArrayList<>();
    String strUsername = "";
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        setControl();
        if(checkLoginRememeber()<0){
            intent = new Intent(DonHangActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        getCart();

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

    private void setControl() {
        lvDonHang = findViewById(R.id.lvDonHang);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_shopping_cart) {
            Intent intent = new Intent(DonHangActivity.this, GioHangActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void getCart() {
        String url = "http://192.168.182.129:8081/api/findAllByIdCustomer/"+strUsername;
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
                //Toast.makeText(DonHangActivity.this, "1", Toast.LENGTH_SHORT).show();
                getData(response);

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
        data.clear();

        try {
            JSONArray jsonObject = new JSONArray(response);

            for(int i=0;i<jsonObject.length();i++){

                //đơn hàng
                JSONObject jresponse = jsonObject.getJSONObject(i);
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId(jresponse.getInt("id"));
                orderDTO.setConvertDate(jresponse.getString("date"));
                orderDTO.setStatus(jresponse.getString("status"));
                JSONArray jsonArray = jresponse.getJSONArray("listOrder");
                for(int j = 0;j < jsonArray.length();j++) {

                    JSONObject object = jsonArray.getJSONObject(j);
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                    orderDetailDTO.setNumber(object.getInt("number"));
                    orderDetailDTO.setId(object.getInt("id"));
                    //Get book*//*
                    JSONObject bookJson = object.getJSONObject("bookOrder");
                    BookDTO bookDTO = new BookDTO();
                    bookDTO.setName(bookJson.getString("name"));
                    bookDTO.setUrlImage(bookJson.getString("urlImage"));
                    bookDTO.setPrice((float) bookJson.getDouble("price"));
                    orderDetailDTO.setBookOrder(bookDTO);
                    orderDetailDTO.setOrderDetail(orderDTO);

                    data.add(orderDetailDTO);
                }

            }
            donHangAdapter = new DonHangAdapter(DonHangActivity.this,R.layout.listview_donhang,data);
            lvDonHang.setAdapter(donHangAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}