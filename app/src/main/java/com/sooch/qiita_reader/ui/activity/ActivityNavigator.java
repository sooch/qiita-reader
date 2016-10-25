package com.sooch.qiita_reader.ui.activity;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.sooch.qiita_reader.data.entity.Post;
import com.sooch.qiita_reader.data.entity.Tag;

import javax.inject.Singleton;

/**
 * Created by Takashi Sou on 2016/10/20.
 */
@Singleton
public class ActivityNavigator {

    public void showPostDetail(@NonNull Activity activity, @NonNull Post post, int requestCode) {
        PostDetailActivity.startForResult(activity, post, requestCode);
    }

    public void showTagDetail(@NonNull Activity activity, @NonNull Tag tag, int requestCode) {
        TagDetailActivity.startForResult(activity, tag, requestCode);
    }
}
