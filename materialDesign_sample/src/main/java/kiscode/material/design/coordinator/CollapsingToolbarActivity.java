package kiscode.material.design.coordinator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import kiscode.material.design.R;

public class CollapsingToolbarActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, CollapsingToolbarActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_sample);
    }
}