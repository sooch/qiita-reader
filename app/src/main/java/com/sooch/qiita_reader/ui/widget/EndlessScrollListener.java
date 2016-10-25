package com.sooch.qiita_reader.ui.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * {@link RecyclerView}の表示が一番下に到達すると,
 * {@link #onLoadMore(int)}をコールするスクロールリスナ.
 * Created by Takashi Sou on 2016/10/23.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0;
    private boolean loading = true;
    private int currentPage = 1;

    private LinearLayoutManager layoutManager;

    public EndlessScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleItemCount)) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    /**
     * さらに読み込む場合の処理を実装.
     * @param currentPage
     */
    public abstract void onLoadMore(int currentPage);
}
