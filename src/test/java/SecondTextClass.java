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

import static constants.NameConstants.*;

public class SecondTextClass {
    private WebDriver webDriver;

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

