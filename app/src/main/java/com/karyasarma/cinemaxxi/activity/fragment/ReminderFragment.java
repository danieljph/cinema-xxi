package com.karyasarma.cinemaxxi.activity.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.karyasarma.cinemaxxi.R;
import com.karyasarma.cinemaxxi.activity.adapter.ListReminderAdapter;
import com.karyasarma.cinemaxxi.exception.ApplicationException;
import com.karyasarma.cinemaxxi.model.ListMovie;
import com.karyasarma.cinemaxxi.model.ListReminder;
import com.karyasarma.cinemaxxi.model.Reminder;
import com.karyasarma.cinemaxxi.model.Theater;
import com.karyasarma.cinemaxxi.repository.MovieRepository;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class ReminderFragment extends SimpleFragment
{
    public static final String TAG = "ReminderFragment";

    private RecyclerView rvReminder;
    private ListReminderAdapter listReminderAdapter;
    private ListReminder listReminder;

    public ReminderFragment()
    {
    }

    @Override
    public void init()
    {
        setFragmentResourceId(R.layout.fragment_reminder);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        listReminder = new ListReminder();
        listReminder.add(new Reminder("Movie - Anggrek XXI", new Theater("15", "JKTANGG", "bioskop-anggrek-xxi")));
        listReminder.add(new Reminder("Movie - Emporium Pluit Premiere", new Theater("281", "JKTPREP", "bioskop-emporium-pluit-premiere")));
        listReminder.add(new Reminder("Movie - Kemang Village Premiere", new Theater("323", "JKTPRKV", "bioskop-kemang-village-premiere")));

        listReminderAdapter = new ListReminderAdapter(listReminder);

        rvReminder = (RecyclerView) getRootView().findViewById(R.id.rv_reminder);
        rvReminder.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvReminder.setItemAnimator(new DefaultItemAnimator());
        rvReminder.setAdapter(listReminderAdapter);

        for(Reminder reminder : listReminder)
        {
            new LoadMovieWorker(reminder).execute();
        }
    }

    public static ReminderFragment newInstance()
    {
        ReminderFragment fragment = new ReminderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private class LoadMovieWorker extends AsyncTask<Void, Void, ListMovie>
    {
        private Reminder reminder;

        public LoadMovieWorker(Reminder reminder)
        {
            this.reminder = reminder;
        }

        @Override
        protected ListMovie doInBackground(Void... voids)
        {
            ListMovie listMovie = new ListMovie();

            try
            {
                listMovie = new MovieRepository().findByTheater(reminder.getTheater());
            }
            catch (ApplicationException ex)
            {
                Log.e(TAG, ex.getMessage(), ex);
                Snackbar.make(getActivity().findViewById(R.id.activity_main_container_layout), "Error: "+ex.getMessage(), Snackbar.LENGTH_INDEFINITE)
                        .setActionTextColor(Color.RED)
                        .setAction("RETRY", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                new LoadMovieWorker(reminder).execute();
                            }
                        })
                        .show();
            }

            return listMovie;
        }

        @Override
        protected void onPostExecute(ListMovie listMovie)
        {
            super.onPostExecute(listMovie);

            reminder.setListMovie(listMovie);
            listReminderAdapter.setListReminder(listReminder);
            listReminderAdapter.notifyDataSetChanged();
        }
    }
}
