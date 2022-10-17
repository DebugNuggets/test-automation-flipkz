package com.debugnuggets.flipkz.main;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.debugnuggets.flipkz.pages.AddressFormPage;
import com.debugnuggets.flipkz.pages.LoginPage;
import com.debugnuggets.flipkz.pages.MainPage;
import com.debugnuggets.flipkz.pages.ProductPage;
import com.debugnuggets.flipkz.util.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.debugnuggets.flipkz.constants.NameConstants.*;
import static com.debugnuggets.flipkz.util.BaseTest.extent;

public class LogOutTest {

    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    public DriverSettings driverSettings = new DriverSettings();

    private WebDriver webDriver;
    private Workbook workbook;
    private static Sheet sheet;
    private FileInputStream fileInputStream;
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static AddressFormPage addressFormPage;
    private static ProductPage productPage;
    ExtentHtmlReporter htmlReporter;

    private static DBUtil dbUtil = DBUtil.getInstance();

    ExtentTest test;

    @BeforeTest
    public void startReport() throws IOException {

        fileInputStream = new FileInputStream(System.getProperty("user.dir") + properties.getProperty(EXCEL_FILE_PATH));
        workbook = WorkbookFactory.create(fileInputStream);
        sheet = workbook.getSheetAt(0);

        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        webDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        webDriver.manage().window().maximize();

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + properties.getProperty(HTML_REPORT_FILE_PATH));
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        mainPage = MainPage.getInstance(webDriver);
        loginPage = LoginPage.getInstance(webDriver);
        dbUtil.createConnection();
    }

    @Test
    public void logOutTest() {
        mainPage.toLogIn();
        loginPage.logIn();
        mainPage.logOut();
        test = extent.createTest("Test Case 1", "The test case 'log out' has passed");
    }
}
