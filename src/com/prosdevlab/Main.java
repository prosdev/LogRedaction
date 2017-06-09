package com.prosdevlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        GZipFile gZip = new GZipFile();
        String filePath = "";

        if( args.length == 0) {
            System.out.println("Please enter an absolute path to a .gz file");
            filePath = "sample_log.gz";
        } else {
            System.out.println(args[0]);
            filePath = args[0];
        }

        File gzFile = new File(filePath);
        //Uncompress log file
        long startTimeDecompress = System.currentTimeMillis();
        File uncompressLogFile = gZip.decompressGzipFile(gzFile);
        long endTimeDecompress = System.currentTimeMillis();
        System.out.println( "⌛... GZIP uncompress time: " + (endTimeDecompress - startTimeDecompress) + "ms");

        System.out.println("------------- Beginning to process log file -----------");
        File redactedLog = LogReader.processLogFile(uncompressLogFile);
        System.out.println("------------- Ending of process log file -----------");

        //Compress processed log file
        long startTime = System.currentTimeMillis();
        gZip.gzipFile(redactedLog);
        long endTime = System.currentTimeMillis();
        System.out.println( "⌛... GZIP compress time: " + (endTime - startTime) + "ms");
    }
}
