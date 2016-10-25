package com.sooch.qiita_reader.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Qiita上のユーザを表します.
 *
 * @see <a hreh="https://qiita.com/api/v2/docs#%E3%83%A6%E3%83%BC%E3%82%B6">ユーザ</a>
 * Created by Takashi Sou on 2016/10/04.
 */

public class User implements Serializable {

    private static final long serialVersionUID = 228206029342792812L;

    /** 自己紹介文 */
    @SerializedName("description")
    public String description;

    /** Facebook ID */
    @SerializedName("facebook_id")
    public String facebookId;

    /** このユーザがフォローしているユーザの数 */
    @SerializedName("followees_count")
    public int followeesCount;

    /** このユーザをフォローしているユーザの数 */
    @SerializedName("followers_count")
    public int followersCount;

    /** GitHub ID */
    @SerializedName("github_login_name")
    public String githubLoginName;

    /** ユーザID */
    @SerializedName("id")
    public String id;

    /** このユーザが qiita.com 上で公開している投稿の数 (Qiita:Teamでの投稿数は含まれません) */
    @SerializedName("items_count")
    public int itemsCount;

    /** LinkedIn ID */
    @SerializedName("linkedin_id")
    public String linkedinId;

    /** 居住地 */
    @SerializedName("location")
    public String location;

    /** 設定している名前 */
    @SerializedName("name")
    public String name;

    /** 所属している組織 */
    @SerializedName("organization")
    public String organization;

    /** ユーザごとに割り当てられる整数のID */
    @SerializedName("permanent_id")
    public int permanentId;

    /** 設定しているプロフィール画像のURL */
    @SerializedName("profile_image_url")
    public String profileImageUrl;

    /** Twitterのスクリーンネーム */
    @SerializedName("twitter_screen_name")
    public String twitterScreenName;

    /** 設定しているWebサイトのURL */
    @SerializedName("website_url")
    public String websiteUrl;

}
