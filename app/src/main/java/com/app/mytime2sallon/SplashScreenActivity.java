package com.app.mytime2sallon;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        handler.postDelayed(runnable,3000);

    }



    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if(SplashScreenActivity.this!=null){
                Intent intent = new Intent(SplashScreenActivity.this,SelectGenderActivity.class);



                if(Build.VERSION.SDK_INT<21) {
                    SplashScreenActivity.this.startActivity(intent);
                    SplashScreenActivity.this.finish();;
                    return;
                }



                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this

                );

                SplashScreenActivity.this.startActivity(intent, options.toBundle());

                SplashScreenActivity.this.finish();;

            }
        }
    };
}
