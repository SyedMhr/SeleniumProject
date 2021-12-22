package steps;

import com.selenium.framework.base.DriverContext;
import com.selenium.framework.base.FrameworkInitialize;
import com.selenium.framework.base.LocalDriverContext;
import com.selenium.framework.config.ConfigReader;
import com.selenium.framework.config.Settings;
import com.selenium.framework.utilities.*;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


import java.io.IOException;
import java.sql.Driver;

public class TestInitialize extends FrameworkInitialize {


    @Before
    public void Initialize(Scenario scenario) throws IOException {
            //Enter the scenario name
            ExtentReport.startScenario(scenario.getName());

            InitializeBrowser(Settings.BrowserType);
            Settings.Logs.Write("Browser Initialized");
            DriverContext.GoToUrl(Settings.AUT);
            Settings.Logs.Write("Navigated to URL " + Settings.AUT);

            try {
                ExcelUtil util = new ExcelUtil(Settings.ExcelSheetPath);
            } catch (Exception e) {
            }
        

    }

    @After
    public void TearDown(){
        DriverContext.QuitDriver();

    }
}
