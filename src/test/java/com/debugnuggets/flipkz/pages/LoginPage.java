package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.DBUtil;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class LoginPage {
    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static LoginPage instance;
    private static WebDriver webDriver;
    private WebElement usernameElement;
    private WebElement passwordElement;
    private WebElement enterButtonElement;
    private static DBUtil dbUtil = DBUtil.getInstance();
    private final By loginUsername = By.xpath("//input[@id='username']");
    private final By loginPassword = By.xpath("//input[@id='password']");
    private final By enterButton = By.xpath("//input[@id='enter_button']");
    private static final String SELECT_EVERYTHING_RIGHT = "select * from user_credential where id = 1";
    private static final String SELECT_EVERYTHING_WRONG = "select * from user_credential where id = 2";
    private static List<Map<String, Object>> resultOfQuery;
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

    public void logIn() {
        resultOfQuery = dbUtil.getQueryResultMap(SELECT_EVERYTHING_RIGHT);
        String phoneNumber = (String) resultOfQuery.get(0).get("phone_number");
        String password = (String) resultOfQuery.get(0).get("password");
        instance.getUsernameElement().sendKeys(phoneNumber);
        instance.getPasswordElement().sendKeys(password);
        instance.getEnterButton().click();
    }

    public void wrongLogIn() {
        resultOfQuery = dbUtil.getQueryResultMap(SELECT_EVERYTHING_WRONG);
        String phoneNumber = (String) resultOfQuery.get(0).get("phone_number");
        String password = (String) resultOfQuery.get(0).get("password");
        instance.getUsernameElement().sendKeys(phoneNumber);
        instance.getPasswordElement().sendKeys(password);
        instance.getEnterButton().click();
    }

}
