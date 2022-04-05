package com.kk.price.viewholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kk.price.R;

public class state_viewholder extends RecyclerView.ViewHolder {


    View view;


    public state_viewholder(@NonNull View itemView) {
        super(itemView);


        view = itemView;


    }

    @SuppressLint("ResourceAsColor")
    public void setdetails(Context context, String name, String img) {


        TextView Name, Img;


        Img = view.findViewById(R.id.dp);


        Img.setText(img);


        Name = view.findViewById(R.id.text);
        Name.setText(name);


    }


}
