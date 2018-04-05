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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.Ketnoicsdl.KetnoiData;
import com.example.admin.banthuocdx.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ThuocFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterThuoc adapterThuoc;
    private CardView cardView;
    public ArrayList<thuoc> list;
    public ArrayList<thuoc> list1;
    AutoCompleteTextView autocom;
    Connection con;
    KetnoiData kc = new KetnoiData();
    TextView textView;
    Button button;
    thuoc th = null;
    List listtk;

    public ThuocFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuoc, container, false);
        recyclerView = view.findViewById(R.id.listRethuoc);
        cardView = view.findViewById(R.id.itemX);
        autocom = view.findViewById(R.id.autoComple);

        Hienthilistthuoc();

        return view;

    }

    private void Cauhinhlist() {
        adapterThuoc = new AdapterThuoc(getLayoutInflater(), list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterThuoc);
    }

    private void AutoCom() {
        ArrayList listAuto = new ArrayList();
        for (thuoc c : list) {
            listAuto.add(c.getTenThuoc());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.select_dialog_item, listAuto);
        autocom.setThreshold(1);
        autocom.setAdapter(arrayAdapter);
    }

    public void Hienthilistthuoc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();

                list = new ArrayList<>();
                try {
                    String sql;
                    sql = "SELECT * FROM thuoc";
                    PreparedStatement prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        th = new thuoc(rs.getString("Tenthuoc"), rs.getFloat("Giatien"), rs.getInt("Soluong"), rs.getString("Mota"), rs.getString("Anhthuoclist"));
                        list.add(th);
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
            AutoCom();
            return true;
        }
    });
}
