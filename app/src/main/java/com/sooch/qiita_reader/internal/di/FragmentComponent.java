package com.sooch.qiita_reader.internal.di;

import com.sooch.qiita_reader.internal.di.scope.FragmentScope;
import com.sooch.qiita_reader.ui.fragment.FeedsFragment;
import com.sooch.qiita_reader.ui.fragment.PostsFragment;
import com.sooch.qiita_reader.ui.fragment.MainFragment;
import com.sooch.qiita_reader.ui.fragment.SettingsFragment;
import com.sooch.qiita_reader.ui.fragment.StocksFragment;
import com.sooch.qiita_reader.ui.fragment.TagsFragment;

import dagger.Subcomponent;

/**
 * {@link javax.inject.Inject}宣言した時に注入するクラスを,
 * モジュールクラス{@link dagger.Module}内の{@link dagger.Provides}において,
 * 宣言したメソッドの戻り値の中から解決するためのクラス.
 * <p>
 * ここでは{@link FragmentModule}の定義を行う.
 *
 * Created by Takashi Sou on 2016/09/26.
 */
@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(MainFragment fragment);

    void inject(FeedsFragment fragment);

    void inject(PostsFragment fragment);

    void inject(TagsFragment fragment);

    void inject(StocksFragment fragment);

    void inject(SettingsFragment fragment);
}
