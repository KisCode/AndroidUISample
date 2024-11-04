package kiscode.material.design.util;

import android.content.Context;

/**
 * Description: 状态栏相关工具类
 * Author: keno
 * Date : 2021/4/27 16:13
 **/
public class StatusBarUtil {
    /***
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }
} 