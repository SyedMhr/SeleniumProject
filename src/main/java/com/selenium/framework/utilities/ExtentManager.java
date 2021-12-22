package com.selenium.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.selenium.framework.config.Settings;

import org.openqa.selenium.Platform;
import java.io.File;

public class ExtentManager {

    private static ExtentReports extent = new ExtentReports();
    private static Platform platform;
    private static String reportFileName = "SeleniumExtentReport.html";
    private static String macPath = System.getProperty("user.dir") + "/TestReport";
    private static String windowsPath = System.getProperty("user.dir") + "\\TestReport";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;
    private static KlovReporter klov = new KlovReporter();


    //Create an extent report instance
    public synchronized static ExtentReports createInstance() {
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        //attach Klov reporter only if its historical flag true
        if(Settings.HistoricalReport.equalsIgnoreCase("Y")) {
            //Initialize Klov reporter
            klov = initKlovReporter();
            extent.attachReporter(htmlReporter, klov);
        }else{
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    //Select the extent report file location based on platform
    private static String getReportFileLocation(Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }

    //Create the report path if it does not exist
    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    //Get current platform
    private static Platform getCurrentPlatform() {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }

    private static KlovReporter initKlovReporter() {
        //ToDo: These settings will be taken into consideration in settings, once we start using it in full fledged
        klov.initMongoDbConnection("localhost", 27017);
        klov.setProjectName("EA Test");
        klov.setReportName("1.0");
        //ToDo: These settings will be taken into consideration in settings, once we start using it in full fledged
        klov.setKlovUrl("http://localhost");
        return klov;
    }

}
