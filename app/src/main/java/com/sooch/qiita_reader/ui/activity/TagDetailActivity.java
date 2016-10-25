package com.sooch.qiita_reader.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.sooch.qiita_reader.BR;
import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.data.entity.Tag;
import com.sooch.qiita_reader.databinding.ActivityTagBinding;
import com.sooch.qiita_reader.ui.MainTabs;
import com.sooch.qiita_reader.ui.TagTabs;
import com.sooch.qiita_reader.ui.widget.MainPagerAdapter;
import com.sooch.qiita_reader.ui.widget.TagPagerAdapter;

/**
 * Created by Takashi Sou on 2016/10/06.
 */

public class TagDetailActivity extends BaseActivity {

    private static final String EXTRA_TAG = "tag";

    private ActivityTagBinding binding;

    static void startForResult(@NonNull Activity activity, @NonNull Tag tag, int requestCode) {
        activity.startActivityForResult(createIntent(activity, tag), requestCode);
    }

    public static Intent createIntent(@NonNull Context context, @NonNull Tag tag) {
        Intent intent = new Intent(context, TagDetailActivity.class);
        intent.putExtra(EXTRA_TAG, tag);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag);

        initView();
    }

    private void initView() {
        final Tag tag = (Tag) getIntent().getSerializableExtra(EXTRA_TAG);

        binding.toolbar.setOnClickListener(view -> finish());

        TagPagerAdapter adapter = new TagPagerAdapter(this, tag.id, getSupportFragmentManager());
        binding.pager.setAdapter(adapter);

        for (TagTabs tab : TagTabs.values()) {
            binding.tabs.addTab(binding.tabs.newTab().setText(tab.getTitleResId()));
        }
        binding.tabs.setupWithViewPager(binding.pager);

        binding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) >= binding.appBar.getTotalScrollRange()) {
                // collapsed
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    binding.appBar.setElevation(getResources().getDimension(R.dimen.elevation));
                    binding.tabs.setElevation(getResources().getDimension(R.dimen.elevation));
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    binding.appBar.setElevation(0);
                    binding.tabs.setElevation(0);
                }
            }
        });

        binding.setVariable(BR.tag, tag);
        binding.executePendingBindings();
    }
}
