package com.kiscode.stackviewpager.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kiscode.stackviewpager.CardFragmnet;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: kanjianxiong
 * Date : 2021/2/20 14:02
 **/
public class CardFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mDatas ;

    @SuppressLint("WrongConstant")
    public CardFragmentAdapter(@NonNull FragmentManager fm, List<String> mDatas) {
        super(fm);
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
}