package com.example.admin.banthuocdx.Dichvu.listgiohang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.banthuocdx.Dichvu.thanhtoan.ThanhtoanActivity;
import com.example.admin.banthuocdx.Doituong.getCartChangeEvent;
import com.example.admin.banthuocdx.Doituong.giohang;
import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.Ketnoicsdl.KetnoiData;
import com.example.admin.banthuocdx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class GiohangFragment extends android.support.v4.app.Fragment {
    private RecyclerView recyclerView;
    private CardView cardView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<thuoc> arrayListThuoc;
    ArrayList<giohang> listgh;
    TextView textTongTien;
    SharedPreferences sharedPreferences;
    Button btnXacNhanDonHang;
    String taiKhoan, hoTen, soDienThoai, anhKhachHang;
    int idKhachhang, idAdmin;
    float tong = 0;
    int c = 0;
    thuoc th;
    int idthuoc = 0;
    int soluongton = 0;

    public GiohangFragment() {

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

        adapter = new AdapterGioHang(getActivity().getApplicationContext(), arrayListThuoc);

        recyclerView.setAdapter(adapter);

        textTongTien = view.findViewById(R.id.textTongTien);
        sharedPreferences = getContext().getSharedPreferences("Myuser", Context.MODE_PRIVATE);
        idKhachhang = sharedPreferences.getInt("IdKhachhang", 0);
        taiKhoan = sharedPreferences.getString("Taikhoan", "tai khoan rong");
        hoTen = sharedPreferences.getString("Hoten", "ho ten rong");
        soDienThoai = sharedPreferences.getString("Sodienthoai", "sdt rong");
        idAdmin = sharedPreferences.getInt("IdAdmin", 0);
        btnXacNhanDonHang = view.findViewById(R.id.btnXacNhanDonHang);

    btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Connection con;
                    arrayListThuoc = new ArrayList<>();
                    KetnoiData kc = new KetnoiData();
                    con = kc.ketnoi();
                    String sql = "Insert into giohang (`Tonggiatien`, `Soluongmua`, `Thuoc_IdThuoc`, `Thuoc_Admin_IdAdmin`, `Khachhang_IdKhachhang`) values (?,?,?,?,?)";
                    try {
                        PreparedStatement stm = con.prepareStatement(sql);
                        listgh = new ArrayList<>();
                        stm.setFloat(1, tong);
                        stm.setInt(2, c);
                        stm.setInt(3, idthuoc);
                        stm.setInt(4, idAdmin);
                        stm.setInt(5, idKhachhang);
                        stm.executeUpdate();
                        mIncomingHandler.sendEmptyMessage(0);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            if (tong>0) {
            Intent intent = new Intent(getActivity().getApplicationContext(), ThanhtoanActivity.class);
            intent.putExtra("gioHang", arrayListThuoc);
            startActivity(intent);
        }
else
        {
            Toast.makeText(getActivity(),"KHông có mặt hàng",Toast.LENGTH_SHORT).show();
        }


    }
    });
      return view;
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {

            return true;
        }
    });

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
        if (isExistInList(arrayListThuoc, th) >= 1) {
            int c = isExistInList(arrayListThuoc, th);
            //t = new thuoc(th.getTenThuoc(),th.getGiatien(),5, c+=1,th.getMota(),th.getAnhThuocList());

            for (int i = 0; i < arrayListThuoc.size(); i++) {
                if (arrayListThuoc.get(i).getTenThuoc().equals(th.getTenThuoc())) {
                    arrayListThuoc.get(i).setSlGioHang(c += 1);
                    arrayListThuoc.get(i).setIdThuoc(c);
                }
            }
            CauHinhList(arrayListThuoc);
        } else {
            t = new thuoc(th.getTenThuoc(), th.getGiatien(), th.getSoluong(), 1, th.getMota(), th.getAnhThuocList());
            t.setIdThuoc(th.getIdThuoc());
            if (th.getSlGioHang()<=th.getSlGioHang()) {
                arrayListThuoc.add(t);
            }
            else
            {
                Toast.makeText(getActivity(),"vuot qua so luong",Toast.LENGTH_SHORT).show();
            }
            CauHinhList(arrayListThuoc);
        }
    }

    // UI updates must run on MainThread
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(getCartChangeEvent event) {
        th = event.getThuoc();
        c = 0;
        for (int i = 0; i < arrayListThuoc.size(); i++) {
            if (arrayListThuoc.get(i).getTenThuoc().equals(th.getTenThuoc())) {
                arrayListThuoc.get(i).setSlGioHang(th.getSlGioHang());
                c = th.getSlGioHang();
            }
        }
        CauHinhList(arrayListThuoc);
    }


    public void CauHinhList(ArrayList<thuoc> arrThuoc) {
        adapter = new AdapterGioHang(getActivity().getApplicationContext(), arrThuoc);
        recyclerView.setAdapter(adapter);
        textTongTien.setText(getTongTien(arrThuoc) + " VND");
        getIdthuoc(arrThuoc);
        getSoluongton(arrThuoc);
    }

    public int isExistInList(ArrayList<thuoc> arrayList, thuoc th) {
        c = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getTenThuoc().equals(th.getTenThuoc())) {
                c = arrayList.get(i).getSlGioHang();
                c = arrayList.get(i).getIdThuoc();
            }
        }

        return c;
    }

    public float getTongTien(ArrayList<thuoc> arrayThuoc) {
        tong = 0;
        c = 0;
        if (arrayThuoc != null) {
            for (int i = 0; i < arrayThuoc.size(); i++) {
                tong += arrayThuoc.get(i).getGiatien() * arrayThuoc.get(i).getSlGioHang();
                c += arrayThuoc.get(i).getSlGioHang();


            }
        }
        return tong;
    }

    public int getIdthuoc(ArrayList<thuoc> arrayListThuoc) {
        idthuoc = 0;

        th = new thuoc();
        for (int i = 0; i < arrayListThuoc.size(); i++) {
            idthuoc = arrayListThuoc.get(i).getIdThuoc();

        }
        return idthuoc;
    }

    public int getSoluongton(ArrayList<thuoc> arrayListThuoc) {
        soluongton = 0;
        th = new thuoc();
        for (int i = 0; i < arrayListThuoc.size(); i++) {
            soluongton = arrayListThuoc.get(i).getSoluong() - arrayListThuoc.get(i).getSlGioHang();

        }
new Thread(new Runnable() {
    @Override
    public void run() {
        Connection con;
       // arrayListThuoc = new ArrayList<>();
        idAdmin = sharedPreferences.getInt("IdAdmin", 0);
       int idtheloai = sharedPreferences.getInt("IdTheloaithuoc", 0);
        KetnoiData kc = new KetnoiData();
        con = kc.ketnoi();
        String sql; //
        sql = "UPDATE thuoc SET Soluong=?,Theloaithuoc_IdTheloaithuoc=?,Admin_IdAdmin=? WHERE IdThuoc=?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(4,idthuoc);
            stm.setFloat(1, soluongton);
            stm.setInt(2,idtheloai);
            stm.setInt(3,idAdmin);
            stm.executeUpdate();


            mIncomingHandler.sendEmptyMessage(0);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}).start();
        return soluongton;
    }

}
