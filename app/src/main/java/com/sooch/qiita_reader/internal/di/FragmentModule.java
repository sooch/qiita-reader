package com.sooch.qiita_reader.internal.di;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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
public class FragmentModule {

    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public Context context() {
        return fragment.getContext();
    }

    @Provides
    public FragmentManager providesFragmentManager() {
        return fragment.getFragmentManager();
    }
}
