package com.keno.switchbutton;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Description: 开关按钮使用示例
 * Author: keno
 * Date : 2021/1/12 18:11
 **/
public class MainActivity extends AppCompatActivity {
    private Switch switchPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        switchPermission = findViewById(R.id.switch_permission);
        switchPermission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.i("MainActivity", "onCheckedChanged:" + b);
            }
        });
    }
}