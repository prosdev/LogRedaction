package com.prosdevlab;

import java.io.*;
import java.util.Scanner;

/**
 * https://docs.oracle.com/javase/tutorial/essential/io/file.html
 */
public class LogReader {

    public static String processLogFile(String uncompressLogFile) throws IOException {
        FileInputStream inputStream = null;
        Scanner scanner = null;
        int lineNumber = 0;
        String redactedLog = "edited_" + uncompressLogFile;
        try {
            inputStream = new FileInputStream(uncompressLogFile);
            scanner = new Scanner(inputStream, "UTF-8");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber ++;
                System.out.println(line);
                try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(redactedLog, true), "UTF-8"))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                System.out.println("Done writing new file!");
            }

            if(scanner.ioException() != null) {
                throw scanner.ioException();
            }

        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
            if(scanner != null) {
                scanner.close();
            }
        }
        return redactedLog;
    }

}
