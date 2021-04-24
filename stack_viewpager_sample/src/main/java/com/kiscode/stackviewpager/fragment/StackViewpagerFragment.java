package com.kiscode.stackviewpager.fragment;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.kiscode.stackviewpager.R;
import com.kiscode.stackviewpager.StackViewpagerFragmentActivity;
import com.kiscode.stackviewpager.adapter.CardFragmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/10 16:32
 **/
public class StackViewpagerFragment extends Fragment implements GestureDetector.OnGestureListener {

    private static final String TAG = "StackViewpager";
    private static final int SIZE_OFFSET = 30;
    final String[] contentArr = {"壹", "贰", "叁", "肆"};
    private boolean mIsRandom = true;
    private ViewPager viewPager;
    private CardFragmentAdapter adapter;

    public static StackViewpagerFragment newInstance() {
        StackViewpagerFragment fragment = new StackViewpagerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stack_viewpager, null);
        initView(view);
        initGestureListener();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initGestureListener() {
        final GestureDetector mGestureDetector = new GestureDetector(getActivity(), this);
        StackViewpagerFragmentActivity.MyOnTouchListener myOnTouchListener = new StackViewpagerFragmentActivity.MyOnTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                boolean result = mGestureDetector.onTouchEvent(ev);
                return result;
            }
        };

        ((StackViewpagerFragmentActivity) getActivity()).registerMyOnTouchListener(myOnTouchListener);
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.viewpager_stack);
        viewPager.setOffscreenPageLimit(5);
        adapter = new CardFragmentAdapter(getChildFragmentManager(), Arrays.asList(contentArr));
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "position:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                //position 参数表示指定页面相对于屏幕中心的位置。
                // 它是一个动态属性，会随着用户滚动浏览页面而变化。当页面填满整个屏幕时，其位置值为 0。 当页面刚刚离开屏幕右侧时，其位置值为 1。如果用户在第一页和第二页之间滚动到一半，则第一页的位置为 -0.5，第二页的位置为 0.5。
//                page.setAlpha(0.5f);
                if (position <= 0.0f) {
                    //当position <=0 表示当前页在翻页
                    page.setTranslationX(0f);
                } else {
                    //设置每个View在中间，即设置相对原位置偏移量
                    page.setTranslationX((-page.getWidth() * position));
                    page.setTranslationY(SIZE_OFFSET * position);

                    if (page.getWidth() > 0) {
                        //缩放比例
                        float scale = (page.getWidth() - SIZE_OFFSET * position) / (float) page.getWidth();

                        Log.i("transformPageScale", "scale:" + scale + "\tposition:" + position + "\tpage.getWidth()\t" + page.getWidth());
                        page.setScaleX(scale);
                        page.setScaleY(scale);
                    }

                }

                Log.i("transformPage", "position:" + position);
            }
        });
    }

    private void reloadData() {
        List<String> list = new ArrayList<>();
        if (mIsRandom) {
            int startNum = new Random().nextInt(90);
            for (int i = startNum; i < startNum + 4; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                StringBuffer sb = new StringBuffer();
                for (int k = 0; k < 50; k++) {
                    sb.append(i);
                }
                list.add(sb.toString());
            }
            mIsRandom = false;
        } else {
            list = Arrays.asList(contentArr);
            mIsRandom = true;
        }
        viewPager.setCurrentItem(0);
        adapter.setNewDatas(list);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.e(TAG, e.getX() + "\te.getY() = " + e.getY());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i(TAG, "onFling  e1.x = " + e1.getX() + "\te2.x = " + e2.getX());
        if (viewPager.getCurrentItem() < adapter.getCount() - 1) {
            return false;
        }

        if (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY() - e2.getY())
                && e1.getX() - e2.getX() > 30) {
            Log.i(TAG, "reloadData ") ;
            reloadData();
            return true;
        }
        return false;
    }
}