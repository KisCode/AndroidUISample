package keno.android.ui.sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import keno.android.ui.sample.adapter.SectionsPagerAdapter;
import keno.android.ui.sample.contans.TabConfig;

public class MainTabActivity extends AppCompatActivity {
    private static final String KEY_TITLE_LIST = "TITLE_LIST";

    public static void start(Context context, String[] titleArrs) {
        Intent starter = new Intent(context, MainTabActivity.class);
        starter.putExtra(KEY_TITLE_LIST, titleArrs);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), getTitles());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private String[] getTitles() {
        String[] titleArrs = getIntent().getStringArrayExtra(KEY_TITLE_LIST);
        if (titleArrs != null && titleArrs.length > 0) {
            return titleArrs;
        }
        return new String[]{
                TabConfig.Error
        };
    }
}