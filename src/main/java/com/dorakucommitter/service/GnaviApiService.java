package com.dorakucommitter.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorakucommitter.Config;
import com.dorakucommitter.domain.TempRestData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GnaviApiService
{
    @Autowired
    Config config;

    /**
     * ぐるなびからレストラン情報を取得し、取得したレストラン情報表示
     * のためにテンプレートに渡すデータ(オブジェクト配列)に成型する。
     *
     * @return List<TempRestData>
     *          templateに渡す検索データ
     */
    public List<TempRestData> restSearch()
    {
        // アクセスキー
        String acckey = config.getGnaviAccessKey();
        // 緯度
        String lat = "35.670082";
        // 経度
        String lon = "139.763267";
        // 範囲
        String range = "1";
        // 返却形式
        String format = "json";
        // エンドポイント
        String gnaviRestUri = "https://api.gnavi.co.jp/RestSearchAPI/20150630/";
        String prmFormat = "?format=" + format;
        String prmKeyid = "&keyid=" + acckey;
        String prmLat = "&latitude=" + lat;
        String prmLon = "&longitude=" + lon;
        String prmRange = "&range=" + range;

        // URI組み立て
        StringBuffer uri = new StringBuffer();
        uri.append(gnaviRestUri);
        uri.append(prmFormat);
        uri.append(prmKeyid);
        uri.append(prmLat);
        uri.append(prmLon);
        uri.append(prmRange);

        // API実行、結果を取得し出力
        JsonNode result = getNodeList(uri.toString());
        if(result != null) {
            //rest Node List取得
            Iterator<JsonNode> restList = result.path("rest").iterator();

            List<TempRestData> restData = new ArrayList<>();
            //店舗番号、店舗名、最寄の路線、最寄の駅、最寄駅から店までの時間、店舗の小業態を出力
            while(restList.hasNext()){
                JsonNode j = restList.next();
                TempRestData t = new TempRestData();
                t.setId(j.path("id").asText());
                t.setName(j.path("name").asText());
                t.setAddress(j.path("address").asText());
                t.setTel(j.path("tel").asText());
                t.setLine(j.path("access").path("line").asText());
                t.setPrShort(j.path("pr").path("pr_short").asText());
                t.setStation(j.path("access").path("station").asText());
                t.setWalk(j.path("access").path("walk").asText() + "分");
                t.setImageUrl1(j.path("image_url").path("shop_image1").asText());
                restData.add(t);
            }
            return(restData);
        } else {
            /* TODO: resultがnullだったらどーすべき？ */
        }
        return(null);
    }

    private JsonNode getNodeList(String url)
    {
        try {
            URL restSearch = new URL(url);
            HttpURLConnection http = (HttpURLConnection)restSearch.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            //Jackson
            ObjectMapper mapper = new ObjectMapper();
            return(mapper.readTree(http.getInputStream()));

        } catch (Exception e){
            //TODO: 例外を考慮していません
        }
        return(null);
    }
}
