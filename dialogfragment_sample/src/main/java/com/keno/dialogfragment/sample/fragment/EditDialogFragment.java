package com.keno.dialogfragment.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.keno.dialogfragment.sample.R;

/**
 * Description:
 * Author: keno
 * Date : 2020/2/3 14:45
 **/
public class EditDialogFragment extends DialogFragment {
    public EditDialogFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置对话框全屏的两种方式
        //方式2： 设置style
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置对话框全屏的两种方式
        //方式1：
        /*Window window = this.getDialog().getWindow();
        //去掉dialog默认的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);*/

        View view = inflater.inflate(R.layout.fragment_edit_name, container);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppDialogTheme);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*
        view.findViewById(R.id.btn_alert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("confirm")
                        .setMessage("I'm Alert")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
*/
        //如在DialogFragment 页面内再弹出AlertDialog会出现被覆盖的情况，弹框必须使用DialogFragment
        ConfirmAlertDialogFragment confirtAlertDialogFragment = new ConfirmAlertDialogFragment();
        confirtAlertDialogFragment.show(getChildFragmentManager(), "ConfirmAlertDialogFragment");
    }
}
