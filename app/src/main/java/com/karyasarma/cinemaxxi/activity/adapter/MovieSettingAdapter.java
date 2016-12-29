package com.karyasarma.cinemaxxi.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karyasarma.cinemaxxi.R;
import com.karyasarma.cinemaxxi.model.MovieSetting;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class MovieSettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final String TAG = "MovieSettingAdapter";

    private static final int NOW_PLAYING = 1;
    private static final int THEATER = 2;

    private List<MovieSetting> listOfMovieSetting;

    public MovieSettingAdapter()
    {
        listOfMovieSetting = new ArrayList<>();
    }

    public MovieSettingAdapter(List<MovieSetting> listOfMovieSetting)
    {
        this.listOfMovieSetting = listOfMovieSetting;
    }

    @Override
    public int getItemViewType(int position)
    {
        int viewType = NOW_PLAYING;
        MovieSetting movieSetting = listOfMovieSetting.get(position);

        switch (movieSetting.getType())
        {
            case MovieSetting.NOW_PLAYING:
            {
                viewType = NOW_PLAYING;
                break;
            }
            case MovieSetting.THEATER:
            {
                viewType = THEATER;
                break;
            }
        }

        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = null;

        switch(viewType)
        {
            case NOW_PLAYING:
            {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_setting_now_playing, parent, false);
                viewHolder = new MovieSettingAdapter.NowPlayingViewHolder(itemView);
                break;
            }
            case THEATER:
            {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_setting_theater, parent, false);
                viewHolder = new MovieSettingAdapter.TheaterViewHolder(itemView);
                break;
            }
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        MovieSetting movieSetting = listOfMovieSetting.get(position);

        switch(movieSetting.getType())
        {
            case MovieSetting.NOW_PLAYING:
            {
                MovieSettingAdapter.NowPlayingViewHolder npViewHolder = (MovieSettingAdapter.NowPlayingViewHolder) viewHolder;
                npViewHolder.tvCity.setText(movieSetting.getCity().getName());
                break;
            }
            case MovieSetting.THEATER:
            {
                MovieSettingAdapter.TheaterViewHolder npViewHolder = (MovieSettingAdapter.TheaterViewHolder) viewHolder;
                npViewHolder.tvCity.setText(movieSetting.getCity().getName());
                npViewHolder.tvTheater.setText(movieSetting.getTheater().getName());
                break;
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return listOfMovieSetting.size();
    }

    public void setListOfMovieSetting(List<MovieSetting> listOfMovieSetting)
    {
        this.listOfMovieSetting = listOfMovieSetting;
    }

    public class NowPlayingViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvType;
        public TextView tvCity;

        public NowPlayingViewHolder(View view)
        {
            super(view);

            tvType = (TextView) view.findViewById(R.id.tv_type);
            tvCity = (TextView) view.findViewById(R.id.tv_city);
        }
    }

    public class TheaterViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvType;
        public TextView tvCity;
        public TextView tvTheater;

        public TheaterViewHolder(View view)
        {
            super(view);

            tvType = (TextView) view.findViewById(R.id.tv_type);
            tvCity = (TextView) view.findViewById(R.id.tv_city);
            tvTheater = (TextView) view.findViewById(R.id.tv_theater);
        }
    }
}
