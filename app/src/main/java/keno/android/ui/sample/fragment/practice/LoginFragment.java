package keno.android.ui.sample.fragment.practice;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import keno.android.ui.sample.R;

/**
 * Description: 登录页面
 * Author: keno
 * Date : 2021/5/12 18:18
 **/
public class LoginFragment extends Fragment {
    public static final String SP_NAME = "sp_user";
    public static final String SP_KEY_USERNAME = "userName";
    public static final String SP_KEY_PASSWORD = "password";
    private static final String DEAFULT_NAME = "android";
    private static final String DEAFULT_PASSWOR = "1234";
    private EditText etName;
    private EditText etPwd;
    private TextView tvLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login_demo, container, false);
        initViews(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        //1.初始化 SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        //2. 获取sharedPreferences存储变量
        String userName = sharedPreferences.getString(SP_KEY_USERNAME, "");
        String pwd = sharedPreferences.getString(SP_KEY_PASSWORD, "");

        etName.setText(userName);
        etPwd.setText(pwd);
    }

    private void initViews(View root) {
        etName = root.findViewById(R.id.et_username);
        etPwd = root.findViewById(R.id.et_password);
        tvLogin = root.findViewById(R.id.tv_login);

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
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    saveUserToSp(name, pwd);
                } else {
                    Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserToSp(String username, String pwd) {
        //1. 创建SharedPreferences对象，参数1 为sp文件名， 参数2为 私有模式只能本应用程序读、写
        SharedPreferences sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        //2. 实例化一个Editor对象 用于对数据进行存储
        SharedPreferences.Editor editor = sp.edit();
        //3. 存储
        editor.putString(SP_KEY_USERNAME, username);
        editor.putString(SP_KEY_PASSWORD, pwd);
        //4. 提交
        editor.commit();
    }
}