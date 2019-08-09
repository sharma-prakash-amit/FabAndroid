package com.fab.kh2280.fab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLoginPage extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);
    }

    public void onLoginClick(View view) {
        Intent i = new Intent(getApplicationContext(),AddProduct.class);
        startActivity(i);
    }
}
