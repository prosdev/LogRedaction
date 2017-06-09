package com.prosdevlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        GZipFile gZip = new GZipFile();

        File gzFile = new File("sample_log.gz");
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
