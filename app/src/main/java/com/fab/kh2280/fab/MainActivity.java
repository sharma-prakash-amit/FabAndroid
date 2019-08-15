package com.fab.kh2280.fab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {

    private CardView shoeCard, tshirtCard, walletCard;
    Constants constants = new Constants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoeCard = (CardView) findViewById(R.id.shoes_view);
        tshirtCard = (CardView) findViewById(R.id.tshirts_view);
        walletCard = (CardView) findViewById(R.id.wallets_view);

        shoeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToContinueAsGuestOrAdminLogin(constants.Shoes);
            }
        });

        tshirtCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToContinueAsGuestOrAdminLogin(constants.Tshirts);
            }
        });

        walletCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToContinueAsGuestOrAdminLogin(constants.Wallets);
            }
        });



    }

    private void navigateToContinueAsGuestOrAdminLogin(String itemType) {
        Intent i = new Intent(MainActivity.this,ContinueAsGuestOrAdminLogin.class);
        i.putExtra("itemType",itemType);
        startActivity(i);
    }

}
