package com.example.admin.banthuocdx.Dichvu.listthuoc;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.Ketnoicsdl.KetnoiData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LayListthuoc extends AsyncTask<Object, Void, ArrayList<thuoc>> {
    private RecyclerView recyclerView;
    private AdapterThuoc adapterThuoc;
    private CardView cardView;
    public ArrayList<thuoc> list;
    Connection con;
    KetnoiData kc = new KetnoiData();
    TextView textView;

    @Override
    protected ArrayList<thuoc> doInBackground(Object...objects) {
        con = kc.ketnoi();
                list = new ArrayList<>();
                try {
                    String sql;
                    sql = "SELECT * FROM thuoc";
                    PreparedStatement prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    while (rs.next()) {
                        thuoc th = new thuoc(rs.getString("Tenthuoc"), rs.getFloat("Giatien"), rs.getInt("Soluong"), rs.getString("Mota"));
                        list.add(th);
                    }
                    prest.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        return null;
            }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<thuoc> thuocs) {
        thuocs=list;
        super.onPostExecute(thuocs);
    }

    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }
}
