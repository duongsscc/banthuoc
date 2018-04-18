package com.example.admin.banthuocdx.Doituong;

/**
 * Created by Admin on 3/21/2018.
 */

public class tkkhachhang {
    int IdKhachhang;
    String Hoten;
    String Sodienthoai;
    String DiaChi;
    String AnhKhachhang;
    String AnhKhachhangchitiet;
    String taikhoan;
    String matkhau;
    public tkkhachhang()
    {}

    public tkkhachhang(String taikhoan) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }

    public int getIdKhachhang() {
        return IdKhachhang;
    }

    public void setIdKhachhang(int idKhachhang) {
        IdKhachhang = idKhachhang;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        Sodienthoai = sodienthoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getAnhKhachhang() {
        return AnhKhachhang;
    }

    public void setAnhKhachhang(String anhKhachhang) {
        AnhKhachhang = anhKhachhang;
    }

    public String getAnhKhachhangchitiet() {
        return AnhKhachhangchitiet;
    }

    public void setAnhKhachhangchitiet(String anhKhachhangchitiet) {
        AnhKhachhangchitiet = anhKhachhangchitiet;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}

