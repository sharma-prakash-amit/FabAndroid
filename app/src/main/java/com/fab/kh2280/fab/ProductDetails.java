package com.fab.kh2280.fab;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //Fetch values sent from intent.putExtra in product list
        String savedExtra = getIntent().getStringExtra("description");
        TextView myText = (TextView) findViewById(R.id.textView);
        myText.setText(savedExtra);
    }
}
