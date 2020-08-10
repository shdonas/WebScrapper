package com.shossain.projectvantage;

import java.io.IOException;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Shakhawat Hossain <shossain_psu@yahoo.com>
 */
public class BrowseTopic {

    /**
     * this method takes page source
     *
     * @param html
     */
    public int displayTopic(String html) {
        Document doc = Jsoup.parse(html);
        Elements temp = doc.select("dd.browse-by-category-content");
        int i = 1;
        for (Element topicList : temp) {
            System.out.println(i + ". " + topicList.getElementsByTag("button").first().text());
            i++;
        }
        return temp.size();
    }

    /**
     * this method stores all the topic and url into a hashmap it returns the
     * url, based on user choice
     *
     * @param value
     * @param url
     * @return
     */
    public String topicLink(int value, String url) {
        HashMap<Integer, String> topicURL = new HashMap<Integer, String>();

        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements temp = doc.select("dd.browse-by-category-content");

            int i = 1;
            for (Element topicList : temp) {
//                String topic = topicList.getElementsByTag("button").first().text();
                String topicHref = topicList.getElementsByTag("a").attr("abs:href");
                topicURL.put(i, topicHref);
                i++;
            }
        } catch (IOException e) {
            System.out.println("error " + e);
        }
        return topicURL.get(value);
    }

}
