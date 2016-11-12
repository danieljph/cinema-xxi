package com.karyasarma.cinemaxxi.model;

import java.util.ArrayList;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class ListMovie extends ArrayList<Movie>
{
    private String theaterInfo;

    public ListMovie()
    {
    }

    public String getTheaterInfo()
    {
        return theaterInfo;
    }

    public void setTheaterInfo(String theaterInfo)
    {
        this.theaterInfo = theaterInfo;
    }
}
