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
        SSNValidator ssnValidator = new SSNValidator();

        FileInputStream inputStream = null;
        Scanner scanner = null;
        int lineNumber = 0;
        int numberOfLineRedacted = 0;
        String redactedLog =  "redacted_"+ uncompressLogFile;
        Path path = Paths.get(redactedLog);
        File file = null;
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());

        //Make sure that we have new unique logs with time stamp, to avoid override/appending to old log
        if(Files.exists(path)) {
            System.out.println("⚠... File already exists. Creating new file...");

            file = new File(redactedLog.substring(0, redactedLog.length()-4)+ "_v" + timestamp + ".txt");
        } else {
            file = new File(redactedLog);
        }

        try {
            //Begin to process uncompressed file
            inputStream = new FileInputStream(uncompressLogFile);
            scanner = new Scanner(inputStream, "UTF-8");

            //While there are lines remaining in the uncompressed file,
            //write to a new file (this new file should not contain log info with SSN or CC#)
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber ++;
                System.out.println(line);
                try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file, true), "UTF-8"))) {

                    //If line do not contain SSN or CC#, will append to new redacted log file.
                    //Otherwise, just increased the tally of redacted lines
                    if(creditCardValidator.validate(line) || ssnValidator.validate(line)) {
                        numberOfLineRedacted++;
                    } else {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                        System.out.println("Added new line to file!");
                    }
                }
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
        System.out.println("------ Total lines processed: " +lineNumber);
        System.out.println("------ File processed: " + uncompressLogFile);
        System.out.println("------ Number of lines redacted from log : " + numberOfLineRedacted);
        return file;
    }

}
