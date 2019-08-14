package com.fab.kh2280.fab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContinueAsGuestOrAdminLogin extends Activity {

    String savedExtra="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_as_guest_or_admin_login);
        savedExtra = getIntent().getStringExtra("itemType");
    }

    public void adminLogin(View view) {
        Intent i = new Intent(getApplicationContext(),AdminLoginPage.class);
        i.putExtra("itemType",savedExtra);
        startActivity(i);
    }

    public void continueAsGuest(View view) {
        Intent i = new Intent(getApplicationContext(),ProductList.class);
        i.putExtra("itemType",savedExtra);
        startActivity(i);
        finish();
    }
}
