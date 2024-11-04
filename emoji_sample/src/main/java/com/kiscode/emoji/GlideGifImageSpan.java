package com.kiscode.emoji;

import android.graphics.Bitmap;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.resource.gif.GifDrawable;

/**
 * Description: 支持gif 动画的span
 * Date : 2024-05-16 10:20
 **/
public class GlideGifImageSpan extends ImageSpan {
    public GlideGifImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    @Override
    public Drawable getDrawable() {
        Drawable drawable = super.getDrawable();
        if (drawable instanceof GifDrawable) {
            ((GifDrawable) drawable).setLoopCount(GifDrawable.LOOP_FOREVER);
            ((GifDrawable) drawable).start();
        }
        drawable.invalidateSelf();
//        drawable.invalidateSelf();
//        drawable.setVisible(true,true);
        return drawable;
    }
}
