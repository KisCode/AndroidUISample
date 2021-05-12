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
 * Description: 辅助线Barrier 用多个view作为决定自己位置的一种辅助线
 * <p>
 * <androidx.constraintlayout.widget.Barrier
 * android:id="@+id/barrier1"
 * android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * app:barrierDirection="right"
 * app:constraint_referenced_ids="view2,view1" />
 * </p>
 * 通过view1,view2的最右边为辅助线位置
 * Author: keno
 * Date : 2021/5/11 22:47
 **/
public class BarrierFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_barrier, container, false);
        return root;
    }
}