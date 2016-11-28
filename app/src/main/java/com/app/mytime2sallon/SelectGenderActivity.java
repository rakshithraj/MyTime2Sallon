package com.app.mytime2sallon;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.anim.AppAnimation;
import com.app.appInterface.RevelInterface;

public class SelectGenderActivity extends AppCompatActivity implements RevelInterface {


    public static String MEN = "men";
    public static String WOMEN = "women";
    public static String GENDER = "gender";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gender);
        if (savedInstanceState == null) {
            final View view =  this.findViewById(R.id.activity_select_gender);
            view.setVisibility(View.INVISIBLE);

            view.post(new Runnable() {
                @Override
                public void run() {
                     AppAnimation.showViewRevel(view, 0, view.getHeight() / 2);
                }
            });
        }

    }

    public void startUserTypeActivity(View view) {
        Intent intent = new Intent(SelectGenderActivity.this, SelectUserTypeActivity.class);

        if (view.getId() == R.id.btMen) {
            intent.putExtra(GENDER, MEN);
        } else {
            intent.putExtra(GENDER, WOMEN);

        }

        if (Build.VERSION.SDK_INT < 21) {
            SelectGenderActivity.this.startActivity(intent);

            return;
        }


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SelectGenderActivity.this

        );

        SelectGenderActivity.this.startActivity(intent, options.toBundle());



    }

    @Override
    public void onRevelFinsh() {

    }
}
