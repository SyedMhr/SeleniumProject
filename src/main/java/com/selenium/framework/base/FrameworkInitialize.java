package com.selenium.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.selenium.framework.config.Settings;

import java.net.MalformedURLException;
import java.net.URL;

public class FrameworkInitialize extends Base {

	public static void InitializeBrowser(BrowserType browserType) throws MalformedURLException {

		RemoteWebDriver driver = null;
		String gridconfig = Settings.GridRunCondition;
		switch (browserType) {
		case Chrome: {

			if (gridconfig.equals("Y")) {

				DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
				// Zelenium Configuration
				capabilities.setCapability("recordVideo", true);
				capabilities.setCapability("build", "1.4.1");
				capabilities.setCapability("idleTimeout", 150);
				driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), capabilities);
			} else {
				//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver",Thread.currentThread().getContextClassLoader().getResource("chromedriver.exe").getFile());

				driver = new ChromeDriver();
				LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
			}
			break;
		}
		case Firefox: {
			if (gridconfig.equals("Y")) {
				// Open the browser

				DesiredCapabilities capabilities = new DesiredCapabilities().firefox();

				driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), capabilities);
			} else {
				//System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\.exe");
				System.setProperty("webdriver.gecko.driver",Thread.currentThread().getContextClassLoader().getResource("geckodriver.exe").getFile());
				driver = new FirefoxDriver();
				LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
			}
			break;
		}

		}
	}

}
