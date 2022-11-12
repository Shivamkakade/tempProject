package com.Bank.pageObjects;

import com.Bank.utilities.PageObjectUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    PageObjectUtils pageObjectUtils;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageObjectUtils = new PageObjectUtils(driver);
    }

    @FindBy(xpath = "//input[@name='uid']")
    private WebElement userIDField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "btnLogin")
    private WebElement loginBtn;

    @FindBy(xpath = "//table/tbody/tr[3]/td")
    private WebElement managerID;

    public void performLogin(final String userID, String password) {
        pageObjectUtils.enterValueInField(userIDField, userID);
        pageObjectUtils.enterValueInField(passwordField, password);
        pageObjectUtils.clickOnElementUsingActionsClass(loginBtn);
    }

    public String getTitle() {
        return driver.getTitle();

    }

    public String getPageSource() {
        return driver.getPageSource();

    }
    public String getManagerID() {
        return managerID.getText().split(":")[1];
    }

}
