package steps;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.GherkinKeyword;
import com.selenium.framework.base.CurrentPageContext;
import com.selenium.framework.base.DriverContext;
import com.selenium.framework.config.Settings;
import com.selenium.framework.controls.elements.SeleniumHelper;
import com.selenium.framework.utilities.CucumberUtil;
import com.selenium.framework.utilities.ExtentReport;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.CreateEmployeePage;
import pages.EmployeeListPage;
import pages.HomePage;

public class EmployeeSteps  {
	private static Logger logger = Logger.getLogger(EmployeeSteps.class.getName());
    @And("^I click employeeList link$")
    public void iClickEmployeeListLink() throws Throwable {
    	Settings.Logs.Write("Starting scenario click employeeList");
    	
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(HomePage.class).ClickEmployeeList());
        DriverContext.WaitForPageToLoad();
        ExtentReport.getScenario().createNode(new GherkinKeyword("And"), "I click employeeList link");
        Settings.Logs.Write("Completed scenario click employeeList");

    }


    @Then("^I click createnew button$")
    public void iClickCreatenewButton() throws Throwable {
    	Settings.Logs.Write("Starting scenario createnew button");
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(EmployeeListPage.class).ClickCreateNew());
        DriverContext.WaitForPageToLoad();
        ExtentReport.getScenario().createNode(new GherkinKeyword("Then"), "I click createnew button");
        Settings.Logs.Write("scenario createnew button completed");

    }

    @And("^I enter following details$")
    public void iEnterFollowingDetails(DataTable table) throws Throwable {
    	
    	Settings.Logs.Write("Starting scenario  enter following details");
        CucumberUtil.ConvertDataTableToDict(table);

        CurrentPageContext.getCurrentPage().As(CreateEmployeePage.class).CreateEmployee(CucumberUtil.GetCellValueWithRowIndex("Name",2), CucumberUtil.GetCellValueWithRowIndex("Salary",2),
                CucumberUtil.GetCellValueWithRowIndex("DurationWorked",2), CucumberUtil.GetCellValueWithRowIndex("Grade",2), CucumberUtil.GetCellValueWithRowIndex("Email",2));

        ExtentReport.getScenario().createNode(new GherkinKeyword("And"), "I enter following details");
        Settings.Logs.Write(" scenario  enter following details ends");

    }

    @And("^I click create button$")
    public void iClickCreateButton() throws Throwable {
    	Settings.Logs.Write(" scenario click create button");
        CurrentPageContext.getCurrentPage().As(CreateEmployeePage.class).ClickCreateButton();
        ExtentReport.getScenario().createNode(new GherkinKeyword("And"), "I click create button");
        Settings.Logs.Write(" scenario  click create button ends");

    }

}
