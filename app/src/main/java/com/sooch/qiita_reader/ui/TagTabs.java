package com.sooch.qiita_reader.ui;

import android.support.v4.app.Fragment;

import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.ui.fragment.PostsFragment;

/**
 * タグ詳細画面に表示するタブを定義.
 * Created by Takashi Sou on 2016/10/24.
 */

public enum TagTabs {

    /** 最新の投稿を表示 */
    LATEST(R.string.tag_latest, PostsFragment.class.getSimpleName()) {
        @Override
        public Fragment createFragment(String id) {
            return PostsFragment.newInstance(id);
        }
    },
    /** 最近ストックされた投稿を表示 */
    // not implement
//    RECENTLY(R.string.tag_recently, PostsFragment.class.getSimpleName()) {
//        @Override
//        public Fragment createFragment() {
//            return PostsFragment.newInstance();
//        }
//    },
    ;

    private final int titleResId;
    private final String name;

    TagTabs(int titleResId, String name) {
        this.titleResId = titleResId;
        this.name = name;
    }

    /**
     * {@link #forName(String)}
     * @param fragment
     * @return
     */
    public static TagTabs forName(Fragment fragment) {
        return forName(fragment.getClass().getSimpleName());
    }

    /**
     * Fragment名から逆引きする
     * @param name
     * @return
     */
    public static TagTabs forName(String name) {
        for (TagTabs page : values()) {
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
    public abstract Fragment createFragment(String id);
}

