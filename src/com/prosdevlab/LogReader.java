package com.prosdevlab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * https://docs.oracle.com/javase/tutorial/essential/io/file.html
 */
public class LogReader {

    public static void processLogFile(String uncompressLogFile) throws IOException {
        FileInputStream inputStream = null;
        Scanner scanner = null;
        int lineNumber = 0;

        try {
            inputStream = new FileInputStream(uncompressLogFile);
            scanner = new Scanner(inputStream, "UTF-8");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber ++;
                System.out.println(line);
                System.out.println("Line number: " + lineNumber);
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
    }
}
