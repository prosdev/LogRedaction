package com.prosdevlab;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * https://docs.oracle.com/javase/tutorial/essential/io/file.html
 */
public class LogReader {

    public static File processLogFile(String uncompressLogFile) throws IOException {
        CreditCardValidator creditCardValidator = new CreditCardValidator();

        FileInputStream inputStream = null;
        Scanner scanner = null;
        int lineNumber = 0;
        String redactedLog =  "redacted_"+ uncompressLogFile;
        Path path = Paths.get(redactedLog);
        File file = null;
        Date date = new Date();

        //Make sure that we have new unique logs with time stamp, to avoid override/appending to old log
        if(Files.exists(path)) {
            System.out.println("⚠... File already exists. Creating new file...");

            file = new File(redactedLog.substring(0, redactedLog.length()-4)+ "_v" + date.getTime() + ".txt");
        } else {
            file = new File(redactedLog);
        }

        try {
            inputStream = new FileInputStream(uncompressLogFile);
            scanner = new Scanner(inputStream, "UTF-8");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber ++;
                System.out.println(line);
                try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file, true), "UTF-8"))) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                System.out.println("Added new line to file!");
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
        return file;
    }

}
