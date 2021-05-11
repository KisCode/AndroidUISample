package keno.android.ui.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import keno.android.ui.sample.R;

/**
 * Description: 实现LinearLayout相同效果的线性布局
 * Author: keno
 * Date : 2021/5/11 22:47
 **/
public class LinearLayoutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_linearlayout, container, false);
        return root;
    }
}