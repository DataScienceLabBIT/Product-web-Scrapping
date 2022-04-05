package com.kk.price.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kk.price.R;
import com.kk.price.model.Petrol_model;
import com.kk.price.model.VegModel;
import com.kk.price.model.state;
import com.kk.price.second;
import com.kk.price.viewholder.petrol_viewholder;
import com.kk.price.viewholder.state_viewholder;
import com.kk.price.viewholder.veg_viewholder;

import org.jetbrains.annotations.NotNull;


public class Petrol extends Fragment {

    FirebaseRecyclerAdapter<Petrol_model, petrol_viewholder> firebaseRecyclerAdapter;
    private ShimmerFrameLayout shimmerFrameLayout;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_petrol, container, false);
        shimmerFrameLayout=root.findViewById(R.id.shimmner);

        recyclerView = root.findViewById(R.id.petrolrec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        try {


            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference = firebaseDatabase.getReference("Data").child("petrol");
            FirebaseRecyclerOptions<Petrol_model> options = new FirebaseRecyclerOptions.Builder<Petrol_model>()
                    .setQuery(reference, Petrol_model.class).build();

            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Petrol_model, petrol_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public petrol_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.petrol_layouts), parent, false);

                    return new petrol_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull petrol_viewholder holder, int position, @NonNull @NotNull Petrol_model model) {

                    try {
                        recyclerView.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.setVisibility(View.INVISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (holder.getAdapterPosition() > -1) {
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
                        holder.itemView.setAnimation(animation);
                    }

                    holder.setdetails(getContext(), model.getName(), model.getPrice(), model.getChange());


                }

            };


            firebaseRecyclerAdapter.startListening();

            recyclerView.setAdapter(firebaseRecyclerAdapter);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return root;

    }


}