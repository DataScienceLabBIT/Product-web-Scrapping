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

public class veg_viewholder extends RecyclerView.ViewHolder {


    View view;


    public veg_viewholder(@NonNull View itemView) {
        super(itemView);


        view = itemView;


    }

    @SuppressLint("ResourceAsColor")
    public void setdetails(Context context, String name, String mp,String rp,String sm,String img) {

        TextView Veg_Name,Mp,Rp,Sm;
        ImageView Img;


        Veg_Name=itemView.findViewById(R.id.name_veg);
        Mp=itemView.findViewById(R.id.marketprice);
        Rp=itemView.findViewById(R.id.retailprice);
        Sm=itemView.findViewById(R.id.mallprice);


        Img=itemView.findViewById(R.id.dps);

        Veg_Name.setText(name);
        Mp.setText("Market Price : "+mp);
        Rp.setText("Retail Price : "+rp);
        Sm.setText("Shopping Mall : "+sm);

        Glide.with(context).load(img).into(Img);




    }


}
