package com.sooch.qiita_reader.internal.di;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

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
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity activity() {
        return activity;
    }

    @Provides
    LayoutInflater layoutInflater() {
        return activity.getLayoutInflater();
    }
}
