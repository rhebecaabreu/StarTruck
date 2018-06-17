package com.example.progmobile.startruck.view;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.progmobile.startruck.R;

import com.example.progmobile.startruck.model.dao.UserDAO;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout a;
    private TextView enterprise;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("StarTruck");
        setSupportActionBar(toolbar);

        fabMenu();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        name = headerView.findViewById(R.id.txtUsernameHeader);
        enterprise = headerView.findViewById(R.id.txtNameHeader);

        UserDAO udao= new UserDAO(Main3Activity.this);

        name.setText(udao.searchFullName(MainActivity.usrname));
        enterprise.setText(udao.searchEnterpriseById(MainActivity.usr));

    }

    public void fabMenu(){
        final FragmentManager fragmentManager = getFragmentManager();
        final FloatingActionButton fab_car, fab_driver;
        final FloatingActionMenu fab = (FloatingActionMenu) findViewById(R.id.fab);

        fab_car = (FloatingActionButton) findViewById(R.id.fab_vehicle);
        fab_driver = (FloatingActionButton) findViewById(R.id.fab_driver);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fab.isOpened()) {
                    fab.close(true);
                }
            }
        });

        fab_car.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fragment, new VehicleRegisterFragment()).commit();
                fab.close(true);


            }
        });

        fab_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.fragment, new DriverRegisterFragment()).commit();
                fab.close(true);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        String bar;

        if (id == R.id.nav_veihcle) {
            fragmentManager.beginTransaction().replace(R.id.fragment, new VehicleListFragment()).commit();
        } else if (id == R.id.nav_maintenance) {

        } else if (id == R.id.nav_stock) {

        } else if (id == R.id.nav_drivers) {
            fragmentManager.beginTransaction().replace(R.id.fragment, new DriversListFragment()).commit();
        } else if (id == R.id.nav_travels) {

        } else if (id == R.id.nav_expenses) {

        } else if (id == R.id.nav_reports){

        } else if (id == R.id.nav_home) {

            fragmentManager.beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
