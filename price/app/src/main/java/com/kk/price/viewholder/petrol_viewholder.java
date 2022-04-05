package com.kk.price.viewholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kk.price.R;

public class petrol_viewholder extends RecyclerView.ViewHolder {


    View view;


    public petrol_viewholder(@NonNull View itemView) {
        super(itemView);


        view = itemView;


    }

    @SuppressLint("ResourceAsColor")
    public void setdetails(Context context, String city, String price,String change) {


        TextView City,Price,Change;

        City=view.findViewById(R.id.pstatename);
        Price=view.findViewById(R.id.pprices);
        Change=view.findViewById(R.id.pchanges);


        City.setText(city);
        Price.setText(price+" Rs");
        Change.setText(change);







    }


}
