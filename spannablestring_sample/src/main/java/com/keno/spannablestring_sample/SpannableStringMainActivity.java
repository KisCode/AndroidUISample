package com.keno.spannablestring_sample;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Description: SpannableString 的基本用法
 * Author: keno
 * CreateDate: 2020/5/20 20:23
 */
public class SpannableStringMainActivity extends AppCompatActivity {
    private static final String FOREGROUND_STRING = "Hello world, SpannableString is ";
    private TextView tvForeground;
    private TextView tvBackground;
    private TextView tvRelativeSize;
    private TextView tvStrikethroughSpan;
    private TextView tvUnderlineSpan;
    private TextView tvSuperscriptSpan;
    private TextView tvSubscriptSpan;
    private TextView tvStyleSpan;
    private TextView tvImageSpan;
    private TextView tvClickableSpan;
    private TextView tvURLSpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_main);
        initView();
        initForegroundColorSpan();
        initBackroundColorSpan();
        initRelativeSizeSpan();
        initStrikethroughSpan();
        initUnderlineSpan();
        initSuperscriptSpan();
        initSubscriptSpan();
        initStyleSpan();
        initImageSpan();
        initClickableSpan();
        initURLSpan();
    }

    private void initView() {
        tvForeground = findViewById(R.id.tv_foreground);
        tvBackground = findViewById(R.id.tv_background);
        tvRelativeSize = findViewById(R.id.tv_RelativeSize);
        tvStrikethroughSpan = findViewById(R.id.tv_StrikethroughSpan);
        tvUnderlineSpan = findViewById(R.id.tv_UnderlineSpan);
        tvSuperscriptSpan = findViewById(R.id.tv_SuperscriptSpan);
        tvSubscriptSpan = findViewById(R.id.tv_SubscriptSpan);
        tvStyleSpan = findViewById(R.id.tv_StyleSpan);
        tvImageSpan = findViewById(R.id.tv_ImageSpan);
        tvClickableSpan = findViewById(R.id.tv_ClickableSpan);
        tvURLSpan = findViewById(R.id.tv_URLSpan);
    }


    void initForegroundColorSpan() {
/*
•SPAN_INCLUSIVE_EXCLUSIVE：包括开始下标，但不包括结束下标
•SPAN_EXCLUSIVE_INCLUSIVE：不包括开始下标，但包括结束下标
•SPAN_INCLUSIVE_INCLUSIVE：既包括开始下标，又包括结束下标
•SPAN_EXCLUSIVE_EXCLUSIVE：不包括开始下标，也不包括结束下标
             */
        String text = FOREGROUND_STRING + tvForeground.getText();
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan span = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAccent));
        spannableString.setSpan(span, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvForeground.setText(spannableString);
    }

    /***
     * 为文本设置下划线
     */
    void initBackroundColorSpan() {
        String text = FOREGROUND_STRING + tvBackground.getText();
        SpannableString spannableString = new SpannableString(text);
        BackgroundColorSpan span = new BackgroundColorSpan(ContextCompat.getColor(this, R.color.colorAccent));
        spannableString.setSpan(span, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvBackground.setText(spannableString);
    }

    void initRelativeSizeSpan() {
        String text = FOREGROUND_STRING + tvRelativeSize.getText();
        SpannableString spannableString = new SpannableString(text);
        //放大字体倍数
        RelativeSizeSpan span = new RelativeSizeSpan(1.5f);
        spannableString.setSpan(span, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvRelativeSize.setText(spannableString);
    }


    /***
     * 为文本设置删除线
     */
    private void initStrikethroughSpan() {
        String text = FOREGROUND_STRING + tvStrikethroughSpan.getText();
        SpannableString spannableString = new SpannableString(text);
        //设置删除线
        StrikethroughSpan span = new StrikethroughSpan();
        spannableString.setSpan(span, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvStrikethroughSpan.setText(spannableString);
    }


    private void initUnderlineSpan() {
        String text = FOREGROUND_STRING + tvUnderlineSpan.getText();
        SpannableString spannableString = new SpannableString(text);
        //设置下划线
        UnderlineSpan span = new UnderlineSpan();
        spannableString.setSpan(span, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvUnderlineSpan.setText(spannableString);
    }

    /***
     * 为文本设置上标
     */
    private void initSuperscriptSpan() {
        String text = FOREGROUND_STRING + tvSuperscriptSpan.getText();
        SpannableString spannableString = new SpannableString(text);
        //为文本设置上标
        SuperscriptSpan span = new SuperscriptSpan();
        spannableString.setSpan(span, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvSuperscriptSpan.setText(spannableString);

    }

    /***
     * 为文本设置下标
     */
    private void initSubscriptSpan() {
        String text = FOREGROUND_STRING + tvSubscriptSpan.getText();
        SpannableString spannableString = new SpannableString(text);
        //为文本设置下标
        SubscriptSpan span = new SubscriptSpan();
        spannableString.setSpan(span, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvSubscriptSpan.setText(spannableString);

    }

    /***
     * 为文本设置样式
     */
    private void initStyleSpan() {
        String text = FOREGROUND_STRING + tvStyleSpan.getText();
        SpannableString spannableString = new SpannableString(text);
        StyleSpan spanBold = new StyleSpan(Typeface.BOLD); //设置粗体
        StyleSpan spanItalic = new StyleSpan(Typeface.ITALIC); //设置斜体
        spannableString.setSpan(spanBold, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(spanItalic, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvStyleSpan.setText(spannableString);
    }


    /**
     * 图文混排
     */
    private void initImageSpan() {
        String text = " " + FOREGROUND_STRING + tvImageSpan.getText();
        SpannableString spannableString = new SpannableString(text);
        //获取一张图片
        Drawable drawable = getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvImageSpan.setText(spannableString);
    }

    /**
     * 为文本设置点击事件
     */
    private void initClickableSpan() {
        String text = FOREGROUND_STRING + tvClickableSpan.getText();
        SpannableString spannableString = new SpannableString(text);
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(SpannableStringMainActivity.this, "Click ME!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                //设置点击时间不显示下划线
                ds.setUnderlineText(false);
            }
        };
        spannableString.setSpan(span, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvClickableSpan.setText(spannableString);
        tvClickableSpan.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /***
     * 设置超链接点击事件
     */
    private void initURLSpan() {
        String text = FOREGROUND_STRING + tvURLSpan.getText();
        SpannableString spannableString = new SpannableString(text);
        URLSpan urlSpan = new URLSpan("https://www.sogou.com/");
        spannableString.setSpan(urlSpan, FOREGROUND_STRING.length(), text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvURLSpan.setMovementMethod(LinkMovementMethod.getInstance());
        tvURLSpan.setHighlightColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        tvURLSpan.setText(spannableString);
    }


}
