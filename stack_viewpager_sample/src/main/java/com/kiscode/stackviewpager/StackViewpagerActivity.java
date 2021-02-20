package com.kiscode.stackviewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.kiscode.stackviewpager.adapter.CardFragmentAdapter;

import java.util.Arrays;

/**
 * Description:
 * Author: keno
 * Date : 2021/2/20 13:57
 **/
public class StackViewpagerActivity extends AppCompatActivity {

    private static final int SIZE_OFFSET = 40;
    private ViewPager viewpagerStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_viewpager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        String[] contentArr = {"One", "Two", "Three", "Four", "Five", "Six"};
        viewpagerStack = findViewById(R.id.viewpager_stack);
        viewpagerStack.setOffscreenPageLimit(5);
        viewpagerStack.setAdapter(new CardFragmentAdapter(getSupportFragmentManager(), Arrays.asList(contentArr)));

        viewpagerStack.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
//                page.setAlpha(0.5f);
                if (position <= 0.0f) {
                    //当position <=0 表示当前页在翻页
                    page.setTranslationX(0f);
                } else {
                    //设置每个View在中间，即设置相对原位置偏移量
                    page.setTranslationX((-page.getWidth() * position));
                    //缩放比例
                    float scale = (page.getWidth() - SIZE_OFFSET * position) / (float) page.getWidth();
                    page.setScaleX(scale);
                    page.setScaleY(scale);

                    page.setTranslationY(SIZE_OFFSET * position);
                }

                Log.i("transformPage", "position:" + position);
            }
        });
    }
}