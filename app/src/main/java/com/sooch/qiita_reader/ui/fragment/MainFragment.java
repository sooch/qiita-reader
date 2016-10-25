package com.sooch.qiita_reader.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.databinding.FragmentMainBinding;
import com.sooch.qiita_reader.ui.MainTabs;
import com.sooch.qiita_reader.ui.widget.MainPagerAdapter;

/**
 * 投稿一覧画面
 * Created by Takashi Sou on 2016/09/24.
 */
public class MainFragment extends BaseFragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    /** データバインディング */
    private FragmentMainBinding binding;

    public MainFragment() {
    }

    /**
     * インスタンスを生成する.
     * @return {@link MainFragment}
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        MainPagerAdapter adapter = new MainPagerAdapter(getActivity(), getChildFragmentManager());
        binding.pagerContent.setAdapter(adapter);

        for (MainTabs page : MainTabs.values()) {
            binding.tabs.addTab(binding.tabs.newTab().setText(page.getTitleResId()));
        }

        binding.tabs.setupWithViewPager(binding.pagerContent);
    }
}