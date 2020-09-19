package com.kiscode.recylerview.sample.decoration;


import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/7/11 14:40
 */

public class StickDecoration extends RecyclerView.ItemDecoration {

    private static final int TYPE_HEAD = 11;
    private static final String TAG = "StickDecoration";

    private LinearLayoutManager mLayoutManager;
    //吸附
    private View mStickView;
    private float mStickyViewMarginTop;
    private boolean mFindStickView;

    private int mCurrentStickPos = -1;
    //记录需要吸附下标
    private List<Integer> mStickItemPositionList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.ViewHolder mViewHolder;

    public StickDecoration() {
        mStickItemPositionList = new ArrayList<>();
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
//        mStickItemPositionList.clear();
        int firstVisibleItemPos = mLayoutManager.findFirstVisibleItemPosition();
        int childCount = parent.getChildCount();
        Log.e(TAG, "firstVisibleItemPos:" + firstVisibleItemPos + ",childCount:" + childCount);
        if (mLayoutManager.findFirstVisibleItemPosition() == 0) {
            mStickItemPositionList.clear();
        }
        mFindStickView = false;

        //遍历查找 吸附的view
        for (int i = 0; i < childCount; i++) {
            int pos = firstVisibleItemPos + i;
            View childView = parent.getChildAt(i);
            Log.i(TAG, "childCount:" + i + ",getItemViewType:" + parent.getAdapter().getItemViewType(pos));
//            if (parent.getAdapter().getItemViewType(pos) == TYPE_HEAD) {
            if (childView.getTag() != null && (Boolean) childView.getTag()) {
                mFindStickView = true;
                saveStickPostion(pos);
                getStickyViewHolder(parent, pos);

                int stickViewHeight = mViewHolder.itemView.getBottom() - mViewHolder.itemView.getTop();
                if (childView.getTop() <= 0) {
                    mStickyViewMarginTop = 0;
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());
                } else {

                    Log.i("previousPostion", "mCurrentStickPos:" + mCurrentStickPos);
                    if (childView.getTop() <= stickViewHeight) {
                        //顶到上一个吸附控件
                        mStickyViewMarginTop = stickViewHeight - childView.getTop();

                        if (i + mLayoutManager.findFirstVisibleItemPosition() == mCurrentStickPos) {
                            int previousPostion = getPreviousViewPostion(parent);
                            Log.i("previousPostion2", "previousPostion:" + previousPostion);
                            if (mStickItemPositionList.contains(previousPostion)) {
                                bindDataForStickyView(previousPostion, parent.getMeasuredWidth());
                            }
                        }
                    }
                }
                drawStickyItemView(c);
            }
        }


        if (!mFindStickView) {
            mStickyViewMarginTop = 0;
            if (mLayoutManager.findFirstVisibleItemPosition() + parent.getChildCount() == parent.getAdapter().getItemCount() && mStickItemPositionList.size() > 0) {
                bindDataForStickyView(mStickItemPositionList.get(mStickItemPositionList.size() - 1), parent.getMeasuredWidth());
            }
            drawStickyItemView(c);
        }

    }

    private int getPreviousViewPostion(RecyclerView parent) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int firsrt = linearLayoutManager.findFirstVisibleItemPosition();
        int indexOfCurrentPos = mStickItemPositionList.indexOf(mCurrentStickPos);
        int indexOfPreviousPos = indexOfCurrentPos - 1;
        if (indexOfPreviousPos >= 0) {
            return mStickItemPositionList.get(indexOfPreviousPos);
//            return parent.getChildAt(previousPos - firsrt);
        }
        return -1;
    }

    private int getNextViewPosition(RecyclerView parent) {
//        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
//        int firsrt = linearLayoutManager.findFirstVisibleItemPosition();
        int indexOfCurrentPos = mStickItemPositionList.indexOf(mCurrentStickPos);
        int indexOfNextPos = indexOfCurrentPos + 1;

        if (indexOfNextPos < mStickItemPositionList.size()) {
            //            return parent.getChildAt(nextPos - firsrt);
            return mStickItemPositionList.get(indexOfNextPos);
        }

        return -1;
    }

    /***
     * 保存吸附View的下标
     * @param pos
     */
    private void saveStickPostion(int pos) {
        if (!mStickItemPositionList.contains(pos)) {
            mStickItemPositionList.add(pos);
        }
    }

    /**
     * 绘制吸附的itemView
     *
     * @param canvas
     */
    private void drawStickyItemView(Canvas canvas) {
        if (mStickView == null) return;

        int saveCount = canvas.save();
        canvas.translate(0, -mStickyViewMarginTop);
        mStickView.draw(canvas);
        canvas.restoreToCount(saveCount);
    }


    /**
     * 得到吸附viewHolder
     *
     * @param recyclerView
     */
    private void getStickyViewHolder(RecyclerView recyclerView, int pos) {
        if (mAdapter != null) return;

        mAdapter = recyclerView.getAdapter();
        mViewHolder = mAdapter.onCreateViewHolder(recyclerView, mAdapter.getItemViewType(pos));
        mStickView = mViewHolder.itemView;

    }

    /**
     * 给StickyView绑定数据
     *
     * @param position
     */
    private void bindDataForStickyView(int position, int width) {
        if (mCurrentStickPos == position || mViewHolder == null) return;

        mCurrentStickPos = position;
        mAdapter.onBindViewHolder(mViewHolder, mCurrentStickPos);
        measureLayoutStickyItemView(width);
    }

    /**
     * 计算布局吸附的itemView
     *
     * @param parentWidth
     */
    private void measureLayoutStickyItemView(int parentWidth) {
        if (mStickView == null || !mStickView.isLayoutRequested()) return;

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        int heightSpec;

        ViewGroup.LayoutParams layoutParams = mStickView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        mStickView.measure(widthSpec, heightSpec);
        mStickView.layout(0, 0, mStickView.getMeasuredWidth(), mStickView.getMeasuredHeight());
    }
}
