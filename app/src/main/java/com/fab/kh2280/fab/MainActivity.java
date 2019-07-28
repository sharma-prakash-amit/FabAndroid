package com.fab.kh2280.fab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {

    private CardView shoeCard, shirtCard, walletCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoeCard = (CardView) findViewById(R.id.shoes_view);
        shirtCard = (CardView) findViewById(R.id.shirts_view);
        walletCard = (CardView) findViewById(R.id.wallets_view);

        shoeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddProduct.class);
                startActivity(i);
            }
        });

        shirtCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddProduct.class);
                startActivity(i);
            }
        });

        walletCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddProduct.class);
                startActivity(i);
            }
        });



    }

}
