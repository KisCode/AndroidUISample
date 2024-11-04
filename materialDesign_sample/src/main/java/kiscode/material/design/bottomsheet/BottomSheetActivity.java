package kiscode.material.design.bottomsheet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kiscode.material.design.R;
import kiscode.material.design.util.StatusBarUtil;

public class BottomSheetActivity extends AppCompatActivity {
    private static final String TAG = "BottomSheetActivity";

    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBottomSheet();
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tv_title);
    }

    private void initBottomSheet() {
        ConstraintLayout containerBottomSheet = findViewById(R.id.laout_container_bottom_sheet);
        BottomSheetBehavior<ConstraintLayout> behavior = BottomSheetBehavior.from(containerBottomSheet);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i(TAG, "BottomSheetBehavior.STATE_DRAGGING 拖动");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        //拖动释放后 会自行滑动 沉降
                        Log.i(TAG, "BottomSheetBehavior.STATE_SETTLING 沉降");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i(TAG, "BottomSheetBehavior.STATE_EXPANDED 完全展开");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i(TAG, "BottomSheetBehavior.STATE_COLLAPSED 收起");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i(TAG, "BottomSheetBehavior.STATE_HIDDEN 隐藏");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.i(TAG, "BottomSheetBehavior.STATE_HALF_EXPANDED 半展开");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}