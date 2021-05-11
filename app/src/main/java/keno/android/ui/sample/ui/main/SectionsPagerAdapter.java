package keno.android.ui.sample.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import keno.android.ui.sample.contans.TabConfig;
import keno.android.ui.sample.fragment.LinearLayoutFragment;
import keno.android.ui.sample.fragment.RelativePositionFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final String[] mTitles;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String[] titles) {
        super(fm);
        mContext = context;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        String title = mTitles[position];
        switch (title) {
            case TabConfig.RelativeLayout:
                return new RelativePositionFragment();
            case TabConfig.LinearLayout:
            case TabConfig.PercentLayout:
                return new LinearLayoutFragment();
            default:
                return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}