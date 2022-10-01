package com.debugnuggets.flipkz.guest;

import com.debugnuggets.flipkz.user.SecondTestClass;
import com.debugnuggets.flipkz.util.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.ADDED_FAVOURITES_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.ANDROID_APP_BUTTON_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.FAVOURITE_BUTTON_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.ID;
import static com.debugnuggets.flipkz.constants.NameConstants.MY_FAVOURITES_BUTTON_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.THERE_IS_NO_FAVOURITES_MESSAGE;
import static com.debugnuggets.flipkz.constants.NameConstants.WWW_FLIP_KZ;

public class FavouritesTests {

    private WebDriver webDriver;
    private Properties properties = PropertiesUtil.getInstance().getProperties();
    private final By profileHover = By.xpath("//span[@class='p500']");
    private void sleep(long sec) {
        long millis = sec * 1000;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addProductToFavourites() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(properties.getProperty(WWW_FLIP_KZ));

        sleep(2);

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(properties.getProperty(ANDROID_APP_BUTTON_XPATH))));

        List<WebElement> favouriteButtons = webDriver
                .findElements(By.xpath(properties.getProperty(FAVOURITE_BUTTON_XPATH)));

        sleep(2);

        if (!favouriteButtons.get(0).isSelected()) {
            String id = favouriteButtons.get(0).getAttribute(properties.getProperty(ID));
            WebElement labelForFavourite = webDriver.findElement(By.xpath("//label[@for='" + id + "']"));
            labelForFavourite.click();

            sleep(2);


            WebElement webElement1 =  webDriver.findElement(profileHover);
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement1).perform();

            WebElement myFavouritesButton = webDriver
                    .findElement(By.xpath(properties.getProperty(MY_FAVOURITES_BUTTON_XPATH)));
            myFavouritesButton.click();


            List<WebElement> addedFavourites = webDriver
                    .findElements(By.xpath(properties.getProperty(ADDED_FAVOURITES_XPATH)));
            String actualId = addedFavourites.get(0).getAttribute("id");
            Assert.assertEquals(actualId, id, properties.getProperty(THERE_IS_NO_FAVOURITES_MESSAGE));


            sleep(4);

            WebElement labelForAddedFavourite = webDriver.findElement(By.xpath("//label[@for='" + id + "']"));
            labelForAddedFavourite.click();

            sleep(2);

            webDriver.navigate().refresh();

            sleep(5);

            webDriver.quit();
        }
    }


    @Test
    public void deleteFromFavourites() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(WWW_FLIP_KZ);
        sleep(2);

        SecondTestClass.logIn(webDriver);
        sleep(2);

        WebElement webElement1 =  webDriver.findElement(profileHover);
        sleep(2);

        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement1).perform();
        WebElement myFavouritesButton = webDriver
                .findElement(By.xpath(properties.getProperty(MY_FAVOURITES_BUTTON_XPATH)));
        myFavouritesButton.click();

        List<WebElement> addedFavourites = webDriver
                .findElements(By.xpath(properties.getProperty(ADDED_FAVOURITES_XPATH)));
        String id = addedFavourites.get(0).getAttribute(properties.getProperty(ID));
        WebElement labelForFavourite = webDriver.findElement(By.xpath("//label[@for='" + id + "']"));
        labelForFavourite.click();
        sleep(2);
        webDriver.navigate().refresh();
    }
}
