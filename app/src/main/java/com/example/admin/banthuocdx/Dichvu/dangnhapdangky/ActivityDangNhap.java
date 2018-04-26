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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.banthuocdx.Dichvu.ListPage.ListPageActivity;
import com.example.admin.banthuocdx.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityDangNhap extends AppCompatActivity {
    TextView textView;
    Animation animation;
    EditText edtTaikhoan, edtMatkhau;
    CardView cardViewDangNhap;
    String taikhoan, matkhau;
    String urlDangNhap = "http://192.168.0.102/qlbt_php/dangnhap.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        textView = findViewById(R.id.textthongbao);
        Anhxa();

        cardViewDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewDangNhap.startAnimation(animation);
                if (edtMatkhau.getText().toString().isEmpty() || edtTaikhoan.getText().toString().isEmpty()) {
                    Toast.makeText(ActivityDangNhap.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    DangNhap(urlDangNhap);
                }
            }
        });
    }

    private void Anhxa() {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        edtTaikhoan = (EditText) findViewById(R.id.editTextTK);
        edtMatkhau = (EditText) findViewById(R.id.editTextMK);

        cardViewDangNhap = (CardView) findViewById(R.id.cardViewDangNhap);
    }

    private void DangNhap(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);

                                String taikhoan = object.getString("Taikhoan");
                                String matkhau = object.getString("Matkhau");
                                if (edtTaikhoan.getText().toString().trim().equals(taikhoan) && edtMatkhau.getText().toString().trim().equals(matkhau)) {
                                    Intent intent = new Intent(ActivityDangNhap.this, ListPageActivity.class);
                                    intent.putExtra("tentk", taikhoan);
                                    startActivity(intent);

                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("loi", error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

}
