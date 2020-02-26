package com.m_mustafa.softwareproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class slideAdapter extends PagerAdapter {

    public int[] slide_imgs = {
            R.drawable.customer,
            R.drawable.driver,
            R.drawable.taxi
    };
    public String[] slide_headings = {
            "Customers",
            "Drivers",
            "Cars"
    };
    public String[] slide_docs = {
            "For each client you have the right to make any complaint from the drivers and rate them\n",
            "For each drivers you have the right to make any complaint from the customers and rate them\n",
            "We make sure that cars are good by check every thing, and this for the comfort of our customers"
    };
    Context context;
    LayoutInflater layoutInflater;

    public slideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView mySlideImg = (ImageView) view.findViewById(R.id.slide_img);
        TextView mySlideHead = (TextView) view.findViewById(R.id.slide_heading);
        TextView mySlideDocs = (TextView) view.findViewById(R.id.slide_desc);

        mySlideImg.setImageResource(slide_imgs[position]);
        mySlideHead.setText(slide_headings[position]);
        mySlideDocs.setText(slide_docs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
