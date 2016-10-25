package com.sooch.qiita_reader.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.sooch.qiita_reader.BuildConfig;
import com.sooch.qiita_reader.R;
import com.sooch.qiita_reader.databinding.FragmentSettingsBinding;
import com.sooch.qiita_reader.domain.constant.Preference;
import com.sooch.qiita_reader.domain.constant.DefaultSettings;
import com.sooch.qiita_reader.util.PrefUtils;

/**
 * Created by Takashi Sou on 2016/09/24.
 */
public class SettingsFragment extends Fragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    private FragmentSettingsBinding binding;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
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
        // API
        binding.seekPerPage.setSecondaryProgress(DefaultSettings.PER_PAGE);
        binding.seekPerPage.setProgress(PrefUtils.getInt(getContext(), Preference.PER_PAGE, DefaultSettings.PER_PAGE));
        binding.seekPerPage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < DefaultSettings.MIN_PER_PAGE) {
                    binding.seekPerPage.setProgress(progress + 1);
                    return;
                }

                binding.perPage.setText(getString(R.string.setting_api_per_page_description) + progress);
                PrefUtils.putInt(getContext(), Preference.PER_PAGE, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.perPage.setText(getString(R.string.setting_api_per_page_description) + binding.seekPerPage.getProgress());

        // build version
        binding.buildVersion.setText(BuildConfig.VERSION_NAME);
    }
}