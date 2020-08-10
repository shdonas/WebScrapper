package com.shossain.projectvantage;

import java.util.Scanner;

/**
 *
 * @author Shakhawat Hossain <shossain_psu@yahoo.com>
 */
public class MasterClass {

    // this is the main topic site link
    private static final String TOPIC_URL = "https://www.cochranelibrary.com/cdsr/reviews/topics";

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        HTTPConnection connHTTP = new HTTPConnection();
        BrowseTopic bt = new BrowseTopic();
        GetReviews gr = new GetReviews();
        FileHandler fh = new FileHandler();

        String exitProg = "n";

        while (exitProg.equalsIgnoreCase("n")) {
//        fh.deleteFile(); // Deleting old cochrane_reviews.txt file if already exist
            int numTopics = bt.displayTopic(connHTTP.getPageSource(TOPIC_URL)); // will display all the available topic
//            System.out.println(numTopics);

            System.out.println("Pick A topic: ");
            int user = userInput.nextInt();
            while (user > numTopics || user <= 0) {
                System.out.println("Pick A topic: ");
                user = userInput.nextInt();
            }

            // WriteToFile(), this method store the reviews to a txt file from the arraylist
            // getReview(), it will return an arraylist of reviews 
            //
            fh.WriteToFile(
                    gr.getReview(
                            bt.topicLink(user, TOPIC_URL)));

            System.out.println("\nDo you want to open generated text file? y/n");
            String openFile = userInput.next();
            if (openFile.equals("y")) {
                fh.openFile();

            }
            fh.getFilePath();

            System.out.println("Terminate application: y/n");
            exitProg = userInput.next();
        }
    }
}
