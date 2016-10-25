package com.sooch.qiita_reader.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sooch.qiita_reader.BR;
import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.databinding.ActivityPostBinding;
import com.squareup.picasso.Picasso;

/**
 * Created by Takashi Sou on 2016/10/06.
 */

public class PostDetailActivity extends BaseActivity {

    private static final String POST = "post";

    private ActivityPostBinding binding;

    static void startForResult(@NonNull Activity activity, @NonNull Post post, int requestCode) {
        activity.startActivityForResult(createIntent(activity, post), requestCode);
    }

    public static Intent createIntent(@NonNull Context context, @NonNull Post post) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra(POST, post);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);

        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.appBar.setElevation(getResources().getDimension(R.dimen.elevation));
        }

        Post post = (Post) getIntent().getSerializableExtra(POST);
        binding.toolbar.setTitle(post.title);
        binding.toolbar.setOnClickListener(view -> finish());
        binding.webView.setWebChromeClient(new WebChromeClient());
        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.loading.setVisibility(View.GONE);
            }
        });
        binding.webView.loadUrl(post.url);

        //        binding.toolbarLayout.setTitle(post.title);
//        binding.webView.loadDataWithBaseURL(post.url, post.renderedBody, "text/html", "UTF-8", null);

//        Picasso.with(binding.userIcon.getContext())
//                .load(post.user.profileImageUrl)
//                .into(binding.userIcon);

        binding.setVariable(BR.post, post);
        binding.executePendingBindings();
    }
}
