package com.sooch.qiita_reader.data.network;

import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.data.entity.Tag;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * {@link retrofit2.Retrofit#create(Class)}に渡すインターフェース定義.
 * <p> 記載方法はRetrofit2仕様に準拠する.
 *
 * Created by Takashi Sou on 2016/09/23.
 * @see <a href="https://square.github.io/retrofit/">Retrofit</a>
 * @see <a href="https://qiita.com/api/v2/docs#%E6%A6%82%E8%A6%81">Qiita APIv2 概要</a>
 */

public interface QiitaV2Service {
    /**
     * タグの取得
     * @param sort
     * @return
     */
    @GET("/api/v2/tags?page=1&per_page=100")
    Observable<List<Tag>> tags(@Query("sort") String sort);

    /**
     * 全投稿を投稿順で取得
     * @param page
     * @param perPage
     * @return
     */
    @GET("/api/v2/items?")
    Observable<List<Post>> items(@Query("page") int page, @Query("per_page") int perPage);

    /**
     * タグ{@code id}が紐付けられた投稿を投稿順で取得
     * @param id {@link Tag} 形式のタグ
     * @param page ページ番号 (1-100)
     * @param perPage 1ページ当たりの取得数 (1-100)
     * @return
     */
    @GET("/api/v2/tags/{id}/items?")
    Observable<List<Post>> tagItems(@Path("id") String id, @Query("page") int page,
                                    @Query("per_page") int perPage);
}
