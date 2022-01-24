package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ecms.ndmecms.ui.Agreement;

public class SplashScreen extends AppCompatActivity {
    ProgressBar progressBarSplash;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen2);
        super.onCreate(savedInstanceState);

        imageView = (ImageView)findViewById(R.id.logo_splash);

        progressBarSplash=(ProgressBar)findViewById(R.id.progress_bar_splash);
        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(5000);
        animation1.setStartOffset(100);
        animation1.setFillAfter(true);
        imageView.startAnimation(animation1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PreferenceUtils preferenceUtils=new PreferenceUtils();
                if(preferenceUtils.getAgree(SplashScreen.this)!=null){
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashScreen.this, Agreement.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 3000);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }
}