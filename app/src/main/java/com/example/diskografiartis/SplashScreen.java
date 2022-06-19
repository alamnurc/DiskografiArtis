package com.example.diskografiartis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        int loadingtime = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah splash maka berpindah ke MainActivity
                Intent home = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(home);
                finish();

            }
        }, loadingtime);
    }
}