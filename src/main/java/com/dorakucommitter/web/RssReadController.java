package com.dorakucommitter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dorakucommitter.domain.Rss;
import com.dorakucommitter.service.RssReadService;

@Controller
@RequestMapping("/rssread")
public class RssReadController
{
    @Autowired
    RssReadService rssReadService;

    @RequestMapping("/")
    public String index(Model model)
    {
        Rss rss = rssReadService.getRss("http://blog.livedoor.jp/dqnplus/index.rdf");
        //System.out.println(rss.toString());

        //List<RssItem> rssitems = rss.getItems();
        //System.out.println(rssitems);
        //rssitems.forEach(item -> item.toString());
        model.addAttribute("Rss", rss);

        return "rssread/index";
    }
}
