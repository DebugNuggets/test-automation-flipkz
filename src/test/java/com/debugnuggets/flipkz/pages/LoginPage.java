package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class LoginPage {
    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static LoginPage instance;
    private static WebDriver webDriver;
    private WebElement usernameElement;
    private WebElement passwordElement;
    private WebElement enterButtonElement;
    private final By loginUsername = By.xpath("//input[@id='username']");
    private final By loginPassword = By.xpath("//input[@id='password']");
    private final By enterButton = By.xpath("//input[@id='enter_button']");
    public LoginPage(WebDriver webDriver)
    {
        LoginPage.webDriver =webDriver;
    }

    public static LoginPage getInstance(WebDriver webDriver) {
        if (instance == null) {
            instance = new LoginPage(webDriver);
        }
        return instance;
    }

    public WebElement getUsernameElement() {
        usernameElement = webDriver.findElement(loginUsername);
        return usernameElement;
    }

    public WebElement getPasswordElement() {
        passwordElement = webDriver.findElement(loginPassword);
        return passwordElement;
    }

    public WebElement getEnterButton() {
        enterButtonElement = webDriver.findElement(enterButton);
        return enterButtonElement;
    }
}
