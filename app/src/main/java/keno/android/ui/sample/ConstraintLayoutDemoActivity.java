package keno.android.ui.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import keno.android.ui.sample.constraint.CenterAndBiasActivity;
import keno.android.ui.sample.constraint.MarginGoneActivity;
import keno.android.ui.sample.constraint.PercentActivity;
import keno.android.ui.sample.constraint.RelaytivePositionActivity;


public class ConstraintLayoutDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayot_demo);
        initView();
    }

    private void initView() {
        Button btnRelativePosition = findViewById(R.id.btn_relative_position);
        Button btnMarginGone = findViewById(R.id.btn_margin_gone);

        btnRelativePosition.setOnClickListener(this);
        btnMarginGone.setOnClickListener(this);
        Button btnCenterAndBias = (Button) findViewById(R.id.btn_center_and_bias);
        btnCenterAndBias.setOnClickListener(this);
        Button btnPercent = (Button) findViewById(R.id.btn_percent);
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
