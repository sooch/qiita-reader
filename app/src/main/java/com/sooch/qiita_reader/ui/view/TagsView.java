package com.sooch.qiita_reader.ui.view;

import android.content.Context;

import com.sooch.qiita_reader.data.entity.Tag;

import java.util.List;

/**
 * タグ一覧画面インターフェース.
 * Created by Takashi Sou on 2016/09/26.
 */

public interface TagsView {
    /**
     * コンテキストの取得
     * @return
     */
    Context context();

    /**
     * ローディングインジケータの表示
     */
    void showLoading();

    /**
     * ローディングインジケータの解除
     */
    void dismissLoading();

    /**
     * タグを表示する
     * @param tags
     */
    void renderTags(List<Tag> tags);
}
