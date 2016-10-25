package com.sooch.qiita_reader.internal.di;

import com.sooch.qiita_reader.internal.di.scope.ActivityScope;
import com.sooch.qiita_reader.ui.activity.MainActivity;
import com.sooch.qiita_reader.ui.activity.PostDetailActivity;
import com.sooch.qiita_reader.ui.activity.TagDetailActivity;

import dagger.Subcomponent;

/**
 * {@link javax.inject.Inject}宣言した時に注入するクラスを,
 * モジュールクラス{@link dagger.Module}内の{@link dagger.Provides}において,
 * 宣言したメソッドの戻り値の中から解決するためのクラス.
 * <p>
 * ここでは{@link ActivityModule}の定義を行う.
 *
 * Created by Takashi Sou on 2016/09/26.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(PostDetailActivity activity);

    void inject(TagDetailActivity activity);

    FragmentComponent plus(FragmentModule module);
}
