package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.selenium.framework.base.BasePage;

public class EmployeeListPage extends BasePage {

    @FindBy(how = How.NAME, using = "searchTerm")
    public WebElement txtSearch;

    @FindBy(how = How.LINK_TEXT, using = "Create New")
    public WebElement lnkCreateNew;

    @FindBy(how = How.CLASS_NAME, using = "table")
    public WebElement tblEmployeeList;


    public CreateEmployeePage ClickCreateNew()
    {
        lnkCreateNew.click();
        return GetInstance(CreateEmployeePage.class);
    }

    public WebElement GetEmployeeList()
    {
        return tblEmployeeList;
    }

}
