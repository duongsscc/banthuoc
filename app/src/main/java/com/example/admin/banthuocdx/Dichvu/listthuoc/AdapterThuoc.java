package com.example.admin.banthuocdx.Dichvu.listthuoc;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.Doituong.tkadmin;
import com.example.admin.banthuocdx.Doituong.tkkhachhang;
import com.example.admin.banthuocdx.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterThuoc extends RecyclerView.Adapter<AdapterThuoc.appviewHolder> implements Filterable {
    LayoutInflater layoutInflater;
    ArrayList<thuoc> thuocArrayList,timlist;
    Context context;
    timkiemAdapter timkiemAdapter;
ArrayList<tkadmin>listad;
ArrayList<tkkhachhang>listkh;
    public AdapterThuoc(LayoutInflater Inflater, ArrayList<thuoc> list) {
        this.layoutInflater = Inflater;
        this.thuocArrayList = list;
    }

    public AdapterThuoc(LayoutInflater layoutInflater, ArrayList<thuoc> thuocArrayList, Context context) {
        this.layoutInflater = layoutInflater;
        this.thuocArrayList = thuocArrayList;
        this.timlist=thuocArrayList;
        this.context = context;
    }


    @Override
    public appviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new appviewHolder(layoutInflater.inflate(R.layout.adapterthuoc, parent, false));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(final appviewHolder holder, final int position) {
        final thuoc th = thuocArrayList.get(position);

        holder.textten.setText(th.getTenThuoc());
        holder.textmota.setText(th.getMota());
        holder.textgiatien.setText(String.valueOf(th.getGiatien()));
        holder.textsoluong.setText(String.valueOf(th.getSoluong()));
        Picasso.with(context)
                .load(th.getAnhThuocList())
                .into(holder.imgAnh);
        holder.buttonthemgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"click",Toast.LENGTH_LONG).show();
                ThuocFragment tf= new ThuocFragment();
                float tongggiatien=0;
                int soluongmua=0, idthuoc=0, idadmin=0, idkhachhang = 0;
                tongggiatien=thuocArrayList.get(position).getGiatien();
                soluongmua=1;
                idthuoc=thuocArrayList.get(position).getIdThuoc();
                idadmin=1;//chưa có key set tạm :V
                idkhachhang=1;
                tf.themgiohang(tongggiatien,soluongmua,idthuoc,idadmin,idkhachhang);
            }
        });
        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return thuocArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (timkiemAdapter==null)
        {
            timkiemAdapter=new timkiemAdapter(this, timlist);
        }
        return timkiemAdapter;
    }

    class appviewHolder extends RecyclerView.ViewHolder {
        TextView textten, textmota, textgiatien, textsoluong;
        ImageView imgAnh;
        CardView cardView;
        Button buttonthemgio;
        public appviewHolder(View itemView) {
            super(itemView);
            buttonthemgio=itemView.findViewById(R.id.btnthemgio);
            textten = itemView.findViewById(R.id.textTenthuoc);
            textmota = itemView.findViewById(R.id.textMota);
            textgiatien = itemView.findViewById(R.id.textGiatien);
            textsoluong = itemView.findViewById(R.id.textSoLuong);
            imgAnh = itemView.findViewById(R.id.anhirecyle);
            cardView = itemView.findViewById(R.id.itemX);
        }
    }
}
