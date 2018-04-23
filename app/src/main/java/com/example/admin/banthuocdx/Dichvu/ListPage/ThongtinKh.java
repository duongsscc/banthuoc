package com.example.admin.banthuocdx.Dichvu.ListPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.banthuocdx.Doituong.tkkhachhang;
import com.example.admin.banthuocdx.Ketnoicsdl.KetnoiData;
import com.example.admin.banthuocdx.R;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThongtinKh extends AppCompatActivity {
    EditText hotenkh, sdtkh, diachikh;
    TextView taikhoankh;
    ImageView anhkh;
    String hoten, sdt, diachi, anh;
    int idkh;
    KetnoiData kc = new KetnoiData();
    ArrayList<tkkhachhang> listkh;
    Button chinhsua, huy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtin_khachhang);
        hotenkh = findViewById(R.id.khhoten);
        taikhoankh = findViewById(R.id.taikhoankh);
        sdtkh = findViewById(R.id.khsdt);
        chinhsua = findViewById(R.id.edit);
        diachikh = findViewById(R.id.khdiachi);
        anhkh = findViewById(R.id.anhkh);
        SharedPreferences sharedPreferences = getSharedPreferences("Myuser", MODE_PRIVATE);
        anh = sharedPreferences.getString("Anhkhachhang", "");
        hoten = sharedPreferences.getString("Hoten", "ho ten rong");
        sdt = sharedPreferences.getString("Sodienthoai", "sdt rong");
        diachi = sharedPreferences.getString("Diachi", "anh khach hang rong");
        hotenkh.setText(hoten);
        sdtkh.setText(sdt);
        diachikh.setText(diachi);
        Picasso.with(this)
                .load(anh)
                .into(anhkh);
        taikhoankh.setText(sharedPreferences.getString("Taikhoan", "Khong co"));
        huy = findViewById(R.id.cancel);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongtinKh.this, ListPageActivity.class);
                startActivity(intent);
            }
        });
        chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chinhsua();
            }
        });
    }

    public void chinhsua() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("Myuser", MODE_PRIVATE);
                idkh = sharedPreferences.getInt("IdKhachhang", 1);

                try {
                    Connection con;
                    String sql;
                    sql = "UPDATE tkkhachhang SET Hoten=?,Sodienthoai=?,Diachi=? WHERE IdKhachhang=?";
                    con = kc.ketnoi();
                    PreparedStatement prest = con.prepareStatement(sql);
                    prest.setInt(4, idkh);
                    if (hotenkh.getText() != null && sdtkh.getText() != null && diachikh.getText() != null) {
                        prest.setString(1, hotenkh.getText().toString().trim());
                        prest.setString(2, sdtkh.getText().toString().trim());
                        prest.setString(3, diachikh.getText().toString().trim());

                        prest.executeUpdate();
                        Intent intent = new Intent(ThongtinKh.this, ListPageActivity.class);
                        intent.putExtra("tentk", sharedPreferences.getString("Taikhoan",""));
                        startActivity(intent);
                    } else {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }

                    mIncomingHandler.sendEmptyMessage(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }).start();
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {

            return true;
        }
    });
}

