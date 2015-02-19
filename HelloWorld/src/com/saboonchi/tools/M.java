package com.saboonchi.tools;

import java.io.File;

/**
 * Created by a on 2014-04-11.
 */
public class M {

    public static void ove(String sourceFilePathName,String destinationFilePathName){

       P.rintln("Move ("+sourceFilePathName+") to ("+destinationFilePathName+") start");

        try{

            File file =new File(sourceFilePathName);

            if(!file.renameTo(new File(destinationFilePathName))){
                System.out.println("File is failed to move!");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        //P.rintln("Move ("+sourceFilePathName+") to ("+destinationFilePathName+") End");

    }

    public static void main(String[] args) {
        File files[] = new File("files\\sampleTextFiles\\").listFiles();

        for (int i = 0 ; i < 5 ; i++){
            ove("files\\sampleTextFiles\\"+files[i].getName(),"files\\sampleTextFiles1\\"+files[i].getName());

        }
    }
}
