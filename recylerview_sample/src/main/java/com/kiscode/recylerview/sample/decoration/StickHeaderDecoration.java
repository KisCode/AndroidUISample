package com.kiscode.recylerview.sample.decoration;


import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description: 通过RecyclerView.ItemDecoration实现RecylerView指定type的View次吸附在顶部
 *
 * <a href="https://www.jianshu.com/p/b335b620af39/">RecyclerView.ItemDecoration参考</a>.
 * Author: keno
 * CreateDate: 2020/7/12 15:45
 */
public class StickHeaderDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "StickDecoration";

    /***
     * 吸附View的Type
     */
    protected int mStickViewType;

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
     * @param canvas
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        Log.i(TAG, "onDrawOver");
        if (mStickView != null) {
            Log.i(TAG, "StickView:" + mStickView.getTop() + "\t at " + mCurrentStickViewPos);
        }

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        if (layoutManager == null) {
            return;
        }

        //获取recyclerView在当前屏幕内显示可见的 childView个数
        int childCount = parent.getChildCount();
        boolean haveStickType = false;
        for (int i = 0; i < childCount; i++) {
            drawStickView(canvas);
            View childView = parent.getChildAt(i);
            int dataPosition = layoutManager.findFirstVisibleItemPosition() + i;

            if (parent.getAdapter() == null) {
                break;
            }

            if (mStickViewType != parent.getAdapter().getItemViewType(dataPosition)) {
                continue;
            }

            haveStickType = true;
            /***
             * 指定itemType 满足 吸附StickView
             */
            saveStickPositionToCache(dataPosition);
            initStickView(parent);
            if (mCurrentStickViewPos == -1) {
                //初始化绑定第一个
                bindDataForStickView(dataPosition, parent.getMeasuredWidth());
            }

            int stickViewHeight = mStickView.getBottom() - mStickView.getTop();

            if (dataPosition != mCurrentStickViewPos) {
                if (Math.abs(childView.getTop()) < stickViewHeight) {
                    Log.i("drawStickViewDrawC", "多stick接触 view dataPosition=" + dataPosition
                            + "\t mCurrentStickViewPos=" + mCurrentStickViewPos
                    );
                    //滑出去了 则作为新的吸附view
                    if (childView.getTop() <= 0) {
                        //向上顶
                        mStickViewMarginTop = 0;
                        bindDataForStickView(dataPosition, parent.getMeasuredWidth());
                    } else {
                        mStickViewMarginTop = childView.getTop() - stickViewHeight;
                    }

                    /*if (Math.abs(childView.getTop()) < stickDistance) {
//                        切换stickView
                        mStickViewMarginTop = 0;
                        bindDataForStickView(dataPosition, parent.getMeasuredWidth());
                        Log.d("drawStickViewDrawC", "新的stickView上位 " + mCurrentStickViewPos);
                    } else if (childView.getTop() > 0) {
                        //向上顶
                        mStickViewMarginTop = childView.getTop() - stickViewHeight;
                    } else if (childView.getTop() < 0) {
                        //向下顶
                        mStickViewMarginTop = -childView.getTop();
                    }*/
                }
            } else {
                if (childView.getTop() > 0) {
                    //向下顶
                    Log.e(TAG, "是时候从上面找出 新的StickView了");

                    int previousPosition = getPreviousViewPostion();
                    if (previousPosition >= 0) {
                        bindDataForStickView(previousPosition, parent.getMeasuredWidth());
                    }
                }
            }

/*
            //旧版实现有bug
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
                        int previousPosition = getPreviousViewPostion(); //获取上一个？下一个？
                        if (previousPosition != -1) {
                            bindDataForStickView(previousPosition, parent.getMeasuredWidth());
                        }
                    } else {
                        Log.i("drawStickViewDraw", "dataPosition=" + dataPosition
                                + "\t mCurrentStickViewPos=" + mCurrentStickViewPos
                        );
                    }
                } else {
                    if (mStickViewMarginTop != 0) {
                        mStickViewMarginTop = 0;
                    }
                }
            }
      */
            Log.i("drawStickView", "getTop=" + mStickView.getTop()
                    + "\t getBottom=" + mStickView.getBottom()
            );

        }
        if (!haveStickType) {
            Log.e(TAG, "屏幕上没有发现 stickView了");
            if (mStickViewMarginTop < 0) {
                mStickViewMarginTop = 0;
            }
        }

        if (mStickViewMarginTop < 0) {
            int prePosition = getPreviousViewPostion();
            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
            Log.i(TAG, "prePosition=" + prePosition + "\tfirstVisiblePosition=" + firstVisiblePosition);
            if (prePosition < firstVisiblePosition - 1) {
//                mStickViewMarginTop = 0;
            }
        }

        drawStickView(canvas);
    }

    /***
     * 保存stickView 的position至集合中
     * @param dataposition stickView 的position
     */
    private void saveStickPositionToCache(int dataposition) {
//        mStickPositionSet.add(dataposition);
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
        Collections.sort(mStickPositionList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int indexOfCurrentPos = mStickPositionList.indexOf(mCurrentStickViewPos);
        int indexOfPreviousPos = indexOfCurrentPos - 1;
        if (indexOfPreviousPos >= 0) {
            return mStickPositionList.get(indexOfPreviousPos);
        }
        return -1;
    }
}
