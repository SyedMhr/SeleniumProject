package runner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.selenium.framework.utilities.ExtentReport;

public class ReportListener implements ITestListener {
    @Override
    public synchronized void onTestStart(ITestResult iTestResult) {
        System.out.println("On Test Start");
    }

    @Override
    public synchronized void onTestSuccess(ITestResult iTestResult) {
        System.out.println("On Test Sucess");
    }

    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        System.out.println("On Test Failure");
        ExtentReport.getScenario().createNode("Failed "+ iTestResult.getInstanceName()).fail(iTestResult.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult iTestResult) {
        System.out.println("On Test Skipped");
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println(" Test Percentage");
    }

    @Override
    public synchronized void onStart(ITestContext iTestContext) {
        System.out.println("On Test Start");
    }

    @Override
    public synchronized void onFinish(ITestContext iTestContext) {
        System.out.println("On Test Finish");
        ExtentReport.flushReport();
    }
}
