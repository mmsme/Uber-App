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

public class history_activity extends AppCompatActivity {
    RecyclerView historyRecyclerView;
    Context historyContext;
    private List<HistoryData> historyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_activity);

        final DrawerLayout drawerLayout;
        Toolbar toolbar = findViewById(R.id.MyToolbare);
        toolbar.setTitle(R.string.history_title);
        setSupportActionBar(toolbar);


        // nav drawer toggel button code
        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //------------------------------- nav Drawer Listener -----------------------------------------
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

                Intent intent = new Intent(history_activity.this, context.getClass());
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        //---------------------------------------------------------------------------------------------

        //------------------------------------- RecycleView -------------------------------------------
        historyRecyclerView = (RecyclerView) findViewById(R.id.history_Rec_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(historyContext);
        historyRecyclerView.setLayoutManager(layoutManager);

        RecycleViewAdapter adapter = new RecycleViewAdapter(historyData);

        initializeData();
        initializeAdapter();
        //---------------------------------------------------------------------------------------------

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

    // initialize History Data to put it in RecycleView
    private void initializeData() {
        historyData = new ArrayList<>();
        historyData.add(new HistoryData("Mohamed Ali", "Mansoura", "Giza", "10/2/2019", R.drawable.person_2));
        historyData.add(new HistoryData("Mai", "Alex", "Aga", "1/12019", R.drawable.person_3));
    }

    // initialize adapter
    private void initializeAdapter() {
        RecycleViewAdapter viewAdapter = new RecycleViewAdapter(historyData);
        historyRecyclerView.setAdapter(viewAdapter);
    }
}
