package com.sooch.qiita_reader.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.sooch.qiita_reader.MyApplication;
import com.sooch.qiita_reader.internal.di.ActivityComponent;
import com.sooch.qiita_reader.internal.di.ActivityModule;

/**
 * 各画面{@link android.app.Activity}の基底クラス.
 * Created by Takashi Sou on 2016/09/26.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    /**
     * {@link ActivityComponent}の取得.
     * <p>
     * 取得したインスタンスはメンバー変数{@code activityComponent}で保持する.
     * @return
     */
    @NonNull
    public ActivityComponent getComponent() {
        if (activityComponent == null) {
            MyApplication application = (MyApplication) getApplication();
            activityComponent = application.getAppComponent().plus(new ActivityModule(this));
        }
        return activityComponent;
    }
}
