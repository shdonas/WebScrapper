package com.shossain.projectvantage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shakhawat Hossain <shossain_psu@yahoo.com>
 */
public class HTTPConnection {

    String html = null;

    /**
     * This method connects to the Topic site using the URL provided. using
     * Apache HttpClient 4.5+
     *
     * @param topicHref
     * @return String This returns the entire page source
     */
    public String getPageSource(String topicHref) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(topicHref))
                .GET() // GET is default
                .build();

        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            html = response.body();

        } catch (IOException ex) {
            Logger.getLogger(HTTPConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HTTPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return html;
    }
}
