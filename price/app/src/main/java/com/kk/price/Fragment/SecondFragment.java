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
import com.kk.price.model.astro;
import com.kk.price.viewholder.astro_viewholder;

import org.jetbrains.annotations.NotNull;


public class SecondFragment extends Fragment {
    FirebaseRecyclerAdapter<astro, astro_viewholder> firebaseRecyclerAdapter;
    RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {





        View root = inflater.inflate(R.layout.fragment_second, container, false);

        shimmerFrameLayout=root.findViewById(R.id.shimmner);


        recyclerView = root.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        try {


            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference = firebaseDatabase.getReference("Data").child("astro");
            FirebaseRecyclerOptions<astro> options = new FirebaseRecyclerOptions.Builder<astro>()
                    .setQuery(reference, astro.class).build();

            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<astro, astro_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public astro_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.horoscope), parent, false);

                    return new astro_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull astro_viewholder holder, int position, @NonNull @NotNull astro model) {

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
                    if (holder.getAdapterPosition() > -1) {
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide);
                        holder.itemView.setAnimation(animation);
                    }
                    holder.setdetails(getContext(), model.getName(), model.getImg(),model.getMessage(),model.getLuckycolor(),model.getLuckynumber(),model.getRemedy());


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