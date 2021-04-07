package com.kiscode.toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

/**
 * Description: 带有SearchView的Toolbar示例
 * Author: keno
 * Date : 2021/4/6 22:58
 **/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initViews();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar_home);
        //加载菜单
        toolbar.inflateMenu(R.menu.menu_home);

        initSearchView(toolbar);

    }

    private void initSearchView(Toolbar toolbar) {

        //从menu中查找对应Searchviews
        SearchView searchView = toolbar.findViewById(R.id.action_menu_search);
        //搜索图标是否显示在搜索框内
        searchView.setIconifiedByDefault(true);
        //设置搜索框展开时是否显示提交按钮，可不显示
        searchView.setSubmitButtonEnabled(false);
        //让键盘的回车键设置成搜索
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        //搜索框是否展开，false表示展开
        searchView.setIconified(true);
        //获取焦点
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();
        //设置提示词
        searchView.setQueryHint(getString(R.string.hint_search));
        //设置输入框文字颜色
        SearchView.SearchAutoComplete editText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        editText.setHintTextColor(ContextCompat.getColor(this, R.color.teal_200));
        editText.setTextColor(ContextCompat.getColor(this, R.color.white));

        //设置搜索按钮颜色
        ImageView ivSearch = searchView.findViewById(androidx.appcompat.R.id.search_button);
        ivSearch.setColorFilter(ContextCompat.getColor(this, R.color.white));

        //设置关闭按钮颜色
        ImageView ivClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        ivClose.setColorFilter(ContextCompat.getColor(this, R.color.white));
    }
}