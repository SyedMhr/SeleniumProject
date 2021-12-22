package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import pages.HomePage;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import com.selenium.framework.base.LocalDriverContext;
import com.selenium.framework.config.Settings;
import com.selenium.framework.utilities.ExtentReport;


@CucumberOptions(features = {"src\\test\\java\\features"}, glue = {"steps"},tags= {"@regression"}, format = {"json:target/cucumber-json-report.json", "html:target/cucumber-report-html"})
public class TestRunner {
	public static WebDriver webDriver = LocalDriverContext.getRemoteWebDriver();
	private static Logger logger = Logger.getLogger(TestRunner.class.getName());
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }


    @Test(dataProvider = "features")
    public void EmployeeTest(CucumberFeatureWrapper cucumberFeatureWrapper) throws ClassNotFoundException {
    	
        String featureName = cucumberFeatureWrapper.getCucumberFeature().getGherkinFeature().getName();
        ExtentReport.startFeature(featureName);
        testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());
    }

    @DataProvider
    public Object[] features(ITestContext context) {
    	return testNGCucumberRunner.provideFeatures();
	}
		/*
		 * Object[] featureName = testNGCucumberRunner.provideFeatures();
		 * 
		 * //Object[] getFeature = null; Object[] getFeature = new Object[0];
		 * 
		 * for(int item : featureName){
		 * if(item[0].toString().equalsIgnoreCase(context.getName())){
		 * 
		 * System.out.println("features"+getFeature.toString()); getFeature = item; } }
		 */
	/* return getFeature; */
   // }

	
	  @AfterClass(alwaysRun = true) public void afterClass() {
	  testNGCucumberRunner.finish();
	  }
	 
    
    
	  @After(order = 1)
	public void endTest(Scenario scenario) {
    	System.out.println("Vinaya");
		if (scenario.isFailed()) {

			try {
				Settings.Logs.Write(scenario.getName() + " is Failed");
				System.out.println("Screen shot took");
				
						 
						  

				/*
				 * final byte[] screenshot = ((TakesScreenshot)
				 * webDriver).getScreenshotAs(OutputType.BYTES); scenario.embed(screenshot,
				 * "image/png"); // ... and embed it in
				 */		
				scenario.embed(((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
			} catch (WebDriverException e) {
				System.out.println("Not taking screen shot");
				e.printStackTrace();
			}

		} else {
			try {
				System.out.println("Not taking screen shot");
				Settings.Logs.Write(scenario.getName() + " is pass");
				scenario.embed(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
				 //scenario.embed(Files.readAllBytes(file.toPath()), "image/png")

			} catch (Exception e) {
				System.out.println("Not taking screen shot");
				e.printStackTrace();
				
			}
		}

		webDriver.quit();
	}

}
