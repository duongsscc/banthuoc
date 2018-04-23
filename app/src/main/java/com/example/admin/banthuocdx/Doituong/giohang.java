package com.example.admin.banthuocdx.Doituong;

/**
 * Created by Admin on 3/21/2018.
 */

public class giohang {
    int IdGioHang;
    float TongGiaTien;
    int SoLuongMua;
    thuoc IdThuoc;
    tkkhachhang IdKhachhang;

    public com.example.admin.banthuocdx.Doituong.tkadmin getTkadmin() {
        return tkadmin;
    }

    public void setTkadmin(com.example.admin.banthuocdx.Doituong.tkadmin tkadmin) {
        this.tkadmin = tkadmin;
    }

    tkadmin tkadmin;
    public giohang()
    {}

    public int getIdGioHang() {
        return IdGioHang;
    }

    public void setIdGioHang(int idGioHang) {
        IdGioHang = idGioHang;
    }

    public float getTongGiaTien() {
        return TongGiaTien;
    }

    public void setTongGiaTien(float tongGiaTien) {
        TongGiaTien = tongGiaTien;
    }

    public int getSoLuongMua() {
        return SoLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        SoLuongMua = soLuongMua;
    }

    public thuoc getIdThuoc() {
        return IdThuoc;
    }

    public void setIdThuoc(thuoc idThuoc) {
        IdThuoc = idThuoc;
    }

    public tkkhachhang getIdKhachhang() {
        return IdKhachhang;
    }

    public void setIdKhachhang(tkkhachhang idKhachhang) {
        IdKhachhang = idKhachhang;
    }
}
