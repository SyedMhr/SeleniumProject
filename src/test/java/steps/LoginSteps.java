package steps;

import com.aventstack.extentreports.GherkinKeyword;
import com.selenium.framework.base.Base;
import com.selenium.framework.base.CurrentPageContext;
import com.selenium.framework.utilities.DatabaseUtil;
import com.selenium.framework.utilities.ExcelUtil;
import com.selenium.framework.utilities.ExtentReport;

import cucumber.api.DataTable;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jxl.read.biff.BiffException;

import org.junit.Assert;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class LoginSteps extends Base {

	/* code for xlsx read */

	ExcelUtil ex;

	@Given("Data ia available in Excel")
	public void hellotest() throws BiffException, IOException {
		/* Reading the xlsx file from resource directory */
		ExcelUtil ex = new ExcelUtil(Thread.currentThread().getContextClassLoader().getResource("data.xls").getFile());

		System.out.println(ex.RowCount());
	}

	@Then("data should be accessible")
	public void data() {
		ex.ReadCell("UserName", 1);

		System.out.println(ex.ReadCell("UserName", 1));
	}

	/* code for dbread */

	/*
	 * DatabaseUtil ex =new DatabaseUtil(); Connection con;
	 * 
	 * @Given("Data is available in DB") public void hellotest() {
	 * con=ex.Open("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 * 
	 * 
	 * }
	 * 
	 * @Then("DB data should be accessible") public void data() { String query
	 * ="Select emp_id from employee where emaployeename='/xxx\'"; ResultSet
	 * rs=ex.ExecuteQuery(query, con); System.out.println(rs); }
	 */

	@And("^I ensure application opened$")
	public void iEnsureApplicationOpened() throws ClassNotFoundException {
		CurrentPageContext.setCurrentPage(GetInstance(HomePage.class));
		Assert.assertTrue("The login page is not loaded",
				CurrentPageContext.getCurrentPage().As(HomePage.class).IsLogin());

		ExtentReport.getScenario().createNode(new GherkinKeyword("And"), "I ensure application opened");
	}

	@Then("^I click login link$")
	public void iClickLoginLink() throws ClassNotFoundException {
		// Navigation to Login Page
		CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(HomePage.class).ClickLogin());
		ExtentReport.getScenario().createNode(new GherkinKeyword("Then"), "I click login link");
	}

	@When("^I enter UserName and Password$")
	public void iEnterUserNameAndPassword(DataTable data) throws ClassNotFoundException {
		List<List<String>> table = data.raw();
		CurrentPageContext.getCurrentPage().As(LoginPage.class).Login(table.get(1).get(0).toString(),
				table.get(1).get(1).toString());
		ExtentReport.getScenario().createNode(new GherkinKeyword("When"), "I enter UserName and Passwor");
	}

	@Then("^I click login button$")
	public void iClickLoginButton() throws InterruptedException, ClassNotFoundException {
		// Home Page
		CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(LoginPage.class).ClickLogin());
		ExtentReport.getScenario().createNode(new GherkinKeyword("Then"), "I click login button");
	}

	@Then("^I should see the username with hello$")
	public void iShouldSeeTheUsernameWithHello() throws Throwable {
		Assert.assertEquals("The user is not admin", "Hello admin!",
				CurrentPageContext.getCurrentPage().As(HomePage.class).GetLoggedInUser());
		ExtentReport.getScenario().createNode(new GherkinKeyword("Then"), "I should see the username with hello");
	}

}
