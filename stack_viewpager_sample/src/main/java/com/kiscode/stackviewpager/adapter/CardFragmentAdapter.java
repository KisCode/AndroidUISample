package com.kiscode.stackviewpager.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kiscode.stackviewpager.CardFragmnet;

import java.util.List;

/**
 * Description:
 * Author: kanjianxiong
 * Date : 2021/2/20 14:02
 **/
//public class CardFragmentAdapter extends FragmentPagerAdapter {
public class CardFragmentAdapter extends FragmentStatePagerAdapter {
    private List<String> mDatas;

    @SuppressLint("WrongConstant")
    public CardFragmentAdapter(@NonNull FragmentManager fm, List<String> mDatas) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return CardFragmnet.newInstance(mDatas.get(position));
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
//        super.getItemPosition(object);
        return POSITION_NONE;
    }

    public void setNewDatas(List<String> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }
}