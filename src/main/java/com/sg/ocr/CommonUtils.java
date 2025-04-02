package com.sg.ocr;



import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;



import java.lang.reflect.Method;

public class CommonUtils {


    public static String getSN(Context context) {
        String serial = "";

        //通过反射获取sn号
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String)get.invoke(c, "ro.serialno");

            if(TextUtils.isEmpty(serial)){
                serial = (String) get.invoke(c, "persist.radio.sn");
            }

            if (!TextUtils.isEmpty(serial) && !serial.equalsIgnoreCase("unknown")) {
                return serial;
            }

            //9.0及以上无法获取到sn，此方法为补充，能够获取到多数高版本手机 sn
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                serial = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
            }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serial = Build.getSerial();
            }

        } catch (Exception e) {
            serial = "";
        }
        return serial;
    }



}
