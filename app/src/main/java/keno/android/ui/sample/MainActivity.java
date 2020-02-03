package keno.android.ui.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Description:
 * Author: keno
 * CreateDate: 2020/2/3 10:21
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConstraintlayoutDemo;
    private Button btnLoginDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnConstraintlayoutDemo = (Button) findViewById(R.id.btn_constraintlayout_demo);
        btnLoginDemo = (Button) findViewById(R.id.btn_login_demo);

        btnConstraintlayoutDemo.setOnClickListener(this);
        btnLoginDemo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_constraintlayout_demo:
                startActivity(new Intent(this, ConstraintLayoutDemoActivity.class));
                break;
            case R.id.btn_login_demo:
                startActivity(new Intent(this, LoginDemoActivity.class));
                break;
        }
    }
}
