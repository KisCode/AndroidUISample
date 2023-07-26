package com.kiscode.activity.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.kiscode.activity.R;

public abstract class BaseToolbarActivity extends BaseActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base_toolbar);
        initToolbar();
        if (layoutResID != 0) {
            ViewGroup rootContentView = findViewById(R.id.fl_layout_content);
            setRootContentView(rootContentView, layoutResID);
        }
    }

    /**
     * 设置自定义页面布局
     *
     * @param root        页面根视图
     * @param layoutResID 自定义布局资源id
     */
    protected void setRootContentView(ViewGroup root, @LayoutRes int layoutResID) {
        View.inflate(this, layoutResID, root);
    }

    private void initToolbar() {
        ViewStub viewStub = findViewById(R.id.stub_title_bar);
        viewStub.setLayoutResource(getTitleBarLayoutId());
        Toolbar toolbar = (Toolbar) viewStub.inflate();
        setSupportActionBar(toolbar);
        boolean titleBackIconVisible = isVisibleBackIcon();
        getSupportActionBar().setDisplayHomeAsUpEnabled(titleBackIconVisible);
        getSupportActionBar().setHomeButtonEnabled(titleBackIconVisible);
    }

    /**
     * 是否显示返回按钮
     *
     * @return 默认true 显示
     */
    protected boolean isVisibleBackIcon() {
        return true;
    }

    protected int getTitleBarLayoutId() {
        return R.layout.base_title_bar;
    }
}