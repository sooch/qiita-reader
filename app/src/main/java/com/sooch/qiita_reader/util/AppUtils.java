package com.sooch.qiita_reader.util;

import com.sooch.qiita_reader.data.entity.Post;

import java.util.List;

import rx.Observable;

/**
 * アプリ仕様に関わるメソッドを提供するユーティリティクラス.
 * Created by Takashi Sou on 2016/10/11.
 */

public class AppUtils {

    private AppUtils() {
    }

    /**
     * アプリ表示日付形式に変換
     * @param date {@link com.sooch.qiita_reader.data.entity.Post#createdAt} format.
     * @return YYYY-DD-MM
     */
    public static String forAppDate(String date) {
        return date.substring(0, date.indexOf("T")).replace("-", "/");
    }

    /**
     * タグリストを文字列に変換
     * @param tags
     * @return
     */
    public static String toString(List<Post.Tag> tags) {
        return Observable.from(tags)
                .reduce("", (r, tag) -> r + tag.name + " ")
                .toBlocking()
                .single();
    }
}
