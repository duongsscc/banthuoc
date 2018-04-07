package com.example.admin.banthuocdx.Dichvu.listthuoc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.SearchView;


import com.example.admin.banthuocdx.Doituong.giohang;
import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.Doituong.tkadmin;
import com.example.admin.banthuocdx.Doituong.tkkhachhang;
import com.example.admin.banthuocdx.Ketnoicsdl.KetnoiData;
import com.example.admin.banthuocdx.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ThuocFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterThuoc adapterThuoc;
    private CardView cardView;
    public ArrayList<thuoc> list;
    public ArrayList<giohang> listgh;
    public ArrayList<tkkhachhang> listkh;
    public ArrayList<tkadmin> listadmin;

    Connection con;
    KetnoiData kc = new KetnoiData();
    thuoc th = null;
    tkadmin tk=null;
    tkkhachhang tkkhachhang=null;
    SearchView searchView;
    Button themgio;


    public ThuocFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuoc, container, false);
        recyclerView = view.findViewById(R.id.listRethuoc);
        cardView = view.findViewById(R.id.itemX);
        searchView = view.findViewById(R.id.searchviews);
        Hienthilistthuoc();
        Hienthilistadmin();
        Hienthilistkhachang();
        return view;

    }

    private void Cauhinhlist() {

        adapterThuoc = new AdapterThuoc(getLayoutInflater(), list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterThuoc);
    }

    private void timkiem() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterThuoc.getFilter().filter(newText);
                return false;
            }
        });
    }


    public void themgiohang(final float tongggiatien, final int soluongmua, final int idthuoc, final int idadmin, final int idkhachhang) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();

                String sql = "INSERT INTO giohang (Tonggiatien, Soluongmua, Thuoc_IdThuoc, Thuoc_Admin_IdAdmin, Khachhang_IdKhachhang) values(?,?,?,?,?);";
                try {
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setFloat(1,tongggiatien);
                    statement.setInt(2,soluongmua);
                    statement.setInt(3,idthuoc);
                    statement.setInt(4,idadmin);
                    statement.setInt(5,idkhachhang);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (!isAdded()) return;
                mIncomingHandler.sendEmptyMessage(0);

            }
        }).start();
    }

    public void Hienthilistthuoc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();

                list = new ArrayList<>();
                listadmin = new ArrayList<>();
                listkh = new ArrayList<>();
                try {
                    String sql;
                    sql = "SELECT * FROM thuoc";
                    PreparedStatement prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        th = new thuoc(rs.getString("Tenthuoc"), rs.getFloat("Giatien"), rs.getInt("Soluong"), rs.getString("Mota"), rs.getString("Anhthuoclist"));
                       th.setIdThuoc(rs.getInt("IdThuoc"));
                        list.add(th);
                        mIncomingHandler.sendEmptyMessage(0);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void Hienthilistkhachang() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                listkh = new ArrayList<>();
                try {
                    String sql;
                    sql = "SELECT * FROM tkkhachhang";
                    PreparedStatement prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        tkkhachhang = new tkkhachhang();
                        tkkhachhang.setIdKhachhang(rs.getInt("IdKhachhang"));
                        tkkhachhang.setHoten(rs.getString("Hoten"));
                        tkkhachhang.setSodienthoai(rs.getString("Sodienthoai"));
                        tkkhachhang.setAnhKhachhang(rs.getString("Anhkhachhang"));
                        tkkhachhang.setAnhKhachhangchitiet(rs.getString("Anhkhachhangchitiet"));
                        tkkhachhang.setTaikhoan(rs.getString("taikhoan"));
                        tkkhachhang.setMatkhau(rs.getString("matkhau"));
                        listkh.add(tkkhachhang);
                        mIncomingHandler.sendEmptyMessage(0);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void Hienthilistadmin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                listadmin = new ArrayList<>();
                try {
                    String sql;
                    sql = "SELECT * FROM tkadmin";
                    PreparedStatement prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        tk = new tkadmin();
                        tk.setIdAdmin(rs.getInt("IdAdmin"));
                        tk.setHoten(rs.getString("Hoten"));
                        tk.setSodienthoai(rs.getString("Sodienthoai"));
                        tk.setChucvu(rs.getString("Chucvu"));
                        tk.setAnhAdmin(rs.getString("Anhadmin"));
                        tk.setAndAdminChitiet(rs.getString("Anhadminchitiet"));
                        tk.setTaikhoan(rs.getString("taikhoan"));
                        tk.setMatkhau(rs.getString("matkhau"));
                       listadmin.add(tk);
                        mIncomingHandler.sendEmptyMessage(0);
                    }

                    prest.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Cauhinhlist();
            timkiem();
            return true;
        }
    });
}
