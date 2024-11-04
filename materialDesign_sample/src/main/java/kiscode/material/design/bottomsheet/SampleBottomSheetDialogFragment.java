package kiscode.material.design.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kiscode.material.design.R;

/**
 * Description:
 * Date : 2024/1/24 17:48
 **/
public class SampleBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private View root;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置样式 圆角背景等颜色
        setStyle(STYLE_NORMAL,R.style.styleBottomSheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_sample_bottom_sheet, container, false);
        return root;
    }
}
