package com.example.admin.banthuocdx.Doituong;

/**
 * Created by Admin on 3/21/2018.
 */

public class thuoc {
int IdThuoc;
String TenThuoc;
String NgaySanXuat;
Float Giatien;
int Soluong;
String HangSanXuat;
String Mota;
String AnhThuocChiTiet;
String AnhThuocList;
theloaithuoc TheLoai;
tkadmin Idadmin;

    public thuoc(String tenThuoc, Float giatien, int soluong, String mota, String anhThuocList) {
        TenThuoc = tenThuoc;
        Giatien = giatien;
        Soluong = soluong;
        Mota = mota;
        AnhThuocList = anhThuocList;
    }

    public thuoc(String tenThuoc, String ngaySanXuat, Float giatien, int soluong, String hangSanXuat, String mota, String anhThuocChiTiet, theloaithuoc theLoai, tkadmin idadmin) {
        TenThuoc = tenThuoc;
        NgaySanXuat = ngaySanXuat;
        Giatien = giatien;
        Soluong = soluong;
        HangSanXuat = hangSanXuat;
        Mota = mota;
        AnhThuocChiTiet = anhThuocChiTiet;
        TheLoai = theLoai;
        Idadmin = idadmin;
    }

    public int getIdThuoc() {
        return IdThuoc;
    }

    public void setIdThuoc(int idThuoc) {
        IdThuoc = idThuoc;
    }

    public String getTenThuoc() {
        return TenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        TenThuoc = tenThuoc;
    }

    public String getNgaySanXuat() {
        return NgaySanXuat;
    }

    public void setNgaySanXuat(String ngaySanXuat) {
        NgaySanXuat = ngaySanXuat;
    }

    public Float getGiatien() {
        return Giatien;
    }

    public void setGiatien(Float giatien) {
        Giatien = giatien;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public String getHangSanXuat() {
        return HangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        HangSanXuat = hangSanXuat;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getAnhThuocChiTiet() {
        return AnhThuocChiTiet;
    }

    public void setAnhThuocChiTiet(String anhThuocChiTiet) {
        AnhThuocChiTiet = anhThuocChiTiet;
    }

    public String getAnhThuocList() {
        return AnhThuocList;
    }

    public void setAnhThuocList(String anhThuocList) {
        AnhThuocList = anhThuocList;
    }

    public theloaithuoc getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(theloaithuoc theLoai) {
        TheLoai = theLoai;
    }

    public tkadmin getIdadmin() {
        return Idadmin;
    }

    public void setIdadmin(tkadmin idadmin) {
        Idadmin = idadmin;
    }
}

