package com.sooch.qiita_reader.data.network;

import com.sooch.qiita_reader.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * HTTPクライアントの生成及び, {@link QiitaV2Service}の実装.
 * <p>生成されたクライアントでAPIインターフェスに対してリクエストを行う.
 * Created by Takashi Sou on 2016/10/17.
 */

public class QiitaV2ServiceImpl implements QiitaV2Service {

    /** 接続までの時間 */
    private final long CONNECTION_TIMEOUT_SEC = 10;

    /** データ取得（リクエストからレスポンスまで） */
    private final long READ_TIMEOUT_SEC = 10;

    /** 書き込みタイムアウト */
    private final long WRITE_TIMEOUT_SEC = 10;

    /**
     * APIリクエストサービスを生成する.
     * {@link Retrofit#create(Class)}
     * @return {@link QiitaV2Service}
     */
    // TODO: singleton
    private QiitaV2Service buildService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(QiitaV2Service.class);
    }

    /**
     * HTTPクライアントを生成する.
     * @return {@link OkHttpClient}
     */
    private OkHttpClient buildClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIMEOUT_SEC, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder.build();
    }

    /**
     * タグの取得 {@link QiitaV2Service#tags(String)}
     * @param sort ソート条件
     * @return
     */
    @Override
    public Observable tags(String sort) {
        return buildService().tags(sort);
    }

    /**
     * 全投稿の取得 {@link QiitaV2Service#items(int, int)}
     * @param page ページ (1-100)
     * @param perPage 1ページ当たりの取得数 (1-100)
     * @return
     */
    @Override
    public Observable items(int page, int perPage) {
        return buildService().items(page, perPage);
    }

    /**
     * 指定のタグ{@code id}が付いた投稿を投稿日時順に取得
     * {@link QiitaV2Service#tagItems(String, int, int)}
     * @param id
     * @param page
     * @param perPage
     * @return
     */
    @Override
    public Observable tagItems(String id, int page, int perPage) {
        return buildService().tagItems(id, page, perPage);
    }
}
