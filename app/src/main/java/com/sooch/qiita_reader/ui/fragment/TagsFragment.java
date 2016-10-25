package com.sooch.qiita_reader.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.data.entity.Tag;
import com.sooch.qiita_reader.data.repository.DataStoreFactory;
import com.sooch.qiita_reader.data.repository.QiitaV2DataRepository;
import com.sooch.qiita_reader.databinding.FragmentTagsBinding;
import com.sooch.qiita_reader.domain.constant.QiitaV2;
import com.sooch.qiita_reader.domain.interactor.GetTags;
import com.sooch.qiita_reader.ui.activity.ActivityNavigator;
import com.sooch.qiita_reader.ui.presenter.TagsPresenter;
import com.sooch.qiita_reader.ui.view.TagsView;
import com.sooch.qiita_reader.ui.widget.DividerItemDecoration;
import com.sooch.qiita_reader.ui.widget.TagsAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Takashi Sou on 2016/09/24.
 */
public class TagsFragment extends BaseFragment implements TagsView {

    private static final String TAG = TagsFragment.class.getSimpleName();

    @Inject
    ActivityNavigator activityNavigator;

    private FragmentTagsBinding binding;

    private TagsPresenter mPresenter;

    public TagsFragment() {
    }

    public static TagsFragment newInstance() {
        return new TagsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tags, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

        mPresenter = new TagsPresenter(this, new GetTags(QiitaV2.Tags.Sort.COUNT,
                new QiitaV2DataRepository(new DataStoreFactory(getContext(), null))));
        mPresenter.init();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onPause() {
        mPresenter.pause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mPresenter.destroy();
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

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void showLoading() {
        binding.loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        binding.loading.setVisibility(View.GONE);
    }

    @Override
    public void renderTags(List<Tag> tags) {
        TagsAdapter adapter = new TagsAdapter(context(), tags);
        adapter.setOnItemClickListener((adapter1, position, item) ->
                activityNavigator.showTagDetail(getActivity(), (Tag) item, 0));
        binding.recyclerTags.setAdapter(adapter);
    }

    private void initView() {
        binding.recyclerTags.setHasFixedSize(true);
        binding.recyclerTags.addItemDecoration(new DividerItemDecoration(getContext(), R.drawable.recycler_divider));
        binding.recyclerTags.setLayoutManager(new LinearLayoutManager(context()));
    }

}