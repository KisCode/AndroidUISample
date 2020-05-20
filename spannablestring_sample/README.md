# RadionButton Sample

# SpannableString 的基本用法

```java
    /***
     * 为文本设置字体颜色
     */
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

    /**
     * 设置字体大小
     */
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
```