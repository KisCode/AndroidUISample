package com.keno.dialogfragment.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.keno.dialogfragment.sample.R;

/**
 * Description: 自定义大小的对话框
 * Author: keno
 * Date : 2020/7/27 17:41
 **/
public class CustomSizeDialogFragment extends DialogFragment {
    public static final String KEY_WIDTH = "WIDTH";
    public static final String KEY_HEIGHT = "HEIGHT";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.styleNormalDialog);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.styleNormalDialog);
    }

    @Override
    public void onStart() {
        super.onStart();

//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        if (getArguments() == null) {
            return;
        }
        int width = getArguments().getInt(KEY_WIDTH);
        int height = getArguments().getInt(KEY_HEIGHT);

        Window dialogWindow = getDialog().getWindow();
//        dialogWindow.setAttributes(lp);
        dialogWindow.setLayout(width, height);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_custom_size, container, false);
    }
}
