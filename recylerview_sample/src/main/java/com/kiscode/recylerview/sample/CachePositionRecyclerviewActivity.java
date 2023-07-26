package com.kiscode.recylerview.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.comman.CommanWithEmptyAdapter;
import com.kiscode.recylerview.sample.comman.CommonViewHolder;
import com.kiscode.recylerview.sample.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
* Description: 缓存Recyclerview位置，下次进入滚动到上次位置
* Date : 2022/10/8 16:51
**/
public class CachePositionRecyclerviewActivity extends AppCompatActivity {
    private static final String KEY_LAST_CACHE_POSITION = "KEY_LAST_CACHE_POSITION";
    private static final String KEY_LAST_CACHE_TOP = "KEY_LAST_CACHE_TOP";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cachew_state_recyclervie);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview_option);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> list = getDatas();
        CommanWithEmptyAdapter<String> commanAdapter = new CommanWithEmptyAdapter<String>(list, R.layout.item_text_layout) {
            @Override
            public void convert(CommonViewHolder holder, int pos) {
                holder.setText(R.id.tv_text_item, mDatas.get(pos));
            }
        };
        commanAdapter.setOnItemClickListener((adapter, pos) ->
                Toast.makeText(this, "click:" + adapter.getItem(pos), Toast.LENGTH_SHORT).show());
        commanAdapter.bindToRecyclerView(recyclerView);
        commanAdapter.setEmptyView(R.layout.item_empty_view);

        recyclerView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Log.i("onScrollStateChanged", "RecyclerView完成绘制");
                        //在数据展示后滚动
                        recoveryRecyclerViewPosition();
                        recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //滚动结束
                    cachePosition(layoutManager);
                }
            }
        });
    }

    /**
     * 缓存RecyclerView位置
     *
     * @param layoutManager LinearLayoutManager
     */
    private void cachePosition(LinearLayoutManager layoutManager) {
        View firstChildView = layoutManager.getChildAt(0);
        if (firstChildView != null) {
            //当前第一个可见childView的位置
            int currentPosition = layoutManager.getPosition(firstChildView);
            //第一个可见childView距离屏幕上边距
            int firstTop = firstChildView.getTop();
            SpUtil.getInstance(CachePositionRecyclerviewActivity.this)
                    .putInt(KEY_LAST_CACHE_POSITION, currentPosition);
            SpUtil.getInstance(CachePositionRecyclerviewActivity.this)
                    .putInt(KEY_LAST_CACHE_TOP, firstTop);
            Log.i("onScrollStateChanged", "滚动停止，记录当前位置：" + currentPosition + "\t" + firstTop);
        }
    }


    /**
     * 恢复至上次RecyclerView的位置
     */
    private void recoveryRecyclerViewPosition() {
        int position = SpUtil.getInstance(CachePositionRecyclerviewActivity.this).getInt(KEY_LAST_CACHE_POSITION);
        int top = SpUtil.getInstance(CachePositionRecyclerviewActivity.this).getInt(KEY_LAST_CACHE_TOP);
        if (position == 0 && top == 0) {
            return;
        }
        if (recyclerView == null
                || recyclerView.getLayoutManager() == null
                || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            return;
        }

        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position, top);
    }

    private List<String> getDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            datas.add("No." + i);
        }
        return datas;
    }

}