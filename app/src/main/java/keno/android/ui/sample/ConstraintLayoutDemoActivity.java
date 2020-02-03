package keno.android.ui.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import keno.android.ui.sample.constraint.CenterAndBiasActivity;
import keno.android.ui.sample.constraint.MarginGoneActivity;
import keno.android.ui.sample.constraint.PercentActivity;
import keno.android.ui.sample.constraint.RelaytivePositionActivity;


public class ConstraintLayoutDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRelativePosition;
    private Button btnMarginGone;
    private Button btnCenterAndBias;
    private Button btnPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayot_demo);
        initView();
    }

    private void initView() {
        btnRelativePosition = findViewById(R.id.btn_relative_position);
        btnMarginGone = findViewById(R.id.btn_margin_gone);

        btnRelativePosition.setOnClickListener(this);
        btnMarginGone.setOnClickListener(this);
        btnCenterAndBias = (Button) findViewById(R.id.btn_center_and_bias);
        btnCenterAndBias.setOnClickListener(this);
        btnPercent = (Button) findViewById(R.id.btn_percent);
        btnPercent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_relative_position:
                RelaytivePositionActivity.start(this);
                break;
            case R.id.btn_margin_gone:
                MarginGoneActivity.start(this);
                break;
            case R.id.btn_center_and_bias:
                CenterAndBiasActivity.start(this);
                break;
            case R.id.btn_percent:
                PercentActivity.start(this);
                break;
        }
    }
}
