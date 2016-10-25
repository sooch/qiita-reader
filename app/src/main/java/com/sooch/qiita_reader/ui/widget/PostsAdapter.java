package com.sooch.qiita_reader.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sooch.qiita_reader.BR;
import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.databinding.ItemPostBinding;
import com.sooch.qiita_reader.ui.transform.CircleStrokeTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * 投稿表示用のデータソースアダプタ.
 * Created by Takashi Sou on 2016/10/03.
 */

public class PostsAdapter extends AbstractRecyclerAdapter {

    /**
     * Bitmap変換インスンタンス
     */
    private CircleStrokeTransformation transformation;

    /**
     * コンストラクタ
     * @param context 呼び出し元コンテキスト
     * @param tags 表示するデータソース
     */
    public PostsAdapter(Context context, List<Post> tags){
        super(context, tags);

        transformation = new CircleStrokeTransformation(
                context, ContextCompat.getColor(context, R.color.transparent), 0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingHolder<ItemPostBinding>(parent.getContext(), parent, R.layout.item_post);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BindingHolder bindingHolder = (BindingHolder) holder;
        final ItemPostBinding binding = (ItemPostBinding) bindingHolder.binding;

        Picasso.with(binding.userIcon.getContext())
                .load(getItem(position).user.profileImageUrl)
                .transform(transformation)
                .into(binding.userIcon);

        binding.getRoot().setOnClickListener(this);

        binding.setVariable(BR.post, getItem(position));
        binding.executePendingBindings();
    }

    @Override
    public Post getItem(int position) {
        return (Post) super.getItem(position);
    }

}
