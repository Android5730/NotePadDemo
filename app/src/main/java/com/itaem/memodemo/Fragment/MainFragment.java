package com.itaem.memodemo.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayoutMediator;
import com.itaem.memodemo.R;
import com.itaem.memodemo.adapter.ViewPagerAdapter;
import com.itaem.memodemo.databinding.FragmentMainBinding;
import com.itaem.memodemo.viewModel.HomePageViewModel;


public class MainFragment extends Fragment {
    private static MainFragment fragment = new MainFragment();

    public MainFragment() {}
    public static MainFragment getInstance(){
        return fragment;
    }
    private FragmentMainBinding binding;
    private HomePageViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(HomePageViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        initView();
        initTouch();
        Log.d("TAG", "onCreateView: MainFragment");
        return binding.getRoot();
    }



    private void initView(){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.searchView.getWindowToken(), 0);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewPager2.setAdapter(viewPagerAdapter);
        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_homePage:
                        binding.viewPager2.setCurrentItem(0);
                        Toast.makeText(getContext(),"切换至首页",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
    }
    private void initTouch() {

        // 焦点集中
        binding.searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){

                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_mainFragment_to_searchFragment);
                }
            }
        });


        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

}