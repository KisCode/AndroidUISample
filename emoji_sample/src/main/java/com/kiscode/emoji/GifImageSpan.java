package com.kiscode.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

import pl.droidsonroids.gif.GifDrawable;

//import com.bumptech.glide.load.resource.gif.GifDrawable;

//import com.bumptech.glide.load.resource.gif.GifDrawable;

/**
 * Description: 支持gif 动画的span
 * Date : 2024-05-16 10:20
 **/
public class GifImageSpan extends ImageSpan{
    public GifImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    @Override
    public Drawable getDrawable() {
        Drawable drawable = super.getDrawable();
        if (drawable instanceof GifDrawable) {
            ((GifDrawable) drawable).start();
            ((GifDrawable) drawable).setLoopCount(1000);
        }
        drawable.invalidateSelf();
        return drawable;
    }
}
