package pages;

import com.selenium.framework.base.BasePage;
import com.selenium.framework.base.DriverContext;
import com.selenium.framework.controls.elements.HyperLink;
import com.selenium.framework.controls.elements.SeleniumHelper;

import static org.awaitility.Awaitility.*;
import static org.awaitility.Duration.*;
import static java.util.concurrent.TimeUnit.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class HomePage extends BasePage {
	private static Logger logger = Logger.getLogger(HomePage.class.getName());
	public SeleniumHelper helper;
	@FindBy(how = How.LINK_TEXT, using = "Login")
	public HyperLink lnkLogin;

	@FindBy(how = How.LINK_TEXT, using = "Employee List")
	public HyperLink lnkEmployeeList;

	@FindBy(how = How.XPATH, using = "//a[@title='Manage']")
	public WebElement lnkUserName;

	public LoginPage ClickLogin() {
		lnkLogin.ClickLink();
		// lnkEmployeeList.ClickLink();
		return GetInstance(LoginPage.class);
	}

	public boolean IsLogin() {
		return lnkLogin.isDisplayed();
	}

	public String GetLoggedInUser() {
		return lnkUserName.getText();
	}

	public EmployeeListPage ClickEmployeeList() throws InterruptedException {
		logger.info("---Getting the employee list--------------");
		helper.WaitForElementVisible(lnkEmployeeList);
		lnkEmployeeList.click();
		logger.info("---Getting the employee clicked--------------");

		return GetInstance(EmployeeListPage.class);
	}
}
