package com.selenium.framework.base;

import org.openqa.selenium.WebDriver;


public class Browser extends Base{


    private WebDriver _driver;

    public Browser(WebDriver driver) {
        _driver = driver;
    }

    public BrowserType Type;



    public void Maximize()
    {
        _driver.manage().window().maximize();
    }
    public void DeleteCookies() {
		_driver.manage().deleteAllCookies();
	}

	public String GetTitle() {
		return _driver.getTitle();
	}

	public String GetCurrentUrl() {
		return _driver.getCurrentUrl();
	}

	public String GetPageSource() {
		return _driver.getPageSource();
	}

	
	public void Quit()
	{
		_driver.quit();
	}
	
	public void Close()
	{
		_driver.close(); }

}



