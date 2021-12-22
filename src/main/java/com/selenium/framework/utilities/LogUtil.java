package com.selenium.framework.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.selenium.framework.config.Settings;


public class LogUtil {

    //File format for the log name
    ZonedDateTime date = ZonedDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
    String fileNameFormat = date.format(formatter);

    private BufferedWriter bufferedWriter = null;


    //Create Log file
    public void CreateLogFile() throws IOException {
        try {
        	System.out.println("Log file created");
            File dir = new File("/SeleniumAutomation/resources/log");
            if (!dir.exists())
                dir.mkdir();
            //Create file
            File logFile = new File(dir + "/" + fileNameFormat + ".log");

            FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);

        } catch (Exception e) {

        }
    }


    //Write message within the log
    public void Write(String message) {
        try {
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH_MM_SS");
            String dateFormat = date.format(formatter);
            bufferedWriter.write("["+dateFormat +"]: "+ message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {

        }
    }

}
