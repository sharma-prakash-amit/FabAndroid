package com.fab.kh2280.fab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
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


public class ProductList extends AppCompatActivity {

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


    //Custom References
    CommonFunctions commonFunctions = new CommonFunctions();
    Constants constants = new Constants();
    private String[] productName={};
    private String[] productDesc={};
    String savedExtra = "";

    //In Built References
    private DrawerLayout d1;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private ListView listView;
    private DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //start loading indicator
        commonFunctions.showProgressDialog(this);

        //Initialise hamburger menu
        initialiseHamburgerMenu();

        //get data according to item type and set the title on title bar
        savedExtra = getIntent().getStringExtra("itemType");
        getData(savedExtra);
        setTitle(savedExtra);
    }

    private void initialiseHamburgerMenu() {
        d1 = (DrawerLayout)findViewById(R.id.activity_product_list);
        t = new ActionBarDrawerToggle(this, d1,R.string.Open, R.string.Close);
        t.setDrawerIndicatorEnabled(true);

        d1.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        onHamburgerItemClicked(nv);

    }

    private void onHamburgerItemClicked(NavigationView nv) {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.shoes:
                        navigateToSamePageWithItemType(constants.Shoes);
                        break;
                    case R.id.tshirt:
                        navigateToSamePageWithItemType(constants.Tshirts);
                        break;
                    case R.id.wallets:
                        navigateToSamePageWithItemType(constants.Wallets);
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });
    }

    //Redirect to this same page with different item type on click of any item in hamburger
    private void navigateToSamePageWithItemType(String itemType) {
        d1.closeDrawers();
        commonFunctions.showProgressDialog(this);
        getData(itemType);
        setTitle(itemType);
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
                public void onDataChange(DataSnapshot dataSnapshot){
                    HashMap<String,String> productData;
                    productData = (HashMap<String, String>) dataSnapshot.getValue();

                    if(productData==null){
                        View parentLayout = findViewById(android.R.id.content);
                        Snackbar.make(parentLayout,constants.noDataFound,Snackbar.LENGTH_LONG).show();
                        productName=new String[0];
                        productDesc=new String[0];
                        populateListView();
                    }else {
                        int i = 0;
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
                    }
                    commonFunctions.dismissProgressDialog();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    commonFunctions.dismissProgressDialog();
                    Toast.makeText(getApplicationContext(),constants.connectivityIssues,Toast.LENGTH_LONG).show();
                }
            });
    }


    //TODO - Facebook and Instagram Link
    public void onFabClick(View view) {
        Toast.makeText(getApplicationContext(),"It will open facebook or insta",Toast.LENGTH_SHORT).show();
//        Intent openFB = openFacebook(getApplicationContext());
//        startActivity(openFB);
//
//        Intent openInsta = openInstagram(getApplicationContext());
//        startActivity(openInsta);
    }

    private static Intent openFacebook(Context context) {
        try{
            context.getPackageManager().getPackageInfo("com.facebook.katana",0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1236513103082929"));

        }catch (Exception e){
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/fabcollectionz21"));
        }
    }

    private static Intent openInstagram(Context context){
        try {
            context.getPackageManager().getPackageInfo("com.instagram.android",0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/fab.collectionz"));
    }

    //Check whether the option is clicked in the hamburger or not
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return t.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
