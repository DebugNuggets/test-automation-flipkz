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

public class SecondTextClass {
    private WebDriver webDriver;
    private String urlFlip = "https://www.flip.kz/";
    private String profileHoverElement = "//span[@class='p500']";
    private String exampleProductXpath = "//a[@class='pic l-h-250']";
    private String addProductXpath = "//input[@id='cart_button']";
    private String submitXpath = "//input[@name='order_button']";
    private String fullNameXpath = "//input[@name='fullName']";
    private String cityXpath = "//input[@name='city']";
    private String addressXpath = "//input[@id='address1]";
    private String buildingXpath = "//input[id='building']";
    private String flatXpath = "//input[id='flat']";
    private String entranceXpath = "//input[id='entrance']";
    private String floorXpath = "//input[id='floor']";
    private String submitOrder1Xpath = "//input[name='address_butt']";
    private String loginUsernameXpath = "//input[@id='username']";
    private String loginPasswordXpath = "//input[@id='password']";
    private String enterButtonXpath = "//input[@id='enter_button']";
    private String logOutXpath = "//a[@href='/user?exit']";
    private String rightPhoneNumber = "+7 705 296 60 64";
    private String rightPassword = "testPassword123";
    private String city = "Нур-Султан";
    private String fullName = "Ожогов Владислав Владимирович";
    private String address = "Байконыр р-н, пр. Республика";



    @Test
    public void logOutTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(urlFlip);
        logIn(webDriver);
        WebElement webElement1 =  webDriver.findElement(By.xpath(profileHoverElement));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement1).perform();
        WebDriverWait wait = new WebDriverWait(webDriver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logOutXpath)));
        WebElement logOutButton = webDriver.findElement(By.xpath(logOutXpath));
        logOutButton.click();
    }

    @Test
    public void justLogin()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(urlFlip);
        logIn(webDriver);
    }

    public void logIn(WebDriver webDriver1) {
        WebElement webElementProfile = webDriver1.findElement(By.xpath(profileHoverElement));
        webElementProfile.click();
        WebElement loginBar = webDriver1.findElement(By.xpath(loginUsernameXpath));
        loginBar.sendKeys(rightPhoneNumber);
        WebElement passwordBar = webDriver1.findElement(By.xpath(loginPasswordXpath));
        passwordBar.sendKeys(rightPassword);
        WebElement loginButton = webDriver1.findElement(By.xpath(enterButtonXpath));
        loginButton.click();
    }

    @Test
    public void addProductToCart()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(urlFlip);
        logIn(webDriver);

        List<WebElement> productsElements = webDriver.findElements(By.xpath(exampleProductXpath));
        WebElement productElement = productsElements.get(0);

        productElement.click();

        WebElement addToCart = webDriver.findElement(By.xpath(addProductXpath));
        addToCart.click();

        WebElement confirm = webDriver.findElement(By.xpath(submitXpath));
        confirm.click();
        submitAddress(webDriver);
    }


    public void submitAddress(WebDriver webDriver1)
    {
        WebElement fullNameElement = webDriver1.findElement(By.xpath(fullNameXpath));
        fullNameElement.sendKeys(fullName);
        WebElement cityElement = webDriver1.findElement(By.xpath(cityXpath));
        cityElement.sendKeys(city);
        WebElement addressElement = webDriver1.findElement(By.xpath(addressXpath));
        addressElement.sendKeys();
    }

}

