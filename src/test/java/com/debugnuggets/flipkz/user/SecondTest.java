package com.debugnuggets.flipkz.user;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.debugnuggets.flipkz.pages.AddressFormPage;
import com.debugnuggets.flipkz.pages.LoginPage;
import com.debugnuggets.flipkz.pages.MainPage;
import com.debugnuggets.flipkz.pages.ProductPage;
import com.debugnuggets.flipkz.util.ActionsUtil;
import com.debugnuggets.flipkz.util.DriverSettings;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import com.debugnuggets.flipkz.util.WebDriverWaitUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.debugnuggets.flipkz.constants.NameConstants.*;
import static com.debugnuggets.flipkz.util.BaseTest.extent;

public class SecondTest {

    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static final ActionsUtil actionsUtil = ActionsUtil.getInstance();
    private static final WebDriverWaitUtil webDriverWaitUtil = WebDriverWaitUtil.getInstance();
    public DriverSettings driverSettings = new DriverSettings();

    private WebDriver webDriver;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

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
    }

    @AfterTest
    public void tearDown() throws IOException {
        fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + properties.getProperty(EXCEL_FILE_PATH));
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        fileInputStream.close();
        workbook.close();
        webDriver.close();
        extent.flush();
        webDriver.quit();
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            cell.setCellValue("FAILED");
            test.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            cell.setCellValue("PASSED");
            test.log(Status.PASS, result.getTestName());
        }
        else {
            test.log(Status.SKIP, result.getTestName());
        }
    }

    @Test
    public void logOutTest() {
        logIn(webDriver);
        MainPage mainPage = MainPage.getInstance(webDriver);
        mainPage.getLogOutButtonElement().click();
        test = extent.createTest("Test Case 1", "The test case 'log out' has passed");
    }

    @Test
    public void justLogin() {
        cell = sheet.getRow(1).getCell(1);
        logIn(webDriver);
        test = extent.createTest("Test Case 2", "The test case 'log in' has passed");
    }

    public static void logIn(WebDriver webDriver1) {
        MainPage mainPage = MainPage.getInstance(webDriver1);
        mainPage.getProfileHoverElement().click();
        LoginPage loginPage = LoginPage.getInstance(webDriver1);
        loginPage.getUsernameElement().sendKeys(properties.getProperty(RIGHT_PHONE_NUMBER));
        loginPage.getPasswordElement().sendKeys(properties.getProperty(RIGHT_PASSWORD));
        loginPage.getEnterButton().click();
    }

    @Test
    public void wrongLogIn() {
        cell = sheet.getRow(2).getCell(1);

        MainPage mainPage = MainPage.getInstance(webDriver);
        mainPage.getProfileHoverElement().click();
        LoginPage loginPage = LoginPage.getInstance(webDriver);
        Random r = new Random();
        StringBuilder wrongPhone = new StringBuilder(properties.getProperty(WRONG_PHONE_NUMBER));
        for (int i = 0; i<10; i++)
        {
            if(i==0 || i==3 || i==6 || i==8)
                wrongPhone.append(SPACE);
            wrongPhone.append(r.nextInt(9));

        }
        loginPage.getUsernameElement().sendKeys(wrongPhone.toString());
        StringBuilder wrongPassword = new StringBuilder(properties.getProperty(WRONG_PASSWORD));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            wrongPassword.append(c);
        }
        loginPage.getPasswordElement().sendKeys(wrongPassword);
        loginPage.getEnterButton().click();

        test = extent.createTest("Test Case 3", "The test case 'wrong log in' has passed");
    }


    @Test
    public void submitOrderWithRightCredentials() {
        cell = sheet.getRow(3).getCell(1);

        logIn(webDriver);
        addProductToCart(webDriver);
        submitRightAddress(webDriver);

        test = extent
                .createTest("Test Case 4", "The test case 'submit order with right credentials' has passed");
    }

    @Test
    public void submitOrderWithWrongCredentials() {
        cell = sheet.getRow(4).getCell(1);

        logIn(webDriver);
        addProductToCart(webDriver);
        submitWrongAddress(webDriver);
    }

    public void addProductToCart(WebDriver webDriver1) {
        MainPage mainPage = MainPage.getInstance(webDriver1);
        mainPage.getFirstProductElement().click();
        ProductPage productPage = ProductPage.getInstance(webDriver1);
        productPage.getAddToCartElement().click();
        webDriverWaitUtil.getWebDriverWait(webDriver).until(ExpectedConditions.elementToBeClickable(productPage.getSubmitButton())).click();
    }

    public void submitRightAddress(WebDriver webDriver1)
    {
        AddressFormPage addressFormPage = AddressFormPage.getInstance(webDriver1);
        addressFormPage.getFullNameElement().sendKeys(properties.getProperty(FULL_NAME));
        addressFormPage.getCityElement().clear();
        addressFormPage.getCityElement().sendKeys(properties.getProperty(CITY));
        addressFormPage.getAddressElement().sendKeys(properties.getProperty(ADDRESS));
        addressFormPage.getBuildingElement().sendKeys(properties.getProperty(BUILDING));
        addressFormPage.getFlatElement().sendKeys(properties.getProperty(FLAT));
        addressFormPage.getEntranceElement().sendKeys(properties.getProperty(ENTRANCE));
        addressFormPage.getFloorElement().sendKeys(properties.getProperty(FLOOR));
        addressFormPage.getPostalCodeElement().clear();
        addressFormPage.getPostalCodeElement().sendKeys(properties.getProperty(POSTAL_CODE));
        addressFormPage.getAddressButtonElement().click();
        addressFormPage.getSubmitButton().click();
    }

    public void submitWrongAddress(WebDriver webDriver1)
    {
        AddressFormPage addressFormPage = AddressFormPage.getInstance(webDriver1);
        addressFormPage.getFullNameElement().sendKeys(properties.getProperty(WRONG_FULL_NAME));
        addressFormPage.getCityElement().clear();
        addressFormPage.getCityElement().sendKeys(properties.getProperty(WRONG_CITY));
        addressFormPage.getAddressElement().sendKeys(properties.getProperty(WRONG_ADDRESS));
        addressFormPage.getBuildingElement().sendKeys(properties.getProperty(WRONG_BUILDING));
        addressFormPage.getFlatElement().sendKeys(properties.getProperty(WRONG_FLAT));
        addressFormPage.getEntranceElement().sendKeys(properties.getProperty(WRONG_ENTRANCE));
        addressFormPage.getFloorElement().sendKeys(properties.getProperty(WRONG_FLOOR));
        addressFormPage.getPostalCodeElement().clear();
        addressFormPage.getPostalCodeElement().sendKeys(properties.getProperty(WRONG_POSTAL_CODE));
        addressFormPage.getAddressButtonElement().click();
        addressFormPage.getSubmitButton().click();
    }

}
