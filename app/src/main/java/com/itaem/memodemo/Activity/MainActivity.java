package com.itaem.memodemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.itaem.memodemo.Fragment.HomePageFragment;
import com.itaem.memodemo.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 加载碎片
     */
    private void initView() {
        // 获取碎片管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 往容器添加碎片，并提交
        fragmentManager.beginTransaction().add(R.id.container_main,new HomePageFragment()).commit();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_VISIBLE|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//实现状态栏图标和文字颜色为白色
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//实现状态栏图标和文字颜色为暗色

    }

}