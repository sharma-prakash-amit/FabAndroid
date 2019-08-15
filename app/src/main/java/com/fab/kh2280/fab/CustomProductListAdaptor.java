package com.fab.kh2280.fab;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KH2280 on 18-07-2019.
 */

public class CustomProductListAdaptor extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    private final Integer[] imageIDarray;

    private final String[] nameArray;

    private final String[] infoArray;

    public CustomProductListAdaptor(Activity context, String[] nameArrayParam, String[] infoArrayParam, Integer[] imageIDArrayParam){

        super(context,R.layout.productlist_item , nameArrayParam);
        this.context=context;
        this.imageIDarray = imageIDArrayParam;
        this.nameArray = nameArrayParam;
        this.infoArray = infoArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.productlist_item, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.product_name);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.product_description);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.productImage);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        infoTextField.setText(infoArray[position]);
        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    };
}
