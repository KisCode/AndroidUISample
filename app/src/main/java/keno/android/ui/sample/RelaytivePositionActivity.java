package keno.android.ui.sample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
