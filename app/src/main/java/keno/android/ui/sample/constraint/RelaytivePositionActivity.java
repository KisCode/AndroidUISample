package keno.android.ui.sample.constraint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import keno.android.ui.sample.R;

/**
 * Description:  Relaytive Layout
 * Author: keno
 * Date : 2019/11/20 16:02
 **/
public class RelaytivePositionActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, RelaytivePositionActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaytive_position);
    }
}


