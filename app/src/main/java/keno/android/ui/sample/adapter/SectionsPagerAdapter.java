package keno.android.ui.sample.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import keno.android.ui.sample.contans.TabConfig;
import keno.android.ui.sample.fragment.ErrorFragment;
import keno.android.ui.sample.fragment.TextFragment;
import keno.android.ui.sample.fragment.basics.AlignFragment;
import keno.android.ui.sample.fragment.basics.BarrierFragment;
import keno.android.ui.sample.fragment.basics.ChainLineFragment;
import keno.android.ui.sample.fragment.basics.CircleFragment;
import keno.android.ui.sample.fragment.basics.GroupFragment;
import keno.android.ui.sample.fragment.basics.LinearLayoutFragment;
import keno.android.ui.sample.fragment.basics.MarginGoneFragment;
import keno.android.ui.sample.fragment.basics.PlaceholderFragment;
import keno.android.ui.sample.fragment.basics.RelativePositionFragment;
import keno.android.ui.sample.fragment.practice.ClockFragment;
import keno.android.ui.sample.fragment.practice.LoginFragment;

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
            case TabConfig.MarginGone:
                return new MarginGoneFragment();
            case TabConfig.ChainLine:
                return new ChainLineFragment();
            case TabConfig.Align:
                return new AlignFragment();
            case TabConfig.Circle:
                return new CircleFragment();
            case TabConfig.Barrier:
                return new BarrierFragment();
            case TabConfig.Group:
                return new GroupFragment();
            case TabConfig.Placeholder:
                return new PlaceholderFragment();

            //综合页面
            case TabConfig.Error:
                return new ErrorFragment();
            case TabConfig.Login:
                return new LoginFragment();
            case TabConfig.Clock:
                return new ClockFragment();
            default:
                return TextFragment.newInstance(position + 1);
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