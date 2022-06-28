package com.example.nhom15bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom15bookshop.models.BookDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DangKyActivity extends AppCompatActivity {
    private EditText etHoTen,etEmail,etUsername,etPassword,etConfirmPassword;

    private Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        setControl();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(full)
                insertUser();
            }
        });
    }
    public void insertUser(){
        Toast.makeText(DangKyActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();

        String url = "http://192.168.182.129:8081/api/user";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", etUsername.getText().toString());
        params.put("password", etPassword.getText().toString());
        params.put("idRole", String.valueOf(1));

        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String username = response.getString("username");
                            insertCustomer(username);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(DangKyActivity.this, "Tài khoản bị trùng!", Toast.LENGTH_SHORT).show();
            }
        });

        // add the request object to the queue to be executed
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
    public void insertCustomer(String username){
        Toast.makeText(DangKyActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();

        String url = "http://192.168.182.129:8081/api/cutomer";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("name", etHoTen.getText().toString());
        params.put("email", etEmail.getText().toString());

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
    public void setControl(){
        etHoTen = findViewById(R.id.etHoTen);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnDangKy = findViewById(R.id.btnDangKy);

    }
}