package com.dorakucommitter.domain;

import lombok.Data;

@Data
public class RssItem
{
    String title;
    String link;
    String description;

    public String toString()
    {
        return("title:" + this.title + "／link" + this.link + "／description" + this.description);
    }
}
