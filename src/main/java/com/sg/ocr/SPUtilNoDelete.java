package com.sg.ocr;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 退出登录的时候不需要被删除的缓存配置保存工具类
 */
class SPUtilNoDelete {

    private final String FILLNAME = "ocr_demo_no_delete";// 文件名称
    public SharedPreferences mSharedPreferences = null;

    private static class Holder {

        private static final SPUtilNoDelete INSTANCE = new SPUtilNoDelete();
    }

    private SPUtilNoDelete() {}

    public static SPUtilNoDelete getInstance() {
        return Holder.INSTANCE;
    }


    public void init(Context context) {
        mSharedPreferences = context.getSharedPreferences(FILLNAME, Context.MODE_PRIVATE);
    }

    /**
     * SharedPreferences常用的10个操作方法
     */
    public void putBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    public void putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }


    public void putLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }


    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    /**
     * 清除所有内容
     */
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }


    public void setIsAuthorized(boolean isAuthorized) {
        SPUtilNoDelete.getInstance().putBoolean("sp_key_is_authorized", isAuthorized);
    }

    public boolean getIsAuthorized() {
        return SPUtilNoDelete.getInstance().getBoolean("sp_key_is_authorized",false);
    }
}
