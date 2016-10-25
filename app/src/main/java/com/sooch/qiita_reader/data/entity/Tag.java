package com.sooch.qiita_reader.data.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 投稿に付けられた個々のタグを表します.
 *
 * @see <a href="https://qiita.com/api/v2/docs#%E3%82%BF%E3%82%B0">タグ</a>
 * Created by Takashi Sou on 2016/09/23.
 */

public class Tag implements Serializable {

    private static final long serialVersionUID = 2674951971944010989L;

    /** このタグをフォローしているユーザの数 */
    @SerializedName("followers_count")
    public int followersCount;

    /** このタグに設定されたアイコン画像のURL */
    @SerializedName("icon_url")
    public String iconUrl;

    /** タグを特定するための一意な名前 */
    @SerializedName("id")
    public String id;

    /** このタグが付けられた投稿の数 */
    @SerializedName("items_count")
    public int itemsCount;

}
