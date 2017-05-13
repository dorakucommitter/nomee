package com.dorakucommitter.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dorakucommitter.domain.Rss;
import com.dorakucommitter.domain.TempRestData;
import com.dorakucommitter.service.GnaviApiService;
import com.dorakucommitter.service.RssReadService;

@Controller
public class DefaultController {
    @Autowired
    GnaviApiService gnaviApiService;

    @Autowired
    RssReadService rssReadService;

    /**
     * http://<IP address or FQDN>:port番号/の表示処理
     * テンプレートファイルは、
     *   src/main/resources/templates/<returnで返す文字列>.html
     * @return String テンプレートファイルの相対パス
     *                 (src/main/resources/templates/からの相対パス)
     */
    @RequestMapping("/")
    public String index(Model model) {
        List<TempRestData> restData = this.gnaviApiService.restSearch();
        /* テンプレートパラメータの「restData」にJavaのローカル
         * パラメータの「restData」を紐付ける。
         * (両者の紐付けはaddAttributeメソッドでやるので、同じ名前
         *  にする必要はない。)
         */
        model.addAttribute("restInfo", restData.get(0));
        model.addAttribute("restData", restData);

        Rss rss = rssReadService.getRss("http://blog.livedoor.jp/dqnplus/index.rdf");
        //System.out.println(rss.toString());

        //List<RssItem> rssitems = rss.getItems();
        //System.out.println(rssitems);
        //rssitems.forEach(item -> item.toString());
        model.addAttribute("Rss", rss);

        return "default/index";
    }
}
