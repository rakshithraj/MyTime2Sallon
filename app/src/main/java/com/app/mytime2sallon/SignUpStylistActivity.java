package com.app.mytime2sallon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.app.utility.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;

public class SignUpStylistActivity extends AppCompatActivity implements View.OnClickListener {

    final int GET_MAP_LOCATION = 123;
    double latitude, longitude;

    HashMap<String,String> mCityList = new HashMap<String,String>();
    HashMap<String,String>  mAreaList = new HashMap<String,String> ();
    HashMap<String,String>  mColonyList = new HashMap<String,String> ();


    int selectCityIndex=-1;
    int selectAreaIndex=-1;
    int selectColonyIndex=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_stylist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intializeClickListner();

        addDummyData();
    }

    private void addDummyData() {


    }

    private void intializeClickListner() {


        findViewById(R.id.rlCity).setOnClickListener(this);
        findViewById(R.id.rlArea).setOnClickListener(this);
        findViewById(R.id.rlColony).setOnClickListener(this);

    }

    public void setLocation(View view) {

        Intent intent = new Intent(SignUpStylistActivity.this, MapsActivity.class);
        startActivityForResult(intent, GET_MAP_LOCATION);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == GET_MAP_LOCATION) {

            TextView tvMapLocation = (TextView) findViewById(R.id.tvMapLocation);
            latitude = data.getDoubleExtra(AppConstants.LATITUDE, 0);
            longitude = data.getDoubleExtra(AppConstants.LONGTITUDE, 0);
            tvMapLocation.setText("latitude=" + (Math.floor(latitude * 100) / 100) + "  longitude=" + Math.floor(longitude * 100) / 100);

        }


    }

    public void signUp(View view) {


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.rlCity:

                showCityList();

                break;
            case R.id.rlArea:
                showAreaList();

                break;
            case R.id.rlColony:
                showColonyList();

                break;


        }
    }

    private void showColonyList() {
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        String list[];
        for (String name : mColonyList.keySet()) {

        }


        ad.setSingleChoiceItems((String[])mColonyList.toArray(), selectColonyIndex, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                selectColonyIndex = arg1;
                arg0.dismiss();

            }
        });
        ad.show();
    }


    private void showAreaList() {
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setSingleChoiceItems((String[])mAreaList.toArray(), selectAreaIndex, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                selectAreaIndex = arg1;
                arg0.dismiss();

            }
        });
        ad.show();
    }

    private void showCityList() {

        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setSingleChoiceItems((String[])mCityList.toArray(), selectCityIndex, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                selectCityIndex = arg1;
                arg0.dismiss();

            }
        });
        ad.show();



    }
}
