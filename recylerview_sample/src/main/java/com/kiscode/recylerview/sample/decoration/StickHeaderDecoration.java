package com.kiscode.recylerview.sample.decoration;


import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 通过RecyclerView.ItemDecoration实现RecylerView指定type的View次吸附在顶部
 * Author: keno
 * CreateDate: 2020/7/12 15:45
 */
public class StickHeaderDecoration extends RecyclerView.ItemDecoration {

    /***
     * 吸附View的Type
     */
    private int mStickViewType;

    /***
     * 用于缓存吸附stickView的pos,此处position为数据源的position
     */
    private List<Integer> mStickPositionList;

    /***
     * 吸附View ,通过RecyclerView.getAdapter().createViewHolder创建一个ViewHolder, viewHolder.ItemView
     */
    private View mStickView;

    /***
     * 当前吸附StickView的position
     */
    private int mCurrentStickViewPos = -1;
    private float mStickViewMarginTop;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.ViewHolder mViewHolder;

    /***
     *
     * @param stickViewType 吸附View的Type
     */
    public StickHeaderDecoration(int stickViewType) {
        this.mStickViewType = stickViewType;
        mStickPositionList = new ArrayList<>();
    }

    /***
     * onDrawOver在RecyclerView绘制完成后绘制，确保ItemDecoration在item之上
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();

        //获取recyclerView在当前屏幕内显示可见的 childView个数
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int dataPosition = layoutManager.findFirstVisibleItemPosition() + i;

            if (parent.getAdapter() == null) {
                break;
            }

            /***
             * 指定itemType 满足 吸附StickView
             */
            if (mStickViewType == parent.getAdapter().getItemViewType(dataPosition)) {
                saveStickPositionToCache(dataPosition);
                initStickView(parent);

                int stickViewHeight = mStickView.getBottom() - mStickView.getTop();
                if (childView.getTop() <= 0) {
                    //当ChildView 滑动到顶部或划出屏幕顶部，则该childView吸附在顶部，直至下一个stickView滑到顶部
                    mStickViewMarginTop = 0;
                    bindDataForStickView(dataPosition, parent.getMeasuredWidth());
                } else {
                    //childView.getTop == stickViewHeight时，child刚好和顶部吸附stickView在一起
                    if (childView.getTop() <= stickViewHeight) {
                        //当前控件顶到 吸附stickView
                        mStickViewMarginTop = childView.getTop() - stickViewHeight;

                        if (dataPosition == mCurrentStickViewPos) {
                            //吸附stickView为当前childView 找childView的前一个吸附控件
                            int previousPosition = getPreviousViewPostion();
                            if (previousPosition != -1) {
                                bindDataForStickView(previousPosition, parent.getMeasuredWidth());
                            }
                        }
                    }
                }
            }

            drawStickView(c);
        }
    }

    /***
     * 保存stickView 的position至集合中
     * @param dataposition stickView 的position
     */
    private void saveStickPositionToCache(int dataposition) {
        if (!mStickPositionList.contains(dataposition)) {
            mStickPositionList.add(dataposition);
        }
    }

    /***
     * 通过RecyclerView.getAdapter().createViewHolder创建一个ViewHolder
     * @param recyclerView 当前recyclerView
     */
    private void initStickView(RecyclerView recyclerView) {
        if (mAdapter != null && mViewHolder != null) {
            return;
        }

        mAdapter = recyclerView.getAdapter();
        mViewHolder = mAdapter.createViewHolder(recyclerView, mStickViewType);
        mStickView = mViewHolder.itemView;
    }

    /**
     * 绘制吸附的itemView
     *
     * @param canvas
     */
    private void drawStickView(Canvas canvas) {
        if (mStickView == null) return;

        int saveCount = canvas.save();
        canvas.translate(0, mStickViewMarginTop);
        mStickView.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    /**
     * 给StickyView绑定数据
     *
     * @param position
     */
    private void bindDataForStickView(int position, int width) {
        if (mCurrentStickViewPos == position || mAdapter == null) return;

        mCurrentStickViewPos = position;
        mAdapter.onBindViewHolder(mViewHolder, mCurrentStickViewPos);
        measureLayoutStickyItemView(width);
    }

    /***
     * 重新测绘StickView的高度
     * @param parentWidth 父控件宽度
     */
    private void measureLayoutStickyItemView(int parentWidth) {
        if (mStickView == null || !mStickView.isLayoutRequested()) {
            return;
        }

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

    private int getPreviousViewPostion() {
        int indexOfCurrentPos = mStickPositionList.indexOf(mCurrentStickViewPos);
        int indexOfPreviousPos = indexOfCurrentPos - 1;
        if (indexOfPreviousPos >= 0) {
            int previousPos = mStickPositionList.get(indexOfPreviousPos);
            return previousPos;
        }
        return -1;
    }

}
