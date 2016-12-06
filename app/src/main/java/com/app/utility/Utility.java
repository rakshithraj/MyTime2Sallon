package com.app.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;

/**
 * Created by Rakshith on 11/29/2016.
 */

public class Utility {

    public static void alerDialog(Context context , String message) {


            AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(context);
            //myAlertDialog.setTitle("LMS");
            message = "<font color='#F0A134'>" + message + "</font>";
            myAlertDialog.setMessage(Html.fromHtml(message));

            myAlertDialog.setPositiveButton(Html.fromHtml("OK"), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.cancel();
                }
            });


            myAlertDialog.show();


    }
}
