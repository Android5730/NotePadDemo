package com.itaem.memodemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.itaem.memodemo.Fragment.HomePageFragment;
import com.itaem.memodemo.Fragment.MainFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull MainFragment fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     *指明tab与Fragment对应的关系，并返回Fragment
     *@paramposition
     *@return
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomePageFragment();
            case 1:
                return null;
            default:
                return null;
        }
    }
    /**
     *几个页面
     *@return
     */
    @Override
    public int getItemCount() {
        return 1;
    }
}
