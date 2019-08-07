package com.fab.kh2280.fab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContinueAsGuestOrAdminLogin extends Activity {

    Button mGuestContinue,mAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_as_guest_or_admin_login);

        mGuestContinue = (Button)findViewById(R.id.btnGuestContinue);
        mAdminLogin = (Button)findViewById(R.id.btnAdminLogin);

        //Navigate to product list page on click of guest login
        mGuestContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ProductList.class);
                startActivity(i);
            }
        });

        mAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminLoginPage.class);
                startActivity(i);
            }
        });

    }


}
