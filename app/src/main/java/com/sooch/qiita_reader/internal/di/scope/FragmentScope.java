package com.sooch.qiita_reader.internal.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * このアノテーションが付与されたコンポーネントは,
 * {@link android.support.v4.app.Fragment}を管理するインターフェースを表す.
 * Created by Takashi Sou on 2016/09/26.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
