package com.dorakucommitter.service;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dorakucommitter.domain.Rss;
import com.dorakucommitter.domain.RssItem;

@Service
public class RssReadService
{
    public Rss getRss(String RssURL)
    {
        Rss rss = null;

        try
        {
            rss = new Rss();
            DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder         builder = factory.newDocumentBuilder();
            Document                document = builder.parse(RssURL);
            Element                 root = document.getDocumentElement();

            /* Get and print Title of RSS Feed. */
            NodeList                channel = root.getElementsByTagName("channel");
            NodeList                title = ((Element)channel.item(0)).getElementsByTagName("title");
            //System.out.println("\nTitle: " + title.item(0).getFirstChild().getNodeValue() + "\n");
            rss.setTitle(title.item(0).getFirstChild().getNodeValue());

            /* Get Node list of RSS items */
            NodeList                item_list = root.getElementsByTagName("item");
            for (int i = 0; i <item_list.getLength(); i++) {
                RssItem rssitem = new RssItem();
                Element  element = (Element)item_list.item(i);
                NodeList item_title = element.getElementsByTagName("title");
                NodeList item_link  = element.getElementsByTagName("link");
                //System.out.println(" title: " + item_title.item(0).getFirstChild().getNodeValue());
                //System.out.println(" link:  " + item_link.item(0).getFirstChild().getNodeValue() + "\n");
                rssitem.setTitle(item_title.item(0).getFirstChild().getNodeValue());
                rssitem.setLink(item_link.item(0).getFirstChild().getNodeValue());
                //System.out.println(rssitem.toString());
                rss.setItems(rssitem);
                rssitem = null;
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        } catch (ParserConfigurationException e) {
            System.out.println("Parser Configuration Exception");
        } catch (SAXException e) {
            System.out.println("SAX Exception");
        }
        return(rss);
    }
}
