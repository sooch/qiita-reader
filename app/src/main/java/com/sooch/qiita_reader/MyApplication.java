package com.sooch.qiita_reader;

import android.app.Application;

import com.sooch.qiita_reader.internal.di.AppComponent;
import com.sooch.qiita_reader.internal.di.AppModule;
import com.sooch.qiita_reader.internal.di.DaggerAppComponent;

/**
 * Created by Takashi Sou on 2016/08/14.
 */
public class MyApplication extends Application {

    /** このインスタンスはアプリが終了するまで保持される. */
    private static MyApplication sInstance;

    /** 初期化済みのDI管理インスタンス */
    private AppComponent appComponent;

    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        // dagger2 DIの初期化
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * {@link AppComponent}取得.
     * @return
     */
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
