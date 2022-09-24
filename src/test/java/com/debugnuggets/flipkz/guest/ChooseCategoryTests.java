package com.debugnuggets.flipkz.guest;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.BOOK_CATALOG_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.FANTASY_CATEGORY_BOOK_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.SORT_BY_REVIEW_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.SORT_DROP_DOWN_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.WWW_FLIP_KZ;

public class ChooseCategoryTests {

    private WebDriver webDriver;
    private Properties properties = PropertiesUtil.getInstance().getProperties();

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

        WebElement bookCatalogElement =  webDriver.findElement(By.xpath(properties.getProperty(BOOK_CATALOG_XPATH)));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(bookCatalogElement).perform();

        sleep(2);

        WebElement fantasyCategoryBookElement = webDriver
                .findElement(By.xpath(properties.getProperty(FANTASY_CATEGORY_BOOK_XPATH)));
        fantasyCategoryBookElement.click();

        sleep(2);

        WebElement sortElement = webDriver.findElement(By.xpath(properties.getProperty(SORT_DROP_DOWN_XPATH)));
        actions.moveToElement(sortElement).perform();

        sleep(2);

        WebElement sortByReviewElement = webDriver.findElement(By.xpath(properties.getProperty(SORT_BY_REVIEW_XPATH)));
        sortByReviewElement.click();

    }
}
