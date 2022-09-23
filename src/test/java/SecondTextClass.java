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

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class SecondTextClass {

    private WebDriver webDriver;

    @Test
    public void logOutTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(WWW_FLIP_KZ);
        logIn(webDriver);
        WebElement webElement1 =  webDriver.findElement(By.xpath(PROFILE_HOVER_ELEMENT));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement1).perform();
        WebDriverWait wait = new WebDriverWait(webDriver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOG_OUT_XPATH)));
        WebElement logOutButton = webDriver.findElement(By.xpath(LOG_OUT_XPATH));
        logOutButton.click();
    }

    @Test
    public void justLogin()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(WWW_FLIP_KZ);
        logIn(webDriver);
    }

    public void logIn(WebDriver webDriver1) {
        WebElement webElementProfile = webDriver1.findElement(By.xpath(PROFILE_HOVER_ELEMENT));
        webElementProfile.click();
        WebElement loginBar = webDriver1.findElement(By.xpath(LOGIN_USERNAME_XPATH));
        loginBar.sendKeys(RIGHT_PHONE_NUMBER);
        WebElement passwordBar = webDriver1.findElement(By.xpath(LOGIN_PASSWORD_XPATH));
        passwordBar.sendKeys(RIGHT_PASSWORD);
        WebElement loginButton = webDriver1.findElement(By.xpath(ENTER_BUTTON_XPATH));
        loginButton.click();
    }

    @Test
    public void addProductToCart()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(WWW_FLIP_KZ);
        logIn(webDriver);

        List<WebElement> productsElements = webDriver.findElements(By.xpath(EXAMPLE_PRODUCT_XPATH));
        WebElement productElement = productsElements.get(0);

        productElement.click();

        WebElement addToCart = webDriver.findElement(By.xpath(ADD_PRODUCT_XPATH));
        addToCart.click();

        WebElement confirm = webDriver.findElement(By.xpath(SUBMIT_XPATH));
        confirm.click();
        submitAddress(webDriver);
    }


    public void submitAddress(WebDriver webDriver1)
    {
        WebElement fullNameElement = webDriver1.findElement(By.xpath(FULL_NAME_XPATH));
        fullNameElement.sendKeys(FULL_NAME);
        WebElement cityElement = webDriver1.findElement(By.xpath(CITY_XPATH));
        cityElement.sendKeys(CITY);
        WebElement addressElement = webDriver1.findElement(By.xpath(ADDRESS_XPATH));
        addressElement.sendKeys();
    }

}

