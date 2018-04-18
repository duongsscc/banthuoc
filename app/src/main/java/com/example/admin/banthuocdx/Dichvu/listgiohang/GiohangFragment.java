package com.example.admin.banthuocdx.Dichvu.listgiohang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.banthuocdx.Doituong.getCartChangeEvent;
import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class GiohangFragment extends android.support.v4.app.Fragment {
    private RecyclerView recyclerView;
    private CardView cardView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<thuoc> arrayListThuoc;
    TextView textTongTien;
    SharedPreferences sharedPreferences;
    public GiohangFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giohang, container, false);
        recyclerView = view.findViewById(R.id.listRethuocGioHang);
        //cardView = view.findViewById(R.id.itemX);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        arrayListThuoc = new ArrayList<>();

        adapter = new AdapterGioHang(getActivity().getApplicationContext(),arrayListThuoc);

        recyclerView.setAdapter(adapter);

        textTongTien = view.findViewById(R.id.textTongTien);
        sharedPreferences = getContext().getSharedPreferences("Myuser",Context.MODE_PRIVATE);
        String hoten = sharedPreferences.getString("Hoten","KHONG CO");
  //      Toast.makeText(getContext(),hoten,)

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    /*    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }*/

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(thuoc th) {
        thuoc t;
        if(isExistInList(arrayListThuoc,th) >= 1){
            int c = isExistInList(arrayListThuoc,th);
             //t = new thuoc(th.getTenThuoc(),th.getGiatien(),5, c+=1,th.getMota(),th.getAnhThuocList());

            for (int i=0;i<arrayListThuoc.size();i++){
                if(arrayListThuoc.get(i).getTenThuoc().equals(th.getTenThuoc())){
                    arrayListThuoc.get(i).setSlGioHang(c+=1);
                }
            }
            CauHinhList(arrayListThuoc);
        }
        else{
            t = new thuoc(th.getTenThuoc(),th.getGiatien(), 6,1,th.getMota(),th.getAnhThuocList());
            //Toast.makeText(getActivity().getApplicationContext(), "url"+t.getAnhThuocList(), Toast.LENGTH_SHORT).show();
            arrayListThuoc.add(t);
            CauHinhList(arrayListThuoc);
        }
    }

    // UI updates must run on MainThread
    @ Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(getCartChangeEvent event) {
        thuoc th = event.getThuoc();

        for (int i=0;i<arrayListThuoc.size();i++){
            if(arrayListThuoc.get(i).getTenThuoc().equals(th.getTenThuoc())){
                arrayListThuoc.get(i).setSlGioHang(th.getSlGioHang());
            }
        }
        CauHinhList(arrayListThuoc);
    }


    public void CauHinhList(ArrayList<thuoc> arrThuoc){
        adapter = new AdapterGioHang(getActivity().getApplicationContext(),arrThuoc);
        recyclerView.setAdapter(adapter);
        textTongTien.setText(getTongTien(arrThuoc) +" VND");
    }

    public int isExistInList(ArrayList<thuoc> arrayList,thuoc th){
        int c = 0;
        for (int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).getTenThuoc().equals(th.getTenThuoc())){
                c = arrayList.get(i).getSlGioHang();
            }
        }
        return c;
    }

    public float getTongTien(ArrayList<thuoc> arrayThuoc){
        float tong = 0f;
        if(arrayThuoc != null){
            for(int i=0;i< arrayThuoc.size();i++){
                tong += arrayThuoc.get(i).getGiatien() * arrayThuoc.get(i).getSlGioHang();
            }
        }
        return tong;
    }

}
