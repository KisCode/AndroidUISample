package com.kiscode.recylerview.sample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.adapter.MutipleTypeViewAdapter;
import com.kiscode.recylerview.sample.comman.CommanMutipleAdapter;
import com.kiscode.recylerview.sample.comman.CommanViewHolder;
import com.kiscode.recylerview.sample.comman.MutipleItemSupport;
import com.kiscode.recylerview.sample.mock.MockApi;

import java.util.List;

public class CommanMutipleAdapterDemoActivity extends AppCompatActivity {

    private static final int TYPE_IMAGE = 592;
    private static final int TYPE_TEXT = 595;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comman_mutiple_adapter_demo);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recylerView_comman_mutiple);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<String> datas = MockApi.getMockDatas();
        final MutipleItemSupport mutipleItemSupport = new MutipleItemSupport<String>() {
            @Override
            public int getItemLayout(int itemType) {
                if (TYPE_IMAGE == itemType) {
                    return R.layout.item_image_layout;
                } else {
                    return R.layout.item_text_layout;
                }
            }

            @Override
            public int getItemViewType(int position) {
                if (position % 10 == 0) {
                    return TYPE_IMAGE;
                } else {
                    return TYPE_TEXT;
                }
            }
        };

        recyclerView.setAdapter(new CommanMutipleAdapter<String>(this, datas, mutipleItemSupport) {
            @Override
            public void convert(@NonNull CommanViewHolder holder, int pos) {
                if (mutipleItemSupport.getItemViewType(pos) == TYPE_IMAGE) {
                    holder.setImageResource(R.id.iv_img_item, R.mipmap.banner);
                } else {
                    holder.setText(R.id.tv_text_item, mDatas.get(pos));
                }
            }
        });
    }
}
