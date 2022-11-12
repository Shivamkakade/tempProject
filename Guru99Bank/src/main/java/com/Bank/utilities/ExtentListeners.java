package com.Bank.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentListeners implements ITestListener {

    public ExtentSparkReporter htmlReporter;
    public ExtentReports reports;
    public ExtentTest test;

    public void configureReport() {
        final String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        final String reportName = "MyStoreTestReport_" + timeStamp + ".html";


        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + reportName);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        reports.setSystemInfo("Machine:", "testpc1");
        reports.setSystemInfo("OS", "windows 11");
        reports.setSystemInfo("browser:", ConfigReader.getProperty("browser"));
        reports.setSystemInfo("user name:", "Shivam");


        //look and feel
        htmlReporter.config().setDocumentTitle("Extent Listener Report");
        htmlReporter.config().setReportName("MyReport");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onStart(ITestContext Result) {

        configureReport();
    }

    public void onFinish(ITestContext Result) {
        reports.flush();
    }

    public void onTestStart(ITestResult Result) {

    }

    public void onTestSuccess(ITestResult Result) {
        test = reports.createTest(Result.getName());
        test.log(Status.PASS, MarkupHelper.createLabel(Result.getName() + " : test case PASSED", ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult Result) {
        test = reports.createTest(Result.getName());
        test.log(Status.FAIL, MarkupHelper.createLabel(Result.getName() + " : test case FAILED", ExtentColor.RED));
        String screenShotPath = System.getProperty("user.dir") + "/ScreenShots/" + Result.getName() + ".png";
        File screenShotFile = new File(screenShotPath);
        if (screenShotFile.exists()) {
            test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));
        }
    }

    public void onTestSkipped(ITestResult Result) {
        test = reports.createTest(Result.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel(Result.getName() + " : test case SKIPPED", ExtentColor.YELLOW));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

    }


}
