package com.kiscode.recylerview.sample;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.comman.CommanMutipleAdapter;
import com.kiscode.recylerview.sample.comman.CommanViewHolder;
import com.kiscode.recylerview.sample.comman.MutipleItemSupport;
import com.kiscode.recylerview.sample.decoration.StickDecoration;
import com.kiscode.recylerview.sample.decoration.StickHeaderDecoration;
import com.kiscode.recylerview.sample.decoration.StickHeaderGridDecoration;
import com.kiscode.recylerview.sample.mock.MockApi;

public class StickHeaderRecylerViewActivity extends AppCompatActivity {

    private static final int TYPE_HEAD = 11;
    private static final int TYPE_TEXT = 595;
    private RecyclerView recyclerviewStick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick_header_recyler_view);
        initView();
    }

    private void initView() {
        recyclerviewStick = findViewById(R.id.recyclerview_stick);

        //线性布局悬停头 单列ListView风格悬停头
        recyclerviewStick.setLayoutManager(new LinearLayoutManager(this));
        StickHeaderDecoration itemDecoration = new StickHeaderDecoration(TYPE_HEAD);

/*        //grid 悬停头部 GridView风格悬停头
        recyclerviewStick.setLayoutManager(new GridLayoutManager(this, 2));
        StickHeaderDecoration itemDecoration = new StickHeaderGridDecoration(TYPE_HEAD, 2);
        */

        recyclerviewStick.addItemDecoration(itemDecoration);

//        recyclerviewStick.setAdapter(new MutipleTypeViewStickAdapter(this, MockApi.getMockNumberDatas()));
        final MutipleItemSupport mutipleItemSupport = new MutipleItemSupport<String>() {
            @Override
            public int getItemLayout(int itemType) {
                if (TYPE_HEAD == itemType) {
                    return R.layout.item_head_layout;
                } else {
                    return R.layout.item_text_layout;
                }
            }

            @Override
            public int getItemViewType(int position) {
                if (position % 15 == 0) {
                    return TYPE_HEAD;
                } else {
                    return TYPE_TEXT;
                }
            }
        };
        recyclerviewStick.setAdapter(new CommanMutipleAdapter<String>(this, MockApi.getMockNumberDatas(), mutipleItemSupport) {
            @Override
            public void convert(@NonNull CommanViewHolder holder, int pos) {
                Log.i("kkk", "ItemViewType:" + holder.getItemViewType());
                if (TYPE_HEAD == holder.getItemViewType()) {
                    holder.itemView.setTag(true);
                    holder.setText(R.id.tv_head_item, mDatas.get(pos));
                } else if (TYPE_TEXT == holder.getItemViewType()) {
                    holder.itemView.setTag(false);
                    holder.setText(R.id.tv_text_item, mDatas.get(pos));
                } else {
                    if (this.getItemViewType(pos) == TYPE_HEAD) {
                        holder.itemView.setTag(true);
                        holder.setText(R.id.tv_head_item, mDatas.get(pos));
                    }
                }
            }
        });
    }

}