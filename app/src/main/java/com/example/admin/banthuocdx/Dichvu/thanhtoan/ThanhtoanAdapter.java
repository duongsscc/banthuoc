package com.example.admin.banthuocdx.Dichvu.thanhtoan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.banthuocdx.Doituong.giohang;
import com.example.admin.banthuocdx.Doituong.tkkhachhang;
import com.example.admin.banthuocdx.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThanhtoanAdapter extends BaseAdapter {
    ArrayList<giohang> listData;
    LayoutInflater inflater;
    Context context;
    tkkhachhang kh;

    // Hàm tạo của custom
    public ThanhtoanAdapter(Context context, ArrayList<giohang> listData) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listData = listData;
        this.context = context;
    }

    // Trả về số lượng phần tử được hiển thị trong listview
    @Override
    public int getCount() {
        return listData.size();
    }

    // Trả về đối tượng được lấy theo vị trí
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        giohang gio = listData.get(position);
        TextView txtName, txtGia, txtHang, txtMota, txtSoluong, txtTonggia;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_item_listview, parent, false);
        }
        txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtGia = (TextView) convertView.findViewById(R.id.txtGia);
        txtHang = (TextView) convertView.findViewById(R.id.txtHang);
        txtMota = (TextView) convertView.findViewById(R.id.txtMota);
        txtSoluong = (TextView) convertView.findViewById(R.id.txtSoluong);
        txtTonggia = (TextView) convertView.findViewById(R.id.txtTonggia);
// Set dữ liệu vào item của list view
        if (gio.getIdThuoc() != null) {
            txtName.setText("Thuốc: " + gio.getIdThuoc().getTenThuoc());
            txtGia.setText("Đơn Giá: " + Float.toString(gio.getIdThuoc().getGiatien())+"VND");
            txtHang.setText("Hãng: " + gio.getIdThuoc().getHangSanXuat());
            txtMota.setText("Mô tả: " + gio.getIdThuoc().getMota());
            ImageView imageView = convertView.findViewById(R.id.image);
            Picasso.with(context).load(gio.getIdThuoc().getAnhThuocList()).into(imageView);
            //làm sao để gọi ảnh ra ntn đc,
        }
        txtSoluong.setText("Số lượng: " + Integer.toString(gio.getSoLuongMua()));
        txtTonggia.setText("Tổng tiền: " + Float.toString(gio.getTongGiaTien())+"VND");

        return convertView;

    }


}
