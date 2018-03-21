package com.example.admin.banthuocdx;
import java.sql.Connection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.banthuocdx.KetnoiCsdl.KetnoiData;

public class MainActivity extends AppCompatActivity implements Runnable{

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
        thread.start();
    }

    public void run() {

        try {
            con=kc.ketnoi();

        }
        catch (Exception e){
            e.printStackTrace();
            errmsg=errmsg+e.getMessage();
        }

         mIncomingHandler.sendEmptyMessage(0);
    }

    Handler mIncomingHandler = new Handler(new Handler.Callback()
    {
        public boolean handleMessage(Message msg) {
            TextView textView = findViewById(R.id.textView0);

            if (con!=null)
            {
                textView.setText("kết nối thành công");
            }
            else
            {
                textView.setText("kết nối không thành công");
            }
          //  textView.setText("Idthuoc="+Idthuoc+" Giatien="+Giatien+" "+errmsg);
            return true;
        }
    });
}



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
//            con.close();