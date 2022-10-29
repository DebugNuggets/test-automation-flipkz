package com.debugnuggets.flipkz.util;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.debugnuggets.flipkz.constants.NameConstants;
import com.debugnuggets.flipkz.pages.LoginPage;
import com.debugnuggets.flipkz.pages.MainPage;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.aeonbits.owner.ConfigFactory;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class BaseTest {
    DriverSettings driverSettings = new DriverSettings();
    PropertiesReader propertiesReader = ConfigFactory.create(PropertiesReader.class);
    public static ExtentReports extent;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentTest extentTest;

    private static Properties properties = PropertiesUtil.getInstance().getProperties();
    private static ActionsUtil actionsUtil = ActionsUtil.getInstance();
    private static WebDriverWaitUtil webDriverWaitUtil = WebDriverWaitUtil.getInstance();
    private WebDriver webDriver;

    @BeforeTest
    public void setUp() {
        extent = new ExtentReports();
        NameConstants.HTML_REPORT_PATH = "target//html/FlipKzAutomationReport" + System.currentTimeMillis() + ".html";
        htmlReporter = new ExtentHtmlReporter(NameConstants.HTML_REPORT_PATH);
        extent.attachReporter(htmlReporter);
//        htmlReporter.loadXMLConfig("src/test/resources/extent-config.xml");
        extent.setSystemInfo("Hostname", "flip.kz");
        extent.setSystemInfo("Execution Environment", "Staging");
        extent.setSystemInfo("Browser", propertiesReader.browser());
    }

    @AfterTest
    public void endReport() throws IOException {
        extent.flush();
    }

    @Test
    public void justLogin()
    {
        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        logIn(webDriver);
    }

    public static void logIn(WebDriver webDriver1) {
        MainPage mainPage = MainPage.getInstance(webDriver1);
        mainPage.getProfileHoverElement().click();
        LoginPage loginPage = LoginPage.getInstance(webDriver1);
        loginPage.getUsernameElement().sendKeys(properties.getProperty(RIGHT_PHONE_NUMBER));
        loginPage.getPasswordElement().sendKeys(properties.getProperty(RIGHT_PASSWORD));
        loginPage.getEnterButton().click();
    }
}
