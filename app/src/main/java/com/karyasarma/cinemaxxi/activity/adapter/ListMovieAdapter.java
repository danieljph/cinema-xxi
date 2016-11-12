package com.karyasarma.cinemaxxi.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karyasarma.cinemaxxi.R;
import com.karyasarma.cinemaxxi.model.ListMovie;
import com.karyasarma.cinemaxxi.model.Movie;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MyViewHolder>
{
    private ListMovie listMovie;
    private int counter;

    public ListMovieAdapter()
    {
        this.listMovie = new ListMovie();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position)
    {
        Movie movie = listMovie.get(position);
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvShowtime.setText(movie.getShowtime());
    }

    @Override
    public int getItemCount()
    {
        return listMovie.size();
    }

    public ListMovie getListMovie()
    {
        return listMovie;
    }

    public void setListMovie(ListMovie listMovie)
    {
        this.listMovie = listMovie;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvTitle;
        public TextView tvShowtime;

        public MyViewHolder(View view)
        {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvShowtime = (TextView) view.findViewById(R.id.tv_showtime);
        }
    }
}
