package com.dorakucommitter.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Rss
{
    String title;
    int itemcnt;
    List<RssItem> items;

    public Rss()
    {
        this.items = new ArrayList<RssItem>();
    }

    public void setItems(RssItem item)
    {
        this.items.add(item);
    }

    public List<RssItem> getItems()
    {
        System.out.println(this.items.size());
        return(this.items);
    }

    public String toString()
    {
        return(this.title + "(" + items.size() + ")");
    }
}
