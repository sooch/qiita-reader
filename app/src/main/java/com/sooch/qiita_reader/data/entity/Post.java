package com.sooch.qiita_reader.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * ユーザからの投稿を表します.
 *
 * @see <a href="https://qiita.com/api/v2/docs#%E6%8A%95%E7%A8%BF">投稿</a>
 * Created by Takashi Sou on 2016/10/04.
 */

public class Post implements Serializable {

    private static final long serialVersionUID = 3721548427838024657L;

    /** HTML形式の本文 */
    @SerializedName("rendered_body")
    public String renderedBody;

    /** Markdown形式の本文 */
    @SerializedName("body")
    public String body;

    /**
     * データ作成日時
     * "2000-01-01T00:00:00+00:00"
     */
    @SerializedName("created_at")
    public String createdAt;

    /** 投稿の一意なID */
    @SerializedName("id")
    public String id;

    /** 限定共有状態かどうかを表すフラグ (Qiita:Teamでは無効) */
    @SerializedName("private")
    public boolean isPrivate;

    /**
     * 投稿に付いたタグ一覧
     * Example: [{"name"=>"Ruby", "versions"=>["0.0.1"]}]
     * {@link Tag}
     */
    @SerializedName("tags")
    public List<Tag> tags;

    /** 投稿のタイトル */
    @SerializedName("title")
    public String title;

    /**
     * データ更新日時
     * "2000-01-01T00:00:00+00:00"
     */
    @SerializedName("update_at")
    public String updateAt;

    /** 投稿のURL */
    @SerializedName("url")
    public String url;

    /**
     * Qiita上のユーザ
     * {@link User}
     */
    @SerializedName("user")
    public User user;


    /**
     * 投稿に付いたタグ
     */
    public static class Tag implements Serializable {

        private static final long serialVersionUID = 5811599780226735745L;

        @SerializedName("name")
        public String name;
    }
}
