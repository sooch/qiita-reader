package com.sooch.qiita_reader.ui.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter}の抽象クラス.
 *
 * <p>
 * {@link #onCreateViewHolder(ViewGroup, int)},
 * {@link #onBindViewHolder(RecyclerView.ViewHolder, int)}
 * を継承先で実装する.
 *
 * <p>
 * データバインディングを使用する場合,
 * {@link BindingHolder}の使用を検討する.
 *
 * <p>
 * セルをクリック可能にする場合,
 * {@link #onBindViewHolder(RecyclerView.ViewHolder, int)}内でルートビューに対して,
 * {@code rootView.setOnClickListener(this)}を実装する.
 *
 * Created by Takashi Sou on 2016/09/26.
 */
public abstract class AbstractRecyclerAdapter<T> extends RecyclerView.Adapter
        implements View.OnClickListener {

    protected LayoutInflater mInflater;
    private RecyclerView mRecycler;
    private OnItemClickListener mListener;
    private List<T> mItems = new ArrayList<>();

    private Object lock = new Object();

    private AbstractRecyclerAdapter() {
    }

    /**
     * コンストラクタ
     *
     * @param context
     * @param items
     */
    public AbstractRecyclerAdapter(Context context, List<T> items) {
        mInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecycler = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecycler = null;
    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * アイテムとして表示される数
     * @return
     */
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View view) {
        if (mRecycler == null) {
            return;
        }

        if (mListener != null && mItems.size() != 0) {
            int position = mRecycler.getChildAdapterPosition(view);
            Object item = getItem(position);
            mListener.onItemClick(this, position, item);
        }
    }

    /**
     * 引数のポジションのオブジェクトを返却します.
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mItems.get(position);
    }

    public void addItem(T item) {
        synchronized (lock) {
            mItems.add(item);
        }
        notifyItemInserted(mItems.size() - 1);
    }

    public void addItems(List<T> items) {
        synchronized (lock) {
            mItems.addAll(items);
        }
    }

    public void insertItem(T item, int position) {
        synchronized (lock) {
            mItems.add(position, item);
        }
        notifyItemInserted(position);
    }

    public void deleteItem(int position) {
        synchronized (lock) {
            mItems.remove(position);
        }
        notifyItemRemoved(position);
    }

    /**
     * 要素の移動. ドラッグ時の移動は通り過ぎた要素が全て入れ替え対象となるため,
     * {@link #swapItem(int, int)}ではなくこのメソッドを利用する.
     *
     * @param from
     * @param to
     */
    public void moveItem(int from, int to) {
        synchronized (lock) {
            mItems.add(to, mItems.remove(from));
        }
        notifyItemMoved(from, to);
    }

    public void swapItem(int from, int to) {
        synchronized (lock) {
            Collections.swap(mItems, from, to);
        }
        notifyItemMoved(from, to);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * {@link #setOnItemClickListener(OnItemClickListener)}でセットされたインターフェースに対して,
     * クリックイベント時にコールバックを返却する.
     * <p>
     * {@link #onCreateViewHolder(ViewGroup, int)}でルートViewに対して,
     * {@link View#setOnClickListener(View.OnClickListener)}を実装しておく必要があります.
     */
    public interface OnItemClickListener {
        /**
         * クリックイベント発生時.
         * @param adapter
         * @param position
         * @param item
         */
        void onItemClick(AbstractRecyclerAdapter adapter, int position, Object item);
    }


    /**
     * データバインディング用ホルダー.
     * @see android.support.v7.widget.RecyclerView.ViewHolder
     * @param <T>
     */
    public static class BindingHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        public final T binding;

        public BindingHolder(@NonNull Context context, @NonNull ViewGroup parent,
                             @LayoutRes int layoutResId) {
            super(LayoutInflater.from(context).inflate(layoutResId, parent, false));
            binding = DataBindingUtil.bind(itemView);
        }
    }
}