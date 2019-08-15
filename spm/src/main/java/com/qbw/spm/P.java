package com.qbw.spm;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.qbw.l.L;

import java.lang.reflect.Type;

/*
 * SharedPreferenceManager管理工具类
 * @author QBW
 * @date 2019/8/15
 */
public class P {

    private static Context sContext;
    private static SharedPreferences sSharedPreferences;
    private static Gson sGson;
    private static L sXLog = new L();

    /**
     * call this method in Application's onCreate method
     */
    public static void init(Context context, boolean debug) {
        sContext = context;
        sSharedPreferences = context.getSharedPreferences("P_qbw", Context.MODE_PRIVATE);
        sGson = new Gson();
        sXLog.setFilterTag("[P]");
        sXLog.setEnabled(debug);
    }

    public static void putInt(String key, int value) {
        sXLog.d("key[%s], value[%d]", key, value);
        sSharedPreferences.edit().putInt(key, value).commit();
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        int value = sSharedPreferences.getInt(key, defaultValue);
        sXLog.d("key[%s], value[%d]", key, value);
        return value;
    }

    public static void putLong(String key, long value) {
        sSharedPreferences.edit().putLong(key, value).commit();
        sXLog.d("key[%s], value[%d]", key, value);
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        long value = sSharedPreferences.getLong(key, defaultValue);
        sXLog.d("key[%s], value[%d]", key, value);
        return value;
    }

    public static void putString(String key, String value) {
        sSharedPreferences.edit().putString(key, value).commit();
        sXLog.d("key[%s], value[%s]", key, value);
    }

    public static String getString(String key) {
        return sSharedPreferences.getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        String value = sSharedPreferences.getString(key, defaultValue);
        sXLog.d("key[%s], value[%s]", key, value);
        return value;
    }

    public static void putFloat(String key, float value) {
        sSharedPreferences.edit().putFloat(key, value).commit();
        sXLog.d("key[%s], value[%f]", key, value);
    }

    public static float getFloat(String key) {
        return getFloat(key, .0F);
    }

    public static float getFloat(String key, float defaultValue) {
        float value = sSharedPreferences.getFloat(key, defaultValue);
        sXLog.d("key[%s], value[%f]", key, value);
        return value;
    }

    public static void putBoolean(String key, boolean value) {
        sSharedPreferences.edit().putBoolean(key, value).commit();
        sXLog.d("key[%s], value[%b]", key, value);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        boolean value = sSharedPreferences.getBoolean(key, defaultValue);
        sXLog.d("key[%s], value[%b]", key, value);
        return value;
    }

    public static <T> T getObject(String key, Type type) {
        try {
            String json = getString(key);
            sXLog.d("key[%s], value[%s], class[%s]", key, json, type.toString());
            return sGson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void putObject(String key, Object value) {
        try {
            String json = "{}";
            String className = "null";
            if (value != null) {
                json = sGson.toJson(value);
                className = value.getClass().getName();
            }
            sSharedPreferences.edit().putString(key, json).commit();
            sXLog.d("key[%s], value[%s], class[%s]", key, json, className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
