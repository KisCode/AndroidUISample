package keno.android.ui.sample.constraint;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import keno.android.ui.sample.R;

/**
* Description:  Relaytive Layout
* Author: KisCode
* Date : 2019/11/20 16:02
**/
public class RelaytivePositionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaytive_position);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RelaytivePositionActivity.class);
        context.startActivity(starter);
    }
}


