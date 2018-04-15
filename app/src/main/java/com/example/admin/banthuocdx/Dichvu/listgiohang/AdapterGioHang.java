package com.example.admin.banthuocdx.Dichvu.listgiohang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.admin.banthuocdx.Doituong.getCartChangeEvent;
import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolder> {
    Context context;
    ArrayList<thuoc> arrayListThuoc;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapterthuocgiohang,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public AdapterGioHang(Context context, ArrayList<thuoc> arrThuoc){
        arrayListThuoc = new ArrayList<>();
        this.context = context;
        this.arrayListThuoc = arrThuoc;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textTenthuocGioHang.setText(arrayListThuoc.get(position).getTenThuoc());
        holder.textMotaThuocGioHang.setText(arrayListThuoc.get(position).getMota());
        holder.textGiatienThuocGioHang.setText(arrayListThuoc.get(position).getGiatien().toString());

        holder.textXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuoc t = arrayListThuoc.get(position);
                changeCartItem(new thuoc(t.getTenThuoc(),t.getGiatien(),0,0,t.getMota(),t.getAnhThuocList()),69);
                arrayListThuoc.remove(position);
                notifyItemRemoved(position);
            }
        });

        holder.btnSoLuongSP.setNumber(String.valueOf(arrayListThuoc.get(position).getSlGioHang()));
        holder.btnSoLuongSP.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                thuoc t = arrayListThuoc.get(position);
                changeCartItem(new thuoc(t.getTenThuoc(),t.getGiatien(),0,newValue,t.getMota(),t.getAnhThuocList()),69);
            }
        });

        Picasso.with(context).load(arrayListThuoc.get(position).getAnhThuocList()).into(holder.anhThuocGioHang);
        //Toast.makeText(context, arrayListThuoc.get(position).getAnhThuocChiTiet(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return arrayListThuoc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textTenthuocGioHang,textMotaThuocGioHang,textGiatienThuocGioHang,textXoa,textTongTien;
        public ImageView anhThuocGioHang;
        public ElegantNumberButton btnSoLuongSP;

        public ViewHolder(View itemView) {
            super(itemView);
            textTenthuocGioHang = itemView.findViewById(R.id.textTenthuocGioHang);
            textGiatienThuocGioHang = itemView.findViewById(R.id.textGiatienThuocGioHang);
            textMotaThuocGioHang = itemView.findViewById(R.id.textMotaThuocGioHang);
            textXoa = itemView.findViewById(R.id.textXoa);
            anhThuocGioHang = itemView.findViewById(R.id.anhThuocGioHang);
            btnSoLuongSP = itemView.findViewById(R.id.btnSoLuongThuoc);

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void sendThuocToFragmentGioHang(thuoc th){
        EventBus.getDefault().postSticky(th);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void changeCartItem(thuoc th,int i){
        EventBus.getDefault().postSticky(new getCartChangeEvent(th,i));
    }

}
