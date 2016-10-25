package com.sooch.qiita_reader.domain.interactor;

import android.app.Activity;
import android.support.v4.app.Fragment;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * ユースケースのための抽象クラス.
 * <p>
 * 慣例により, 各ユースケースの実装では, バックグラウンドスレッドでそのジョブを実行し,
 * UIスレッドでの結果を掲載します{@link rx.Subscriber}を使用して結果を返す.
 *
 * Created by Takashi Sou on 2016/10/23.
 */

public abstract class AbstractUseCase {

    /**
     * {@link Observable#subscribe()}により生成された{@link Subscription}.
     * この{@code subscription}はコンテキストを握っているため, ライフサイクルに正しくバインド
     * する必要がある.
     * ライフサイクルが終了する際に, 必ず{@link #unsubscribe()}する必要がある.
     */
    protected Subscription subscription = Subscriptions.empty();

    protected AbstractUseCase() {
    }

    /**
     * 継承先で{@link Observable}を生成の実装.
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * 生成された{@link Observable}を{@link Observable#subscribe()}する.
     * @param subscriber
     */
    public void execute(Subscriber subscriber) {
        this.subscription = buildUseCaseObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * {@link Subscription}をリリースする.
     * <p>
     * {@link Activity#onStop()}, {@link Fragment#onStop()} もしくは
     * {@link Activity#onDestroy()}, {@link Fragment#onDestroyView()} でコールする.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
