package com.kk.price;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kk.price.Adapter.FragmentAdapter1;
import com.kk.price.model.petrol;
import com.kk.price.viewholder.petrol_viewholder;

import org.jetbrains.annotations.NotNull;

public class second extends AppCompatActivity {

   public static String clicked;
    TabLayout tabLayout;
    public String kk;
    ViewPager2 pager2;

    FragmentAdapter1 adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        //

        try {
            Intent a = getIntent();
            clicked = a.getStringExtra("clicked");


        } catch (Exception e) {
            e.printStackTrace();
        }

      //  Toast.makeText(this, clicked, Toast.LENGTH_SHORT).show();

        try {
            tabLayout = findViewById(R.id.tab_layout);
            pager2 = findViewById(R.id.view_pager2);


            FragmentManager fm = getSupportFragmentManager();
            adapter = new FragmentAdapter1(fm, getLifecycle());




            pager2.setAdapter(adapter);


            tabLayout.addTab(tabLayout.newTab().setText("Vegetables"));
            tabLayout.addTab(tabLayout.newTab().setText("Fruits"));
            tabLayout.addTab(tabLayout.newTab().setText("Non-Veg"));


            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {


                    pager2.setCurrentItem(tab.getPosition());


                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


            pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {

                    tabLayout.selectTab(tabLayout.getTabAt(position));

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  Toast.makeText(this, clicked, Toast.LENGTH_SHORT).show();
    }
    public static String getClicked()
    {

        return clicked;
    }
}