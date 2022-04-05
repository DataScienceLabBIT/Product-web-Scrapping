package com.kk.price.viewholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kk.price.R;

public class egg_viewholder extends RecyclerView.ViewHolder {


    View view;


    public egg_viewholder(@NonNull View itemView) {
        super(itemView);


        view = itemView;


    }

    @SuppressLint("ResourceAsColor")
    public void setdetails(Context context, String date, String price) {

     TextView textView,name;

        try {
            name=view.findViewById(R.id.names);
            name.setText(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        textView=view.findViewById(R.id.eggprice);

     textView.setText(price + " rs");




    }


}
