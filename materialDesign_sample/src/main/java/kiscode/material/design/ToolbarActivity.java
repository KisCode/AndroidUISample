package kiscode.material.design;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class ToolbarActivity extends AppCompatActivity {
    private static final String TAG = "ToolbarActivity";
    private Toolbar toolbarHead;
    private Toolbar toolbarMenu;
    private Toolbar toolbarSearch;

    public static void start(Context context) {
        Intent starter = new Intent(context, ToolbarActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        toolbarHead = findViewById(R.id.toolbar_head);
        toolbarMenu = findViewById(R.id.toolbar_menu);
        toolbarSearch = findViewById(R.id.toolbar_search);

        setSupportActionBar(toolbarHead);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //显示向上返回
        getSupportActionBar().setDisplayShowHomeEnabled(true);      //显示返回按钮

        toolbarMenu.inflateMenu(R.menu.menu_toolbar);

        //通过inflateMenu方式加载 带有SearchView的菜单
        toolbarSearch.inflateMenu(R.menu.menu_toolbar_with_search);
//        initToolBarSearchView(toolbarSearch);
    }

    private void initToolBarSearchView(Toolbar toolbar) {
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
        searchView.setQueryHint(getString(R.string.action_search));
        
        //设置输入框文字颜色
        SearchView.SearchAutoComplete editText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        editText.setHintTextColor(ContextCompat.getColor(this, R.color.white));
        editText.setTextColor(ContextCompat.getColor(this, R.color.white));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG,"beforeTextChanged:"+s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG,"afterTextChanged:"+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG,"afterTextChanged:"+s);
            }
        });

        //设置搜索按钮颜色
        ImageView ivSearch = searchView.findViewById(androidx.appcompat.R.id.search_button);
        ivSearch.setColorFilter(ContextCompat.getColor(this, R.color.white));

        //设置关闭按钮颜色
        ImageView ivClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        ivClose.setColorFilter(ContextCompat.getColor(this, R.color.white));
        
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //点击返回按钮监听
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}