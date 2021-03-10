package com.kiscode.stackviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_stack_viewpager_activity).setOnClickListener(this);
        findViewById(R.id.btn_stack_viewpager_fragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stack_viewpager_activity:
                startActivity(new Intent(this, StackViewpagerActivity.class));
                break;
            case R.id.btn_stack_viewpager_fragment:
                startActivity(new Intent(this, StackViewpagerFragmentActivity.class));
                break;
        }
    }
}