package com.sooch.qiita_reader.data.repository;

import com.sooch.qiita_reader.domain.constant.QiitaV2;
import com.sooch.qiita_reader.domain.repository.QiitaV2Repository;

import rx.Observable;

/**
 * {@link QiitaV2Repository} QiitaV2形式のデータを取得する
 * Created by Takashi Sou on 2016/09/23.
 */

public class QiitaV2DataRepository implements QiitaV2Repository {

    private DataStoreFactory dataStoreFactory;

    /**
     * コンストラクタ
     * @param dataStoreFactory {@link DataStoreFactory} データストア取得インスタンスをDI.
     */
    public QiitaV2DataRepository(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    /**
     * タグの取得
     * {@link QiitaV2Repository#tags(QiitaV2.Tags.Sort)}
     * @param sort
     * @return
     */
    @Override
    public Observable tags(QiitaV2.Tags.Sort sort) {
        final DataStore dataStore = dataStoreFactory.create();
        return dataStore.tags(sort.getSort());
    }

    /**
     * 最新の投稿を取得
     * @param page ページ番号 (1-100)
     * @param perPage 1ページ当たりの取得数 (1-100)
     * @return
     */
    @Override
    public Observable items(int page, int perPage) {
        final DataStore dataStore = dataStoreFactory.create();
        return dataStore.items(page, perPage);
    }

    /**
     * タグの投稿を取得
     * @param id {@link com.sooch.qiita_reader.data.entity.Tag#id} 形式
     * @param page ページ番号 (1-100)
     * @param perPage 1ページ当たりの取得数 (1-100)
     * @return
     */
    @Override
    public Observable tagItems(String id, int page, int perPage) {
        final DataStore dataStore = dataStoreFactory.create();
        return dataStore.tagItems(id, page, perPage);
    }
}
