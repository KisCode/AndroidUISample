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
 * Description: 辅助View Group,能控制多个控件同时消失或隐藏
 * <p>
 * 通过 app:constraint_referenced_ids="view1,view2,view3,view4" 关联多个view,设置group本身visble属性则对关联控件批量作用
 * Author: keno
 * Date : 2021/5/11 22:47
 **/
public class GroupFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_group, container, false);
        return root;
    }
}