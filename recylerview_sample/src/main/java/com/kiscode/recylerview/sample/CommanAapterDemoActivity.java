package com.kiscode.recylerview.sample;

import android.os.Bundle;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comman_apter_demo);
        initView();
    }

    private void initView() {
        RecyclerView recylerViewComman = (RecyclerView) findViewById(R.id.recylerView_comman);
        recylerViewComman.setLayoutManager(new LinearLayoutManager(this));
        CommanAdapter<String> commanAdapter = new CommanAdapter<String>(MockApi.getMockDatas(), R.layout.item_text_layout) {
            @Override
            public void convert(@NonNull CommanViewHolder holder, int postion) {
                holder.setText(R.id.tv_text_item, mDatas.get(postion));
            }
        };

        commanAdapter.setOnItemClickListener(new CommanAdapter.OnItemClickListener<String>() {
            @Override
            public void onClick(CommanAdapter<String> adapter, int pos) {
                Toast.makeText(CommanAapterDemoActivity.this, "click:" + adapter.getItem(pos), Toast.LENGTH_SHORT).show();
            }
        });

        recylerViewComman.setAdapter(commanAdapter);
    }
}
