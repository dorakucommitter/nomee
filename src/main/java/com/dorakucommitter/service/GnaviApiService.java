//
package com.dorakucommitter.service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GnaviApiService {

	  public String restSearch(String[] args) {
	      // アクセスキー
	        String acckey = "044ec29464ad5827815d83bba1db9d9a";
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
	      getNodeList(uri.toString());
	      return null;
	  }

	    private void getNodeList(String url) {
	        try {
	            URL restSearch = new URL(url);
	            HttpURLConnection http = (HttpURLConnection)restSearch.openConnection();
	            http.setRequestMethod("GET");
	            http.connect();
	            //Jackson
	            ObjectMapper mapper = new ObjectMapper();
	            viewJsonNode(mapper.readTree(http.getInputStream()));

	        } catch (Exception e){
	          //TODO: 例外を考慮していません
	        }
	    }

	    private void viewJsonNode(JsonNode nodeList){
	        if(nodeList != null){
	            //トータルヒット件数
	            String hitcount   = "total:" + nodeList.path("total_hit_count").asText();
	            System.out.println(hitcount);
	            //restのみ取得
	            JsonNode restList = nodeList.path("rest");
	            Iterator<JsonNode> rest = restList.iterator();
	            //店舗番号、店舗名、最寄の路線、最寄の駅、最寄駅から店までの時間、店舗の小業態を出力
	            while(rest.hasNext()){
	              JsonNode r = rest.next();
	              String id = r.path("id").asText();
	              String name = r.path("name").asText();
	              String line = r.path("access").path("line").asText();
	              String station = r.path("access").path("station").asText();
	              String walk    = r.path("access").path("walk").asText() + "分";
	              String categorys = "";

	              for(JsonNode n : r.path("code").path("category_name_s")){
	                  categorys += n.asText();
	              }
	              System.out.println(id + "¥t" + name + "¥t" + line + "¥t" + station + "¥t" + walk + "¥t" + categorys);
	           }
	        }
	    }

}
