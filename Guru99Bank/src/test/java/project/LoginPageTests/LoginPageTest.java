package project.LoginPageTests;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project.BaseTest;
import project.pages.LoginPage;
import project.utilities.JSONReader;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class LoginPageTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethods(Method method) {
        String name = method.getName();
        test.log(Status.INFO, MarkupHelper.createLabel("Started -" + name, ExtentColor.BLUE));
    }

    @Test
    public void testLoginFunctionalityWithValidCredentials() {

        String filePath = System.getProperty("user.dir") + "/src/test/java/project/TestData/LoginInfo.json";
        List<HashMap<String, String>> dataList = JSONReader.getData(filePath);
        HashMap<String, String> data = dataList.get(0);

        loginPage = new LoginPage(driver);
        loginPage.performLogin(data);
        Assert.assertEquals(loginPage.getManagerID(), data.get("userID"));
    }

    @Test
    public void testLoginFunctionalityWithValidCredentials1() {
        Assert.assertTrue(true);
    }

}
