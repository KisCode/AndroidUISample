package com.keno.dialogfragment.sample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.keno.dialogfragment.sample.fragment.ConfirtAlertDialogFragment;
import com.keno.dialogfragment.sample.fragment.EditDialogFragment;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/2/3 15:05
 * https://github.com/codepath/android_guides/wiki/Using-DialogFragment
 * Google官方推荐使用DialogFragment替代Dialog
 * 优点：
 * 1. 具有和Fragment的相同的生命周期管理
 * 2. 旋转屏幕时更好的适配（如一个包含EditText输入对话框传统Dialog 输入内容后旋转屏幕后，输入内容消失，Activity销毁时不允许对话框未关闭
 * 后台崩溃日志：android.view.WindowLeaked: Activity com.keno.dialogfragment.sample.DialogFragmentSampleActivity has leaked window DecorView@54552c[] that was originally added here）
 */

public class DialogFragmentSampleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnShowDialog;
    private Button btnShowDialogfragment;
    private Button btnShowConfirmDialogfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment_sample);
        initView();
    }

    private void initView() {
        btnShowDialog = (Button) findViewById(R.id.btn_show_dialog);

        btnShowDialog.setOnClickListener(this);
        btnShowDialogfragment = (Button) findViewById(R.id.btn_show_dialogfragment);
        btnShowDialogfragment.setOnClickListener(this);
        btnShowConfirmDialogfragment = (Button) findViewById(R.id.btn_show_confirm_dialogfragment);
        btnShowConfirmDialogfragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_dialog:
                showAlertDialog();
                break;
            case R.id.btn_show_dialogfragment:
EditDialogFragment editDialogFragment = new EditDialogFragment();
// style dialog 设置dialogFragment样式
//                editDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppDialogTheme);
editDialogFragment.show(getSupportFragmentManager(), "EditDialogFragment");
                break;
            case R.id.btn_show_confirm_dialogfragment:
                ConfirtAlertDialogFragment confirtAlertDialogFragment = new ConfirtAlertDialogFragment();
                confirtAlertDialogFragment.show(getSupportFragmentManager(), "EditDialogFragment");
                break;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.fragment_edit_name);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogFragmentSampleActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
