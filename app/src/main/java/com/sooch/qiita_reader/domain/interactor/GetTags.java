package com.sooch.qiita_reader.domain.interactor;

import com.sooch.qiita_reader.data.entity.Tag;
import com.sooch.qiita_reader.domain.constant.QiitaV2;
import com.sooch.qiita_reader.domain.repository.QiitaV2Repository;

import java.util.List;

import rx.Observable;

/**
 * タグ一覧取得におけるレイヤー間の相互作用を取りまとめる.
 *
 * Created by Takashi Sou on 2016/10/23.
 */

public class GetTags extends AbstractUseCase {

    /** 取得時のソート指定 */
    private final QiitaV2.Tags.Sort sort;

    /** Domainレイヤーのデータアクセスインターフェース */
    private final QiitaV2Repository repository;

    public GetTags(QiitaV2.Tags.Sort sort, QiitaV2Repository repository) {
        this.sort = sort;
        this.repository = repository;
    }

    /**
     * {@link AbstractUseCase#buildUseCaseObservable()}
     * @return
     */
    @Override
    protected Observable<List<Tag>> buildUseCaseObservable() {
        return repository.tags(sort);
    }
}
