package com.debugnuggets.flipkz.user;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class SecondTestClass {

    private static WebDriver webDriver;
    private static Properties properties = PropertiesUtil.getInstance().getProperties();

    @Test
    public void logOutTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        logIn(webDriver);
        WebElement webElement1 =  webDriver.findElement(By.xpath(properties.getProperty(PROFILE_HOVER_ELEMENT)));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement1).perform();
        WebDriverWait wait = new WebDriverWait(webDriver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(LOG_OUT_XPATH))));
        WebElement logOutButton = webDriver.findElement(By.xpath(properties.getProperty(LOG_OUT_XPATH)));
        logOutButton.click();
    }

    @Test
    public void justLogin()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        logIn(webDriver);
    }

    public static void logIn(WebDriver webDriver1) {
        WebElement webElementProfile = webDriver1.findElement(By.xpath(properties.getProperty(PROFILE_HOVER_ELEMENT)));
        webElementProfile.click();
        WebElement loginBar = webDriver1.findElement(By.xpath(properties.getProperty(LOGIN_USERNAME_XPATH)));
        loginBar.sendKeys(properties.getProperty(RIGHT_PHONE_NUMBER));
        WebElement passwordBar = webDriver1.findElement(By.xpath(properties.getProperty(LOGIN_PASSWORD_XPATH)));
        passwordBar.sendKeys(properties.getProperty(RIGHT_PASSWORD));
        WebElement loginButton = webDriver1.findElement(By.xpath(properties.getProperty(ENTER_BUTTON_XPATH)));
        loginButton.click();
    }

    @Test
    public void wrongLogIn()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        new WebDriverWait(webDriver, 20).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(properties.getProperty(PROFILE_HOVER_ELEMENT))));
        WebElement webElementProfile = webDriver.findElement(By.xpath(properties.getProperty(PROFILE_HOVER_ELEMENT)));
        webElementProfile.click();
        WebElement loginBar = webDriver.findElement(By.xpath(properties.getProperty(LOGIN_USERNAME_XPATH)));

        Random r = new Random();
        StringBuilder wrongPhone = new StringBuilder(properties.getProperty(WRONG_PHONE_NUMBER));
        for (int i = 0; i<10; i++)
        {
            if(i==0 || i==3 || i==6 || i==8)
                wrongPhone.append(SPACE);
            wrongPhone.append(r.nextInt(9));

        }
        loginBar.sendKeys(wrongPhone.toString());
        WebElement passwordBar = webDriver.findElement(By.xpath(properties.getProperty(LOGIN_PASSWORD_XPATH)));
        StringBuilder wrongPassword = new StringBuilder(properties.getProperty(WRONG_PASSWORD));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            wrongPassword.append(c);
        }
        passwordBar.sendKeys(wrongPassword);
        WebElement loginButton = webDriver.findElement(By.xpath(properties.getProperty(ENTER_BUTTON_XPATH)));
        loginButton.click();
    }
    @Test
    public void submitOrderWithRightCredentials()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        webDriver.manage().window().maximize();
        logIn(webDriver);
        addProductToCart(webDriver);
        submitRightAddress(webDriver);
    }

    public void addProductToCart(WebDriver webDriver1) {

        List<WebElement> productsElements = webDriver1
                .findElements(By.xpath(properties.getProperty(EXAMPLE_PRODUCT_XPATH)));
        WebElement productElement = productsElements.get(0);

        productElement.click();

        WebElement addToCart = webDriver1.findElement(By.xpath(properties.getProperty(ADD_PRODUCT_XPATH)));
        addToCart.click();

        WebElement confirm = webDriver1.findElement(By.xpath(properties.getProperty(SUBMIT_XPATH)));
        new WebDriverWait(webDriver1, 20).until(ExpectedConditions.elementToBeClickable(confirm)).click();
    }

    public void submitRightAddress(WebDriver webDriver1)
    {
        WebElement fullNameElement = webDriver1.findElement(By.xpath(properties.getProperty(FULL_NAME_XPATH)));
        fullNameElement.sendKeys(properties.getProperty(FULL_NAME));
        WebElement cityElement = webDriver1.findElement(By.xpath(properties.getProperty(CITY_XPATH)));
        cityElement.clear();
        cityElement.sendKeys(properties.getProperty(CITY));
        WebElement addressElement = webDriver1.findElement(By.xpath(properties.getProperty(ADDRESS_XPATH)));
        addressElement.sendKeys(properties.getProperty(ADDRESS));
        WebElement buildingElement = webDriver1.findElement(By.xpath(properties.getProperty(BUILDING_XPATH)));
        buildingElement.sendKeys(properties.getProperty(BUILDING));
        WebElement flatElement = webDriver1.findElement(By.xpath(properties.getProperty(FLAT_XPATH)));
        flatElement.sendKeys(properties.getProperty(FLAT));
        WebElement entranceElement = webDriver1.findElement(By.xpath(properties.getProperty(ENTRANCE_XPATH)));
        entranceElement.sendKeys(properties.getProperty(ENTRANCE));
        WebElement floorElement = webDriver1.findElement(By.xpath(properties.getProperty(FLOOR_XPATH)));
        floorElement.sendKeys(properties.getProperty(FLOOR));
        WebElement postalCodeElement = webDriver1.findElement(By.xpath(properties.getProperty(POSTAL_CODE_XPATH)));
        postalCodeElement.clear();
        postalCodeElement.sendKeys(properties.getProperty(POSTAL_CODE));
        WebElement addressButton = webDriver1.findElement(By.xpath(properties.getProperty(SUBMIT_ORDER_1_XPATH)));
        addressButton.click();
        WebElement submitButton = webDriver1.findElement(By.xpath(properties.getProperty(DEFAULT_SUBMIT_XPATH)));
        submitButton.click();
    }

    @Test
    public void submitOrderWithWrongCredentials()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(properties.getProperty(WWW_FLIP_KZ));
        webDriver.manage().window().maximize();
        logIn(webDriver);
        addProductToCart(webDriver);
        submitWrongAddress(webDriver);
    }

    public void submitWrongAddress(WebDriver webDriver1)
    {
        WebElement fullNameElement = webDriver1.findElement(By.xpath(properties.getProperty(FULL_NAME_XPATH)));
        fullNameElement.sendKeys(properties.getProperty(WRONG_FULL_NAME));
        WebElement cityElement = webDriver1.findElement(By.xpath(properties.getProperty(CITY_XPATH)));
        cityElement.clear();
        cityElement.sendKeys(properties.getProperty(WRONG_CITY));
        WebElement addressElement = webDriver1.findElement(By.xpath(properties.getProperty(ADDRESS_XPATH)));
        addressElement.sendKeys(properties.getProperty(WRONG_ADDRESS));
        WebElement buildingElement = webDriver1.findElement(By.xpath(properties.getProperty(BUILDING_XPATH)));
        buildingElement.sendKeys(properties.getProperty(WRONG_BUILDING));
        WebElement flatElement = webDriver1.findElement(By.xpath(properties.getProperty(FLAT_XPATH)));
        flatElement.sendKeys(properties.getProperty(WRONG_FLAT));
        WebElement entranceElement = webDriver1.findElement(By.xpath(properties.getProperty(ENTRANCE_XPATH)));
        entranceElement.sendKeys(properties.getProperty(WRONG_ENTRANCE));
        WebElement floorElement = webDriver1.findElement(By.xpath(properties.getProperty(FLOOR_XPATH)));
        floorElement.sendKeys(properties.getProperty(WRONG_FLOOR));
        WebElement postalCodeElement = webDriver1.findElement(By.xpath(properties.getProperty(POSTAL_CODE_XPATH)));
        postalCodeElement.clear();
        postalCodeElement.sendKeys(properties.getProperty(WRONG_POSTAL_CODE));
        WebElement addressButton = webDriver1.findElement(By.xpath(properties.getProperty(SUBMIT_ORDER_1_XPATH)));
        addressButton.click();
        WebElement submitButton = webDriver1.findElement(By.xpath(properties.getProperty(DEFAULT_SUBMIT_XPATH)));
        submitButton.click();
    }
}

