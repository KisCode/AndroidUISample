package keno.android.ui.sample.fragment.basics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import keno.android.ui.sample.R;

/**
 * Description:MarginGone 属性当约束控件visibility属性为Gone对应Margin值才生效
 * Author: keno
 * Date : 2021/5/12 18:08
 **/
public class MarginGoneFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_margin_gone, container, false);
        return root;
    }
}