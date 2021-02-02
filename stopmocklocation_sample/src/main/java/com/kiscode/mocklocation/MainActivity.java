package com.kiscode.mocklocation;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Process;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * Description: 禁用模拟定位
 * <p>
 * 1. 开发者选项 -> 模拟定位
 * 2. 第三方应用多开 -> 虚拟环境设置多开
 * Author: keno
 * Date : 2021/2/2 16:56
 **/
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "StopMockLocation";
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.tv_text_content);

//        boolean isMockLocation = isMockLocation(this);
//        Log.i(TAG, "isMockLocation = " + isMockLocation);

        Log.i(TAG, "isMockLocation = " + MockLocationDetector.checkForAllowMockLocationsApps(this));

        Log.i(TAG, "isEnableAdb = " + isEnableDevelopmentSetting());
        Toast.makeText(this, "isEnableAdb " + isEnableDevelopmentSetting(), Toast.LENGTH_SHORT).show();

    }

    public void refresh(View view) {

        UserManager userManager = (UserManager) getSystemService(Context.USER_SERVICE);
        StringBuffer stringBuffer = new StringBuffer();
        Log.i(TAG, "isDualApp = " + isDualApp());

        stringBuffer.append("androidId =").append(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        stringBuffer.append("\n");
        stringBuffer.append("packageName =").append(getPackageName());
        stringBuffer.append("\n");
        stringBuffer.append("getCacheDir =").append(getCacheDir().getAbsolutePath());
        stringBuffer.append("\n");
        stringBuffer.append("processId =").append(Process.myUid());
        stringBuffer.append("\n");
        stringBuffer.append("isDualApp = ").append(isDualApp());
        stringBuffer.append("\n");
        stringBuffer.append("Enable DevelopmentSetting = ").append(isEnableDevelopmentSetting());
//        Toast.makeText(this, "isEnableDevelopmentSetting " + isEnableDevelopmentSetting(), Toast.LENGTH_SHORT).show();
        tvContent.setText(stringBuffer);
    }

    /***
     * 是否开启了开发者选项
     * @return
     */
    private boolean isEnableDevelopmentSetting() {
        return Settings.Secure.getInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) > 0;
    }

    boolean isDualApp() {
        return 0 != Process.myUid() / 100000;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startRequestLocation();
        }
    }

    private void startRequestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000L,
                    1f,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            boolean isfromMockProvider = location.isFromMockProvider();
                            Log.i(TAG, "isfromMockProvider = " + isfromMockProvider);
                        }
                    });
        }
    }

    private boolean isMockLocation(Context context) {
        boolean isMock = false;
        if (android.os.Build.VERSION.SDK_INT >= 18) {

            AppOpsManager opsManager = (AppOpsManager) getApplicationContext().getSystemService(Context.APP_OPS_SERVICE);
            isMock = (opsManager.checkOp(AppOpsManager.OPSTR_MOCK_LOCATION, android.os.Process.myUid(), BuildConfig.APPLICATION_ID) == AppOpsManager.MODE_ALLOWED);

            /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 10086);
                return false;
            } else {
                startRequestLocation();
            }*/
        } else {
            isMock = !Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION).equals("0");
        }

        return isMock;
    }

}