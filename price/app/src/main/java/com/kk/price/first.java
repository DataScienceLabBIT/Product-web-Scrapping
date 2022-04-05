package com.kk.price;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kk.price.Adapter.FragmentAdapter;
import com.kk.price.model.state;
import com.kk.price.viewholder.state_viewholder;
import com.razorpay.Checkout;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class first extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    private ImageView menu;
    private Dialog dialog;
    String sharemessage;


    Animation roate;

    public void rotate(ImageView img) {
        roate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        img.startAnimation(roate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        try {
            FirebaseMessaging.getInstance().subscribeToTopic("price")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                // Toast.makeText(first.this, "don", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        } catch (Exception e) {
            // Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        dialog = new Dialog(this);
        menu = findViewById(R.id.menus);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    rotate(menu);
                    Window window = dialog.getWindow();
                    window.getAttributes().windowAnimations = R.style.Dialogani;
                    window.setGravity(Gravity.END | Gravity.TOP);
                    dialog.setContentView(R.layout.menu_dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            dialog.show();

                        }
                    }, 500);

                    TextView in, wh, ru, sh;



                    try {


                        sharemessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n\nMr.Programmmer";


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    in = dialog.findViewById(R.id.insta);
                    wh = dialog.findViewById(R.id.what);
                    ru = dialog.findViewById(R.id.support);
                    sh = dialog.findViewById(R.id.share);

                    sh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");

                                intent.putExtra(Intent.EXTRA_TEXT, sharemessage);
                                startActivity(Intent.createChooser(intent, "Share by"));
                                Toast.makeText(first.this, "Thanks for Sharing !", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } catch (Exception e) {
                                Toast.makeText(first.this, "Error !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    ru.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                funds("50");
                                dialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                    in.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.instagram.com/thug__rider/";
                            Intent insta = new Intent(Intent.ACTION_VIEW);
                            insta.setData(Uri.parse(url));
                            startActivity(insta);
                            dialog.dismiss();
                        }
                    });

                    wh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String number = "+917548882167";
                            String url = "https://api.whatsapp.com/send?phone=" + number;


                            Intent whatsapp = new Intent(Intent.ACTION_VIEW);
                            whatsapp.setData(Uri.parse(url));
                            startActivity(whatsapp);
                            dialog.dismiss();
                        }
                    });


                } catch (Exception e) {
                    Toast.makeText(first.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });



        try {
            tabLayout = findViewById(R.id.tab_layout);
            pager2 = findViewById(R.id.view_pager2);


            FragmentManager fm = getSupportFragmentManager();
            adapter = new FragmentAdapter(fm, getLifecycle());



            pager2.setAdapter(adapter);



            tabLayout.addTab(tabLayout.newTab().setText("Price"));
            tabLayout.addTab(tabLayout.newTab().setText("Petrol"));
            tabLayout.addTab(tabLayout.newTab().setText("Horoscope"));


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
    }

    public void funds(String price) throws JSONException {

        try {
            int pr = Integer.parseInt(price);
            pr = pr * 100;

            Checkout checkout = new Checkout();
            checkout.setKeyID("rzp_live_DQNBfqiiJI7WzZ");
            JSONObject object = new JSONObject();
            object.put("description", "Mr.Price");
            object.put("currency", "INR");
            object.put("amount", pr);

            checkout.open(first.this, object);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}