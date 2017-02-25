package com.dorakucommitter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class DefaultController 
{
    /**
     * http://<IP address or FQDN>:port番号/の表示処理
     * テンプレートファイルは、
     *   src/main/resources/templates/<returnで返す文字列>.html
     * @return String テンプレートファイルの相対パス
     *                 (src/main/resources/templates/からの相対パス)
     */
    @RequestMapping("/")
    public String index()
    {
        return "default/index";
    }
}
