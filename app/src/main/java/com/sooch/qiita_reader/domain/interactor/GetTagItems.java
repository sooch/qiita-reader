package com.sooch.qiita_reader.domain.interactor;

import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.domain.repository.QiitaV2Repository;

import java.util.List;

import rx.Observable;

/**
 * 指定タグの記事取得におけるレイヤー間の相互作用を取りまとめる.
 *
 * Created by Takashi Sou on 2016/10/23.
 */

public class GetTagItems extends GetItems {

    /**
     * {@link com.sooch.qiita_reader.data.entity.Tag#id}
     * 取得対象のタグID. このタグが付いた記事が取得される.
     */
    private final String id;

    /** ページ番号 */
    private int page;

    /** 1ページ当たりの取得件数 */
    private int perPage;

    /** Domainレイヤーのデータアクセスインターフェース */
    private final QiitaV2Repository repository;

    public GetTagItems(String id, int page, int perPage, QiitaV2Repository repository) {
        super(page, perPage, repository);

        this.id = id;
        this.page = page;
        this.perPage = perPage;
        this.repository = repository;
    }

    /**
     * {@link AbstractUseCase#buildUseCaseObservable()}
     * @return
     */
    @Override
    protected Observable<List<Post>> buildUseCaseObservable() {
        return repository.tagItems(id, page, perPage);
    }

    /**
     * ページ番号をセット（更新）
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 取得件数をセット（更新）
     * @param perPage
     */
    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
