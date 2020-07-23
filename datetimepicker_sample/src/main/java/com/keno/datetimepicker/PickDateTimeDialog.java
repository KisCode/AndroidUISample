package com.keno.datetimepicker;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.keno.datetimepicker.util.PickerViewUtil;

import java.util.Calendar;

/**
 * Description: 包含 日期+时间的时间选择滚轮控件
 * Author: kanjianxiong
 * Date : 2020/7/23 16:54
 **/
public class PickDateTimeDialog extends DialogFragment implements View.OnClickListener {

    private DatePicker datepicker;
    private TimePicker timepicker;
    private TextView tvCancel;
    private TextView tvSure;
    private OnPickedListener onPickedListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppDialogTheme);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        //去掉dialog默认的padding
//        window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.colorAccent));
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        //获取屏幕宽度，设置每DatePicker 每一列的宽度
        DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int itemWidth = dm.widthPixels / 7;

        PickerViewUtil.setDividerColor(getContext(), datepicker, R.color.colorPrimary);
        PickerViewUtil.setDividerColor(getContext(), timepicker, R.color.colorPrimary);
        PickerViewUtil.resizePikcer(datepicker, itemWidth);
        PickerViewUtil.resizePikcer(timepicker, itemWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_datetime_selector, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        datepicker = view.findViewById(R.id.datepicker_dialog);
        timepicker = view.findViewById(R.id.timepicker_dialog);
        timepicker.setIs24HourView(true);

        tvCancel = view.findViewById(R.id.tv_cancle_dialog);
        tvSure = view.findViewById(R.id.tv_sure_dialog);
        tvCancel.setOnClickListener(this);
        tvSure.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle_dialog:
                dismiss();
                break;
            case R.id.tv_sure_dialog:
                /*
                String currentDateTime = datepicker.getYear() + "-" + (datepicker.getMonth() + 1) + "-" + datepicker.getDayOfMonth()
                        + "\t"
                        + timepicker.getHour() + ":" + timepicker.getMinute();
                Toast.makeText(getContext(), currentDateTime, Toast.LENGTH_SHORT).show();
                */
                if (onPickedListener != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth(), timepicker.getHour(), timepicker.getMinute());
                    onPickedListener.onPickedDate(calendar.getTimeInMillis());
                }
                dismiss();
                break;
        }
    }

    public void setOnPickedListener(OnPickedListener onPickedListener) {
        this.onPickedListener = onPickedListener;
    }

    public interface OnPickedListener {
        void onPickedDate(long datetime);
    }
}
