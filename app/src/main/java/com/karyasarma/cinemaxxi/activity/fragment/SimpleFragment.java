package com.karyasarma.cinemaxxi.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public abstract class SimpleFragment extends Fragment
{
    private int fragmentResourceId;
    private SimpleFragmentListener simpleFragmentListener;
    private View rootView;

    public SimpleFragment()
    {
    }

    /**
     * Set fragmentResourceId value on this method.
     */
    public abstract void init();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        init();
        rootView = inflater.inflate(fragmentResourceId, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if(context instanceof SimpleFragmentListener)
        {
            simpleFragmentListener = (SimpleFragmentListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement SimpleFragmentListener.");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        simpleFragmentListener = null;
    }

    public Context getApplicationContext()
    {
        return getActivity().getApplicationContext();
    }

    public int getFragmentResourceId()
    {
        return fragmentResourceId;
    }

    public void setFragmentResourceId(int fragmentResourceId)
    {
        this.fragmentResourceId = fragmentResourceId;
    }

    public SimpleFragmentListener getSimpleFragmentListener()
    {
        return simpleFragmentListener;
    }

    public View getRootView()
    {
        return rootView;
    }
}
