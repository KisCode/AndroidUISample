package com.kiscode.recylerview.sample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.comman.CommanViewHolder;
import com.kiscode.recylerview.sample.comman.CommanWithEmptyAdapter;
import com.kiscode.recylerview.sample.mock.MockApi;

import java.util.Collections;
import java.util.List;

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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerView_comman);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
/*        CommanAdapter<String> commanAdapter = new CommanAdapter<String>(MockApi.getMockDatas(), R.layout.item_text_layout) {
            @Override
            public void convert(@NonNull CommanViewHolder holder, int postion) {
                holder.setText(R.id.tv_text_item, mDatas.get(postion));
            }
        };

        commanAdapter.setOnItemClickListener((adapter, pos) ->
                Toast.makeText(CommanAapterDemoActivity.this, "click:" + adapter.getItem(pos), Toast.LENGTH_SHORT).show());

        recyclerView.setAdapter(commanAdapter);
                */

        List<String> datas = Collections.emptyList();
        CommanWithEmptyAdapter<String> commanAdapter = new CommanWithEmptyAdapter<String>(datas, R.layout.item_text_layout) {
            @Override
            public void convert(CommanViewHolder holder, int pos) {
                holder.setText(R.id.tv_text_item, mDatas.get(pos));
            }
        };
        commanAdapter.setOnItemClickListener((adapter, pos) ->
                Toast.makeText(CommanAapterDemoActivity.this, "click:" + adapter.getItem(pos), Toast.LENGTH_SHORT).show());
        commanAdapter.bindToRecyclerView(recyclerView);
        commanAdapter.setEmptyView(R.layout.item_empty_view);
    }
}
