package demo.kiscode.webview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import demo.kiscode.webview.fragment.WebviewFragment;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://juejin.cn/post/6844903567506014222";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadWebview();
    }

    private void loadWebview() {
        WebviewFragment webviewFragment = WebviewFragment.newInstance(URL);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, webviewFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}