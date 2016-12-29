package com.karyasarma.cinemaxxi.repository;

import com.karyasarma.android.util.Log2;
import com.karyasarma.android.exception.ApplicationException;
import com.karyasarma.cinemaxxi.model.City;

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
public class CityRepository
{
    private static final String TAG = "CityRepository";

    public CityRepository()
    {
    }

    public List<City> findAll() throws ApplicationException
    {
        Log2.i(TAG, "findAll method called.");
        List<City> listOfCities = new ArrayList<>();

        try
        {
            String url = "http://www.21cineplex.com/nowplaying";
            Log2.i(TAG, "Connecting to "+url);
            Connection connection = Jsoup.connect(url);
            Log2.i(TAG, "Connecting to "+url+" successful.");
            connection.followRedirects(false);
            Document document = connection.get();
            Elements elements = document.select("#content > div.col-l_240 > div.grad.boxleft-240 > div.col-content > ul");

            Element ul = elements.first();
            Elements listOfLi = ul.children();

            for(Element li : listOfLi)
            {
                Element a = li.getElementsByTag("a").first();
                String href = a.attr("href");

                /**
                 * Remove http://www.21cineplex.com/nowplaying/ and .htm
                 * from http://www.21cineplex.com/nowplaying/bengkulu,81,BKL.htm.
                 */
                String cityInfo = href.substring(37); //Remove "http://www.21cineplex.com/nowplaying/".
                cityInfo = cityInfo.substring(0, cityInfo.length()-4); //Remove ".htm".
                String[] temp = cityInfo.split(",");

                String cityName = temp[0].toUpperCase();
                String cityId = temp[1];
                String cityCode = temp[2];

                City city = new City();
                city.setId(Long.parseLong(cityId));
                city.setCode(cityCode);
                city.setName(cityName);
                listOfCities.add(city);
            }
        }
        catch(IOException|NullPointerException ex)
        {
            throw new ApplicationException("Failed to retrieve list of cities from Cinema XXI.", ex);
        }

        return listOfCities;
    }

    public static void main(String[] args) throws ApplicationException
    {
        List<City> listOfCity = new CityRepository().findAll();

        for(City city : listOfCity)
        {
            System.out.println(city);
        }
    }
}
