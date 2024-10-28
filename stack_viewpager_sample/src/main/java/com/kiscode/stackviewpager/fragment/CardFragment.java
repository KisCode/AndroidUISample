package com.kiscode.stackviewpager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kiscode.stackviewpager.R;

/**
 * Description:
 * Author: keno
 * Date : 2021/2/20 14:03
 **/
public class CardFragment extends Fragment {
    private static final String KEY_CONTENT = "CONTENT";

    public static CardFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(KEY_CONTENT, content);
        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, null);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String content = getArguments().getString(KEY_CONTENT);
        TextView tvContent = view.findViewById(R.id.tv_content_card);
        tvContent.setText(content);
    }
}