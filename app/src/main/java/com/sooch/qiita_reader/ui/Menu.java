package com.sooch.qiita_reader.ui;

import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.ui.fragment.MainFragment;
import com.sooch.qiita_reader.ui.fragment.SettingsFragment;
import com.sooch.qiita_reader.ui.fragment.TagsFragment;

/**
 * サイドメニュー{@link android.support.v4.widget.DrawerLayout}
 * に表示する画面及び, 生成メソッドを定義.
 *
 * Created by Takashi Sou on 2016/10/20.
 */

public enum Menu {

    /** 投稿一覧画面 */
    MAIN(R.id.nav_main, R.string.nav_main, false, MainFragment.class.getSimpleName()) {
        @Override
        public Fragment createFragment() {
            return MainFragment.newInstance();
        }
    },

    /** タグ一覧画面 */
    TAGS(R.id.nav_tags, R.string.nav_tags, true, TagsFragment.class.getSimpleName()) {
        @Override
        public Fragment createFragment() {
            return TagsFragment.newInstance();
        }
    },

    /** 設定画面 */
    SETTINGS(R.id.nav_settings, R.string.nav_settings, true, SettingsFragment.class.getSimpleName()) {
        @Override
        public Fragment createFragment() {
            return SettingsFragment.newInstance();
        }
    },
    ;

    /** メニューID */
    private final int menuId;

    /** 画面タイトルに表示する文字列のリソースID */
    private final int titleResId;

    /**
     * {@link android.support.v7.widget.Toolbar}のZ軸を設定するかどうか
     * {@code toggleToolbar == true}であれば設定する.
     */
    private final boolean toggleToolbar;

    /** ページ名 {@link Class#getSimpleName()}. */
    private final String pageName;

    Menu(int menuId, int titleResId, boolean toggleToolbar, String pageName) {
        this.menuId = menuId;
        this.titleResId = titleResId;
        this.toggleToolbar = toggleToolbar;
        this.pageName = pageName;
    }

    /**
     * {@link #forMenuId(MenuItem)}
     * @param item
     * @return
     */
    public static Menu forMenuId(MenuItem item) {
        return forMenuId(item.getItemId());
    }

    /**
     * メニューIDから逆引きする
     * @param id
     * @return
     */
    public static Menu forMenuId(int id) {
        for (Menu page : values()) {
            if (page.menuId == id) {
                return page;
            }
        }
        throw new AssertionError("no menu enum found for the id. you forgot to implement?");
    }

    /**
     * {@link #forName(String)}
     * @param fragment
     * @return
     */
    public static Menu forName(Fragment fragment) {
        return forName(fragment.getClass().getSimpleName());
    }

    /**
     * Fragment名から逆引きする
     * @param name
     * @return
     */
    public static Menu forName(String name) {
        for (Menu page : values()) {
            if (page.pageName.equals(name)) {
                return page;
            }
        }
        throw new AssertionError("no menu enum found for the id. you forgot to implement?");
    }

    /**
     * メニューIDを取得する
     * @return
     */
    public int getMenuId() {
        return menuId;
    }

    /**
     * タイトルリソースを取得する
     * @return
     */
    public int getTitleResId() {
        return titleResId;
    }

    /**
     * Toggleツールバーにするかどうか
     * @return
     */
    public boolean shouldToggleToolbar() {
        return toggleToolbar;
    }

    /**
     * ページ名を取得する
     * @return
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * Fragmentの生成
     * @return
     */
    public abstract Fragment createFragment();
}

