package com.sooch.qiita_reader.ui.presenter;

import com.sooch.qiita_reader.data.entity.Tag;
import com.sooch.qiita_reader.domain.interactor.AbstractUseCase;
import com.sooch.qiita_reader.ui.view.TagsView;

import java.util.List;

import javax.inject.Named;

import rx.Subscriber;

/**
 * タグ一覧のPresentationレイヤーの実装.
 * Created by Takashi Sou on 2016/09/25.
 */

public class TagsPresenter implements Presenter {

    /** ビューインターフェース */
    private final TagsView tagsView;

    /** ユースケース */
    private final AbstractUseCase getTagsUseCase;

    // TODO: DI
    public TagsPresenter(TagsView tagsView, @Named("getTags")AbstractUseCase getTagsUseCase) {
        this.tagsView = tagsView;
        this.getTagsUseCase = getTagsUseCase;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        getTagsUseCase.unsubscribe();
    }

    /**
     * 画面初期化時にコールする.
     */
    public void init() {
        loadTags();
    }

    /**
     * タグ一覧を取得する.
     */
    private void loadTags() {
        tagsView.showLoading();

        getTagsUseCase.execute(new Subscriber<List<Tag>>() {
            @Override
            public void onCompleted() {
                tagsView.dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                tagsView.dismissLoading();
            }

            @Override
            public void onNext(List<Tag> tags) {
                tagsView.renderTags(tags);
            }
        });
    }
}
