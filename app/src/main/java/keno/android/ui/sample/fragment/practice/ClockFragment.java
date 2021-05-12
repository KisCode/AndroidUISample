package keno.android.ui.sample.fragment.practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import keno.android.ui.sample.R;

/**
 * Description: 时钟效果 通过ConstraintLayout 角度约束实现
 * Author: keno
 * Date : 2021/5/12 21:26
 **/
public class ClockFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_clock, container, false);
        return root;
    }
}