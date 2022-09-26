package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.DriverSettings;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class MainPage {

    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static MainPage instance;
    private final WebElement profileHoverElement;
    private final WebElement logOutButton;
    private final WebElement firstProductElement;
    private final List<WebElement> productsElements;


    public MainPage(WebDriver webDriver)
    {
        profileHoverElement = webDriver.findElement(By.xpath(properties.getProperty(PROFILE_HOVER_ELEMENT)));
        logOutButton = webDriver.findElement(By.xpath(properties.getProperty(LOG_OUT_XPATH)));
        productsElements = webDriver.findElements(By.xpath(properties.getProperty(EXAMPLE_PRODUCT_XPATH)));
        firstProductElement = productsElements.get(0);
    }

    public static MainPage getInstance(WebDriver webDriver) {
        if (instance == null) {
            instance = new MainPage(webDriver);
        }
        return instance;
    }

    public WebElement getProfileHoverElement() {
        return profileHoverElement;
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public WebElement getFirstProductElement() {
        return firstProductElement;
    }

    public List<WebElement> getProductsElements() {
        return productsElements;
    }
}
