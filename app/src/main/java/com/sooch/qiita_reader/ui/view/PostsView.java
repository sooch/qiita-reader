package com.sooch.qiita_reader.ui.view;

import android.content.Context;

import com.sooch.qiita_reader.data.entity.Post;

import java.util.List;

/**
 * 投稿画面インターフェース.
 * Created by Takashi Sou on 2016/09/26.
 */

public interface PostsView {
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
     * 投稿を表示する
     * @param posts
     */
    void renderItems(List<Post> posts);

    /**
     * エラー通知
     * @param message
     */
    void showError(String message);
}
