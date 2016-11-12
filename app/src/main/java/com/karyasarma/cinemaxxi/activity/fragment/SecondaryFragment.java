package com.karyasarma.cinemaxxi.activity.fragment;

import android.os.Bundle;

import com.karyasarma.cinemaxxi.R;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class SecondaryFragment extends SimpleFragment
{
    public static final String TAG = "SecondaryFragment";

    public SecondaryFragment()
    {
    }

    @Override
    public void init()
    {
        setFragmentResourceId(R.layout.fragment_secondary);
    }

    public static SecondaryFragment newInstance()
    {
        SecondaryFragment fragment = new SecondaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
