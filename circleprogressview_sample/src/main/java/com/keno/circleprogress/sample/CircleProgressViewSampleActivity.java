package com.keno.circleprogress.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.keno.circleprogress.sample.view.CircleProgreeView;

public class CircleProgressViewSampleActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleProgreeView progressCircular;
    private Button btnAdd;
    private CircleProgreeView progressCircular1;
    private CircleProgreeView progressCircular2;
    private CircleProgreeView progressCircular3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress_view_sample);
        initView();
    }

    private void initView() {
        progressCircular1 = (CircleProgreeView) findViewById(R.id.progress_circular1);
        progressCircular2 = (CircleProgreeView) findViewById(R.id.progress_circular2);
        progressCircular3 = (CircleProgreeView) findViewById(R.id.progress_circular3);
        progressCircular = findViewById(R.id.progress_circular);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                float progress = progressCircular.getProgress() + 1;
                progressCircular.setProgress(progress);
                progressCircular1.setProgress(progress);
                progressCircular2.setProgress(progress);
                progressCircular3.setProgress(progress);
                break;
        }
    }
}
