package com.karyasarma.cinemaxxi.task;

import android.os.AsyncTask;

import com.karyasarma.android.task.AsyncTaskListener;
import com.karyasarma.android.task.AsyncTaskResult;
import com.karyasarma.android.util.Log2;
import com.karyasarma.cinemaxxi.model.City;
import com.karyasarma.cinemaxxi.model.MovieSetting;
import com.karyasarma.cinemaxxi.model.Theater;

import java.util.List;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */

public class MovieSettingsAsyncTask extends AsyncTask<Void, Void, AsyncTaskResult<List<MovieSetting>>>
{
    private AsyncTaskListener<List<MovieSetting>> asyncTaskListener;

    public MovieSettingsAsyncTask(AsyncTaskListener<List<MovieSetting>> asyncTaskListener)
    {
        this.asyncTaskListener = asyncTaskListener;
    }

    @Override
    protected AsyncTaskResult<List<MovieSetting>> doInBackground(Void... voids)
    {
        List<MovieSetting> listOfEntity = null;
        Exception error = null;

        try
        {
            City city = new City();
            city.setId(999L);
            city.setCode("MDN");
            city.setName("Medan");
            city.save();

            Theater theater = new Theater();
            theater.setId(999L);
            theater.setCode("STB1");
            theater.setName("Setiabudi One");
            theater.save();

            MovieSetting movieSetting = new MovieSetting();
            movieSetting.setId(1L);
            movieSetting.setType(MovieSetting.NOW_PLAYING);
            movieSetting.setCity(city);
            movieSetting.setTheater(theater);
            movieSetting.save();

            Log2.i("CITY", String.valueOf(City.listAll(City.class)));

            Thread.sleep(1000);

            listOfEntity = MovieSetting.listAll(MovieSetting.class);
        }
        catch(Exception ex)
        {
            error = ex;
        }

        return new AsyncTaskResult<>(listOfEntity, error);
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<List<MovieSetting>> result)
    {
        if(result.isError())
        {
            asyncTaskListener.onError(result.getException());
        }
        else
        {
            asyncTaskListener.onSuccess(result.getData());
        }
    }
}
