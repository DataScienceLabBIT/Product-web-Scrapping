package com.kk.price.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kk.price.R;
import com.kk.price.model.Fruits;
import com.kk.price.model.VegModel;
import com.kk.price.second;
import com.kk.price.viewholder.veg_viewholder;

import org.jetbrains.annotations.NotNull;


public class FruitsFragment extends Fragment {


    String click;
    FirebaseRecyclerAdapter<Fruits, veg_viewholder> firebaseRecyclerAdapter;
    RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_fruits, container, false);

        shimmerFrameLayout=root.findViewById(R.id.shimmner);
        try {
            second s = new second();
            click = s.getClicked();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        recyclerView = root.findViewById(R.id.vegre);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        try {


            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference = firebaseDatabase.getReference("Data").child("Fruits").child(click);
            FirebaseRecyclerOptions<Fruits> options = new FirebaseRecyclerOptions.Builder<Fruits>()
                    .setQuery(reference, Fruits.class).build();

            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Fruits, veg_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public veg_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.veg_iteam), parent, false);

                    return new veg_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull veg_viewholder holder, int position, @NonNull @NotNull Fruits model) {

                    try {
                        recyclerView.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (holder.getAdapterPosition() > -1) {
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
                        holder.itemView.setAnimation(animation);
                    }

                    holder.setdetails(getContext(), model.getFruitsName(), model.getMarketPrice(), model.getRetailPrice(), model.getShoppingMall(), model.getImg());


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