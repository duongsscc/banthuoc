package com.example.admin.banthuocdx.Dichvu.navigationdr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.admin.banthuocdx.Dichvu.ListPage.ListPageActivity;
import com.example.admin.banthuocdx.Dichvu.dangnhapdangky.ActivityDangnhapDangky;
import com.example.admin.banthuocdx.Dichvu.listgiohang.GiohangFragment;
import com.example.admin.banthuocdx.Dichvu.listthuoc.ThuocFragment;
import com.example.admin.banthuocdx.Doituong.thuoc;
import com.example.admin.banthuocdx.R;

public class ActivityNavigationDr extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_navigation_dr );
        mDrawerlayout = (DrawerLayout) findViewById( R.id.drawer );
        mToggle = new ActionBarDrawerToggle( this, mDrawerlayout, R.string.open, R.string.close );
        mDrawerlayout.addDrawerListener( mToggle );
//        navigationView = findViewById( R.id.navi );
//        navigationView.setNavigationItemSelectedListener( this );
//
//        mDrawerlayout.addDrawerListener( mToggle );
        mToggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById( R.id.navi );
        navigationView.setNavigationItemSelectedListener( this );

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );


    }
//    @Override
//    public void onBackPressed() {
//        mDrawerlayout = findViewById( R.id.drawer );
//        if (mDrawerlayout.isDrawerOpen( GravityCompat.START ))
//        {
//            mDrawerlayout.closeDrawer( GravityCompat.START );
//        }
//        else
//        { super.onBackPressed();
//
//        }
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate( R.menu.drawermenu_nav,menu );
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected( item )) {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int m = item.getItemId();
        if (m == R.id.thuoc) {
            //TODO add thuoc fragment
            Log.d( "dmm", "menu click" );
            Intent thuoc = new Intent( ActivityNavigationDr.this, ListPageActivity.class );
            startActivity( thuoc );
        } else if (m == R.id.giohang) {
            Intent giohang = new Intent( ActivityNavigationDr.this, GiohangFragment.class );
            startActivity( giohang );
        } else if (m == R.id.dangxuat) {
            Intent dangxuat = new Intent( ActivityNavigationDr.this, ActivityDangnhapDangky.class );
            startActivity( dangxuat );
        }
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer );
        drawer.closeDrawer( GravityCompat.START );
        return true;

    }
}
