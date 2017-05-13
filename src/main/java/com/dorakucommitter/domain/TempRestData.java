package com.dorakucommitter.domain;

import lombok.Data;

@Data
public class TempRestData
{
    private String id;
    private String name;
    private String address;
    private String tel;
    private String line;
    private String prShort;
    private String station;
    private String walk;
    private String categorys;
    private String imageUrl1;

    public String getId()
    {
        return(this.id);
    }

    public void setId(String id)
    {
        this.id = id;
    }


    public String getName()
    {
        return(this.name);
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String getLine()
    {
        return(this.line);
    }

    public void setLine(String line)
    {
        this.line = line;
    }


    public String getStation()
    {
        return(this.station);
    }

    public void setStation(String station)
    {
        this.station = station;
    }


    public String getWalk()
    {
        return(this.walk);
    }

    public void setWalk(String walk)
    {
        this.walk = walk;
    }


    public String getCategorys()
    {
        return(this.categorys);
    }

    public void setCategorys(String categorys)
    {
        this.categorys = categorys;
    }
}
