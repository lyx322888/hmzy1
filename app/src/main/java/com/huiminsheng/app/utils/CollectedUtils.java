package com.huiminsheng.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

//收集的一些工具
public class CollectedUtils {
    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     * @param phoneNum 电话号码
     */
    public static void callPhone(Activity activity, String phoneNum) {
        if (!TextUtils.isEmpty(phoneNum)){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + phoneNum);
            intent.setData(data);
            activity.startActivity(intent);
        }
    }
    //获取当前版本号
    /**
     * 获取当前应用版本号
     */
    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }
    /**
     * 获取当前应用版本名
     */
    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "0";
        }
    }
}
