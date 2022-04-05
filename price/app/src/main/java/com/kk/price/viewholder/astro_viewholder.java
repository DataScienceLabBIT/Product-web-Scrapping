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

public class astro_viewholder extends RecyclerView.ViewHolder {


    View view;


    public astro_viewholder(@NonNull View itemView) {
        super(itemView);


        view = itemView;


    }

    @SuppressLint("ResourceAsColor")
    public void setdetails(Context context, String name, String img,String message,String col,String no,String re) {


        TextView Name, Message,Col,No,Re;
        ImageView Img;


        Img = view.findViewById(R.id.horoimg);
        Name=view.findViewById(R.id.name);
        Message=view.findViewById(R.id.message);


        Col=view.findViewById(R.id.color);
        No=view.findViewById(R.id.no);
        Re=view.findViewById(R.id.remedy);

        Glide.with(context).load(img).into(Img);

        Name.setText(name);
        Message.setText(message);
        Col.setText("Lucky Color :"+col);
        No.setText("Lucky No : "+no);
        Re.setText("Remedy :"+re);









    }


}
