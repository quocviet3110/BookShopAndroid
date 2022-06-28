package com.example.nhom15bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom15bookshop.models.BookDTO;
import com.example.nhom15bookshop.models.UserDTO;
import com.example.nhom15bookshop.ui.trangchu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private EditText tvUsername,tvPassword;
    private Button btnLogin,btnSignup;
    private CheckBox chkRemerberPass;
    private UserDTO data = new UserDTO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setControl() {
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        btnLogin = findViewById(R.id.btnLogin);
        chkRemerberPass= findViewById(R.id.chkRemerberPass);
        btnSignup = findViewById(R.id.btnDangKy);
    }
    private void login() {
        String url = "http://192.168.182.129:8081/api/auth/signin";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", tvUsername.getText().toString());
        params.put("password", tvPassword.getText().toString());

        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String responseStr = response.toString();
                        if (responseStr != null){
                            try {
                                responseStr = new String(responseStr.getBytes("ISO-8859-1"), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                        getData(responseStr);
                        rememberUser(data.getUsername(),data.getJwt(), data.getPassword(),chkRemerberPass.isChecked());
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mặt khẩu!", Toast.LENGTH_SHORT).show();
            }
        });

        // add the request object to the queue to be executed
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
    public void getData(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            data.setUsername(jsonObject.getString("username"));
            data.setPassword(jsonObject.getString("password"));
            data.setJwt(jsonObject.getString("jwt"));
            //data.setRole(jsonObject.getString("roles"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void rememberUser(String username,String jwt,String password,boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        if(!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",username);
            editor.putString("JWT",jwt);
            editor.putString("PASSWORD",password);
            editor.putBoolean("REMEMBER",status);

        }
        editor.commit();
    }

}