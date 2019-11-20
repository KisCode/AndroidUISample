package keno.android.ui.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
设置 GONE属性后，控件就不会出现在布局中了，B控件对A控件的margin属性也就没有作用了。
 但是 ConstraintLayout 能对已经设置 GONE属性的控件进行特殊处理。当A控件设置 GONE之后，A控件相当于变成了一个点，B控件相对于对A的约束仍然是起作用的。
 */
public class MarginGoneActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivAvatar;
    private TextView tvUsername;
    private TextView tvEmail;
    private Button btnGone;
    private Button btnVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margin_gone);
        initView();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MarginGoneActivity.class);
        context.startActivity(starter);
    }

    private void initView() {
        ivAvatar = findViewById(R.id.iv_avatar);
        tvUsername = findViewById(R.id.tv_username);
        tvEmail = findViewById(R.id.tv_email);
        btnGone = (Button) findViewById(R.id.btn_gone);
        btnGone.setOnClickListener(this);
        btnVisible = (Button) findViewById(R.id.btn_visible);
        btnVisible.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gone:
                tvUsername.setVisibility(View.GONE);
                //btnVisible 距离左边控件btnGone marginStart = 20dp，layout_goneMarginStart=0 ,当btnGone设置为gone时，layout_goneMarginStart 生效
                btnGone.setVisibility(View.GONE);
                break;
            case R.id.btn_visible:
                tvUsername.setVisibility(View.VISIBLE);

                btnGone.setVisibility(View.VISIBLE);
                break;
        }
    }
}
