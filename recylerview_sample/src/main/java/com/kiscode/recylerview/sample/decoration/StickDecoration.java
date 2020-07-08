package com.kiscode.recylerview.sample.decoration;


import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/****
 * ProjectName: AndroidUISample
 * Package: com.kiscode.recylerview.sample.decoration
 * ClassName: StickDecoration
 * Description: TODO
 * Author:  Administrator
 * CreateDate: 2020/7/8 21:52
 */
public class StickDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "StickDecoration";
    private View mStickView;

    /***
     * 当Recylcer滚动时触发
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int firstVisibleItemPos = linearLayoutManager.findFirstVisibleItemPosition();
        Log.i(TAG, "firstVisibleItemPos:" + firstVisibleItemPos);

    }
}
