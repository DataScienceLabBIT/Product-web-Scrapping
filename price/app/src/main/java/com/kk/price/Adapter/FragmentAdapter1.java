package com.kk.price.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kk.price.Fragment.FruitsFragment;
import com.kk.price.Fragment.NonFragment;
import com.kk.price.Fragment.VegFragment;

public class FragmentAdapter1 extends FragmentStateAdapter {
    public FragmentAdapter1(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            case 1:


                return new FruitsFragment();


            case 2:


                return new NonFragment();




        }
        




        return new VegFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
