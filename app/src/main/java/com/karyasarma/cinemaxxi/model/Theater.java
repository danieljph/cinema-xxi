package com.karyasarma.cinemaxxi.model;

import com.orm.SugarRecord;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class Theater extends SugarRecord implements SimpleEntity
{
    private String code;
    private String name;
    private String urlName;
    private String phone;
    private String address;

    public Theater()
    {
    }

    public Theater(long id, String code, String urlName)
    {
        setId(id);
        this.code = code;
        this.urlName = urlName;
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

    public String getUrlName()
    {
        return urlName;
    }

    public void setUrlName(String urlName)
    {
        this.urlName = urlName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "Theater{"+"id="+getId()+", code="+code+", name="+name+", urlName="+urlName+", phone="+phone+", address="+address+'}';
    }
}
