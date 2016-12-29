package com.karyasarma.cinemaxxi.model;

import com.orm.SugarRecord;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class City extends SugarRecord implements SimpleEntity
{
    private String code;
    private String name;

    public City()
    {
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "City{"+"id="+getId()+", code="+code+", name="+name+'}';
    }
}
