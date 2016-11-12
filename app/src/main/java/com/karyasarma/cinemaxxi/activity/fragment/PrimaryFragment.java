package com.karyasarma.cinemaxxi.activity.fragment;

import android.os.Bundle;

import com.karyasarma.cinemaxxi.R;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class PrimaryFragment extends SimpleFragment
{
    public static final String TAG = "PrimaryFragment";

    public PrimaryFragment()
    {
    }

    @Override
    public void init()
    {
        setFragmentResourceId(R.layout.fragment_primary);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public static PrimaryFragment newInstance()
    {
        PrimaryFragment fragment = new PrimaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
