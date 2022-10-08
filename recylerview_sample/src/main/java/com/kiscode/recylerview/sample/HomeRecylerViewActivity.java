package com.kiscode.recylerview.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class HomeRecylerViewActivity extends AppCompatActivity {

    private String[] menuOptions = {"Simple Demo", "GridLayoutManager"
            , "MutipleTypeViewRecyclerActivity"
            , "CommanAapterDemoActivity"
            , "CommanMutipleAdapterDemoActivity"
            , "StickHeaderRecylerViewActivity"
            , "MutipleTypeGridRecyclerActivity"
            , "CommanWithEmptyAapterDemoActivity"
            , "RecyclerView Linkage"
            , "Cache Last Position"
    };

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_recyler_view);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recylerView_home);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置分割线 DividerItemDecoration为系统自带分割线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //设置分割线drawable，可自定义样式
        itemDecoration.setDrawable(getDrawable(R.drawable.shape_divider));
        recyclerView.addItemDecoration(itemDecoration);
        MyAdapter adapter = new MyAdapter(this, Arrays.asList(menuOptions));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("onItemClick", "onItemClick:" + position);
                switch (position) {
                    case 0:
                        // RecylerView基本用法demo
                        startActivity(new Intent(HomeRecylerViewActivity.this, RecylerViewSampleActivity.class));
                        break;
                    case 1:
                        // RecylerView基本用法demo
                        startActivity(new Intent(HomeRecylerViewActivity.this, GridLayoutActivity.class));
                        break;
                    case 2:
                        // RecylerView基本用法demo
                        startActivity(new Intent(HomeRecylerViewActivity.this, MutipleTypeViewRecyclerActivity.class));
                        break;
                    case 3:
                        // CommanAapter 用法demo
                        startActivity(new Intent(HomeRecylerViewActivity.this, CommanAapterDemoActivity.class));
                        break;
                    case 4:
                        // CommanAapter 用法demo
                        startActivity(new Intent(HomeRecylerViewActivity.this, CommanMutipleAdapterDemoActivity.class));
                        break;
                    case 5:
                        // RecylerView磁性吸附header
                        startActivity(new Intent(HomeRecylerViewActivity.this, StickHeaderRecylerViewActivity.class));
                        break;
                    case 6:
                        // 多Type Grid类型的 RecyclerView
                        startActivity(new Intent(HomeRecylerViewActivity.this, MutipleTypeGridRecyclerActivity.class));
                        break;
                    case 7:
                        // EmptyView的Comman adapter
                        startActivity(new Intent(HomeRecylerViewActivity.this, CommanWithEmptyAdapterActivity.class));
                        break;
                    case 8:
                        // Recyclerview联动
                        startActivity(new Intent(HomeRecylerViewActivity.this, LinkageRecyclerViewActivity.class));
                        break;
                    case 9:
                        // 缓存上次位置
                        startActivity(new Intent(HomeRecylerViewActivity.this, CachePositionRecyclerviewActivity.class));
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.i("onItemLongClick", "onItemLongClick:" + position);

            }
        });

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHomeViewHolder> {
        private Context context;
        private List<String> mDatas;
        private OnItemClickLitener onItemClickLitener;

        public MyAdapter(Context context, List<String> mDatas) {
            this.context = context;
            this.mDatas = mDatas;
        }

        @NonNull
        @Override
        public MyHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHomeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHomeViewHolder holder, final int position) {
            holder.tvText.setText(mDatas.get(position));

            if (onItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickLitener.onItemClick(view, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        onItemClickLitener.onItemLongClick(view, position);
                        return true;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
            this.onItemClickLitener = onItemClickLitener;
        }

        public interface OnItemClickLitener {
            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }

        private static class MyHomeViewHolder extends RecyclerView.ViewHolder {
            TextView tvText;

            public MyHomeViewHolder(@NonNull View itemView) {
                super(itemView);
                tvText = itemView.findViewById(R.id.tv_text_item);
            }
        }
    }

}
