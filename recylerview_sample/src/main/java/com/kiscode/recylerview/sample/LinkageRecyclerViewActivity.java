package com.kiscode.recylerview.sample;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

import com.kiscode.recylerview.sample.adapter.LinkageGridAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * 展示Recyclerview文字选择框
 * 1. 根据RecyclerView宽度动态计算宽高
 * 2. 不同Recyclerview联动
 */
public class LinkageRecyclerViewActivity extends AppCompatActivity {
    private static final int SIZE_DIVIDER = 10;
    private static final int COUNT_SPAN_GRID = 6;
    private boolean isResetItemWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkage_recyclerview);

        initView();
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_option);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COUNT_SPAN_GRID));
        String dataSource = getString(R.string.text_letter_idiom);
        String[] arr = dataSource.split("");
        LinkageGridAdapter adapter = new LinkageGridAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        //设置动画
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setRemoveDuration(80);
        recyclerView.setItemAnimator(defaultItemAnimator);

        // 设置分割线
        recyclerView.addItemDecoration(new ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = SIZE_DIVIDER;
                outRect.bottom = SIZE_DIVIDER;
                outRect.left = SIZE_DIVIDER;
                outRect.right = SIZE_DIVIDER;
            }
        });

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!isResetItemWidth) {
                    isResetItemWidth = true;

                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
                    int recyclerWidth = recyclerView.getLayoutManager().getWidth()
                            - layoutParams.leftMargin
                            - layoutParams.rightMargin
                            - recyclerView.getPaddingStart()
                            - recyclerView.getPaddingEnd();
                    Log.i("onGlobalLayout", "width:" + recyclerWidth);
                    List<String> list = new ArrayList<>();
                    Collections.addAll(list, arr);
                    LinkageGridAdapter adapter = new LinkageGridAdapter(list, recyclerWidth / COUNT_SPAN_GRID - SIZE_DIVIDER * 2);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}