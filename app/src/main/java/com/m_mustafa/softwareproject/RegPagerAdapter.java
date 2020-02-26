package com.m_mustafa.softwareproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RegPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> myList = new ArrayList<>();

    public RegPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return myList.get(position);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    public void addFrag(Fragment fragment) {
        myList.add(fragment);
    }
}
