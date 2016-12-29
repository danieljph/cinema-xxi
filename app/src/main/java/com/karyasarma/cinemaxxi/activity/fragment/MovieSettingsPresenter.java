package com.karyasarma.cinemaxxi.activity.fragment;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.karyasarma.android.mvp.SimpleMvpView;
import com.karyasarma.cinemaxxi.model.MovieSetting;
import com.karyasarma.android.task.AsyncTaskListener;
import com.karyasarma.cinemaxxi.task.MovieSettingsAsyncTask;

import java.util.List;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class MovieSettingsPresenter extends MvpBasePresenter<SimpleMvpView<List<MovieSetting>>>
{
    private MovieSettingsAsyncTask movieSettingsAsyncTask;

    public MovieSettingsPresenter()
    {
    }

    public void loadMovieSettings()
    {
        cancelRunningTask();
        getView().showLoading(true);

        movieSettingsAsyncTask = new MovieSettingsAsyncTask(new AsyncTaskListener<List<MovieSetting>>()
        {
            @Override
            public void onSuccess(List<MovieSetting> data)
            {
                if(isViewAttached())
                {
                    getView().showContent(data);
                    getView().showLoading(false);
                }
            }

            @Override
            public void onError(Exception ex)
            {
                if(isViewAttached())
                {
                    getView().showError(ex);
                    getView().showLoading(false);
                }
            }
        });

        movieSettingsAsyncTask.execute();
    }

    private void cancelRunningTask()
    {
        if(movieSettingsAsyncTask!=null)
        {
            movieSettingsAsyncTask.cancel(true);
        }

        if(isViewAttached())
        {
            getView().showLoading(false);
        }
    }
}
