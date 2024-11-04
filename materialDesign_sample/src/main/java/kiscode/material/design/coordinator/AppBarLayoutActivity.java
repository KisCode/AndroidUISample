package kiscode.material.design.coordinator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import kiscode.material.design.R;

public class AppBarLayoutActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, AppBarLayoutActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
    }
}