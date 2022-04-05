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
import com.kk.price.model.state;
import com.kk.price.second;
import com.kk.price.viewholder.state_viewholder;


import org.jetbrains.annotations.NotNull;


public class FirstFragment extends Fragment {

    FirebaseRecyclerAdapter<state, state_viewholder> firebaseRecyclerAdapter;
    RecyclerView recyclerView;
    String clicked_pro;
    private ShimmerFrameLayout shimmerFrameLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_first, container, false);

        shimmerFrameLayout=root.findViewById(R.id.shimmner);
        recyclerView = root.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        try {


            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference = firebaseDatabase.getReference("Data").child("StateName");
            FirebaseRecyclerOptions<state> options = new FirebaseRecyclerOptions.Builder<state>()
                    .setQuery(reference, state.class).build();

            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<state, state_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public state_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.state_list_iteam), parent, false);

                    return new state_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull state_viewholder holder, int position, @NonNull @NotNull state model) {

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

                    CardView cardView=holder.itemView.findViewById(R.id.state_card);

                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clicked_pro = getRef(position).getKey();
                            Intent i=new Intent(getContext(), second.class);
                            i.putExtra("clicked",clicked_pro);
                            startActivity(i);


                            //Toast.makeText(getContext(), clicked_pro, Toast.LENGTH_SHORT).show();
                        }
                    });


                    holder.setdetails(getContext(), model.getName(), model.getCode());


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