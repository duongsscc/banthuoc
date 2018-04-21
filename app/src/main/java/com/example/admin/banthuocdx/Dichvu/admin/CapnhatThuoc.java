package com.example.admin.banthuocdx.Dichvu.admin;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.banthuocdx.Dichvu.listthuoc.AdapterThuoc;
import com.example.admin.banthuocdx.Doituong.AdminTheloaiPK;
import com.example.admin.banthuocdx.Doituong.giohang;
import com.example.admin.banthuocdx.Doituong.theloaithuoc;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CapnhatThuoc extends Fragment {

    private RecyclerView recyclerView;
    private AdapterThuocA adapterThuoc;
    public ArrayList<thuoc> list;
    public ArrayList<tkadmin> listAd;
    public ArrayList<theloaithuoc> listth;
    Connection con;
    KetnoiData kc = new KetnoiData();
    thuoc th = null;
    tkadmin tk = null;
    tkkhachhang tkkhachhang = null;
    SearchView searchView;
    Button themthuoc;
    theloaithuoc theloaithuoc;

    public CapnhatThuoc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_capnhat_thuoc, container, false);
        recyclerView = view.findViewById(R.id.listRethuocA);
        searchView = view.findViewById(R.id.searchviewsA);
        Hienthilistthuoc();
        return view;


    }

    private void Cauhinhlist() {

        adapterThuoc = new AdapterThuocA(getLayoutInflater(), list, getContext());
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

    public void Hienthilistthuoc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();

                list = new ArrayList<>();
                listAd = new ArrayList<>();
                listth = new ArrayList<>();
                try {
                    String sql;
                    sql = "SELECT * FROM thuoc";
                    PreparedStatement prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        th = new thuoc(rs.getString("Tenthuoc"), rs.getFloat("Giatien"), rs.getInt("Soluong"), rs.getString("Mota"), rs.getString("Anhthuoclist"));
                        th.setIdThuoc(rs.getInt("IdThuoc"));
                     //   AdminTheloaiPK pk = null;
                    //    th.setIdadmin(pk.getIdAdmin());
                        //th.setIdadmin(th.getIdadmin().getIdAdmin());
                   //     tk.setIdAdmin(rs.getInt("Admin_IdAdmin"));
                   //    theloaithuoc.setIdTheLoaiThuoc(rs.getInt("Theloaithuoc_IdTheloaithuoc"));
                       list.add(th);
                    //   listAd.add(tk);
                    //    listth.add(theloaithuoc);
                        mIncomingHandler.sendEmptyMessage(0);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void Xoathuoc(final int idThuoc, final int idAdmin, final int idTheloaithuoc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isAdded()) {
                con = kc.ketnoi();
                    list = new ArrayList<>();
                    String sql = "DELETE FROM thuoc WHERE IdThuoc=? and Admin_IdAdmin=? and Theloaithuoc_IdTheloaithuoc=? ";
                    PreparedStatement prest = null;
                    try {

                        prest = con.prepareStatement(sql);
                        prest.setInt(1, idThuoc);
                        prest.setInt(2, idAdmin);
                        prest.setInt(3, idTheloaithuoc);
                            try {
                                wait(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        prest.executeUpdate();

                        mIncomingHandler.sendEmptyMessage(0);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Cauhinhlist();
            timkiem();
            return true;
        }
    });
}
