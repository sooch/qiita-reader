package com.sooch.qiita_reader.ui.presenter;

import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.domain.interactor.GetItems;
import com.sooch.qiita_reader.ui.view.PostsView;

import java.util.List;

import javax.inject.Named;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 投稿一覧のPresentationレイヤーの実装.
 * Created by Takashi Sou on 2016/10/12.
 */

public class PostsPresenter implements Presenter {

    /** ビューインターフェース */
    private final PostsView postsView;

    /** ユースケース */
    private final GetItems getItemsUseCase;

    // TODO: DI
    public PostsPresenter(PostsView postsView, @Named("getItems")GetItems getItemsUseCase) {
        this.postsView = postsView;
        this.getItemsUseCase = getItemsUseCase;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        getItemsUseCase.unsubscribe();
    }

    /**
     * 画面初期化時にコールする.
     */
    public void init() {
        loadItems();
    }

    /**
     * 投稿をさらに取得する.
     * @param page ページ番号
     * @param perPage 1ページ当たりの取得数
     */
    public void loadMoreItems(int page, int perPage) {
        getItemsUseCase.setPage(page);
        getItemsUseCase.setPerPage(perPage);
        loadItems();
    }

    /**
     * 投稿を取得する.
     */
    private void loadItems() {
        postsView.showLoading();

        getItemsUseCase.execute(new Subscriber<List<Post>>() {
            @Override
            public void onCompleted() {
                postsView.dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                // TODO: handle error
                if (e instanceof HttpException && ((HttpException) e).code() == 403) {
                    postsView.showError("Rate limit exceeded!");
                } else {
                    postsView.showError("通信エラーが発生しました");
                }

                postsView.dismissLoading();
            }

            @Override
            public void onNext(List<Post> posts) {
                postsView.renderItems(posts);
            }
        });
    }
}
