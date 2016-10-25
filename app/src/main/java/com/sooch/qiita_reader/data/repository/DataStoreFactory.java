package com.sooch.qiita_reader.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;

import com.sooch.qiita_reader.data.cache.Cache;
import com.sooch.qiita_reader.data.network.QiitaV2ServiceImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * キャッシュ状況に応じ適切なデータストアを生成する.
 * Created by Takashi Sou on 2016/10/17.
 */
@Singleton
public class DataStoreFactory {

    /** 呼び出し元コンテキスト */
    private final Context context;

    /** ローカルキャッシュ */
    private final Cache cache;

    @Inject
    public DataStoreFactory(@NonNull Context context, Cache cache) {
        this.context = context;
        this.cache = cache;
    }

    /**
     * データストアの生成
     * @return
     */
    public DataStore create() {
        DataStore dataStore;

        if (false) {
            // TODO: キャッシュ有り

        } else {
            // キャッシュ無し
            dataStore = new CloudDataStore(new QiitaV2ServiceImpl());
        }

        return dataStore;
    }
}
