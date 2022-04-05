package com.kk.price.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kk.price.R;
import com.kk.price.model.Beefs;
import com.kk.price.model.Egg;
import com.kk.price.second;
import com.kk.price.viewholder.egg_viewholder;

import org.jetbrains.annotations.NotNull;


public class NonFragment extends Fragment {


    String click;
    FirebaseRecyclerAdapter<Egg, egg_viewholder> firebaseRecyclerAdapter;
    FirebaseRecyclerAdapter<Beefs, egg_viewholder> firebaseRecyclerAdapter1;

    RecyclerView eggs,rebeff,remtn,chi,chicken;

    ShimmerFrameLayout shimmerFrameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_non, container, false);

        shimmerFrameLayout=root.findViewById(R.id.shimmner);
        eggs=root.findViewById(R.id.reegg);
        eggs.setHasFixedSize(true);
        eggs.setLayoutManager(new GridLayoutManager(getContext(), 2));


        rebeff=root.findViewById(R.id.rebeff);
        rebeff.setHasFixedSize(true);
        rebeff.setLayoutManager(new GridLayoutManager(getContext(), 2));


        remtn=root.findViewById(R.id.remtn);
        remtn.setHasFixedSize(true);
        remtn.setLayoutManager(new GridLayoutManager(getContext(), 2));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        chi=root.findViewById(R.id.rechi);
        chi.setHasFixedSize(true);
        chi.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        chicken=root.findViewById(R.id.chicken);
        chicken.setHasFixedSize(true);
        chicken.setLayoutManager(linearLayoutManager1);

        try {
            second s = new second();
            click = s.getClicked();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {


           // Toast.makeText(getContext(), "in", Toast.LENGTH_SHORT).show();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference = firebaseDatabase.getReference("Data").child("Egg").child(click);
            FirebaseRecyclerOptions<Egg> options = new FirebaseRecyclerOptions.Builder<Egg>()
                    .setQuery(reference, Egg.class).build();



            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Egg, egg_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public egg_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.egg), parent, false);

                    return new egg_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull egg_viewholder holder, int position, @NonNull @NotNull Egg model) {


                    if (holder.getAdapterPosition() > -1) {
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
                        holder.itemView.setAnimation(animation);
                    }

                    holder.setdetails(getContext(), model.getDate(), model.getPrice());




                }

            };


            firebaseRecyclerAdapter.startListening();

            eggs.setAdapter(firebaseRecyclerAdapter);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {


            // Toast.makeText(getContext(), "in", Toast.LENGTH_SHORT).show();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference1 = firebaseDatabase.getReference("Data").child("nonveg").child(click).child("beff");
            FirebaseRecyclerOptions<Beefs> options = new FirebaseRecyclerOptions.Builder<Beefs>()
                    .setQuery(reference1, Beefs.class).build();



            firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<Beefs, egg_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public egg_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.beef), parent, false);

                    return new egg_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull egg_viewholder holder, int position, @NonNull @NotNull Beefs model) {


                    if (holder.getAdapterPosition() > -1) {
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
                        holder.itemView.setAnimation(animation);
                    }

                    holder.setdetails(getContext(), model.getName(), model.getPrice());




                }

            };


            firebaseRecyclerAdapter1.startListening();

            rebeff.setAdapter(firebaseRecyclerAdapter1);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

///
        try {


            // Toast.makeText(getContext(), "in", Toast.LENGTH_SHORT).show();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference1 = firebaseDatabase.getReference("Data").child("nonveg").child(click).child("mutton");
            FirebaseRecyclerOptions<Beefs> options = new FirebaseRecyclerOptions.Builder<Beefs>()
                    .setQuery(reference1, Beefs.class).build();



            firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<Beefs, egg_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public egg_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.mtn), parent, false);

                    return new egg_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull egg_viewholder holder, int position, @NonNull @NotNull Beefs model) {




                    if (holder.getAdapterPosition() > -1) {
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
                        holder.itemView.setAnimation(animation);
                    }

                    holder.setdetails(getContext(), model.getName(), model.getPrice());




                }

            };


            firebaseRecyclerAdapter1.startListening();

            remtn.setAdapter(firebaseRecyclerAdapter1);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        try {


            // Toast.makeText(getContext(), "in", Toast.LENGTH_SHORT).show();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference2 = firebaseDatabase.getReference("Data").child("nonveg").child(click).child("chicken");
            FirebaseRecyclerOptions<Beefs> options = new FirebaseRecyclerOptions.Builder<Beefs>()
                    .setQuery(reference2, Beefs.class).build();



            firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<Beefs, egg_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public egg_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.ch), parent, false);

                    return new egg_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull egg_viewholder holder, int position, @NonNull @NotNull Beefs model) {


                    if (holder.getAdapterPosition() > -1) {
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
                        holder.itemView.setAnimation(animation);
                    }


                    holder.setdetails(getContext(), model.getName(), model.getPrice());




                }

            };


            firebaseRecyclerAdapter1.startListening();

            chicken.setAdapter(firebaseRecyclerAdapter1);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {


            // Toast.makeText(getContext(), "in", Toast.LENGTH_SHORT).show();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference2 = firebaseDatabase.getReference("Data").child("nonveg").child(click).child("fish");
            FirebaseRecyclerOptions<Beefs> options = new FirebaseRecyclerOptions.Builder<Beefs>()
                    .setQuery(reference2, Beefs.class).build();



            firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<Beefs, egg_viewholder>(options) {


                @NonNull
                @NotNull
                @Override
                public egg_viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.chic), parent, false);

                    return new egg_viewholder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull egg_viewholder holder, int position, @NonNull @NotNull Beefs model) {


                    try {
                        ScrollView relativeLayout=root.findViewById(R.id.mains);
                        relativeLayout.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.setVisibility(View.INVISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (holder.getAdapterPosition() > -1) {
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
                        holder.itemView.setAnimation(animation);
                    }


                    holder.setdetails(getContext(), model.getName(), model.getPrice());




                }

            };


            firebaseRecyclerAdapter1.startListening();

            chi.setAdapter(firebaseRecyclerAdapter1);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return root;

    }


}