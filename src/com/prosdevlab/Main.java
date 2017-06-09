package com.prosdevlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

//    private static final String GZIP_OUTPUT_FILE = "/Users/flamingo/dev/LogRedaction/sample_log.gz";
//    private static final String SOURCE_FILE = "/Users/flamingo/dev/LogRedaction/sample_log.txt";
    private static final String GZIP_SOURCE_FILE = "sample_log.gz";

    public static void main(String[] args) throws IOException {
        GZipFile gZip = new GZipFile();

        //Uncompress log file
        long startTimeDecompress = System.currentTimeMillis();
        String uncompressLogFile = gZip.decompressGzipFile(GZIP_SOURCE_FILE);
        long endTimeDecompress = System.currentTimeMillis();
        System.out.println( "⌛... GZIP uncompress time: " + (endTimeDecompress - startTimeDecompress) + "ms");

        File redactedLog = LogReader.processLogFile(uncompressLogFile);

        //Compress processed log file
        long startTime = System.currentTimeMillis();
        gZip.gzipFile(redactedLog);
        long endTime = System.currentTimeMillis();
        System.out.println( "⌛... GZIP compress time: " + (endTime - startTime) + "ms");
    }
}
