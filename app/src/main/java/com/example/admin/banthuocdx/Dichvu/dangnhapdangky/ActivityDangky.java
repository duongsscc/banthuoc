package com.example.admin.banthuocdx.Dichvu.dangnhapdangky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.banthuocdx.R;

import java.util.HashMap;
import java.util.Map;

public class ActivityDangky extends AppCompatActivity {

    CardView cardViewDangNhap, cardViewDangKy;
    EditText edtHoTen, edtTaiKhoan, edtMatKhau, edtSdt, edtDiaChi;
    Animation animation;
    String urlDangKy = "http://192.168.0.102/qlbt_php/dangky.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        AnhXa();

        cardViewDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewDangKy.startAnimation(animation);
                if (edtTaiKhoan.getText().toString().isEmpty() || edtMatKhau.getText().toString().isEmpty()
                        || edtHoTen.getText().toString().isEmpty() || edtSdt.getText().toString().isEmpty()
                        || edtDiaChi.getText().toString().isEmpty()) {
                    Toast.makeText(ActivityDangky.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    DangKy(urlDangKy);
                    startActivity(new Intent(ActivityDangky.this, ActivityDangNhap.class));
                }
            }
        });

        cardViewDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewDangNhap.startAnimation(animation);
                startActivity(new Intent(ActivityDangky.this, ActivityDangNhap.class));
            }
        });
    }

    private void AnhXa() {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

        cardViewDangNhap = (CardView) findViewById(R.id.cardViewLogIn);
        cardViewDangKy = (CardView) findViewById(R.id.cardViewSignUp);
        edtHoTen = (EditText) findViewById(R.id.editTextHoTen);
        edtTaiKhoan = (EditText) findViewById(R.id.editTextTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.editTextMatKhau);
        edtSdt = (EditText) findViewById(R.id.editTextSDT);
        edtDiaChi = (EditText) findViewById(R.id.editTextDiaChi);
    }

    private void DangKy(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("fail")) {
                            Toast.makeText(ActivityDangky.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActivityDangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("hotenKH", edtHoTen.getText().toString().trim());
                params.put("sdtKH", edtSdt.getText().toString().trim());
                params.put("diachiKH", edtDiaChi.getText().toString().trim());
                params.put("anhKH", "");
                params.put("anhchitietKH", "");
                params.put("taikhoanKH", edtTaiKhoan.getText().toString().trim());
                params.put("matkhauKH", edtMatKhau.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
