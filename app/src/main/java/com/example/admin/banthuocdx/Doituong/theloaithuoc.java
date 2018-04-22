package com.example.admin.banthuocdx.Doituong;

/**
 * Created by Admin on 3/21/2018.
 */

public class theloaithuoc {
    int IdTheLoaiThuoc;
    AdminTheloaiPK adminTheloaiPK;

    public theloaithuoc(int idTheLoaiThuoc, AdminTheloaiPK adminTheloaiPK, String tenTheLoai) {
        IdTheLoaiThuoc = idTheLoaiThuoc;
        this.adminTheloaiPK = adminTheloaiPK;
        TenTheLoai = tenTheLoai;
    }

    public AdminTheloaiPK getAdminTheloaiPK() {
        return adminTheloaiPK;
    }

    public void setAdminTheloaiPK(AdminTheloaiPK adminTheloaiPK) {
        this.adminTheloaiPK = adminTheloaiPK;
    }

    String TenTheLoai;
    public theloaithuoc()
    {}

    public int getIdTheLoaiThuoc() {
        return IdTheLoaiThuoc;
    }

    public void setIdTheLoaiThuoc(int idTheLoaiThuoc) {
        IdTheLoaiThuoc = idTheLoaiThuoc;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }
}
