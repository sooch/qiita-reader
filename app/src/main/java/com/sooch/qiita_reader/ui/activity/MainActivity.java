package com.sooch.qiita_reader.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.databinding.ActivityMainBinding;
import com.sooch.qiita_reader.ui.Menu;

/**
 * 起動時の呼び出し画面, サイドメニューの画面を管理する.
 * Created by Takashi Sou on 2016/09/26.
 */
public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String EXTRA_MENU = "menu";

    /** ドロワーを閉じてからの処理のディレイ */
    private static final long DRAWER_CLOSE_DELAY_MILLS = 0L;

    /** バインドビューの参照 (Data-Binding) */
    private ActivityMainBinding binding;

    /** メインスレッドハンドラ */
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new Handler();

        initView();

        if (savedInstanceState == null) {
            Menu page = Menu.MAIN;
            replaceFragment(page.createFragment());
            toggleToolbarElevation(page.shouldToggleToolbar());

        } else if (savedInstanceState.getString(EXTRA_MENU) != null) {
            String name = savedInstanceState.getString(EXTRA_MENU);
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(name);
            if (fragment == null) {
                fragment = Menu.forName(name).createFragment();
            }
            replaceFragment(fragment);
            toggleToolbarElevation(Menu.forName(fragment).shouldToggleToolbar());

        } else {
            throw new AssertionError("'savedInstanceState' Unknown key.");
        }

        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.content);
        if (current != null) {
            outState.putString(EXTRA_MENU, Menu.forName(current).getPageName());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * サイドメニューのアイテムを選択した際に呼び出される.
     * @param item 選択されたアイテム.
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (!item.isChecked()) {
            if (item.getGroupId() == R.id.group_main) {
                Menu page = Menu.forMenuId(id);
                changePage(page.createFragment());
                toggleToolbarElevation(page.shouldToggleToolbar());
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * {@link FragmentManager}のバックスタックの数が変化した際に呼び出される.
     */
    @Override
    public void onBackStackChanged() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment current = fm.findFragmentById(R.id.content);
        if (current == null) {
            // no more fragments in the stack. finish.
            finish();
            return;
        }
        Menu page = Menu.forName(current);
        binding.navView.setCheckedItem(page.getMenuId());
        binding.toolbar.setTitle(page.getTitleResId());
        toggleToolbarElevation(page.shouldToggleToolbar());
    }

    /**
     * ビューの初期化
     */
    private void initView() {
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.text_reversal));
        binding.navView.setNavigationItemSelectedListener(this);
    }

    /**
     * ページを切り替える {@link #replaceFragment(Fragment)}
     * <p>
     * 切り替えの際, {@link #DRAWER_CLOSE_DELAY_MILLS}のディレイをかけて切り替えを実行する.
     * @param fragment
     */
    private void changePage(Fragment fragment) {
        handler.postDelayed(() -> replaceFragment(fragment), DRAWER_CLOSE_DELAY_MILLS);
    }

    /**
     * {@link Fragment}の切り替え.
     * @param fragment 切り替え対象.
     */
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
                .replace(binding.content.getId(), fragment, fragment.getClass().getSimpleName())
                // addToBackStackをコメントアウトしない場合, Fragmentは破棄されないことに注意する.
                // http://stackoverflow.com/questions/27913009/memory-leak-in-fragmentmanager/27913962#27913962
                .addToBackStack(null)
                .commit();
    }

    /**
     * Elevationを設定する必要がある場合, Elevationを設定する.
     * @param enable 設定するかどうか
     */
    private void toggleToolbarElevation(boolean enable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float elevation = enable ? getResources().getDimension(R.dimen.elevation) : 0;
            binding.appBar.setElevation(elevation);
        }
    }
}
