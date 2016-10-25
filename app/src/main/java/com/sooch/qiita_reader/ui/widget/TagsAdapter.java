package com.sooch.qiita_reader.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.sooch.qiita_reader.BR;
import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.data.entity.Tag;
import com.sooch.qiita_reader.databinding.ItemTagBinding;
import com.sooch.qiita_reader.ui.transform.CircleStrokeTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * タグ一覧表示用のデータソースアダプタ
 * Created by Takashi Sou on 2016/10/03.
 */

public class TagsAdapter extends AbstractRecyclerAdapter {

    /**
     * Bitmap変換インスンタンス
     */
    private CircleStrokeTransformation transformation;

    /**
     * コンストラクタ
     * @param context 呼び出し元コンテキスト
     * @param tags 表示するデータソース
     */
    public TagsAdapter(Context context, List<Tag> tags) {
        super(context, tags);

        transformation = new CircleStrokeTransformation(
                context, ContextCompat.getColor(context, R.color.transparent), 0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingHolder<ItemTagBinding>(parent.getContext(), parent, R.layout.item_tag);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BindingHolder bindingHolder = (BindingHolder) holder;
        final ItemTagBinding binding = (ItemTagBinding) bindingHolder.binding;
        binding.getRoot().setOnClickListener(this);

        Picasso.with(binding.tagIcon.getContext())
                .load(getItem(position).iconUrl)
                .transform(transformation)
                .into(binding.tagIcon);

        binding.checkbox.setChecked(false);

//        binding.getRoot().setOnClickListener(view ->
//                binding.checkbox.setChecked(!binding.checkbox.isChecked()));

        binding.setVariable(BR.tag, getItem(position));
        binding.executePendingBindings();
    }

    @Override
    public Tag getItem(int position) {
        return (Tag) super.getItem(position);
    }
}
