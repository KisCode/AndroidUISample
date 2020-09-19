package com.keno.dialogfragment.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.keno.dialogfragment.sample.R;


/**** @ProjectName: AndroidUISample
 * @Package: com.keno.dialogfragment.sample.fragment
 * @ClassName: EditDialogFragment
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/2/3 14:45
 * @UpdateUser: 更新者： 
 * @UpdateDate: 2020/2/3 14:45
 * @UpdateRemark: 更新说明： 
 * @Version: 1.0
 */
public class EditDialogFragment extends DialogFragment {
    public EditDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
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

}
