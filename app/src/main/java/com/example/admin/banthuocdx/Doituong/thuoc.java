package com.example.admin.banthuocdx.Doituong;

import java.io.Serializable;

/**
 * Created by Admin on 3/21/2018.
 */

public class thuoc implements Serializable {
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
int slGioHang;

    public thuoc(int idThuoc, String tenThuoc, Float giatien, int soluong, String mota, String anhThuocList, theloaithuoc theLoai, tkadmin idadmin) {
        IdThuoc = idThuoc;
        TenThuoc = tenThuoc;
        Giatien = giatien;
        Soluong = soluong;
        Mota = mota;
        AnhThuocList = anhThuocList;
        TheLoai = theLoai;
        Idadmin = idadmin;
    }

    public thuoc(String tenThuoc, Float giatien, int soluong, String mota, String anhThuocList) {
        TenThuoc = tenThuoc;
        Giatien = giatien;
        Soluong = soluong;
        Mota = mota;
        AnhThuocList = anhThuocList;
    }
    public thuoc(String tenThuoc, Float giatien, int soluong, String mota)
    {
        TenThuoc = tenThuoc;
        Giatien = giatien;
        Soluong = soluong;
        Mota = mota;
    }

    public thuoc(String tenThuoc, Float giatien, int soluong, int slGioHang, String mota,String anhThuocList)
    {
        this.TenThuoc = tenThuoc;
        this.Giatien = giatien;
        this.Soluong = soluong;
        this.Mota = mota;
        this.slGioHang = slGioHang;
        this.AnhThuocList = anhThuocList;
    }

public thuoc()
{}
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

    public int getSlGioHang(){
        return this.slGioHang;
    }
    public int getIdThuoc() {
        return IdThuoc;
    }

    public void setSlGioHang(int sl){
        this.slGioHang = sl;
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

