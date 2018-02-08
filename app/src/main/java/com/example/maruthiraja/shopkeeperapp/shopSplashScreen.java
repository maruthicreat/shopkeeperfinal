package com.example.maruthiraja.shopkeeperapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class shopSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_splash_screen);
        Thread mythread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1500);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }
}
