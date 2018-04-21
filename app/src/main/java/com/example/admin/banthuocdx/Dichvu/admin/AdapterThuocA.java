package com.example.admin.banthuocdx.Dichvu.admin;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.banthuocdx.Dichvu.listthuoc.timkiemAdapter;
import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.Doituong.tkadmin;
import com.example.admin.banthuocdx.Doituong.tkkhachhang;
import com.example.admin.banthuocdx.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class AdapterThuocA extends RecyclerView.Adapter<AdapterThuocA.appviewHolder> implements Filterable {
    LayoutInflater layoutInflater;
    ArrayList<thuoc> thuocArrayList,timlist;
    Context context;
    timkiemAdapterA timkiemAdapter;
    CapnhatThuoc capnhatThuoc = new CapnhatThuoc();
ArrayList<tkadmin>listad;
ArrayList<tkkhachhang>listkh;
    public AdapterThuocA(LayoutInflater Inflater, ArrayList<thuoc> list) {
        this.layoutInflater = Inflater;
        this.thuocArrayList = list;
    }

    public AdapterThuocA(LayoutInflater layoutInflater, ArrayList<thuoc> thuocArrayList, Context context) {
        this.layoutInflater = layoutInflater;
        this.thuocArrayList = thuocArrayList;
        this.timlist=thuocArrayList;
        this.context = context;
    }


    @Override
    public appviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new appviewHolder(layoutInflater.inflate(R.layout.adapterthuoca, parent, false));
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
holder.buttonxoa.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
int idthuoc=thuocArrayList.get(position).getIdThuoc();
//int idadmin=thuocArrayList.get(position).getIdadmin().getIdAdmin();
//int idtheloaithuoc=thuocArrayList.get(position).getTheLoai().getIdTheLoaiThuoc();
int idadmin=1;int idtheloaithuoc=1;
capnhatThuoc.Xoathuoc(idthuoc,idadmin,idtheloaithuoc);
thuocArrayList.remove(position);
notifyDataSetChanged();
    }
});
holder.buttonsua.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

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
            timkiemAdapter=new timkiemAdapterA(this, timlist);
        }
        return timkiemAdapter;
    }

    class appviewHolder extends RecyclerView.ViewHolder {
        TextView textten, textmota, textgiatien, textsoluong;
        ImageView imgAnh;
        CardView cardView;
        Button buttonxoa,buttonsua;
        public appviewHolder(View itemView) {
            super(itemView);
            buttonxoa=itemView.findViewById(R.id.btnxoagio);
            textten = itemView.findViewById(R.id.textTenthuocA);
            textmota = itemView.findViewById(R.id.textMotaA);
            textgiatien = itemView.findViewById(R.id.textGiatienA);
            textsoluong = itemView.findViewById(R.id.textSoLuongA);
            imgAnh = itemView.findViewById(R.id.anhirecyleA);
            cardView = itemView.findViewById(R.id.itemXA);
            buttonsua =itemView.findViewById(R.id.btnsua);
        }
    }

}
