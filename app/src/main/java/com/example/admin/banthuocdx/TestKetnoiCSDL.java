package com.example.admin.banthuocdx;
import java.sql.Connection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.banthuocdx.ketnoicsdl.KetnoiData;

public class TestKetnoiCSDL extends AppCompatActivity implements Runnable{

//    private String Idthuoc="";
//    private int Giatien=0;
//    int count = 0;
    Connection con ;
    KetnoiData kc=new KetnoiData();
    private String errmsg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(this);
        thread.start(); //khi chạy thành công trên màn hình giả lập sẻ hiện thị kết nối thành công vs database
    }

    public void run() {
        con=kc.ketnoi();
        mIncomingHandler.sendEmptyMessage(0);
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback()
    {
        public boolean handleMessage(Message msg) {
            TextView textView = findViewById(R.id.textView0);

            if (con!=null)
            {

                textView.setText("ket noi thanh cong");
            }
            else
            {
               textView.setText("ket noi that cmn bai");
            }
            //  textView.setText("Idthuoc="+Idthuoc+" Giatien="+Giatien+" "+errmsg);
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