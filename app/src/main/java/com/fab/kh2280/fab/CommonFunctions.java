package com.fab.kh2280.fab;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by KH2280 on 09-08-2019.
 */

public class CommonFunctions {

    ProgressDialog progress;

    public void showProgressDialog(Context context){
        progress= new ProgressDialog(context,R.style.Theme_AppCompat_DayNight_Dialog);
        progress.setTitle("Loading");
        progress.setMessage("Please Wait...");
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
    }

    public void dismissProgressDialog(){
        progress.dismiss();
    }
}
