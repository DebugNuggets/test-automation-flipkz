package com.debugnuggets.flipkz.guest;

import com.debugnuggets.flipkz.PropertiesUtil;
import com.debugnuggets.flipkz.SecondTestClass;
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

import static com.debugnuggets.flipkz.constants.NameConstants.PROFILE_HOVER_ELEMENT;
import static com.debugnuggets.flipkz.constants.NameConstants.WWW_FLIP_KZ;

public class FavouritesTests {

    private WebDriver webDriver;

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
        webDriver.get(WWW_FLIP_KZ);

        sleep(2);

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='android']")));

        List<WebElement> favouriteButtons = webDriver
                .findElements(By.xpath("//input[@name='favorite']"));

        sleep(2);

        if (!favouriteButtons.get(0).isSelected()) {
            String id = favouriteButtons.get(0).getAttribute("id");
            WebElement labelForFavourite = webDriver.findElement(By.xpath("//label[@for='" + id + "']"));
            labelForFavourite.click();

            sleep(2);


            WebElement webElement1 =  webDriver.findElement(By.xpath(PROFILE_HOVER_ELEMENT));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement1).perform();

            WebElement myFavouritesButton = webDriver.findElement(By.xpath("//a[@href='/favorites']"));
            myFavouritesButton.click();


            List<WebElement> addedFavourites = webDriver
                    .findElements(By.xpath("//input[@name='favorite']"));

            String actualId = addedFavourites.get(0).getAttribute("id");

            Assert.assertEquals(actualId, id, "There is no favourites" );


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

        WebElement webElement1 =  webDriver.findElement(By.xpath(PROFILE_HOVER_ELEMENT));

        sleep(2);

        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement1).perform();

        WebElement myFavouritesButton = webDriver.findElement(By.xpath("//a[@href='/favorites']"));
        myFavouritesButton.click();


        List<WebElement> addedFavourites = webDriver
                .findElements(By.xpath("//input[@name='favorite']"));

        String id = addedFavourites.get(0).getAttribute("id");
        WebElement labelForFavourite = webDriver.findElement(By.xpath("//label[@for='" + id + "']"));

        labelForFavourite.click();

        sleep(2);

        webDriver.navigate().refresh();
    }
}
