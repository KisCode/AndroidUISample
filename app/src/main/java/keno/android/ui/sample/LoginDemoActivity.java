package keno.android.ui.sample;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginDemoActivity extends AppCompatActivity {
    private static final String DEAFULT_NAME = "android";
    private static final String DEAFULT_PASSWOR = "1234";
    public static final String SP_NAME = "sp_user";
    public static final String SP_KEY_USERNAME = "userName";
    public static final String SP_KEY_PASSWORD = "password";


    private EditText etName;
    private EditText etPwd;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_demo);

        initViews();
        initData();
    }

    private void initData() {
        //1.初始化 SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        //2. 获取sharedPreferences存储变量
        String userName = sharedPreferences.getString(SP_KEY_USERNAME, "");
        String pwd = sharedPreferences.getString(SP_KEY_PASSWORD, "");

        etName.setText(userName);
        etPwd.setText(pwd);
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
                    saveUserToSp(name, pwd);
                } else {
                    Toast.makeText(LoginDemoActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserToSp(String username, String pwd) {
        //1. 创建SharedPreferences对象，参数1 为sp文件名， 参数2为 私有模式只能本应用程序读、写
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        //2. 实例化一个Editor对象 用于对数据进行存储
        SharedPreferences.Editor editor = sp.edit();
        //3. 存储
        editor.putString(SP_KEY_USERNAME, username);
        editor.putString(SP_KEY_PASSWORD, pwd);
        //4. 提交
        editor.commit();
    }
}
