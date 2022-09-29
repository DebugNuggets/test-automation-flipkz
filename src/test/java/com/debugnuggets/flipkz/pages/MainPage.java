package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.ActionsUtil;
import com.debugnuggets.flipkz.util.DriverSettings;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import com.debugnuggets.flipkz.util.WebDriverWaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class MainPage {

    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static WebDriverWaitUtil webDriverWaitUtil = WebDriverWaitUtil.getInstance();
    private static ActionsUtil actionsUtil = ActionsUtil.getInstance();
    private static MainPage instance;
    private static WebDriver webDriver;
    private WebElement profileHoverElement;
    private WebElement logOutButton;
    private WebElement firstProductElement;
    private List<WebElement> productsElements;


    public MainPage(WebDriver webDriver)
    {
        MainPage.webDriver = webDriver;
    }

    public static MainPage getInstance(WebDriver webDriver) {
        if (instance == null) {
            instance = new MainPage(webDriver);
        }
        return instance;
    }

    public WebElement getProfileHoverElement() {
        profileHoverElement = webDriver.findElement(By.xpath(properties.getProperty(PROFILE_HOVER_ELEMENT)));
        return profileHoverElement;
    }

    public WebElement getLogOutButton() {
        Actions actions = actionsUtil.getActions(webDriver);
        actions.moveToElement(getProfileHoverElement()).perform();
        WebDriverWait wait = webDriverWaitUtil.getWebDriverWait(webDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(LOG_OUT_XPATH))));
        logOutButton = webDriver.findElement(By.xpath(properties.getProperty(LOG_OUT_XPATH)));
        return logOutButton;
    }

    public WebElement getFirstProductElement() {
        firstProductElement = getProductsElements().get(0);
        return firstProductElement;
    }

    public List<WebElement> getProductsElements() {
        productsElements = webDriver.findElements(By.xpath(properties.getProperty(EXAMPLE_PRODUCT_XPATH)));
        return productsElements;
    }
}
