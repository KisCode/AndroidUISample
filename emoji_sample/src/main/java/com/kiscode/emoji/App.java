package com.kiscode.emoji;


import android.app.Application;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.provider.FontRequest;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.text.FontRequestEmojiCompatConfig;


/****
 * Description:
 * Author:  keno
 * CreateDate: 2022/4/19 19:48
 */

public class App extends Application {
    private static final String TAG = "ApplicationX";

    @Override
    public void onCreate() {
        super.onCreate();
        /*FontRequest fontRequest = new FontRequest(
                "com.example.fontprovider",
                "com.example",
                "emoji compat Font Query",
                R.array.com_google_android_gms_fonts_certs);
        EmojiCompat.Config config = new FontRequestEmojiCompatConfig(this, fontRequest);
        config.setReplaceAll(true);
        config.registerInitCallback(new EmojiCompat.InitCallback() {
            @Override
            public void onInitialized() {
                super.onInitialized();
                Log.i(TAG, "onInitialized");
            }

            @Override
            public void onFailed(@Nullable Throwable throwable) {
                super.onFailed(throwable);
                Log.e(TAG, "onFailed:" + throwable);
            }
        });
        EmojiCompat.init(config);*/


        FontRequest fontRequest = new FontRequest(
                "com.example.fontprovider",
                "com.example",
                "emoji compat Font Query",
                R.array.com_google_android_gms_fonts_certs);
        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        config.setReplaceAll(true);
        config.registerInitCallback(new EmojiCompat.InitCallback() {
            @Override
            public void onInitialized() {
                //初始化成功回调
                Log.i(TAG, "onInitialized");
            }

            @Override
            public void onFailed(@Nullable Throwable throwable) {
                //初始化失败回调
                Log.e(TAG, "onFailed:" + throwable);
            }
        });
        EmojiCompat.init(config);
    }
}
