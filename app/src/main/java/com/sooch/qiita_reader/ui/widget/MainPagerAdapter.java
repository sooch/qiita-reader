package com.sooch.qiita_reader.ui.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.sooch.qiita_reader.ui.MainTabs;

/**
 * タブに表示する{@link Fragment}を提供するアダプタ.
 * Created by Takashi Sou on 2016/10/04.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    /** 呼び出し元コンテキスト */
    private Context context;

    /**
     * コンストラクタ
     * @param context 呼び出し元コンテキスト
     * @param fm 呼び出し元が保持する{@link FragmentManager}
     */
    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return MainTabs.values()[position].createFragment();
    }

    @Override
    public int getCount() {
        return MainTabs.values().length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    /**
     * キャッシュ外の破棄時
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        if (position <= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }

    /**
     * {@code position}に該当するページタイトルの取得
     * @param position 取得するページ
     * @return {@link CharSequence} ページタイトル
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(MainTabs.values()[position].getTitleResId());
    }

    /**
     * 指定した{@code position}の{@link Fragment}を取得する
     * @param viewPager
     * @param position
     * @return
     */
    public Fragment findFragmentByPosition(ViewPager viewPager, int position) {
        return (Fragment) instantiateItem(viewPager, position);
    }
}
