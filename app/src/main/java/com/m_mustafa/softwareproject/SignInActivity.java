package com.m_mustafa.softwareproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setupViewPager();
    }

    private void setupViewPager() {
        RegPagerAdapter adapter = new RegPagerAdapter(getSupportFragmentManager());
        // add Fragment
        adapter.addFrag(new DriverRegFrag());
        adapter.addFrag(new CustomerRegFrag());
        // setup view pager
        ViewPager vp = (ViewPager) findViewById(R.id.sign_in_viewPager);
        vp.setAdapter(adapter);
        // initial tab layout
        TabLayout myTab = findViewById(R.id.account_type);
        myTab.setupWithViewPager(vp);
        // tab Items
        myTab.getTabAt(0).setText(R.string.driver).setIcon(R.drawable.person_icon);
        myTab.getTabAt(1).setText(R.string.customer).setIcon(R.drawable.person_icon);
    }
}
