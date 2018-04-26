package com.example.admin.banthuocdx.Dichvu.thanhtoan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.banthuocdx.Dichvu.ListPage.ListPageActivity;
import com.example.admin.banthuocdx.Doituong.giohang;
import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.Doituong.tkkhachhang;
import com.example.admin.banthuocdx.Ketnoicsdl.KetnoiData;
import com.example.admin.banthuocdx.R;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThanhtoanActivity extends AppCompatActivity {


    private int idKhach = 0;
    private ArrayList<giohang> listGio = new ArrayList<>();
    private ArrayList<tkkhachhang> listKhach = new ArrayList<>();
    private ArrayList<Float> listTong = new ArrayList<>();
    private tkkhachhang kh = new tkkhachhang();
    Button Thanhtoan, trolai;
    private float tong = 0;
    ImageView imageView;
    KetnoiData kc = new KetnoiData();
    TextView txtThongTinKhachHang, txtThongTinGioHang;
    ListView lv;
    TextView txtName, txtDiachi, txtSdt, txtTongGia;
    ThanhtoanAdapter adapter;
    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan2);
        lv = (ListView) findViewById(R.id.listview);
        final SharedPreferences sharedPreferences = getSharedPreferences("Myuser", Context.MODE_PRIVATE);
        idKhach = sharedPreferences.getInt("IdKhachhang", 0);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        ShowCart();
        sumCart(listGio);
        ShowCustomer();
        txtName = (TextView) findViewById(R.id.txtName);
        txtTongGia = (TextView) findViewById(R.id.txtPrice);
        txtDiachi = (TextView) findViewById(R.id.txtDiachi);
        txtSdt = (TextView) findViewById(R.id.txtSdt);
        imageView = findViewById(R.id.imageKhach);
        Thanhtoan = findViewById(R.id.btThanhtoan);
        Thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showdiaglog();
            }
        });
        trolai = findViewById(R.id.btBack);
        trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhtoanActivity.this, ListPageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Showdiaglog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ThanhtoanActivity.this);
        builder.setTitle("Đơn Hàng");
        builder.setMessage(" Ho ten:" + kh.getHoten() + "\n So dien thoai:" + kh.getSodienthoai() + "\n Diachi :" + kh.getDiaChi() + "\n Tổng giá tiền :" + tong+"VND"+"\n\n\n Thanh Toán khi nhận hàng");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void Cauhinhlist() {

        Picasso.with(this).load(kh.getAnhKhachhang()).into(imageView);
        adapter = new ThanhtoanAdapter(this, listGio);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void ShowCustomer() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        con = kc.ketnoi();
                        kh = new tkkhachhang();
                        try {
                            String sql = "SELECT * FROM tkkhachhang WHERE IdKhachhang='" + idKhach + "'";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                kh.setHoten(rs.getString("Hoten"));
                                kh.setDiaChi(rs.getString("Diachi"));
                                kh.setSodienthoai(rs.getString("Sodienthoai"));
                                kh.setAnhKhachhang(rs.getString("Anhkhachhang"));
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        txtTongGia.setText(sumCart(listGio) + "");
                                        txtName.setText("Họ tên:  " + kh.getHoten());
                                        txtSdt.setText("Số điện thoại:  " + kh.getSodienthoai());
                                        txtDiachi.setText("Địa chi:  " + kh.getDiaChi());

                                    }
                                });
                                mIncomingHandler.sendEmptyMessage(0);
                            }
                            con.close();
                            ps.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    public void ShowCart() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        con = kc.ketnoi();
                        listGio = new ArrayList();
                        try {
                            String sql = "SELECT * " +
                                    "FROM giohang INNER JOIN  thuoc" +
                                    " ON Thuoc_IdThuoc = IdThuoc " +
                                    "WHERE Khachhang_IdKhachhang ='" + idKhach + "'";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                giohang gio = new giohang();
                                gio.setIdGioHang(rs.getInt("IdGiohang"));
                                thuoc t = new thuoc();
                                t.setIdThuoc(rs.getInt("Thuoc_IdThuoc"));
                                t.setTenThuoc(rs.getString("Tenthuoc"));
                                t.setGiatien(rs.getFloat("Giatien"));
                                t.setHangSanXuat(rs.getString("Hangsanxuat"));
                                t.setAnhThuocList(rs.getString("Anhthuoclist"));
                                t.setMota(rs.getString("Mota"));
                                gio.setIdThuoc(t);
                                gio.setTongGiaTien(rs.getFloat("Tonggiatien"));
                                gio.setSoLuongMua(rs.getInt("Soluongmua"));
                                gio.setIdThuoc(t);
                                listGio.add(gio);

                                mIncomingHandler.sendEmptyMessage(0);
                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }


        ).start();
    }

    public float sumCart(ArrayList<giohang> list) {
        tong = 0;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                tong += list.get(i).getTongGiaTien();
            }
        }
        return tong;
    }


    Handler mIncomingHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Cauhinhlist();
            return true;
        }
    });

}
