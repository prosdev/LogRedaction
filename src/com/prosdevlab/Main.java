package com.prosdevlab;

public class Main {

    private static final String GZIP_OUTPUT_FILE = "/Users/flamingo/dev/LogRedaction/sample_log.gz";
    private static final String SOURCE_FILE = "/Users/flamingo/dev/LogRedaction/sample_log.txt";
    private static final String GZIP_SOURCE_FILE = "/Users/flamingo/dev/LogRedaction/sample_log.gz";
    private static final String TXT_OUTPUT_FILE = "/Users/flamingo/dev/LogRedaction/sample_log_out.txt";

    public static void main(String[] args) {
        GZipFile gZip = new GZipFile();
        long startTime = System.currentTimeMillis();
        gZip.gzipFile(GZIP_OUTPUT_FILE, SOURCE_FILE);
        long endTime = System.currentTimeMillis();
        System.out.println( "⌛... GZIP compress time: " + (endTime - startTime) + "ms");

        gZip.decompressGzipFile(GZIP_SOURCE_FILE, TXT_OUTPUT_FILE);
    }
}
