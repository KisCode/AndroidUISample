package kiscode.material.design;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kiscode.material.design.bottomsheet.BottomSheetActivity;
import kiscode.material.design.coordinator.CoordinatorLayoutSampleActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startToolbar(View view) {
        startDemoActivity(ToolbarActivity.class);
    }

    public void startDrawerLayout(View view) {
        startDemoActivity(DrawerLayoutActivity.class);
    }

    public void startBottomNavigationView(View view) {
    }

    public void startFloatingActionBar(View view) {
    }

    public void startImmersiveStatus(View view) {
        startDemoActivity(ImmersiveStatusActivity.class);
    }

    public void startCoordinatorLayout(View view) {
        startDemoActivity(CoordinatorLayoutSampleActivity.class);
    }

    public void startBottomSheet(View view) {
        startDemoActivity(BottomSheetActivity.class);
    }

    private void startDemoActivity(Class targetActivity) {
        startActivity(new Intent(this, targetActivity));
    }
}