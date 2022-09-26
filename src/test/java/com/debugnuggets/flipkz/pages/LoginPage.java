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
    private final WebElement usernameElement;
    private final WebElement passwordElement;
    private final WebElement enterButton;

    public LoginPage(WebDriver webDriver)
    {
        usernameElement = webDriver.findElement(By.xpath(properties.getProperty(LOGIN_USERNAME_XPATH)));
        passwordElement = webDriver.findElement(By.xpath(properties.getProperty(LOGIN_PASSWORD_XPATH)));
        enterButton = webDriver.findElement(By.xpath(properties.getProperty(ENTER_BUTTON_XPATH)));
    }

    public static LoginPage getInstance(WebDriver webDriver) {
        if (instance == null) {
            instance = new LoginPage(webDriver);
        }
        return instance;
    }

    public WebElement getUsernameElement() {
        return usernameElement;
    }

    public WebElement getPasswordElement() {
        return passwordElement;
    }

    public WebElement getEnterButton() {
        return enterButton;
    }
}
