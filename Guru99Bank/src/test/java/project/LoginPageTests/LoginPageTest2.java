package project.LoginPageTests;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project.BaseTest;
import project.pages.LoginPage;
import project.utilities.JSONReader;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class LoginPageTest2 extends BaseTest {
    LoginPage loginPage;

    @Test
    public void testLoginFunctionalityWithValidCredentials() {
        Assert.assertTrue(false);
    }

    @Test
    public void testLoginFunctionalityWithValidCredentials1() {
        Assert.assertTrue(true);
    }

}
