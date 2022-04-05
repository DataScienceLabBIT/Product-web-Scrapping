package com.kk.price.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1:


                return new com.kk.price.Fragment.Petrol();


            case 2:
                return new com.kk.price.Fragment.SecondFragment();

        }

        return new com.kk.price.Fragment.FirstFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
