package project;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import project.utilities.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    public WebDriver driver;
    static ExtentSparkReporter htmlReport;
    public ExtentReports reports;
    public ExtentTest test;


  /*  @BeforeMethod
    public void beforeMethods(Method method){
        method = method.get
        test.info( "Started");
    }*/
/*
    @AfterMethod
    public void afterMethods(){
        test.info("Finished");
    }*/

    @BeforeClass
    public void initiateBrowserAndOpenApplication() {
        final String browser = ConfigReader.getProperty("browser");

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
        driver.get(ConfigReader.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        test = reports.createTest(this.getClass().getSimpleName());


    }

    @BeforeTest
    public void configureReport() {
        Date date = new Date();
        htmlReport = new ExtentSparkReporter("reports/testReport_" + date.getTime() + ".html");
        reports = new ExtentReports();
        reports.attachReporter(htmlReport);

        // add environmental details
        reports.setSystemInfo("Machine Name", "PC1");
        reports.setSystemInfo("Environment", "systest");
        reports.setSystemInfo("Browser", "chrome");

        //configure looks
        htmlReport.config().setTheme(Theme.DARK);
        htmlReport.config().setDocumentTitle("MyTestReport");



    }

    @AfterMethod
    public void getTestResults(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Failed", ExtentColor.RED));
            test.fail(result.getThrowable());
            takeScreenShot(result.getTestName());

            String screenshotPath = getScreenshot(driver, result.getName());
            test.fail("Captured screenshot : " + test.addScreenCaptureFromPath(screenshotPath));

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Skipped", ExtentColor.YELLOW));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Passed", ExtentColor.GREEN));
        }
    }

    @AfterClass
    public void tearDown() {
        reports.flush();
        driver.quit();
    }

    public void takeScreenShot(final String testName) {
        Date date = new Date();
        String timeStamp = date.toString().replace(":", "_").replace(" ", "_");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File("/screenshots/" + testName + "_" + timeStamp + ".jpg");
        try {
            FileUtils.copyFile(srcFile, destFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }


}
