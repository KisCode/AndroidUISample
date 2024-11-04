package com.kiscode.emoji;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.widget.EmojiTextView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifTextView;
import pl.droidsonroids.gif.GifTextureView;
import pl.droidsonroids.gif.MultiCallback;

public class MainActivity extends AppCompatActivity {
    private TextView tvGif;
    private GifTextView tvGifSupport;
    private ImageView ivGif;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String emojiStr = "笑脸：\uD83D\uDE01";

        EmojiTextView tvContent = findViewById(R.id.emoji_tv_content);
        tvContent.setText(emojiStr);

        tvGif = findViewById(R.id.tv_gif);
        ivGif = findViewById(R.id.iv_gif);
        tvGifSupport = findViewById(R.id.tv_gif_support);
        btnLoad = findViewById(R.id.btn_load_gif);

        //图片方式加载
//        Glide.with(this)
//                .asGif()
//                .load(R.drawable.biggif)
//                .into(ivGif);


        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
//                Drawable drawable = ContextCompat.getDrawable(MainActivity.this, R.mipmap.biggif);
//
//                drawable.setBounds(0, 0, 280, 280);
//                SpannableString spannableString = new SpannableString("This is a gif image!");
//                ImageSpan imageSpan = new GifImageSpan(drawable, -1);
//                spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//                tvGif.setText(spannableString);
//                loadGifDrawable();
                loadGifDrawable2();
            }
        });
    }

    public void loadGifDrawable() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    GifDrawable drawable = Glide.with(MainActivity.this)
//                            .asDrawable()
                            .asGif()
                            .load(R.drawable.biggif)
                            .submit()
                            .get();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            drawable.setBounds(0, 0, 280, 280);
                            SpannableString spannableString = new SpannableString("This is a gif image!");
                            GlideGifImageSpan imageSpan = new GlideGifImageSpan(drawable);
                            spannableString.setSpan(imageSpan, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                            tvGif.setText(spannableString);

//                            drawable.startFromFirstFrame();
//                            ivGif.setImageDrawable(drawable);
                            drawable.setVisible(false,false);
                            ivGif.setImageDrawable(drawable);
//                            tvGif.invalidateDrawable(drawable);
                        }
                    });
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadGifDrawableImageSpan() {
        SpannableString spannableString = new SpannableString("This is a gif image!");
        try {
            pl.droidsonroids.gif.GifDrawable gifDrawable = new pl.droidsonroids.gif.GifDrawable(getResources(), R.drawable.biggif);

            ImageSpan imageSpan = new ImageSpan(gifDrawable);
            spannableString.setSpan(imageSpan, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            tvGif.setText(spannableString);
            tvGifSupport.setText(spannableString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGifDrawable2() {
        SpannableString spannableString = new SpannableString("This is a gif image!");
        try {
            pl.droidsonroids.gif.GifDrawable gifDrawable = new pl.droidsonroids.gif.GifDrawable(getResources(), R.drawable.biggif);

            gifDrawable.setBounds(0, 0, 280, 280);
            ImageSpan imageSpan = new ImageSpan(gifDrawable);
            spannableString.setSpan(imageSpan, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            tvGif.setText(spannableString);
//            tvGifSupport.setText(spannableString);
            GifEmoticonHelper.getInstance().playGifEmoticon(tvGif);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        GifEmoticonHelper.getInstance().stopGifEmoticon(tvGif);
    }
}