package com.dorakucommitter.domain;

import java.util.ArrayList;
import java.util.List;

//@Data
public class Rss
{
    private String title;
    private List<RssItem> items;

    /**
     * コンストラクタ
     * プロパティのitemsに入れ物となるメモリが確保されてないので、確保する。
     */
    public Rss()
    {
        this.items = new ArrayList<RssItem>();
    }

    /**
     * RSSのタイトルを設定する
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * RSSに登録されていたアイテムを登録する。
     * 通常複数登録されているのでArrayListに突っ込む。
     * @param item
     */
    public void setItems(RssItem item)
    {
        this.items.add(item);
    }

    /**
     * RSSに設定されていたTitleを取得する。
     * @return
     */
    public String getTitle()
    {
        return(this.title);
    }

    /**
     * RSSのアイテム（ArrayList）を取得する。
     * @return
     */
    public List<RssItem> getItems()
    {
        System.out.println(this.items.size());
        return(this.items);
    }

    /**
     * RSSオブジェクト情報出力（デバッグ用）
     * RSSオブジェクトの登録内容をテキスト出力します。
     */
    public String toString()
    {
        return(this.title + "(" + items.size() + ")");
    }
}
