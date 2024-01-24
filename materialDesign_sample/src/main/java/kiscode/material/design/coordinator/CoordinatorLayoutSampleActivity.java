package kiscode.material.design.coordinator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import kiscode.material.design.R;

/***
 * CoordinatorLayout的使用场景：
 * 1. FloatActionbar + SnackBar
 * 2. AppBarLayout
 * 3. CollapsingToolbarLayout
 * 4. Behavior
 */
public class CoordinatorLayoutSampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_sample);

        initToolBar();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //点击返回按钮监听
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initToolBar() {
        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //显示向上返回
        getSupportActionBar().setDisplayShowHomeEnabled(true);      //显示返回按钮
    }

    /***
     * CoordinatorLayout根布局下，FloatActionbar + SnackBar
     */
    public void startSnackBarSample(View view) {
        SnackBarActivity.start(this);
    }

    /***
     * CoordinatorLayout下AppBarLayout
     */
    public void startAppBarLayoutSample(View view) {
        AppBarLayoutActivity.start(this);
    }

    /***
     * CoordinatorLayout下折叠ToolBar
     */
    public void startCollapsingToolbarSample(View view) {
        CollapsingToolbarActivity.start(this);
    }

    /***
     * CoordinatorLayout下折叠ToolBar 内包含图片上滑折叠
     */
    public void startCollapsingToolbarImageSample(View view) {
        CollapsingToolBarImageActivity.start(this);
    }

    /***
     * RecyclerView滑动 通过behavior联动 其他childview
     * @param view
     */
    public void startNestRecyclerViewSample(View view) {
        NestRecycleViewActivity.start(this);
    }
}