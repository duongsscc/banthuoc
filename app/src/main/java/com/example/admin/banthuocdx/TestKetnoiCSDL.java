package com.example.admin.banthuocdx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.Ketnoicsdl.KetnoiData;

public class TestKetnoiCSDL extends AppCompatActivity implements Runnable{
    ArrayList<thuoc>list;
  String tenthuoc;
  Float giatien;
  String anhthuoc;
  String mota;
  int soluong;
    int count = 0;
    Connection con ;
    KetnoiData kc=new KetnoiData();
    private String errmsg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Thread thread = new Thread(this);
        thread.start(); //khi chạy thành công trên màn hình giả lập sẻ hiện thị kết nối thành công vs database
    }

    public void run() {

        con=kc.ketnoi();
        list=new ArrayList<>();
try {
    String sql;
    sql = "SELECT * FROM thuoc";
    PreparedStatement prest = con.prepareStatement(sql);
    ResultSet rs = prest.executeQuery();
    while (rs.next()) {
        thuoc th= new thuoc();
       tenthuoc=rs.getString("Tenthuoc");
       giatien=rs.getFloat("Giatien");
       mota=rs.getString("Mota");
       soluong=rs.getInt("Soluong");
       anhthuoc=rs.getString("Anhthuoclist");
      //  thuoc td = new thuoc(rs.getString("Tenthuoc"), rs.getFloat("Giatien"), rs.getInt("Soluong"), rs.getString("Mota"), rs.getString("Anhthuoclist"));

        list.add(th);
    }
    prest.close();
    con.close();
}
catch (Exception e)
{
    e.printStackTrace();
}
        mIncomingHandler.sendEmptyMessage(0);
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback()
    {
        public boolean handleMessage(Message msg) {
            TextView textView = findViewById(R.id.textView0);

            if (list!=null)
            {

                textView.setText("ket noi thanh cong");
            }
            else
            {
               textView.setText("ket noi that cmn bai");
            }
             // textView.setText(Idthuoc);
            return true;
        }
    });
}


//   Câu lệnh Cơ bản:
//            String sql;
//            sql = "SELECT * FROM thuoc";
//            PreparedStatement prest = con.prepareStatement(sql);
//            ResultSet rs = prest.executeQuery();
//            while (rs.next()) {
//                Idthuoc = rs.getString(1);
//                Giatien = rs.getInt(4);
//                count++;
//            }
//            prest.close();
//            con.close();ose();