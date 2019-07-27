package com.fab.kh2280.fab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class ProductList extends Activity {

    //Master data of list
    String[] nameArray = {"Octopus","Pig","Sheep","Rabbit","Snake","Spider" };

    String[] infoArray = {
            "8 tentacled monster",
            "Delicious in rolls",
            "Great for jumpers",
            "Nice in a stew",
            "Great for shoes",
            "Scary."
    };

    Integer[] imageArray = {R.drawable.dog,
            R.drawable.dog,
            R.drawable.dog,
            R.drawable.dog,
            R.drawable.dog,
            R.drawable.dog};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        myRef.setValue("Hello, Country!");
        myRef.setValue("Hello, State!");
        myRef.setValue("Hello, City!");

        //Set Custom Adapter to list
        CustomProductListAdaptor whatever = new CustomProductListAdaptor(this, nameArray, infoArray, imageArray);
        listView = (ListView) findViewById(R.id.product_list_item);
        listView.setAdapter(whatever);

        //Set on click method of list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ProductList.this, ProductDetails.class);
                String message = nameArray[position];
                intent.putExtra("animal", message);
                startActivity(intent);
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }




}
