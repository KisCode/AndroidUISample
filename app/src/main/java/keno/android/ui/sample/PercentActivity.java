package keno.android.ui.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 当 ConstraintLayout 子布局的宽或高设置为0dp时，可以对宽或高设置百分比，例如设置一个按钮的宽是屏幕宽度的30%
 */
public class PercentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
    }
}
