package com.m_mustafa.softwareproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AppStartActivity extends AppCompatActivity {
    private Button signInBtn, logInBtn;
    private ImageView logoImgLamp;
    private Animation anim_lamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);

        //-------------------------------- logo setup anim --------------------------------------------
        logoImgLamp = (ImageView) findViewById(R.id.uber_lamp);
        anim_lamp = (Animation) AnimationUtils.loadAnimation(AppStartActivity.this, R.anim.app_start_anim);
        logoImgLamp.startAnimation(anim_lamp);
        //---------------------------------------------------------------------------------------------

        signInBtn = findViewById(R.id.sign_in_btn);
        logInBtn = findViewById(R.id.log_in_btn);

        //-------------------------------------- Login & Sign up button listener ----------------------
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppStartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppStartActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        //---------------------------------------------------------------------------------------------
    }
}
