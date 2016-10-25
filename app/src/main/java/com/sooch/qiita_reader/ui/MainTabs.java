package com.sooch.qiita_reader.ui;

import android.support.v4.app.Fragment;

import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.ui.fragment.FeedsFragment;
import com.sooch.qiita_reader.ui.fragment.PostsFragment;

/**
 * 投稿一覧画面のタブを定義する.
 * Created by Takashi Sou on 2016/10/15.
 */

public enum MainTabs {

    /** 全ての投稿 */
    ITEMS(R.string.main_items, PostsFragment.class.getSimpleName()) {
        @Override
        public Fragment createFragment() {
            return PostsFragment.newInstance();
        }
    },

    /** フィード（認証済みの場合のみ） */
    FEEDS(R.string.main_feeds, FeedsFragment.class.getSimpleName()) {
        @Override
        public Fragment createFragment() {
            return FeedsFragment.newInstance();
        }
    },
    ;

    /** タイトルリソースID */
    private final int titleResId;

    /** ページ名 {@link Class#getSimpleName()}. */
    private final String name;

    MainTabs(int titleResId, String name) {
        this.titleResId = titleResId;
        this.name = name;
    }

    /**
     * {@link #forName(String)}
     * @param fragment
     * @return
     */
    public static MainTabs forName(Fragment fragment) {
        return forName(fragment.getClass().getSimpleName());
    }

    /**
     * Fragment名から逆引きする
     * @param name
     * @return
     */
    public static MainTabs forName(String name) {
        for (MainTabs page : values()) {
            if (page.name.equals(name)) {
                return page;
            }
        }
        throw new AssertionError("no menu enum found for the id. you forgot to implement?");
    }

    /**
     * タイトルリソースを取得する
     * @return
     */
    public int getTitleResId() {
        return titleResId;
    }

    /**
     * ページ名を取得する
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Fragmentの生成
     * @return
     */
    public abstract Fragment createFragment();
}

