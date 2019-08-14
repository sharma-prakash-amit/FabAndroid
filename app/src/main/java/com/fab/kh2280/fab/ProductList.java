package com.fab.kh2280.fab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class ProductList extends Activity {

    //Master data of list
    Integer[] imageArray = {
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,
            R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog,R.drawable.dog
    };


    String[] productName={};
    String[] productDesc={};


    ListView listView;
    DatabaseReference reff;
    CommonFunctions commonFunctions = new CommonFunctions();

    String savedExtra = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        savedExtra = getIntent().getStringExtra("itemType");

        commonFunctions.showProgressDialog(this);
        getData(savedExtra);
    }

    private void populateListView() {

        //Set Custom Adapter to list
        CustomProductListAdaptor singleProduct = new CustomProductListAdaptor(this, productName, productDesc, imageArray);
        listView = (ListView) findViewById(R.id.product_list_item);
        listView.setAdapter(singleProduct);

        //Set on click method of list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ProductList.this, ProductDetails.class);
                String message = productDesc[position];
                intent.putExtra("description", message);
                startActivity(intent);
            }
        });


    }

    private void getData(String itemType) {

        reff = FirebaseDatabase.getInstance().getReference().child("Product").child(itemType);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,String> productData = new HashMap<>();
                productData = (HashMap<String, String>) dataSnapshot.getValue();

                int i=0;
                productName = new String[productData.keySet().size()];
                productDesc = new String[productData.keySet().size()];

                for (String key : productData.keySet()) {

                    productName[i] = dataSnapshot.child(key).child("name") != null ?
                            dataSnapshot.child(key).child("name").getValue().toString() :
                            "-";

                    productDesc[i] = dataSnapshot.child(key).child("description") != null ?
                            dataSnapshot.child(key).child("description").getValue().toString() :
                            "-";

                    i++;
                }
                populateListView();
                commonFunctions.dismissProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                commonFunctions.dismissProgressDialog();
                Toast.makeText(getApplicationContext(),"There is some issue in the database connectivity",Toast.LENGTH_LONG).show();
            }
        });

    }


}
