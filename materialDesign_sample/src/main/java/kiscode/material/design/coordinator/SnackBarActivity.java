package kiscode.material.design.coordinator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import kiscode.material.design.R;

/**
 * Description: 根布局为 CoordinatorLayout页面内，自动协调处理子View,
 * 当SnackBar从底部弹出时会将FloatingActionButton向上顶；当SnackBar消失时，FloatingActionButton回到原位置
 * Author: keno
 * Date : 2021/4/25 14:08
 **/
public class SnackBarActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private View containView;

    public static void start(Context context) {
        Intent starter = new Intent(context, SnackBarActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        initView();
    }

    private void initView() {
        containView = findViewById(R.id.container);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> Snackbar.make(containView, "FloatingActionButton will move up", Snackbar.LENGTH_LONG).show());
    }
}