package project.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

public class ReportManager {
    static ExtentSparkReporter htmlReport;
    static ExtentReports reports;
    ExtentTest test;

    @BeforeTest
    public void configureReport() {
        htmlReport = new ExtentSparkReporter("/report/testReport.html");
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
    public void getTestResults(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Failed", ExtentColor.RED));
            //takeScreenshotLogic
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Skipped", ExtentColor.YELLOW));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Passed", ExtentColor.GREEN));
        }
    }

}
