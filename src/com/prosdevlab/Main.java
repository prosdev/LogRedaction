package com.prosdevlab;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

//    private static final String GZIP_OUTPUT_FILE = "/Users/flamingo/dev/LogRedaction/sample_log.gz";
//    private static final String SOURCE_FILE = "/Users/flamingo/dev/LogRedaction/sample_log.txt";
    private static final String GZIP_SOURCE_FILE = "sample_log.gz";

    public static void main(String[] args) throws IOException {
        GZipFile gZip = new GZipFile();
//        long startTime = System.currentTimeMillis();
//        gZip.gzipFile(GZIP_OUTPUT_FILE, SOURCE_FILE);
//        long endTime = System.currentTimeMillis();
//        System.out.println( "⌛... GZIP compress time: " + (endTime - startTime) + "ms");

        long startTimeDecompress = System.currentTimeMillis();
        String uncompressLogFile = gZip.decompressGzipFile(GZIP_SOURCE_FILE);
        long endTimeDecompress = System.currentTimeMillis();
        System.out.println( "⌛... GZIP uncompress time: " + (endTimeDecompress - startTimeDecompress) + "ms");

        LogReader.processLogFile(uncompressLogFile);
    }
}
