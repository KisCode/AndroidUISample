package com.kiscode.recylerview.sample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.comman.CommanViewHolder;
import com.kiscode.recylerview.sample.comman.CommanWithEmptyAdapter;
import com.kiscode.recylerview.sample.mock.MockApi;

import java.util.Collections;
import java.util.List;

public class CommanWithEmptyAdapterActivity extends AppCompatActivity {
    private CommanWithEmptyAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comman_apter_demo);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加入指定菜单
        getMenuInflater().inflate(R.menu.menu_recylerview, menu);
        // 返回true才会显示Menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_item:
//                mAdapter.addItem(1);
                mAdapter.setNewDatas(MockApi.getMockDatas().subList(0,3));
                break;
            case R.id.menu_remove_item:
                if(!mAdapter.getDatas().isEmpty()){
                    mAdapter.getDatas().remove(0);
                    mAdapter.notifyItemRemoved(0);
                }
                break;
        }
        return true;
    }


    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerView_comman);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> datas = MockApi.getMockDatas().subList(0,3);
        mAdapter = new CommanWithEmptyAdapter<String>(datas, R.layout.item_text_layout) {
            @Override
            public void convert(CommanViewHolder holder, int pos) {
                holder.setText(R.id.tv_text_item, mDatas.get(pos));
            }
        };
        mAdapter.setOnItemClickListener((adapter, pos) ->
                Toast.makeText(this, "click:" + adapter.getItem(pos), Toast.LENGTH_SHORT).show());
        mAdapter.bindToRecyclerView(recyclerView);
        mAdapter.setEmptyView(R.layout.item_empty_view);
    }
} 