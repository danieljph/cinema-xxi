package com.karyasarma.cinemaxxi.activity.adapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karyasarma.cinemaxxi.R;
import com.karyasarma.cinemaxxi.model.ListMovie;
import com.karyasarma.cinemaxxi.model.ListReminder;
import com.karyasarma.cinemaxxi.model.Reminder;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class ListReminderAdapter extends RecyclerView.Adapter<ListReminderAdapter.MyViewHolder>
{
    private ListReminder listReminder;

    public ListReminderAdapter()
    {
        this.listReminder = new ListReminder();
    }

    public ListReminderAdapter(ListReminder listReminder)
    {
        this.listReminder = new ListReminder();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reminder, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position)
    {
        Reminder reminder = listReminder.get(position);
        viewHolder.tvReminderTitle.setText(reminder.getTitle());

        ListMovie listMovie = reminder.getListMovie();

        if(listMovie!=null)
        {
            viewHolder.tvReminderTheaterInfo.setText(listMovie.getTheaterInfo());
            viewHolder.listReminderAdapter.setListMovie(listMovie);
            viewHolder.listReminderAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount()
    {
        return listReminder.size();
    }

    public void setListReminder(ListReminder listReminder)
    {
        this.listReminder = listReminder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvReminderTitle;
        public TextView tvReminderTheaterInfo;
        public ListMovieAdapter listReminderAdapter;

        public MyViewHolder(View view)
        {
            super(view);

            tvReminderTitle = (TextView) view.findViewById(R.id.tv_reminder_title);
            tvReminderTheaterInfo = (TextView) view.findViewById(R.id.tv_reminder_theater_info);

            listReminderAdapter = new ListMovieAdapter();

            RecyclerView rvMovie = (RecyclerView) view.findViewById(R.id.rv_movie);
            rvMovie.setLayoutManager(new LinearLayoutManager(view.getContext()));
            rvMovie.setItemAnimator(new DefaultItemAnimator());
            rvMovie.setAdapter(listReminderAdapter);
        }
    }
}
