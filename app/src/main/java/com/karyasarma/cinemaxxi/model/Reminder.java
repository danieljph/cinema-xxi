package com.karyasarma.cinemaxxi.model;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class Reminder
{
    private String title;
    private Theater theater;
    private ListMovie listMovie;

    public Reminder()
    {
    }

    public Reminder(String title, ListMovie listMovie)
    {
        this.title = title;
        this.listMovie = listMovie;
    }

    public Reminder(String title, Theater theater)
    {
        this.title = title;
        this.theater = theater;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public ListMovie getListMovie() {
        return listMovie;
    }

    public void setListMovie(ListMovie listMovie) {
        this.listMovie = listMovie;
    }
}
