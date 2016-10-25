package com.sooch.qiita_reader.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Set;

/**
 * {@link SharedPreferences}のヘルパークラス.
 * Created by Takashi Sou on 2016/08/14.
 */
public class PrefUtils {

    /**
     * 内部ストレージの保存名
     */
    private static final String PREFERENCE_NAME = "8e21a0ef";

    /**
     * プリファレンスを取得
     *
     * @param context
     * @return
     */
    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
    }

    /**
     * エディターを取得
     *
     * @param context
     * @return
     */
    private static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    /**
     * Stringの取得
     *
     * @param key
     * @param defValue
     * @return
     */
    @Nullable
    public static String getString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    /**
     * Set<String>の取得
     *
     * @param context
     * @param key
     * @param defValues
     * @return
     */
    @Nullable
    public static Set<String> getStringSet(Context context, String key, Set<String> defValues) {
        return getPreferences(context).getStringSet(key, defValues);
    }

    /**
     * intの取得
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    /**
     * longの取得
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);
    }

    /**
     * floatの取得
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    /**
     * booleanの取得
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    /**
     * Stringを保存
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, @Nullable String value) {
        getEditor(context).putString(key, value).apply();
    }

    /**
     * Set<String> (重複要素無しのコレクション型)を保存
     *
     * @param context
     * @param key
     * @param values
     */
    public static void putStringSet(Context context, String key, @Nullable Set<String> values) {
        getEditor(context).putStringSet(key, values).apply();
    }

    /**
     * int型を保存
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putInt(Context context, String key, int value) {
        getEditor(context).putInt(key, value).apply();
    }

    /**
     * long型を保存
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).apply();
    }

    /**
     * float型を保存
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).apply();
    }

    /**
     * boolean型を保存
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).apply();
    }

    /**
     * 削除
     *
     * @param context コンテキスト
     * @param key     削除する対象のキー
     */
    public static void remove(Context context, String key) {
        getEditor(context).remove(key).apply();
    }

    /**
     * 全削除
     *
     * @param context コンテキスト
     */
    public static void clear(Context context) {
        getEditor(context).clear().apply();
    }
}