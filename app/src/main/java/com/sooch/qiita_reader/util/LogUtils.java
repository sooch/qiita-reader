package com.sooch.qiita_reader.util;

import android.util.Log;

import com.sooch.qiita_reader.BuildConfig;

/**
 * {@link Log}のヘルパークラス.
 * Created by Takashi Sou on 2016/09/28.
 */
public class LogUtils {

    /**
     * ログ出力制御
     * {@code DEBUG == true}の場合のみログを出力する.
     */
    private static final boolean DEBUG = BuildConfig.DEBUG;

    /**
     * デバッグ出力用
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * エラー出力用
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    /**
     * 情報出力用
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    /**
     * 冗長ログ出力用
     * <p>障害解析など必要情報の詳細を出力する際に使用する.
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    /**
     * 警告情報出力.
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }
}