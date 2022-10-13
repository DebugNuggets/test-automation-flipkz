package com.debugnuggets.flipkz.guest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.debugnuggets.flipkz.pages.MainPage;
import com.debugnuggets.flipkz.util.DriverSettings;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.debugnuggets.flipkz.constants.NameConstants.*;
import static com.debugnuggets.flipkz.constants.NameConstants.HTML_REPORT_FILE_PATH;
import static com.debugnuggets.flipkz.util.BaseTest.extent;

public class FilterSearchTests {

    private WebDriver webDriver;
    public DriverSettings driverSettings = new DriverSettings();
    private Properties properties = PropertiesUtil.getInstance().getProperties();
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private static MainPage mainPage;
    private String searchKeyExample = "Java";
    ExtentHtmlReporter htmlReporter;

    ExtentReports reports;

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
    }

    private void sleep(long sec) {
        long millis = sec * 1000;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addFilterToSearch() {
        mainPage.getSearchInputElement().sendKeys(searchKeyExample);
        mainPage.getSearchSubmitButtonElement().click();

        sleep(3);

        mainPage.getAllowedCheckBoxElement().click();

        sleep(3);

        WebElement filterFieldBook = webDriver.findElement(By.xpath(properties.getProperty(FILTER_FIELD_BOOK_XPATH)));
        filterFieldBook.click();

        WebElement filterYear = webDriver.findElement(By.xpath(properties.getProperty(FILTER_YEAR_XPATH)));
        filterYear.click();
    }
}
