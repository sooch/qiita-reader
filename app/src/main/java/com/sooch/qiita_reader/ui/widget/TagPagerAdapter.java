package com.sooch.qiita_reader.ui.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.sooch.qiita_reader.ui.MainTabs;
import com.sooch.qiita_reader.ui.TagTabs;

/**
 * Created by Takashi Sou on 2016/10/04.
 */

public class TagPagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    private final String id;

    public TagPagerAdapter(Context context, String id, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        return TagTabs.values()[position].createFragment(id);
    }

    @Override
    public int getCount() {
        return TagTabs.values().length;
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

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TagTabs.values()[position].getTitleResId());
    }

    /**
     * 指定した{@code position}のFragmentを取得する
     * @param viewPager
     * @param position
     * @return
     */
    public Fragment findFragmentByPosition(ViewPager viewPager, int position) {
        return (Fragment) instantiateItem(viewPager, position);
    }
}
