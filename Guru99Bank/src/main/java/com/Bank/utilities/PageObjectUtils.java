package com.Bank.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageObjectUtils {
    WebDriver driver;
    WaitUtils waitUtils = new WaitUtils();

    public PageObjectUtils(WebDriver driver) {
        this.driver = driver;
    }

    //isDisplayed
    public boolean verifyElementIsDisplayed(final WebElement element) {
        boolean isVisible = element.isDisplayed();
        if (isVisible) {
            System.out.println(element + " is visible");
        } else {
            System.out.println(element + " is not visible");
        }
        return isVisible;
    }

    public boolean verifyElementIsEnabled(final WebElement element) {
        boolean isEnable = element.isEnabled();
        if (isEnable) {
            System.out.println(element + " is enabled");
        } else {
            System.out.println(element + " is disabled");
        }
        return isEnable;
    }

    //sendKeys
    public void enterValueInField(WebElement element, String value) {
        waitUtils.waitForElementUntilVisible(driver, element);
        verifyElementIsEnabled(element);
        element.click();
        element.clear();
        //String property = value.replaceAll("(?!^([-]*)([\\d]+))\\.0*$", "");
        element.sendKeys(value);

    }

    //click action
    public void clickOnElementUsingActionsClass(WebElement element) {
        waitUtils.waitForElementUntilClickable(driver, element);
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();
    }

    public String selectDropdownValue(WebElement element, String value) {
        Select dropdown = new Select(element);
        String previousDropdownValue = dropdown.getFirstSelectedOption().getText();
        if (!(previousDropdownValue.equals(value))) {
            dropdown.selectByVisibleText(value);
            return value;
        } else {
            return null;
        }
    }


}
