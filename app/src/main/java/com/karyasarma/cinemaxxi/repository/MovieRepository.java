package com.karyasarma.cinemaxxi.repository;

import com.karyasarma.android.util.Log2;
import com.karyasarma.android.exception.ApplicationException;
import com.karyasarma.cinemaxxi.model.ListMovie;
import com.karyasarma.cinemaxxi.model.Movie;
import com.karyasarma.cinemaxxi.model.Theater;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class MovieRepository
{
    private static final String TAG = "TheaterRepository";

    public MovieRepository()
    {
    }

    public ListMovie findByTheater(Theater theater) throws ApplicationException
    {
        Log2.i(TAG, "findByTheater method called.");
        ListMovie listMovie = new ListMovie();

        try
        {
            String url = String.format("http://www.21cineplex.com/theater/%s,%s,%s.htm", theater.getUrlName(), theater.getId(), theater.getCode());
            Log2.i(TAG, String.format("Connecting to %s", url));
            Connection connection = Jsoup.connect(url);
            Log2.i(TAG, String.format("Connecting to %s successful.", url));
            connection.followRedirects(false);
            Document document = connection.get();
            Elements elements = document.select("[class=table-theater-det]");
            String theaterInfo = document.select("#content > div.col-m_462 > div.col-content").text();
            listMovie.setTheaterInfo(theaterInfo);

            for(Element table : elements)
            {
                Elements listOfTr = table.select("[class~=light|dark]");

                for(Element tr : listOfTr)
                {
                    Element a = tr.select("td:nth-child(1) > a:nth-child(2)").first();
                    Element td2 = tr.select("td:nth-child(2)").first();

                    String movieTitle = a.html();
                    String movieShowtime = Jsoup.parse(td2.html()).text().replaceAll("\u00a0", " "); //Replace &nbsp; character to [SPACE] character.

                    Movie movie = new Movie();
                    movie.setTitle(movieTitle);
                    movie.setShowtime(movieShowtime);
                    listMovie.add(movie);
                }
            }
        }
        catch(IOException |NullPointerException ex)
        {
            throw new ApplicationException(String.format("Failed to retrieve list of movies in \"%s\" from Cinema XXI.", theater.getName()), ex);
        }

        return listMovie;
    }

    public static void main(String[] args) throws ApplicationException
    {
        /*List<Theater> lt = new TheaterRepository().findAll();

        for(Theater theater : lt)
        {
            System.out.println(theater);
        }*/

        //Theater{id=334, code=JKTPRLS, name=LOTTE S. AVENUE XXI PREMIERE, urlName=bioskop-lotte-s-avenue-xxi-premiere, phone=(021) 29889421, address=Ciputra World Jakarta Jl. PROF. DR. Satrio KAV 3-5, Kuningan Jakarta Selatan}

        Theater theater = new Theater();
        theater.setId(334L);
        theater.setCode("JKTPRLS");
        theater.setUrlName("bioskop-lotte-s-avenue-xxi-premiere");
        ListMovie lm = new MovieRepository().findByTheater(theater);

        System.out.println(lm.getTheaterInfo());

        for(Movie movie : lm)
        {
            System.out.println(movie);
        }
    }
}
