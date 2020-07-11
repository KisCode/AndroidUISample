package com.kiscode.recylerview.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.kiscode.recylerview.sample.decoration.StickDecoration;
import com.kiscode.recylerview.sample.decoration.StickyItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: About RecylerView
 * Author: keno
 * CreateDate: 2020/2/11 20:59
 */

public class RecylerViewSampleActivity extends AppCompatActivity {


    private List<String> mDatas;
    private RecyclerView recyclerView;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylerview_sample);
        initDatas();
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
                mAdapter.addItem(1);
                break;
            case R.id.menu_remove_item:
                mAdapter.removeItem(1);
                break;
        }
        return true;
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("No." + i + "-->" + (char) i);
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview_list_sample);

        //1. 设置布局管理器 LinearLayoutManager线性布局管理器，支持横向、纵向
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置布局管理器 GridLayoutManager网格布局管理
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        //设置布局管理器 GridLayoutManager网格布局管理
        //纵向 3列
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        //3行
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.HORIZONTAL));

        //2. 设置adapter
        mAdapter = new HomeAdapter();
        recyclerView.setAdapter(mAdapter);

      /*  //3. 设置分割线 DividerItemDecoration为系统自带分割线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //设置分割线drawable，可自定义样式
        itemDecoration.setDrawable(getDrawable(R.drawable.shape_divider));*/

        StickDecoration itemDecoration = new StickDecoration();
        recyclerView.addItemDecoration(itemDecoration);

        //4. 设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //显示item_layout根布局样式
            MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(RecylerViewSampleActivity.this).inflate(R.layout.item_text_layout, parent, false));
//            MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(RecylerViewSampleActivity.this).inflate(R.layout.item_text_layout, null));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            if (position % 5 == 0) {
                holder.itemView.setTag(true);
            }
            holder.tvText.setText(mDatas.get(position));
            holder.tvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(RecylerViewSampleActivity.this, mDatas.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public void addItem(int pos) {
            mDatas.add(pos, "inser New Item");
            notifyItemInserted(pos);
        }

        public void removeItem(int pos) {
            mDatas.remove(pos);
            notifyItemRemoved(pos);
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView tvText;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvText = itemView.findViewById(R.id.tv_text_item);
                itemView.setTag(false);
            }
        }
    }
}
