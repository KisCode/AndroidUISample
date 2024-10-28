package com.kiscode.stackviewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.kiscode.stackviewpager.adapter.CardFragmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Author: keno
 * Date : 2021/2/20 13:57
 **/
public class StackViewpagerActivity extends AppCompatActivity {
    private static final String TAG = "StackViewpager";
    private static final int SIZE_OFFSET = 40;
    final String[] contentArr = {"One", "Two", "Three", "Four", "Five", "Six"};
    private boolean mIsRandom = true;
    private float actionDownX, actionDownY;
    private ViewPager viewPager;
    private CardFragmentAdapter adapter;

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
        viewPager = findViewById(R.id.viewpager_stack);
        viewPager.setOffscreenPageLimit(5);
        adapter = new CardFragmentAdapter(getSupportFragmentManager(), Arrays.asList(contentArr));
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "position:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                //position 参数表示指定页面相对于屏幕中心的位置。
                // 它是一个动态属性，会随着用户滚动浏览页面而变化。当页面填满整个屏幕时，其位置值为 0。 当页面刚刚离开屏幕右侧时，其位置值为 1。如果用户在第一页和第二页之间滚动到一半，则第一页的位置为 -0.5，第二页的位置为 0.5。
//                page.setAlpha(0.5f);
                if (position <= 0.0f) {
                    //当position <=0 表示当前页在翻页
                    page.setTranslationX(0f);
                } else {
                    //设置每个View在中间，即设置相对原位置偏移量
                    page.setTranslationX((-page.getWidth() * position));
                    page.setTranslationY(SIZE_OFFSET * position);

                    if (page.getWidth() > 0) {
                        //缩放比例
                        float scale = (page.getWidth() - SIZE_OFFSET * position) / (float) page.getWidth();

                        Log.i("transformPageScale", "scale:" + scale + "\tposition:" + position + "\tpage.getWidth()\t" + page.getWidth());
                        page.setScaleX(scale);
                        page.setScaleY(scale);
                    }

                }

                Log.i("transformPage", "position:" + position);
            }
        });

        viewPager.setOnTouchListener((v, event) -> {
            if (viewPager.getCurrentItem() < adapter.getCount() - 1) {
                return false;
            }

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                actionDownX = event.getX();
                actionDownY = event.getY();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (actionDownX - event.getX() > 50) { //向左滑
                    Log.i(TAG, actionDownX + "\t" + actionDownY + "\tevent:\tx = " + event.getX() + ", y = " + event.getY());
//                    Toast.makeText(StackViewpagerActivity.this, "Detect left scroll...", Toast.LENGTH_SHORT).show();
                    reloadData();
                    return true;
                }
            }
            return false;
        });

        viewPager.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void reloadData() {
        List<String> list = new ArrayList<>();
        if (mIsRandom) {
            int startNum = new Random().nextInt(90);
            for (int i = startNum; i < startNum + 6; i++) {
                list.add(String.valueOf(i));
            }
            mIsRandom = false;
        } else {
            list = Arrays.asList(contentArr);
            mIsRandom = true;
        }

        adapter.setNewDatas(list);
        viewPager.setCurrentItem(0);
    }
}