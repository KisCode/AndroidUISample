package keno.android.ui.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginDemoActivity extends AppCompatActivity {
    private static final String DEAFULT_NAME = "android";
    private static final String DEAFULT_PASSWOR = "1234";


    private EditText etName;
    private EditText etPwd;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_demo);

        initViews();
    }

    private void initViews() {
        etName = findViewById(R.id.et_username);
        etPwd = findViewById(R.id.et_password);
        tvLogin = findViewById(R.id.tv_login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String pwd = etPwd.getText().toString();
                Log.i("Login", "name:" + name + " \t password:" + pwd);
                Log.v("Login", "name:" + name + " \t password:" + pwd);
                Log.d("Login", "name:" + name + " \t password:" + pwd);
                Log.w("Login", "name:" + name + " \t password:" + pwd);
                Log.e("Login", "name:" + name + " \t password:" + pwd);

                if (DEAFULT_NAME.equals(name) && DEAFULT_PASSWOR.equals(pwd)) {
                    Toast.makeText(LoginDemoActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginDemoActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
