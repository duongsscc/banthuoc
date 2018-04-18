package com.example.admin.banthuocdx.Dichvu.ListPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.banthuocdx.Dichvu.dangnhapdangky.ActivityDangky;
import com.example.admin.banthuocdx.Dichvu.listgiohang.GiohangFragment;
import com.example.admin.banthuocdx.Dichvu.listthuoc.ThuocFragment;
import com.example.admin.banthuocdx.Doituong.tkadmin;
import com.example.admin.banthuocdx.Doituong.tkkhachhang;
import com.example.admin.banthuocdx.Ketnoicsdl.KetnoiData;
import com.example.admin.banthuocdx.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ListPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;
    public ArrayList<tkkhachhang> listkh;
    public ArrayList<tkadmin> listadmin;
    tkkhachhang tkkhachhang = null;
    Connection con;
    KetnoiData kc = new KetnoiData();
    TextView tenKH, sdtKH;
    ImageView imgAnhkh;
    String hoten, sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);
        imgAnhkh = findViewById(R.id.anhhead);


        String tentk = getIntent().getStringExtra("tentk");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navi);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        tenKH = headerview.findViewById(R.id.textheadTEN);
        sdtKH = headerview.findViewById(R.id.textheadSDT);
        Hienthikhachang(tentk);

    }


    public void Hienthikhachang(final String taikhoan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                con = kc.ketnoi();
                listkh = new ArrayList<>();
                try {
                    String sql;
                    sql = "SELECT * FROM tkkhachhang WHERE taikhoan=?";
                    PreparedStatement prest = con.prepareStatement(sql);
                  prest.setString(1,taikhoan);
                    ResultSet rs = prest.executeQuery();

                    while (rs.next()) {
                        tkkhachhang = new tkkhachhang();
                        tkkhachhang.setIdKhachhang(rs.getInt("IdKhachhang"));
                        tkkhachhang.setHoten(rs.getString("Hoten"));
                        tkkhachhang.setSodienthoai(rs.getString("Sodienthoai"));
                        tkkhachhang.setAnhKhachhang(rs.getString("Anhkhachhang"));
                        tkkhachhang.setTaikhoan(rs.getString("taikhoan"));
                        tkkhachhang.setMatkhau(rs.getString("matkhau"));
                        listkh.add(tkkhachhang);
                        SharedPreferences sharedPreferences = getSharedPreferences("Myuser", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("Taikhoan",rs.getString("taikhoan"));
                        edit.putString("Hoten",rs.getString("Hoten"));
                        edit.putString("Sodienthoai",rs.getString("Sodienthoai"));
                        edit.putString("Anhkhachhang",rs.getString("Anhkhachhang"));
                        edit.commit();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    wait(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                tenKH.setText(listkh.get(0).getHoten());
                                sdtKH.setText(listkh.get(0).getSodienthoai());

                            }
                        });
                        mIncomingHandler.sendEmptyMessage(0);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    Handler mIncomingHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {

            return true;
        }
    });

    @Override
    public void onBackPressed() {
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        if (mDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int m = item.getItemId();
        if (m == R.id.thuoc) {
            //TODO add thuoc fragment
            Log.d("gg", "menu click");
            Intent thuoc = new Intent(ListPageActivity.this, ListPageActivity.class);
            startActivity(thuoc);
        } else if (m == R.id.giohang) {
            Intent giohang = new Intent(ListPageActivity.this, GiohangFragment.class);
            startActivity(giohang);
        } else if (m == R.id.dangxuat) {
            Intent dangxuat = new Intent(ListPageActivity.this, ActivityDangky.class);
            startActivity(dangxuat);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_page, menu);
        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_list_page, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    ThuocFragment thuocFragment = new ThuocFragment();
                    return thuocFragment;
                case 1:
                    GiohangFragment giohangFragment = new GiohangFragment();
                    return giohangFragment;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "THUOC";
                case 1:
                    return "GIOHANG";
            }
            return null;
        }
    }
}
