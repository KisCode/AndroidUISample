package com.kiscode.recylerview.sample.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:  GridLayoutManager RecylerView的悬停头
 *
 * Author: keno
 * Date : 2021/1/12 17:24
 **/
public class StickHeaderGridDecoration extends StickHeaderDecoration {
    private GridLayoutManager.SpanSizeLookup lookup;
    private int spanCount;

    /***
     *
     * @param stickViewType 吸附View的Type
     */
    public StickHeaderGridDecoration(int stickViewType) {
        super(stickViewType);
    }

    public StickHeaderGridDecoration(int stickViewType, int spanCount) {
        super(stickViewType);
        this.spanCount = spanCount;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull final RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (lookup == null) {
            lookup = new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = parent.getAdapter().getItemViewType(position);
                    if (mStickViewType == type) {
                        return spanCount;
                    }
                    return 1;
                }
            };
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
            gridLayoutManager.setSpanSizeLookup(lookup);
        }
    }
}