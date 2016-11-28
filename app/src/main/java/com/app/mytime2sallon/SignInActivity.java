package com.app.mytime2sallon;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
