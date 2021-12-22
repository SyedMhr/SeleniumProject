package com.selenium.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Scenario;


import java.util.HashMap;
import java.util.Map;

public class ExtentReport {

    //Extent Report Declarations
    //For all features
    private static ExtentReports extentReports = ExtentManager.createInstance();
    //For scenario
    private static ExtentTest extentTest = null;
    private static Map extentFeatureMap = new HashMap();
    private static Map extentScenarioMap = new HashMap();
    private static Map screenShotPathMap = new HashMap();

    public static synchronized void flushReport() {
        extentReports.flush();
    }

    public static synchronized String getScreenshotPath() {
        return (String) screenShotPathMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void putScreenshotPath(String path) {
        screenShotPathMap.put((int) (long) (Thread.currentThread().getId()), path);
    }


    public static synchronized ExtentTest getFeature() {
        return (ExtentTest) extentFeatureMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest getScenario() {
        return (ExtentTest) extentScenarioMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startFeature(String featureName) throws ClassNotFoundException {
        ExtentTest test = extentReports.createTest(new GherkinKeyword("Feature"), featureName);
        extentFeatureMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

    public static synchronized ExtentTest startScenario(String scenarioName) {
        extentTest = getFeature();
        extentTest = extentTest.createNode(Scenario.class, scenarioName);
        extentScenarioMap.put((int) (long) (Thread.currentThread().getId()), extentTest);
        return extentTest;
    }


}
