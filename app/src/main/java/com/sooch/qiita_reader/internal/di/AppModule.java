package com.sooch.qiita_reader.internal.di;

import android.app.Application;
import android.content.Context;

import com.sooch.qiita_reader.ui.activity.ActivityNavigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * DIするインスタンスを返すメソッドを定義する.
 * <p>
 * {@link Provides} を付けたメソッドの戻り値がDIされるときのインスタンスとなる.
 *
 * Created by Takashi Sou on 2016/09/26.
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    ActivityNavigator provideActivityNavigator() {
        return new ActivityNavigator();
    }
}
