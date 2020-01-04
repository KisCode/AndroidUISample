package keno.android.ui.sample.constraint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import keno.android.ui.sample.R;

public class CenterAndBiasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_and_bias);
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, CenterAndBiasActivity.class);
        context.startActivity(starter);
    }
}
