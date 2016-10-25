package com.sooch.qiita_reader.data.repository;

import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.data.entity.Tag;
import com.sooch.qiita_reader.data.network.QiitaV2Service;

import java.util.List;

import rx.Observable;

/**
 *
 * Created by Takashi Sou on 2016/10/17.
 */

public class CloudDataStore implements DataStore {

    private QiitaV2Service service;

    CloudDataStore(QiitaV2Service service) {
        this.service = service;
    }

    @Override
    public Observable<List<Tag>> tags(final String sort) {
        return service.tags(sort);
    }

    @Override
    public Observable<List<Post>> items(final int page, final int perPage) {
        return service.items(page, perPage);
    }

    @Override
    public Observable<List<Post>> tagItems(final String id, final int page, final int perPage) {
        return service.tagItems(id, page, perPage);
    }
}
