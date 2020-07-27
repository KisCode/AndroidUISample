package com.keno.dialogfragment.sample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.keno.dialogfragment.sample.fragment.ConfirtAlertDialogFragment;
import com.keno.dialogfragment.sample.fragment.CustomSizeDialogFragment;
import com.keno.dialogfragment.sample.fragment.EditDialogFragment;
import com.keno.dialogfragment.sample.util.ScreenUtil;

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
    private Button btnShowCustomeSizeDialogFragment;
    private SeekBar seekbarWidth;
    private SeekBar seekbarHeight;

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
        btnShowCustomeSizeDialogFragment = (Button) findViewById(R.id.btn_show_customsize_dialogfragment);
        btnShowCustomeSizeDialogFragment.setOnClickListener(this);
        seekbarWidth = findViewById(R.id.seekbar_width);
        seekbarHeight = findViewById(R.id.seekbar_height);
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
            case R.id.btn_show_customsize_dialogfragment:
                //自定义大小的对话框
                CustomSizeDialogFragment customSizeDialogFragment = new CustomSizeDialogFragment();
                Bundle bundle = new Bundle();
                int width = getScreenWidth() * seekbarWidth.getProgress() / 100;
                int height = getScreenHeight() * seekbarHeight.getProgress() / 100;
                bundle.putSerializable(CustomSizeDialogFragment.KEY_WIDTH, width);
                bundle.putSerializable(CustomSizeDialogFragment.KEY_HEIGHT, height);
                customSizeDialogFragment.setArguments(bundle);
                customSizeDialogFragment.show(getSupportFragmentManager(), "CustomSizeDialogFragment");
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

    private int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    private int getScreenHeight() {
//        return ScreenUtil.getScreenRealHeight(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
