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

import static com.app.mytime2sallon.SelectGenderActivity.GENDER;

public class SelectUserTypeActivity extends AppCompatActivity implements RevelInterface {
    public static String STYLIST = "stylist";
    public static String CUSTOMER = "customer";
    public static String USER_TYPE = "userType";
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        gender = this.getIntent().getStringExtra(GENDER);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            final View view =  this.findViewById(R.id.activity_select_user_type);
            view.setVisibility(View.INVISIBLE);

            view.post(new Runnable() {
                @Override
                public void run() {
                   // AppAnimation.showViewRevel(view, 0, view.getHeight() / 2);
                    AppAnimation.showViewSideReveal(view, SelectUserTypeActivity.this);
                }
            });
        }


    }

    public void startLoginSignUpActivity(View view) {


        Intent intent = new Intent(SelectUserTypeActivity.this, LoginSignUpActivity.class);

        if (view.getId() == R.id.btStylist) {
            intent.putExtra(USER_TYPE, STYLIST);
        } else {
            intent.putExtra(USER_TYPE, CUSTOMER);

        }
        intent.putExtra(GENDER, gender);


        if (Build.VERSION.SDK_INT < 21) {
            SelectUserTypeActivity.this.startActivity(intent);

            return;
        }


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SelectUserTypeActivity.this

        );

        SelectUserTypeActivity.this.startActivity(intent, options.toBundle());


    }

    @Override
    public void onRevelFinsh() {

    }
}
