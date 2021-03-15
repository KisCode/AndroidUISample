package com.kiscode.stackviewpager;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.kiscode.stackviewpager.fragment.StackViewpagerFragment;

import java.util.ArrayList;
import java.util.List;

public class StackViewpagerFragmentActivity extends AppCompatActivity {
    private List<MyOnTouchListener> myOnTouchListenerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_viewpager_fragment);

        initFragment();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener myOnTouchListener : myOnTouchListenerList) {
            myOnTouchListener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container_layout, StackViewpagerFragment.newInstance()).commit();
    }

    public void registerMyOnTouchListener(MyOnTouchListener onTouchListener) {
        myOnTouchListenerList.add(onTouchListener);
    }

    public interface MyOnTouchListener {
        boolean onTouch(MotionEvent motionEvent);
    }
}