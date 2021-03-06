package com.sooch.qiita_reader.data.repository;

import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.data.entity.Tag;

import java.util.List;

import rx.Observable;

/**
 * Domainレイヤーからデータアクセスを行うインターフェース定義.
 * Created by Takashi Sou on 2016/09/23.
 */

public interface DataStore {
    /**
     * タグ一覧を取得
     * @see <a href="https://qiita.com/api/v2/docs#%E3%82%BF%E3%82%B0">タグ</a>
     * @param sort ソート条件
     * @return {@link List<Tag>}を{@link Observable}でラップして返却
     */
    Observable<List<Tag>> tags(final String sort);

    /**
     * 最新の投稿を取得
     * @see <a href="https://qiita.com/api/v2/docs#%E6%8A%95%E7%A8%BF">投稿</a>
     * @param page ページ番号 (1-100)
     * @param perPage 1ページ当たりの取得数 (1-100)
     * @return {@link List<Post>}を{@link Observable}でラップして返却
     */
    Observable<List<Post>> items(final int page, final int perPage);

    /**
     * タグの投稿を取得
     * @see <a href="https://qiita.com/api/v2/docs#%E6%8A%95%E7%A8%BF">投稿</a>
     * @param id {@link Tag#id} 形式
     * @param page ページ番号 (1-100)
     * @param perPage 1ページ当たりの取得数 (1-100)
     * @return {@link List<Post>}を{@link Observable}でラップして返却
     */
    Observable<List<Post>> tagItems(final String id, final int page, final int perPage);
}
