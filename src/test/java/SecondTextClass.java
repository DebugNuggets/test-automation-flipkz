import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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
        new WebDriverWait(webDriver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PROFILE_HOVER_ELEMENT)));
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
    public void wrongLogIn()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(WWW_FLIP_KZ);
        new WebDriverWait(webDriver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PROFILE_HOVER_ELEMENT)));
        WebElement webElementProfile = webDriver.findElement(By.xpath(PROFILE_HOVER_ELEMENT));
        webElementProfile.click();
        WebElement loginBar = webDriver.findElement(By.xpath(LOGIN_USERNAME_XPATH));

        Random r = new Random();
        StringBuilder wrongPhone = new StringBuilder(WRONG_PHONE_NUMBER);
        for (int i = 0; i<10; i++)
        {
            if(i==0 || i==3 || i==6 || i==8)
                wrongPhone.append(SPACE);
            wrongPhone.append(r.nextInt(9));

        }
        loginBar.sendKeys(wrongPhone.toString());
        WebElement passwordBar = webDriver.findElement(By.xpath(LOGIN_PASSWORD_XPATH));
        StringBuilder wrongPassword = new StringBuilder(WRONG_PASSWORD);
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            wrongPassword.append(c);
        }
        passwordBar.sendKeys(wrongPassword);
        WebElement loginButton = webDriver.findElement(By.xpath(ENTER_BUTTON_XPATH));
        loginButton.click();
    }
    @Test
    public void addProductToCart() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(WWW_FLIP_KZ);
        webDriver.manage().window().maximize();
        logIn(webDriver);

        List<WebElement> productsElements = webDriver.findElements(By.xpath(EXAMPLE_PRODUCT_XPATH));
        WebElement productElement = productsElements.get(0);

        productElement.click();

        WebElement addToCart = webDriver.findElement(By.xpath(ADD_PRODUCT_XPATH));
        addToCart.click();

        WebElement confirm = webDriver.findElement(By.xpath(SUBMIT_XPATH));
        new WebDriverWait(webDriver, 20).until(ExpectedConditions.elementToBeClickable(confirm)).click();
        //confirm.click();
        submitAddress(webDriver);
    }

    public void submitAddress(WebDriver webDriver1)
    {
        WebElement fullNameElement = webDriver1.findElement(By.xpath(FULL_NAME_XPATH));
        fullNameElement.sendKeys(FULL_NAME);
        WebElement cityElement = webDriver1.findElement(By.xpath(CITY_XPATH));
        cityElement.clear();
        cityElement.sendKeys(CITY);
        WebElement addressElement = webDriver1.findElement(By.xpath(ADDRESS_XPATH));
        addressElement.sendKeys(ADDRESS);
        WebElement buildingElement = webDriver1.findElement(By.xpath(BUILDING_XPATH));
        buildingElement.sendKeys(BUILDING);
        WebElement flatElement = webDriver1.findElement(By.xpath(FLAT_XPATH));
        flatElement.sendKeys(FLAT);
        WebElement entranceElement = webDriver1.findElement(By.xpath(ENTRANCE_XPATH));
        entranceElement.sendKeys(ENTRANCE);
        WebElement floorElement = webDriver1.findElement(By.xpath(FLOOR_XPATH));
        floorElement.sendKeys(FLOOR);
        WebElement postalCodeElement = webDriver1.findElement(By.xpath(POSTAL_CODE_XPATH));
        postalCodeElement.clear();
        postalCodeElement.sendKeys(POSTAL_CODE);
        WebElement addressButton = webDriver1.findElement(By.xpath(SUBMIT_ORDER_1_XPATH));
        addressButton.click();
        WebElement submitButton = webDriver1.findElement(By.xpath(DEFAULT_SUBMIT_XPATH));
        submitButton.click();
    }

}

