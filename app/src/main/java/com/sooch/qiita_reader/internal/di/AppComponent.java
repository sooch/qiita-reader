package com.sooch.qiita_reader.internal.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * {@link javax.inject.Inject}宣言した時に注入するクラスを,
 * モジュールクラス{@link dagger.Module}内の{@link dagger.Provides}において,
 * 宣言したメソッドの戻り値の中から解決するためのクラス.
 * <p>
 * ここでは{@link AppModule}の定義を行う.
 *
 * Created by Takashi Sou on 2016/09/26.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    ActivityComponent plus(ActivityModule module);
}
