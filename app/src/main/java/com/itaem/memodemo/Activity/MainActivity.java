package com.itaem.memodemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.itaem.memodemo.Fragment.HomePageFragment;
import com.itaem.memodemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniView();
    }

    /**
     * 加载碎片
     */
    private void iniView() {
        // 获取碎片管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 往容器添加碎片，并提交
        fragmentManager.beginTransaction().add(R.id.container_main,new HomePageFragment()).commit();
    }
}