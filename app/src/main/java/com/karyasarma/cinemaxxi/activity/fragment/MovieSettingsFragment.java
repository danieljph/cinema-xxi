package com.karyasarma.cinemaxxi.activity.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.karyasarma.android.activity.fragment.SimpleMvpFragment;
import com.karyasarma.android.mvp.SimpleMvpView;
import com.karyasarma.cinemaxxi.R;
import com.karyasarma.cinemaxxi.activity.adapter.MovieSettingAdapter;
import com.karyasarma.cinemaxxi.model.MovieSetting;

import java.util.List;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class MovieSettingsFragment extends SimpleMvpFragment<SimpleMvpView<List<MovieSetting>>, MovieSettingsPresenter> implements SwipeRefreshLayout.OnRefreshListener, SimpleMvpView<List<MovieSetting>>
{
    public static final String TAG = "MovieSettingsFragment";

    private SwipeRefreshLayout contentView;
    private RecyclerView rvMovieSettings;
    private MovieSettingAdapter movieSettingsAdapter;

    public MovieSettingsFragment()
    {
    }

    @Override
    public void init()
    {
        setFragmentResourceId(R.layout.fragment_movie_settings);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        contentView = (SwipeRefreshLayout) getRootView().findViewById(R.id.contentView);
        contentView.setOnRefreshListener(this);

        movieSettingsAdapter = new MovieSettingAdapter();

        rvMovieSettings = (RecyclerView) getRootView().findViewById(R.id.rv_movie_settings);
        rvMovieSettings.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMovieSettings.setItemAnimator(new DefaultItemAnimator());
        rvMovieSettings.setAdapter(movieSettingsAdapter);

        loadData();
    }

    @Override @NonNull
    public MovieSettingsPresenter createPresenter()
    {
        return new MovieSettingsPresenter();
    }

    @Override
    public void onRefresh()
    {
        loadData();
    }

    @Override
    public void loadData()
    {
        presenter.loadMovieSettings();
    }

    @Override
    public void showLoading(boolean isLoading)
    {
        contentView.setRefreshing(isLoading);
    }

    @Override
    public void showContent(List<MovieSetting> data)
    {
        movieSettingsAdapter.setListOfMovieSetting(data);
        movieSettingsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable ex)
    {
        Snackbar.make(getActivity().findViewById(R.id.activity_main_container_layout), "Error: "+ex.getMessage(), Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(Color.RED)
                .show();
    }

    public static MovieSettingsFragment newInstance()
    {
        MovieSettingsFragment fragment = new MovieSettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
