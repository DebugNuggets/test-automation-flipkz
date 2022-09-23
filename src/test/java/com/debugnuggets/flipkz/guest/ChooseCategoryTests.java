package com.debugnuggets.flipkz.guest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static com.debugnuggets.flipkz.constants.NameConstants.WWW_FLIP_KZ;

public class ChooseCategoryTests {

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
    public void chooseCategoryTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(WWW_FLIP_KZ);

        sleep(2);

        WebElement bookCatalogElement =  webDriver.findElement(By.xpath("//a[@href='/catalog?subsection=1']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(bookCatalogElement).perform();

        sleep(2);

        WebElement fantasyCategoryBookElement = webDriver.findElement(By.xpath("//a[@href='/catalog?subsection=134']"));
        fantasyCategoryBookElement.click();

        sleep(2);

        WebElement sortElement = webDriver.findElement(By.xpath("//div[@class='sort text-unselectable']"));
        actions.moveToElement(sortElement).perform();

        sleep(2);

        WebElement sortByReviewElement = webDriver.findElement(By.xpath("//label[@for='sort-reviews.down']"));
        sortByReviewElement.click();

    }
}
