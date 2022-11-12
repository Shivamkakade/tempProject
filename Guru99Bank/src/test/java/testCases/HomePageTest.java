package testCases;

import com.Bank.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTestClass {
    LoginPage loginPage;

    @Test
    public void verifyTitleAfterSuccessfulLogin(){
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getTitle(),"Guru99 Bank Manager HomePage");
    }

    @Test
    public void verifyWelcomeMessageOnHomePage(){
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.getPageSource().contains("Welcome To Manager's Page of Guru99 Bank"));
    }
}
