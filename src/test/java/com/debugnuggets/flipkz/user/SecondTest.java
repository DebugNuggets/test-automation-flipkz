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
import com.debugnuggets.flipkz.util.*;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private static Sheet sheet;
    private static Cell cell;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static AddressFormPage addressFormPage;
    private static ProductPage productPage;
    ExtentHtmlReporter htmlReporter;

    ExtentReports reports;

    private static final String SELECT_USER_FULL_NAME = "select 'full_name' from user_credential where id = 1";
    private static final String SELECT_PHONE_NUMBER = "select 'phone_number' from user_credential where id = 1";
    private static final String SELECT_PASSWORD = "select 'password' from user_credential where id = 1";
    private static final String SELECT_EVERYTHING = "select * from user_credential where id = 1";
    private static List<Map<String, Object>> resultOfQuery;

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

        productPage = ProductPage.getInstance(webDriver);
        mainPage = MainPage.getInstance(webDriver);
        addressFormPage = AddressFormPage.getInstance(webDriver);
        loginPage = LoginPage.getInstance(webDriver);
        dbUtil.createConnection();
    }

    @AfterTest
    public void tearDown() throws IOException {
//        fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + properties.getProperty(EXCEL_FILE_PATH));
//        workbook.write(fileOutputStream);
//        fileOutputStream.close();
//        fileInputStream.close();
//        workbook.close();
//        webDriver.close();
//        extent.flush();
//        webDriver.quit();
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
        mainPage.getLogOutButtonElement().click();
        test = extent.createTest("Test Case 1", "The test case 'log out' has passed");
    }

    @Test
    public void justLogin() {
        cell = sheet.getRow(1).getCell(2);
        System.out.println(cell.toString());
        logIn(webDriver);
        test = extent.createTest("Test Case 2", "The test case 'log in' has passed");
    }

    public static void logIn(WebDriver webDriver1) {
        mainPage.getProfileHoverElement().click();
        resultOfQuery = dbUtil.getQueryResultMap(SELECT_EVERYTHING);
        String phoneNumber = (String) resultOfQuery.get(0).get("phone_number");
        String password = (String) resultOfQuery.get(0).get("password");
        loginPage.getUsernameElement().sendKeys(phoneNumber);
        loginPage.getPasswordElement().sendKeys(password);
        loginPage.getEnterButton().click();
    }

    @Test
    public void wrongLogIn() {
        cell = sheet.getRow(4).getCell(2);

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
        submitRightAddress();

        test = extent
                .createTest("Test Case 4", "The test case 'submit order with right credentials' has passed");
    }

    @Test
    public void submitOrderWithWrongCredentials() {
        cell = sheet.getRow(4).getCell(1);

        logIn(webDriver);
        addProductToCart(webDriver);
        submitWrongAddress();
    }

    public void addProductToCart(WebDriver webDriver1) {
        mainPage.getFirstProductElement().click();
        productPage.getAddToCartElement().click();
        webDriverWaitUtil.getWebDriverWait(webDriver).until(ExpectedConditions.elementToBeClickable(productPage.getSubmitButton())).click();
    }

    public void submitRightAddress()
    {
        addressFormPage.getFullNameElement().sendKeys(sheet.getRow(5).getCell(1).toString());
        addressFormPage.getFullNameElement().sendKeys(properties.getProperty(FULL_NAME));
        addressFormPage.getCityElement().clear();
        addressFormPage.getCityElement().sendKeys(sheet.getRow(6).getCell(1).toString());
        addressFormPage.getAddressElement().sendKeys(sheet.getRow(7).getCell(1).toString());
        addressFormPage.getBuildingElement().sendKeys(sheet.getRow(8).getCell(1).toString());
        addressFormPage.getFlatElement().sendKeys(sheet.getRow(9).getCell(1).toString());
        addressFormPage.getEntranceElement().sendKeys(sheet.getRow(10).getCell(1).toString());
        addressFormPage.getFloorElement().sendKeys(sheet.getRow(11).getCell(1).toString());
        addressFormPage.getPostalCodeElement().clear();
        addressFormPage.getPostalCodeElement().sendKeys(sheet.getRow(12).getCell(1).toString());
        addressFormPage.getAddressButtonElement().click();
        addressFormPage.getSubmitButton().click();
    }

    public void submitWrongAddress()
    {
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

