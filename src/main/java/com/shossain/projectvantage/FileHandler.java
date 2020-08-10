package com.shossain.projectvantage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author Shakhawat Hossain <shossain_psu@yahoo.com>
 */
public class FileHandler {

    GetReviews gr = new GetReviews();

    private final String name = "cochrane_reviews.txt";
    File file = new File(name);

    /**
     * This function takes an Arraylist and stores it to a text file
     *
     * @param al
     */
    public void WriteToFile(ArrayList al) {
        try {

            ArrayList listCloned = new ArrayList();
            listCloned = (ArrayList) al.clone();

            File fout = new File(name);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (Object arrL : listCloned) {
                bw.write(arrL.toString());
                bw.newLine();
                bw.newLine();
            }
            bw.close();

            bw.close();

        } catch (IOException ioe) {

        }
    }

    /**
     * this method opens the text file with notepad
     */
    public void openFile() {
        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", name);
            pb.start();
        } catch (IOException ioe) {

        }
    }

    public void deleteFile() {
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public String getFilePath() {
        return file.getAbsolutePath().toString();
    }

}
