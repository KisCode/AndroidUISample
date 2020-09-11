package demo.kiscode.start.opt;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/***
 * 应用启动优化
 */
public class StartOptMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            Thread.sleep(1200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        setContentView(R.layout.activity_start_opt_main);
    }
}
