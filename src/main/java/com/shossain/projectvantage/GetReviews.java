package com.shossain.projectvantage;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Shakhawat Hossain <shossain_psu@yahoo.com>
 */
public class GetReviews {

    /**
     * this method directs to the review page and go through all the review and
     * gets all the metadata and prints it to the terminal
     *
     * @param topicHref
     * @return listOfReviews, an ArrayList with all the metadata
     */
    public ArrayList getReview(String topicHref) {

        String url, topic, title, author, date;

        ArrayList<String> listOfReviews = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect(topicHref).userAgent("Mozilla").get();
            System.out.println(doc.location());
            Elements temp = doc.select("div.search-results-item-body");

            int i = 1;
            for (Element reviewData : temp) {

                url = reviewData.getElementsByTag("a").attr("abs:href");
                System.out.println("URL - " + url);
                topic = doc.getElementById("searchResultText").text();
                System.out.println("Topic - " + topic);
                title = reviewData.getElementsByTag("a").first().text();
                System.out.println("Title - " + title);
                author = reviewData.getElementsByClass("search-result-authors").first().text();
                System.out.println("Author - " + author);
                date = reviewData.getElementsByClass("search-result-date").first().text();
                System.out.println("Date - " + date + "\n");

                listOfReviews.add(url + "|" + topic + "|" + title + "|" + author + "|" + date);
                i++;
            }

        } catch (IOException ioe) {

        }
        return listOfReviews;
    }

    /**
     * Method unused
     *
     * @param topicHref
     * @return
     */
    public int totalReviewPage(String topicHref) {
        int numOfPages = 0;
        try {
            Document doc = Jsoup.connect(topicHref).userAgent("Mozilla").get();
            Elements li = doc.select("div.pagination-page-links > ul > li");
            numOfPages = li.size();
        } catch (IOException ioe) {

        }
        return numOfPages;
    }

    /**
     * method unused
     *
     * @param topicHref
     * @return
     */
    public String getNextPageLink(String topicHref) {
        String nextLink = null;
        try {
            Document doc = Jsoup.connect(topicHref).userAgent("Mozilla").get();
            Elements next = doc.select("div.pagination-next-link");

            int i = 1;
            for (Element n : next) {
                if (n.text().equals("Next")) {
//                    System.out.println("Found next");
                    nextLink = n.getElementsByTag("a").attr("abs:href");
                } else {
                    System.out.println("no more pages");
                }
                i++;
            }
        } catch (IOException ex) {

        }
        return nextLink;
    }
}
