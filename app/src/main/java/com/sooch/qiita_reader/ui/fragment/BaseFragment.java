package com.sooch.qiita_reader.ui.fragment;

import android.app.Activity;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.sooch.qiita_reader.internal.di.FragmentComponent;
import com.sooch.qiita_reader.internal.di.FragmentModule;
import com.sooch.qiita_reader.ui.activity.BaseActivity;

/**
 * 各画面{@link Fragment}の基底クラス.
 * Created by Takashi Sou on 2016/09/26.
 */

public abstract class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    /**
     * {@link FragmentComponent}の取得.
     * <p>
     * 取得したインスタンスはメンバー変数{@code fragmentComponent}で保持する.
     * @return
     */
    @NonNull
    public FragmentComponent getComponent() {
        if (fragmentComponent != null) {
            return fragmentComponent;
        }

        Activity activity = getActivity();
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalStateException(
                    "The activity of this fragment is not an instance of BaseActivity");
        }
        fragmentComponent = ((BaseActivity) activity).getComponent()
                .plus(new FragmentModule(this));
        return fragmentComponent;
    }

    /**
     * カラーリソースを取得する.
     * @param id リソースID
     * @return
     */
    protected int getColor(@ColorRes int id) {
        return ContextCompat.getColor(getContext(), id);
    }
}
