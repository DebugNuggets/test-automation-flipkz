package com.debugnuggets.flipkz.user;

import com.debugnuggets.flipkz.pages.AddressFormPage;
import com.debugnuggets.flipkz.pages.LoginPage;
import com.debugnuggets.flipkz.pages.MainPage;
import com.debugnuggets.flipkz.pages.ProductPage;
import com.debugnuggets.flipkz.util.ActionsUtil;
import com.debugnuggets.flipkz.util.DriverSettings;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import com.debugnuggets.flipkz.util.WebDriverWaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.Random;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class SecondTestClass {
    private static Properties properties = PropertiesUtil.getInstance().getProperties();
    private static ActionsUtil actionsUtil = ActionsUtil.getInstance();
    private static WebDriverWaitUtil webDriverWaitUtil = WebDriverWaitUtil.getInstance();
    public DriverSettings driverSettings = new DriverSettings();
    private WebDriver webDriver;

    @Test
    public void logOutTest() {
        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        logIn(webDriver);
        MainPage mainPage = MainPage.getInstance(webDriver);
        Actions actions = actionsUtil.getActions(webDriver);
        actions.moveToElement(mainPage.getProfileHoverElement()).perform();
        WebDriverWait wait = webDriverWaitUtil.getWebDriverWait(webDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) mainPage.getLogOutButton()));
        mainPage.getLogOutButton().click();
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

    @Test
    public void wrongLogIn()
    {
        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        MainPage mainPage = MainPage.getInstance(webDriver);
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        webDriverWaitUtil.getWebDriverWait(webDriver).until(ExpectedConditions
                .visibilityOfElementLocated((By) mainPage.getProfileHoverElement()));
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
    }
    @Test
    public void submitOrderWithRightCredentials()
    {
        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        webDriver.manage().window().maximize();
        logIn(webDriver);
        addProductToCart(webDriver);
        submitRightAddress(webDriver);
    }

    @Test
    public void submitOrderWithWrongCredentials()
    {
        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        webDriver.manage().window().maximize();
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
        addressFormPage.getAddressButton().click();
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
        addressFormPage.getAddressButton().click();
        addressFormPage.getSubmitButton().click();
    }
}

