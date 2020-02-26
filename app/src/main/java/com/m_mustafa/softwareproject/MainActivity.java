package com.m_mustafa.softwareproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private ViewPager introViewPager;
    private LinearLayout myDotsLayout;
    private slideAdapter slideADb;
    private TextView[] mdots;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    //------------------------------- intro slides adapter -------------------------------------------
    private Button mNextBtn;
    private int mCurrentPage;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        //----------------------- show finish button --------------------------------------------------
        @Override
        public void onPageSelected(int i) {
            addDotsIndincater(i);
            mCurrentPage = i;

            if (i == 0) {
                mNextBtn.setEnabled(false);
                mNextBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("");
            } else if (i == mdots.length - 1) {
                mNextBtn.setEnabled(true);
                mNextBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("finish");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------ listener to chick if user had sign in ------------------------
        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, mapActivity.class);
                    startActivity(intent);
                }
            }
        };

        mNextBtn = (Button) findViewById(R.id.my_next_btn);

        introViewPager = (ViewPager) findViewById(R.id.intro_viewpager);
        myDotsLayout = (LinearLayout) findViewById(R.id.dots_layout);

        slideADb = new slideAdapter(this);
        introViewPager.setAdapter(slideADb);

        addDotsIndincater(0);
        introViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AppStartActivity.class);
                startActivity(intent);
            }
        });

    }

    //-------------------------------- assignment intro slides ----------------------------------------
    public void addDotsIndincater(int position) {
        mdots = new TextView[3];
        myDotsLayout.removeAllViews();
        for (int i = 0; i < mdots.length; i++) {
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.bottomColor));

            myDotsLayout.addView(mdots[i]);
        }

        //--------------------- active dot color -------------------------------
        if (mdots.length > 0) {
            mdots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    //------------------------------- check if user sign in  ------------------------------------------------
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

}
