package com.sooch.qiita_reader.ui.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Presentationレイヤーのインターフェース定義.
 * Created by Takashi Sou on 2016/09/25.
 */

public interface Presenter {
    /**
     * {@link Activity#onResume()} or {@link Fragment#onResume()}とバインドする.
     */
    void resume();

    /**
     * {@link Activity#onPause()} ()} or {@link Fragment#onPause()}とバインドする.
     */
    void pause();

    /**
     * {@link Activity#onDestroy()} ()} ()} or {@link Fragment#onDestroyView()}とバインドする.
     */
    void destroy();
}
