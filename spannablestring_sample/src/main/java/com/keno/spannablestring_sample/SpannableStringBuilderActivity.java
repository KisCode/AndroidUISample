package com.keno.spannablestring_sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description: 演示SpannableStringBuilder的使用
 * Author: keno
 * CreateDate: 2020/7/14 21:55
 */
public class SpannableStringBuilderActivity extends AppCompatActivity {

    private TextView tvContent;

    public static void start(Context context) {
        Intent starter = new Intent(context, SpannableStringBuilderActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_builder);
        initView();

        /***
         * SpannableStringBuilder 和SpannableString区别可以append
         */
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        List<SpannableString> spannableStringList = getSpannableLists();
        for (SpannableString spannableStr : spannableStringList) {
            spannableStringBuilder.append(spannableStr);
            //获取SpannableString包含的span
            CharacterStyle[] spanList = spannableStr.getSpans(0, spannableStr.length(), ClickableSpan.class);
            for (CharacterStyle span : spanList) {
                Log.i("SpanType", span.getClass().getName());
            }
            spannableStringBuilder.append("\n");
        }
        tvContent.setText(spannableStringBuilder);
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());

     /*   final String text= "00000000000000000";
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary));
        spannableString.setSpan(foregroundColorSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.i("clickableSpan", text);
            }
        };
        spannableString.setSpan(clickableSpan, 0, 1, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        tvContent.setText(spannableString);
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());*/

    }

    private List<SpannableString> getSpannableLists() {
        List<SpannableString> spannableStringList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final String text = String.format("%d .  %d", i, random.nextInt(10000));
            SpannableString spannableString = new SpannableString(text);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary));
            spannableString.setSpan(foregroundColorSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    Log.i("clickableSpan", text);
                }
            };
            spannableString.setSpan(clickableSpan, 0, 1, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableStringList.add(spannableString);
        }

        return spannableStringList;
    }

    private void initView() {
        tvContent = (TextView) findViewById(R.id.tv_content);
    }
}