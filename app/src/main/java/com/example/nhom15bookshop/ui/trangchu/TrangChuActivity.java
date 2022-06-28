package com.example.nhom15bookshop.ui.trangchu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom15bookshop.ChiTietSanPhamActivity;
import com.example.nhom15bookshop.GioHangActivity;
import com.example.nhom15bookshop.LoginActivity;
import com.example.nhom15bookshop.MainActivity;
import com.example.nhom15bookshop.R;
import com.example.nhom15bookshop.RecyclerItemClickListener;
import com.example.nhom15bookshop.adapter.RecyclerViewBookAdapter;
import com.example.nhom15bookshop.models.BookDTO;
import com.example.nhom15bookshop.models.PaginationScrollListener;
import com.example.nhom15bookshop.models.UserDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnLienQuan, btnMoiNhat, btnBanChay, btnGia;
    private List<BookDTO> data;
    private List<BookDTO> mlist = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerViewBookAdapter adapter;
    private boolean isLoadding;
    private boolean isLastPage;
    private int currentPage = 1;
    private int totalPage = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu2);
        // anh xa
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setControl();
        adapter = new RecyclerViewBookAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(TrangChuActivity.this,2);
        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            public void loadMoreItem() {
                isLoadding = true;
                progressBar.setVisibility(View.VISIBLE);
                currentPage += 1;
                loadNextPage(currentPage);

            }

            @Override
            public boolean isLoading() {
                return isLoadding;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });



        btnLienQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChuActivity.this, ChiTietSanPhamActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(TrangChuActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        int id = data.get(position).getId();
                        Intent intent = new Intent(TrangChuActivity.this, ChiTietSanPhamActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        getBook(1);
    }
    private List<BookDTO> getBook(int currentPage) {
        data = new ArrayList<BookDTO>();
        String url = "http://192.168.182.129:8081/api/bookList?limit=6&page="+currentPage;
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

                adapter = new RecyclerViewBookAdapter(data,TrangChuActivity.this);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        return data;
    }
    public void getData(String response) {
        data.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject object;
            totalPage = jsonObject.getInt("totalPage");
            currentPage = jsonObject.getInt("page");
            JSONArray jsonArray = jsonObject.getJSONArray("listResult");
            for(int i = 0;i < jsonArray.length();i++){
                object = jsonArray.getJSONObject(i);
                BookDTO bookDTO = new BookDTO();
                bookDTO.setId(object.getInt("id"));
                bookDTO.setName(object.getString("name"));
                bookDTO.setTitle(object.getString("title"));
                bookDTO.setNumber(object.getInt("number"));
                bookDTO.setPrice((float) object.getDouble("price"));
                bookDTO.setUrlImage(object.getString("urlImage"));
                data.add(bookDTO);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void setControl() {
        recyclerView = findViewById(R.id.idCourseRV);
        btnLienQuan = findViewById(R.id.btnLienQuan);
        btnMoiNhat = findViewById(R.id.btnMoiNhat);
        btnBanChay = findViewById(R.id.btnBanChay);
        btnGia = findViewById(R.id.btnGia);
        progressBar=findViewById(R.id.progress_bar);
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
            Intent intent = new Intent(TrangChuActivity.this, GioHangActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadNextPage(int pageLoad){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<BookDTO> list = getBook(pageLoad);
                mlist.addAll(list);
                adapter.notifyDataSetChanged();
                isLoadding = false;
                progressBar.setVisibility(View.GONE);
                if(currentPage == totalPage){
                    isLastPage=true;
                }

            }
        },2000);

    }


}
