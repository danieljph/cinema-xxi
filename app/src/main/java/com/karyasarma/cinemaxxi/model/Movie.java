package com.karyasarma.cinemaxxi.model;

import java.io.Serializable;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class Movie implements Serializable
{
    private int id;
    private String code;
    private String title;
    private String showtime;

    public Movie()
    {
    }

    public Movie(String title, String showtime)
    {
        this.title = title;
        this.showtime = showtime;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getShowtime()
    {
        return showtime;
    }

    public void setShowtime(String showtime)
    {
        this.showtime = showtime;
    }

    @Override
    public String toString()
    {
        return "Movie{"+"id="+id+", code="+code+", title="+title+", showtime="+showtime+'}';
    }
}
