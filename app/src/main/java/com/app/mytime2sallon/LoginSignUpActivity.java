package com.app.mytime2sallon;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.anim.AppAnimation;
import com.app.appInterface.RevelInterface;

import static com.app.mytime2sallon.SelectGenderActivity.GENDER;
import static com.app.mytime2sallon.SelectUserTypeActivity.STYLIST;
import static com.app.mytime2sallon.SelectUserTypeActivity.USER_TYPE;

public class LoginSignUpActivity extends AppCompatActivity implements RevelInterface {

    String gender, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
        gender = this.getIntent().getStringExtra(GENDER);
        userType = this.getIntent().getStringExtra(USER_TYPE);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            final View view = this.findViewById(R.id.activity_login_sign_up);
            view.setVisibility(View.INVISIBLE);

            view.post(new Runnable() {
                @Override
                public void run() {

                    AppAnimation.showViewCenterRevel(view, LoginSignUpActivity.this);
                }
            });
        }

    }

    public void signIn(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        this.startActivity(intent);
    }

    public void signUp(View view) {
        Intent intent=null;
        if(userType.equals(STYLIST)){
             intent = new Intent(this, SignUpStylistActivity.class);

        }else{
            intent = new Intent(this, SignUpCustomerActivity.class);

        }
        intent.putExtra(GENDER,gender);
        this.startActivity(intent);

    }

    @Override
    public void onRevelFinsh() {

    }
}
