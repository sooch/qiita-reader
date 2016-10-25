package com.sooch.qiita_reader.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.data.repository.DataStoreFactory;
import com.sooch.qiita_reader.data.repository.QiitaV2DataRepository;
import com.sooch.qiita_reader.databinding.FragmentPostsBinding;
import com.sooch.qiita_reader.domain.constant.Preference;
import com.sooch.qiita_reader.domain.constant.DefaultSettings;
import com.sooch.qiita_reader.domain.interactor.AbstractUseCase;
import com.sooch.qiita_reader.domain.interactor.GetItems;
import com.sooch.qiita_reader.domain.interactor.GetTagItems;
import com.sooch.qiita_reader.ui.activity.ActivityNavigator;
import com.sooch.qiita_reader.ui.presenter.PostsPresenter;
import com.sooch.qiita_reader.ui.view.PostsView;
import com.sooch.qiita_reader.ui.widget.DividerItemDecoration;
import com.sooch.qiita_reader.ui.widget.EndlessScrollListener;
import com.sooch.qiita_reader.ui.widget.PostsAdapter;
import com.sooch.qiita_reader.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Takashi Sou on 2016/09/24.
 */
public class PostsFragment extends BaseFragment implements PostsView {

    private static final String TAG = PostsFragment.class.getSimpleName();

    private static final String KEY_TAG_ID = "tag_id";

    @Inject
    ActivityNavigator activityNavigator;

    private FragmentPostsBinding binding;

    private PostsPresenter presenter;

    /** {@link #renderItems(List)}が呼ばれた際に生成. */
    private PostsAdapter adapter;

    /** ページ読み込み件数 */
    private int perPage;

    public PostsFragment() {
    }

    /**
     * 投稿一覧用のインスタンスを生成する.
     * @return {@link PostsFragment}
     */
    public static PostsFragment newInstance() {
        return new PostsFragment();
    }

    /**
     * タグ紐付き投稿一覧表示用のインスタンスを生成する.
     * @param tagId
     * @return {@link PostsFragment}
     */
    public static PostsFragment newInstance(String tagId) {
        PostsFragment f = new PostsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TAG_ID, tagId);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

        perPage = PrefUtils.getInt(getActivity(), Preference.PER_PAGE, DefaultSettings.PER_PAGE);

        GetItems useCase;
        if (getArguments() == null) {
            useCase = new GetItems(1, perPage,
                    new QiitaV2DataRepository(new DataStoreFactory(getContext(), null)));
        } else {
            String id = getArguments().getString(KEY_TAG_ID);
            useCase = new GetTagItems(id, 1, perPage,
                    new QiitaV2DataRepository(new DataStoreFactory(getContext(), null)));
        }

        presenter = new PostsPresenter(this, useCase);
        presenter.init();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        presenter.pause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        presenter.destroy();
        binding.recyclerPosts.setAdapter(null);

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
    public void showLoading() {
        binding.loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        binding.loading.setVisibility(View.GONE);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    // TODO: キャッシュ数を制限する
    @Override
    public void renderItems(List<Post> posts) {
        adapter.addItems(posts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    private void initView() {
        binding.recyclerPosts.setHasFixedSize(true);
        binding.recyclerPosts.addItemDecoration(
                new DividerItemDecoration(getContext(), R.drawable.recycler_divider));
        binding.recyclerPosts.setLayoutManager(new LinearLayoutManager(context()));

        binding.recyclerPosts.addOnScrollListener(new EndlessScrollListener(
                (LinearLayoutManager) binding.recyclerPosts.getLayoutManager()) {
            @Override
            public void onLoadMore(int currentPage) {
                presenter.loadMoreItems(currentPage, perPage);
            }
        });

        adapter = new PostsAdapter(context(), new ArrayList<>());
        adapter.setOnItemClickListener((adapter, position, item) ->
                activityNavigator.showPostDetail(getActivity(), (Post) item, 0));

        binding.recyclerPosts.setAdapter(adapter);
    }

}