package keno.android.ui.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRelativePosition;
    private Button btnMarginGone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnRelativePosition = findViewById(R.id.btn_relative_position);
        btnMarginGone = findViewById(R.id.btn_margin_gone);

        btnRelativePosition.setOnClickListener(this);
        btnMarginGone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_relative_position:
                //Start RelaytivePositionActivity
                RelaytivePositionActivity.start(this);
                break;
            case R.id.btn_margin_gone:

                break;
        }
    }
}
