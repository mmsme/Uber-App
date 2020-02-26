package com.m_mustafa.softwareproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class inbox_activity extends AppCompatActivity {
    RecyclerView inboxRecyclerView;
    Context inboxContext;
    private List<inboxRecData> inboxData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_activity);

        final DrawerLayout drawerLayout;
        Toolbar toolbar = findViewById(R.id.MyToolbare);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.inbox_title);


        // nav drawer toggel button code
        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                Context context;
                if (id == R.id.nav_menu_history) {
                    context = new history_activity();
                } else if (id == R.id.nav_menu_inbox) {
                    context = new inbox_activity();
                } else if (id == R.id.nav_menu_payment) {
                    context = new payment_activity();
                } else if (id == R.id.nav_menu_setting) {
                    context = new setting_activity();
                } else if (id == R.id.nav_menu_logout) {
                    FirebaseAuth.getInstance().signOut();
                    context = new AppStartActivity();
                } else {
                    context = new mapActivity();
                }

                Intent intent = new Intent(inbox_activity.this, context.getClass());
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //--------------- Recycle View Code----------------------------
        inboxRecyclerView = (RecyclerView) findViewById(R.id.inbox_RecView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(inboxContext);
        inboxRecyclerView.setLayoutManager(layoutManager);

        RecViewAdapter adapter = new RecViewAdapter(inboxData);

        inboxDataAssign();
        assignAdapter();
    }

    public void inboxDataAssign() {
        inboxData = new ArrayList<>();
        inboxData.add(new inboxRecData("Mohamed Mustafa", "10/2/2018", "hink you for rating and i wish taht you be happy to request me", R.id.sender_img));
    }

    public void assignAdapter() {
        RecViewAdapter viewAdapter = new RecViewAdapter(inboxData);
        inboxRecyclerView.setAdapter(viewAdapter);
    }

    // back btn pressed to close nav drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
