package com.itaem.memodemo.Activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.itaem.memodemo.Fragment.HomePageFragment;
import com.itaem.memodemo.Fragment.MainFragment;
import com.itaem.memodemo.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "onCreate: Activity");
        initView();
    }

    /**
     * 加载碎片
     */
    private void initView() {
/*        // 获取碎片管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 往容器添加碎片，并提交
        fragmentManager.beginTransaction().add(R.id.fragmentContainerView,new MainFragment()).commit();*/
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int currentNightMode = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is active, we're using dark theme
            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is not active, we're using the light theme
                recreate();
                break;
        }
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_VISIBLE|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//实现状态栏图标和文字颜色为白色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//实现状态栏图标和文字颜色为暗色
        }
    }
}