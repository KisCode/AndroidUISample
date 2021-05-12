package keno.android.ui.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import keno.android.ui.sample.contans.TabConfig;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/2/3 10:21
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConstraintActivity, getBtnConstraintTab, btnLoginDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        getBtnConstraintTab = findViewById(R.id.btn_constraintlayout_tab);
        btnConstraintActivity = findViewById(R.id.btn_constraintlayout_demo);
        btnLoginDemo = findViewById(R.id.btn_practice_demo);

        getBtnConstraintTab.setOnClickListener(this);
        btnConstraintActivity.setOnClickListener(this);
        btnLoginDemo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_constraintlayout_tab:
                String[] titleArrs = {
                        TabConfig.RelativeLayout,
                        TabConfig.LinearLayout,
                        TabConfig.PercentLayout,
                        TabConfig.MarginGone,
                        TabConfig.Circle,
                        TabConfig.ChainLine,
                        TabConfig.Align,
                        TabConfig.Barrier,
                        TabConfig.Group,
                        TabConfig.Placeholder
                };
                MainTabActivity.start(this, titleArrs);
                break;
            case R.id.btn_constraintlayout_demo:
                startActivity(new Intent(this, ConstraintLayoutDemoActivity.class));
                break;
            case R.id.btn_practice_demo:
                String[] titlePracticeArrs = {
                        TabConfig.Login,
                        TabConfig.Clock,
                        TabConfig.Error
                };
                MainTabActivity.start(this, titlePracticeArrs);
                break;
        }
    }
}
