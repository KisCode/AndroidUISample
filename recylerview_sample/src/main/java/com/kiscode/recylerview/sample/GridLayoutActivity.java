package com.kiscode.recylerview.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.adapter.GridAdapter;
import com.kiscode.recylerview.sample.decoration.GridDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: GridViewlayout
 * Author: keno
 * CreateDate: 2020/2/13 21:13
 */

public class GridLayoutActivity extends AppCompatActivity {

    private RecyclerView recylerViewGrid;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        initDatas();
        initView();
    }

    private void initView() {
        recylerViewGrid = (RecyclerView) findViewById(R.id.recylerView_grid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recylerViewGrid.addItemDecoration(new GridDividerItemDecoration());
        //设置item不同的宽度
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 5 == 0) {
                    return 2;    //每个元素占2列，则每行显示2个元素
                }
                return 1;
            }
        });
        recylerViewGrid.setLayoutManager(gridLayoutManager);
        recylerViewGrid.setAdapter(new GridAdapter(this, mDatas));

    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("No." + i);
        }
    }
}
