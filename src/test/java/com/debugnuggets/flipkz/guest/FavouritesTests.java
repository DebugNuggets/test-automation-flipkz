package com.debugnuggets.flipkz.guest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.debugnuggets.flipkz.pages.LoginPage;
import com.debugnuggets.flipkz.pages.MainPage;
import com.debugnuggets.flipkz.util.DBUtil;
import com.debugnuggets.flipkz.util.DriverSettings;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.debugnuggets.flipkz.constants.NameConstants.*;
import static com.debugnuggets.flipkz.constants.NameConstants.HTML_REPORT_FILE_PATH;
import static com.debugnuggets.flipkz.util.BaseTest.extent;

public class FavouritesTests {

    private WebDriver webDriver;
    public DriverSettings driverSettings = new DriverSettings();
    private Properties properties = PropertiesUtil.getInstance().getProperties();
    private final By profileHover = By.xpath("//span[@class='p500']");
    private static MainPage mainPage;
    private static LoginPage loginPage;
    ExtentHtmlReporter htmlReporter;

    private static DBUtil dbUtil = DBUtil.getInstance();
    private void sleep(long sec) {
        long millis = sec * 1000;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void startReport() throws IOException {
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
    public void addProductToFavourites() {
        sleep(2);

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(properties.getProperty(ANDROID_APP_BUTTON_XPATH))));

        List<WebElement> favouriteButtons = webDriver
                .findElements(By.xpath(properties.getProperty(FAVOURITE_BUTTON_XPATH)));

        sleep(2);

        if (!favouriteButtons.get(0).isSelected()) {
            String id = favouriteButtons.get(0).getAttribute(properties.getProperty(ID));
            WebElement labelForFavourite = webDriver.findElement(By.xpath("//label[@for='" + id + "']"));
            labelForFavourite.click();

            sleep(2);


            WebElement webElement1 =  webDriver.findElement(profileHover);
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement1).perform();

            WebElement myFavouritesButton = webDriver
                    .findElement(By.xpath(properties.getProperty(MY_FAVOURITES_BUTTON_XPATH)));
            myFavouritesButton.click();


            List<WebElement> addedFavourites = webDriver
                    .findElements(By.xpath(properties.getProperty(ADDED_FAVOURITES_XPATH)));
            String actualId = addedFavourites.get(0).getAttribute("id");
            Assert.assertEquals(actualId, id, properties.getProperty(THERE_IS_NO_FAVOURITES_MESSAGE));


            sleep(4);

            WebElement labelForAddedFavourite = webDriver.findElement(By.xpath("//label[@for='" + id + "']"));
            labelForAddedFavourite.click();

            sleep(2);

            webDriver.navigate().refresh();

            sleep(5);

            webDriver.quit();
        }
    }


    @Test
    public void deleteFromFavourites() {
        sleep(2);

        mainPage.toLogIn();
        loginPage.logIn();
        sleep(2);

        WebElement webElement1 =  webDriver.findElement(profileHover);
        sleep(2);

        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement1).perform();
        WebElement myFavouritesButton = webDriver
                .findElement(By.xpath(properties.getProperty(MY_FAVOURITES_BUTTON_XPATH)));
        myFavouritesButton.click();

        List<WebElement> addedFavourites = webDriver
                .findElements(By.xpath(properties.getProperty(ADDED_FAVOURITES_XPATH)));
        String id = addedFavourites.get(0).getAttribute(properties.getProperty(ID));
        WebElement labelForFavourite = webDriver.findElement(By.xpath("//label[@for='" + id + "']"));
        labelForFavourite.click();
        sleep(2);
        webDriver.navigate().refresh();
    }
}
