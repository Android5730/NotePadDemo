package com.itaem.memodemo.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayoutMediator;
import com.itaem.memodemo.R;
import com.itaem.memodemo.adapter.ViewPagerAdapter;


public class MainFragment extends Fragment {
    private static MainFragment fragment = new MainFragment();

    public MainFragment() {}
    public static MainFragment getInstance(){
        return fragment;
    }

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        Log.d("TAG", "onCreateView: MainFragment");
        return view;
    }
    private void initView(){
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        BottomNavigationView bottomNav = view.findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_homePage:
                        viewPager2.setCurrentItem(0);
                        Toast.makeText(getContext(),"切换至首页",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
    }
}