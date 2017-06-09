package com.prosdevlab;


import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipFile {

    /**
     * Will gzip a given file.
     * @param sourceFile
     *
     */
    public void gzipFile(File sourceFile) throws IOException {
        //Create gzipOutputFile name
        String gzipOutputFile = sourceFile.getName().substring(0, sourceFile.getName().length() - 4)+".gz";

        //Allot buffer size
        byte [] buffer = new byte[1024];

        //Open GZIPOutputStream to process
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(gzipOutputFile));

        FileInputStream inputStream = new FileInputStream(sourceFile);
        try {
            int length;

            while((length = inputStream.read(buffer)) > 0) {
                System.out.println("\uD83D\uDD28 ... GZIP compress in process.");
                gzipOutputStream.write(buffer, 0, length);
            }
        } finally {
            //Close all the streams
            inputStream.close();
            gzipOutputStream.finish();
            gzipOutputStream.close();
            System.out.println("\uD83D\uDCC1 ... Done with gzip process.");
        }
    }

    /**
     * Will uncompress gzip file given source.
     * @param gzipSourceFile
     * @return file
     */
    public String decompressGzipFile(String gzipSourceFile) throws IOException {

        //Open compressed (input) file
        FileInputStream inputStream = new FileInputStream(gzipSourceFile);
        FileOutputStream outputStream = null;
        byte[] buffer = new byte[2048];
        GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);

        //Create output file (take away the .gz extension)
        String outputFileName = gzipSourceFile.substring(0, gzipSourceFile.length()-3) + ".txt";
        outputStream = new FileOutputStream(outputFileName);

        try {

            int length;

            while ((length = gzipInputStream.read(buffer)) > 0) {
                System.out.println("\uD83D\uDD28 ... GZIP decompress in process.");
                outputStream.write(buffer, 0, length);
            }
        } finally {

            //Close streams
            gzipInputStream.close();
            if(outputStream != null) {
                outputStream.close();
            }
            inputStream.close();

            System.out.println("\uD83D\uDCC1 ... Done with gzip process.");
        }
        return outputFileName;
    }
}
