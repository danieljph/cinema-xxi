package com.karyasarma.cinemaxxi.model;

import com.orm.SugarRecord;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class MovieSetting extends SugarRecord implements SimpleEntity
{
    public static final String NOW_PLAYING = "NOW_PLAYING";
    public static final String THEATER = "THEATER";

    private String type;
    private City city;
    private Theater theater;

    public MovieSetting()
    {
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public Theater getTheater()
    {
        return theater;
    }

    public void setTheater(Theater theater)
    {
        this.theater = theater;
    }
}
