package com.kiscode.emoji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.widget.EmojiTextView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String emojiStr ="笑脸：\uD83D\uDE01";

        EmojiTextView tvContent = findViewById(R.id.emoji_tv_content);
        tvContent.setText(emojiStr);
    }

}