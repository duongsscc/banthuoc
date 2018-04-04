package com.example.admin.banthuocdx.Dichvu.listthuoc;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterThuoc extends RecyclerView.Adapter<AdapterThuoc.appviewHolder> {
    LayoutInflater layoutInflater;
    ArrayList<thuoc> thuocArrayList;
    Context context;

    public AdapterThuoc(LayoutInflater Inflater, ArrayList<thuoc> list) {
        this.layoutInflater = Inflater;
        this.thuocArrayList = list;
    }

    public AdapterThuoc(LayoutInflater layoutInflater, ArrayList<thuoc> thuocArrayList, Context context) {
        this.layoutInflater = layoutInflater;
        this.thuocArrayList = thuocArrayList;
        this.context = context;
    }

    @Override
    public appviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new appviewHolder(layoutInflater.inflate(R.layout.adapterthuoc, parent, false));
    }

    @Override
    public void onBindViewHolder(appviewHolder holder, int position) {
        thuoc th = thuocArrayList.get(position);
        holder.textten.setText(th.getTenThuoc());
        holder.textmota.setText(th.getMota());
        holder.textgiatien.setText(String.valueOf(th.getGiatien()));
        holder.textsoluong.setText(String.valueOf(th.getSoluong()));
        Picasso.with(context)
                .load(th.getAnhThuocList())
                .into(holder.imgAnh);
        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return thuocArrayList.size();
    }

    class appviewHolder extends RecyclerView.ViewHolder {
        TextView textten, textmota, textgiatien, textsoluong;
        ImageView imgAnh;
        CardView cardView;

        public appviewHolder(View itemView) {
            super(itemView);
            textten = itemView.findViewById(R.id.textTenthuoc);
            textmota = itemView.findViewById(R.id.textMota);
            textgiatien = itemView.findViewById(R.id.textGiatien);
            textsoluong = itemView.findViewById(R.id.textSoLuong);
            imgAnh = itemView.findViewById(R.id.anhirecyle);
            cardView = itemView.findViewById(R.id.itemX);
        }
    }
}
