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
                Intent i = new Intent(MainActivity.this,ContinueAsGuestOrAdminLogin.class);
                i.putExtra("itemType","Shoes");
                startActivity(i);
            }
        });

        tshirtCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ContinueAsGuestOrAdminLogin.class);
                i.putExtra("itemType","T-Shirts");
                startActivity(i);
            }
        });

        walletCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ContinueAsGuestOrAdminLogin.class);
                i.putExtra("itemType","Wallets");
                startActivity(i);
            }
        });



    }

}
