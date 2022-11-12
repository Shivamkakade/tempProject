package testCases;

import com.Bank.pageObjects.LoginPage;
import com.Bank.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTestClass {
    String url = ConfigReader.getProperty("baseUrl");
    String browser = ConfigReader.getProperty("browser");

    String userID = ConfigReader.getProperty("userID");
    String password = ConfigReader.getProperty("password");
    public WebDriver driver;

    LoginPage loginPage;


    @BeforeClass
    public void setup() {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        loginPage.performLogin(userID,password);
        Assert.assertEquals(driver.getTitle(),"Guru99 Bank Manager HomePage");


    }

    @AfterClass
    public void tearDown() {
        driver.close();
        if (driver!=null)
        driver.quit();
    }


    public void captureScreenShot(WebDriver driver, String testName) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + testName + ".png");
        FileUtils.copyFile(src, dest);
    }


}
