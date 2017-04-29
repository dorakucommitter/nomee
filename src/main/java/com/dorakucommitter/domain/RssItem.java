package com.dorakucommitter.domain;

import lombok.Data;

@Data
public class RssItem
{
    String title;
    String link;

    public String toString()
    {
        return("title:" + this.title + "／link" + this.link);
    }
}
