package com.kiscode.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kiscode.activity.base.BaseActivity;
import com.kiscode.activity.base.BaseToolbarActivity;
import com.kiscode.activity.single.AActivity;

public class HomeActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startToA(View view) {
        AActivity.start(this);
    }

    @Override
    protected boolean isVisibleBackIcon() {
        return false;
    }
}