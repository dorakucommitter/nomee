package com.dorakucommitter.domain;

//@Data
public class RssItem
{
    String title;
    String link;
    String description;

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public String getTitle()
    {
        return(this.title);
    }

    public String getLink()
    {
        return(this.link);
    }

    public String getDescription()
    {
        return(this.description);
    }

    /**
     * RssItemオブジェクト情報出力（デバッグ用）
     * RssItemオブジェクトの登録内容をテキスト出力します。
     * 書式はかなりテキトーです。
     */
    public String toString()
    {
        return("title:" + this.title + "／link" + this.link + "／description" + this.description);
    }
}
