package com.prosdevlab;


import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipFile {

    /**
     * gzipFile
     * @param
     * @param gzipOutputFile
     * @param sourceFile
     *
     */
    public void gzipFile(String gzipOutputFile, String sourceFile){
        byte [] buffer = new byte[1024];

        /**
         * Implement a stream filter to write compressed data in GZIP format.
         * Open a gzipOutputStream while there is still data from the source file,
         * and then create a new output stream with the specified buffer size.
         * See: https://docs.oracle.com/javase/8/docs/api/java/util/zip/GZIPOutputStream.html
         */
        try {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(gzipOutputFile));

            FileInputStream inputStream = new FileInputStream(sourceFile);

            int length;

            while((length = inputStream.read(buffer)) > 0) {
                System.out.println("\uD83D\uDD28 ... GZIP in process.");
                gzipOutputStream.write(buffer, 0, length);
            }

            inputStream.close();
            gzipOutputStream.finish();
            gzipOutputStream.close();

            System.out.println("\uD83D\uDCC1 ... Done with gzip process.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decompressGzipFile(String gzipSourceFile, String txtOutputFile) {
        byte[] buffer = new byte[1024];
        try {
            GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(gzipSourceFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
