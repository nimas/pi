package com.saboonchi.tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by a on 2014-03-22.
 */
public class R {
    public static byte[] fileBytes = null;

    public static byte[] ead(String filePathName){
        byte[] fileBytes = null;
        try {

            Path path = Paths.get(filePathName);
            fileBytes = Files.readAllBytes(path);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileBytes;
    }

    public static String eadMD5String(String filePathName){

        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.reset();
        m.update(R.ead(filePathName));

        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashText = bigInt.toString(16);
        while(hashText.length() < 32 ){
            hashText = "0"+hashText;
        }
        return  hashText;

    }

    public static byte[] eadMD5ByteArray(String filePathName){

        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.reset();
        m.update(R.ead(filePathName));

        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashText = bigInt.toString(16);
        while(hashText.length() < 32 ){
            hashText = "0"+hashText;
        }
        return  digest;

    }

    public static void listItemsInsideFolder(String folderPath,String extension) {

        // Directory path here
        String path = folderPath;

        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++)
        {

            if (listOfFiles[i].isFile())
            {
                files = listOfFiles[i].getName();
                if (files.endsWith("."+extension))
                {
                    System.out.println(files);
                }
            }
        }

    }
    public static void listItemsInsideFolder(String folderPath) {

        // Directory path here
        String path = folderPath;

        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++)
        {

            if (listOfFiles[i].isFile())
            {
                files = listOfFiles[i].getName();

                    System.out.println(files);
            }
        }

    }

}
