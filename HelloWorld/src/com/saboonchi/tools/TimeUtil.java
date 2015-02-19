package com.saboonchi.tools;

import java.math.BigInteger;

/**
 * Created by nisab1 on 2014-05-23.
 */
public class TimeUtil {
    public static long startTime,endTime;
    public static long start(){
        startTime = System.currentTimeMillis();
        return startTime;
    }
    public static long end(long startTime,String processName){
        endTime = System.currentTimeMillis();
        P.rintln("Time for process ("+processName+") was : " + (endTime-startTime));
        return endTime;
    }
    public static void end(long startTime,String processName,int loopNumber){
        endTime = System.currentTimeMillis();
        P.rintln("Time for process ("+processName+") was : " + (endTime-startTime) +" millisecond");
        P.rintln("Time for process ("+processName+") was : " + ((endTime-startTime)/1000) +" second");
        P.rintln("Time for process ("+processName+") was : " + (((endTime-startTime)/loopNumber) +" Milli second per process "));
    }

}
