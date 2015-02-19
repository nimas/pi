package com.saboonchi.tools;

import java.io.FileOutputStream;

/**
 * Created by a on 2014-03-22.
 */
public class W {
    public static boolean result = false;
    public static boolean rite (String filePath,String fileName,byte[] fileBytes){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath+fileName);
            fileOutputStream.write(fileBytes);
            fileOutputStream.close();
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }

        return result;
    }
    public static boolean rite (String filePathName,byte[] fileBytes){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePathName);
            fileOutputStream.write(fileBytes);
            fileOutputStream.close();
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }

        return result;
    }

}
