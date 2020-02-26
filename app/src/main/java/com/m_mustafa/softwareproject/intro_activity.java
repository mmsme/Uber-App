package com.m_mustafa.softwareproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class intro_activity extends AppCompatActivity {
    private ImageView introImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_activity);

        introImg = (ImageView) findViewById(R.id.intro_logo);
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.intro_splash);
        introImg.startAnimation(myAnim);

        final Intent intent = new Intent(intro_activity.this, MainActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
