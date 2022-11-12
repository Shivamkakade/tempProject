package com.Bank.pageObjects;

import com.Bank.utilities.PageObjectUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewCustomerPage {
    WebDriver driver;
    PageObjectUtils pageObjectUtils;

    public NewCustomerPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        pageObjectUtils = new PageObjectUtils(driver);
    }

    @FindBy(name = "name")
    WebElement customerNameField;

    @FindBy(xpath = "//input[@value='m']")
    WebElement radioButtonForMale;

    @FindBy(xpath = "//input[@value='f']")
    WebElement radioButtonForFemale;

    @FindBy(id = "dob")
    WebElement dateOfBirthField;

    @FindBy(name = "addr")
    WebElement addressField;

    @FindBy(name = "city")
    WebElement cityField;

    @FindBy(name = "state")
    WebElement stateField;

    @FindBy(name = "pinno")
    WebElement pinCodeField;

    @FindBy(name = "telephoneno")
    WebElement mobileNumberField;

    @FindBy(name = "emailid")
    WebElement emailIDField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@value='Submit']")
    WebElement submitButton;

    @FindBy(xpath = "//input[@value='Reset']")
    WebElement resetButton;

    public void fillValidDetails(){
        pageObjectUtils.enterValueInField(customerNameField,"John");
        pageObjectUtils.clickOnElementUsingActionsClass(radioButtonForMale);
        pageObjectUtils.enterValueInField(dateOfBirthField,"20/10/2000");
        pageObjectUtils.enterValueInField(addressField,"CT 1 Tower");
        pageObjectUtils.enterValueInField(cityField,"New York City");
        pageObjectUtils.enterValueInField(stateField,"New York");
        pageObjectUtils.enterValueInField(pinCodeField,"543210");
        pageObjectUtils.enterValueInField(mobileNumberField,"9876543210");
        pageObjectUtils.enterValueInField(emailIDField,"John@test.com");
        pageObjectUtils.enterValueInField(passwordField,"PlayStation");
    }

    public void clickOnSubmitButton(){
        pageObjectUtils.clickOnElementUsingActionsClass(submitButton);
    }

    public void clickOnResetButton(){
        pageObjectUtils.clickOnElementUsingActionsClass(resetButton);
    }

}
