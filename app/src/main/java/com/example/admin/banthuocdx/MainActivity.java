package com.example.admin.banthuocdx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCon();
    }
        public Connection getCon() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banthuoc", "root", "1234");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return con;
        }
    }
//FUCKKKKK
// Câu lệnh lấy dữ liệu đơn giản :

//    public ArrayList<"tentrong"-Mohinh> getlist()
//
//        ArrayList<"tentrong"-Mohinh> list = null;
//        DBConnect mydb = new DBConnect();
//        Connection con = mydb.getCon();