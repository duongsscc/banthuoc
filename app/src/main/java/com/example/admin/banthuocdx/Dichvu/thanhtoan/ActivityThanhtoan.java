package com.example.admin.banthuocdx.Dichvu.thanhtoan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.R;

import java.util.ArrayList;

public class ActivityThanhtoan extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String taiKhoan,hoTen,soDienThoai,anhKhachHang;
    TextView txtThongTinKhachHang,txtThongTinGioHang;

    int tongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);

        txtThongTinGioHang = findViewById(R.id.txtThongTinGioHang);
        txtThongTinKhachHang = findViewById(R.id.txtThongTinKhachHang);

        sharedPreferences = getSharedPreferences("Myuser",MODE_PRIVATE);

        taiKhoan = sharedPreferences.getString("Taikhoan","tai khoan rong");
        hoTen = sharedPreferences.getString("Hoten","ho ten rong");
        soDienThoai = sharedPreferences.getString("Sodienthoai","sdt rong");
        anhKhachHang = sharedPreferences.getString("Anhkhachhang","anh khach hang rong");

        ArrayList<thuoc> arrListThuoc = (ArrayList<thuoc>) getIntent().getSerializableExtra("gioHang");

        if(arrListThuoc != null){
            for(int i=0;i<arrListThuoc.size();i++){
                tongTien += arrListThuoc.get(i).getGiatien() * arrListThuoc.get(i).getSlGioHang();
            }
        }
        else{
            Toast.makeText(this, "ROng", Toast.LENGTH_SHORT).show();
        }

        txtThongTinKhachHang.setText("Tai khoan:"+taiKhoan+"\n Ho ten:"+hoTen+"\n So dien thoai:"+ soDienThoai+"\n Anh Khach Hang"+ anhKhachHang);
        txtThongTinGioHang.setText("Tổng số tiền cần thanh toán: "+tongTien+" VND");

    }
}
