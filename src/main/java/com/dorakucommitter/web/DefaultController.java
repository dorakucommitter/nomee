package com.dorakucommitter.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dorakucommitter.service.GnaviApiService;
import com.dorakucommitter.domain.TempRestData;

@Controller
public class DefaultController
{
    @Autowired
    GnaviApiService gnaviApiService;

    /**
     * http://<IP address or FQDN>:port番号/の表示処理
     * テンプレートファイルは、
     *   src/main/resources/templates/<returnで返す文字列>.html
     * @return String テンプレートファイルの相対パス
     *                 (src/main/resources/templates/からの相対パス)
     */
    @RequestMapping("/")
    public String index(Model model)
    {
        List<TempRestData> restData = this.gnaviApiService.restSearch();
        /* テンプレートパラメータの「restData」にJavaのローカル
         * パラメータの「restData」を紐付ける。
         * (両者の紐付けはaddAttributeメソッドでやるので、同じ名前
         *  にする必要はない。)
         */
        model.addAttribute("restData", restData);
        return "default/index";
    }
}
