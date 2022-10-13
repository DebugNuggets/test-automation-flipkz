package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.ActionsUtil;
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


public class MainPage {

    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static WebDriverWaitUtil webDriverWaitUtil = WebDriverWaitUtil.getInstance();
    private static ActionsUtil actionsUtil = ActionsUtil.getInstance();
    private static MainPage instance;
    private static WebDriver webDriver;
    private WebElement profileHoverElement;
    private WebElement logOutButtonElement;
    private WebElement firstProductElement;
    private WebElement searchInputElement;
    private WebElement searchSubmitButtonElement;
    private WebElement allowedCheckBoxElement;
    private List<WebElement> productsElements;

    private final By profileHover = By.xpath("//span[@class='p500']");
    private final By logOutButton = By.xpath("//a[@href='/user?exit']");
    private final By exampleProducts = By.xpath("//a[@class='pic l-h-250']");
    private final By searchInput = By.xpath("//input[@id='search_input']");
    private final By searchSubmitButton = By.xpath("//input[@type='submit']");
    private final By allowedCheckBox = By.xpath(properties.getProperty("//label[@for='filter-field-i101']"));

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
        profileHoverElement = webDriver.findElement(profileHover);
        return profileHoverElement;
    }

    public WebElement getLogOutButtonElement() {
        Actions actions = actionsUtil.getActions(webDriver);
        actions.moveToElement(getProfileHoverElement()).perform();
        WebDriverWait wait = webDriverWaitUtil.getWebDriverWait(webDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
        logOutButtonElement = webDriver.findElement(logOutButton);
        return logOutButtonElement;
    }

    public WebElement getFirstProductElement() {
        firstProductElement = getProductsElements().get(0);
        return firstProductElement;
    }

    public List<WebElement> getProductsElements() {
        productsElements = webDriver.findElements(exampleProducts);
        return productsElements;
    }

    public WebElement getSearchInputElement() {
        searchInputElement = webDriver.findElement(searchInput);
        return searchInputElement;
    }

    public WebElement getSearchSubmitButtonElement() {
        searchSubmitButtonElement = webDriver.findElement(searchSubmitButton);
        return searchSubmitButtonElement;
    }

    public WebElement getAllowedCheckBoxElement() {
        allowedCheckBoxElement = webDriver.findElement(allowedCheckBox);
        return allowedCheckBoxElement;
    }
}
