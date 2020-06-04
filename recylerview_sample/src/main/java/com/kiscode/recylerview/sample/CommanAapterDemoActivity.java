package com.kiscode.recylerview.sample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.comman.CommanAdapter;
import com.kiscode.recylerview.sample.comman.CommanViewHolder;
import com.kiscode.recylerview.sample.mock.MockApi;

/**
 * Description: 通用Adapter的使用
 * Author: keno
 * CreateDate: 2020/2/17 9:57
 */

public class CommanAapterDemoActivity extends AppCompatActivity {

    private RecyclerView recylerViewComman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comman_apter_demo);
        initView();
    }

    private void initView() {
        recylerViewComman = (RecyclerView) findViewById(R.id.recylerView_comman);
        recylerViewComman.setLayoutManager(new LinearLayoutManager(this));

        recylerViewComman.setAdapter(new CommanAdapter<String>(this, MockApi.getMockDatas(), R.layout.item_text_layout) {
            @Override
            public void convert(@NonNull CommanViewHolder holder, int postion) {
                holder.setText(R.id.tv_text_item, mDatas.get(postion));
            }
        });
    }
}
