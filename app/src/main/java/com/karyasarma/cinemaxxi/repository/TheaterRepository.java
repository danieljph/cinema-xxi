package com.karyasarma.cinemaxxi.repository;

import com.karyasarma.android.util.Log2;
import com.karyasarma.cinemaxxi.exception.ApplicationException;
import com.karyasarma.cinemaxxi.model.Theater;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class TheaterRepository
{
    private static final String TAG = "TheaterRepository";

    public TheaterRepository()
    {
    }

    public List<Theater> findAll() throws ApplicationException
    {
        Log2.i(TAG, "findAll method called.");
        List<Theater> listOfTheaters = new ArrayList<>();

        try
        {
            String url = "http://www.21cineplex.com/theaters/daftar-bioskop-21-di-jakarta,3.htm"; // This URL contains list of all theaters.
            Log2.i(TAG, String.format("Connecting to %s", url));
            Connection connection = Jsoup.connect(url);
            Log2.i(TAG, String.format("Connecting to %s successful.", url));
            connection.followRedirects(false);
            Document document = connection.get();
            Elements elements = document.select("[data-city]");

            for(Element tr : elements)
            {
                String theaterPhone = tr.children().last().html();

                /**
                 * Find theater name.
                 * Remove <br><span class="kota">JAKARTA</span> from
                 * GADING IMAX<br><span class="kota">JAKARTA</span>.
                 */
                Element a = tr.select("a").first();
                String theaterName = a.html();
                theaterName = theaterName.substring(0, theaterName.indexOf("<br>")).trim();

                String href = a.attr("href");

                /**
                 * Remove http://www.21cineplex.com/theater/ and .htm
                 * from http://www.21cineplex.com/theater/bioskop-gading-imax,331,JKTIXGD.htm.
                 */
                String theaterInfo = href.substring(34); //Remove "http://www.21cineplex.com/theater/".
                theaterInfo = theaterInfo.substring(0, theaterInfo.length()-4); //Remove ".htm".
                String[] temp = theaterInfo.split(",");

                String theaterUrlName = temp[0];
                String theaterId = temp[1];
                String theaterCode = temp[2];

                String theaterAddress = Jsoup.parse(a.attr("rel")).text();

                Theater theater = new Theater();
                theater.setId(theaterId);
                theater.setCode(theaterCode);
                theater.setName(theaterName);
                theater.setUrlName(theaterUrlName);
                theater.setPhone(theaterPhone);
                theater.setAddress(theaterAddress);
                listOfTheaters.add(theater);
            }
        }
        catch(IOException |NullPointerException ex)
        {
            throw new ApplicationException("Failed to retrieve list of all theaters from Cinema XXI.", ex);
        }

        return listOfTheaters;
    }
}
