package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.DriverSettings;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class LoginPage {
    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static LoginPage instance;
    private static WebDriver webDriver;
    private WebElement usernameElement;
    private WebElement passwordElement;
    private WebElement enterButton;

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
        usernameElement = webDriver.findElement(By.xpath(properties.getProperty(LOGIN_USERNAME_XPATH)));
        return usernameElement;
    }

    public WebElement getPasswordElement() {
        passwordElement = webDriver.findElement(By.xpath(properties.getProperty(LOGIN_PASSWORD_XPATH)));
        return passwordElement;
    }

    public WebElement getEnterButton() {
        enterButton = webDriver.findElement(By.xpath(properties.getProperty(ENTER_BUTTON_XPATH)));
        return enterButton;
    }
}
