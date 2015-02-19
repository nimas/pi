package com.saboonchi.tools;

import java.io.*;

/**
 * Created by nima on 15/03/14.
 */
public class CreateSampleFile {
    public static void create(String filePath,String fileName) {
        String sampleText;
        FileOutputStream fileOutputStream;
        try{
            for (int i = 0 ; i < 10000 ; i++){
                sampleText = "This is sample text file " + i;

                fileOutputStream = new FileOutputStream(filePath+"\\"+fileName+String.format("%08d", i)+".txt");
                fileOutputStream.write(sampleText.getBytes());
                fileOutputStream.close();


            }
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        };


    }

    public static void main(String[] args) {
        create("files\\sampleTextFiles","TAX");
    }
}

