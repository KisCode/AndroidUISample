package com.keno.dialogfragment.sample.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.keno.dialogfragment.sample.R;


/**** @ProjectName: AndroidUISample
 * @Package: com.keno.dialogfragment.sample.fragment
 * @ClassName: ConfirtAlertDialogFragment
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/2/3 14:52
 * @UpdateUser: 更新者： 
 * @UpdateDate: 2020/2/3 14:52
 * @UpdateRemark: 更新说明： 
 * @Version: 1.0
 */

public class ConfirtAlertDialogFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.fragment_edit_name);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        Dialog dialog = builder.create();
        return dialog;
    }
}
