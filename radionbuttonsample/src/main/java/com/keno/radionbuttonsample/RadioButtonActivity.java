package com.keno.radionbuttonsample;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class RadioButtonActivity extends AppCompatActivity {
    private static final String TAG = "RadioButtonActivity";
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //radioGroup动态添加 item
        radioGroup = findViewById(R.id.radioGourp2);
        for (int i = 0; i < 4; i++) {
//            TextView tv = new TextView(this);
//            tv.setText("radio " + (i + 1));
            RadioButton radioButton = new RadioButton(this);
            String text = "radio " + (i + 1);
            radioButton.setText(text);
            Log.i(TAG, "ID:" + radioButton.getId() + ",\ttext;" + text);
            radioGroup.addView(radioButton, i);
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i(TAG, "ID:" + checkedId);
                RadioButton radioButton = findViewById(checkedId);
                Log.i(TAG, "radioButton:" + radioButton.isChecked());
            }
        });
    }
}
