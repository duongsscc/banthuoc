package com.example.admin.banthuocdx.Doituong;

public class AdminTheloaiPK {
    int idAdmin;
    int idTheloaithuoc;
    int idthuoc;

    public AdminTheloaiPK(int idAdmin, int idTheloaithuoc, int idthuoc) {
        this.idAdmin = idAdmin;
        this.idTheloaithuoc = idTheloaithuoc;
        this.idthuoc = idthuoc;
    }

    public AdminTheloaiPK(int idAdmin, int idTheloaithuoc) {
        this.idAdmin = idAdmin;
        this.idTheloaithuoc = idTheloaithuoc;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdTheloaithuoc() {
        return idTheloaithuoc;
    }

    public void setIdTheloaithuoc(int idTheloaithuoc) {
        this.idTheloaithuoc = idTheloaithuoc;
    }

    public int getIdthuoc() {
        return idthuoc;
    }

    public void setIdthuoc(int idthuoc) {
        this.idthuoc = idthuoc;
    }
}
