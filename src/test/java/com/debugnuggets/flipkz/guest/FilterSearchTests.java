package com.debugnuggets.flipkz.guest;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.ALLOWED_CHECKBOX_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.FILTER_FIELD_BOOK_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.FILTER_YEAR_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.FIND_BUTTON_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.SEARCH_INPUT_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.SEARCH_KEY_EXAMPLE;
import static com.debugnuggets.flipkz.constants.NameConstants.WWW_FLIP_KZ;

public class FilterSearchTests {

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
    public void addFilterToSearch() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(properties.getProperty(WWW_FLIP_KZ));

        WebElement searchInput = webDriver.findElement(By.xpath(properties.getProperty(SEARCH_INPUT_XPATH)));
        searchInput.sendKeys(properties.getProperty(SEARCH_KEY_EXAMPLE));
        WebElement searchSubmitButton = webDriver.findElement(By.xpath(properties.getProperty(FIND_BUTTON_XPATH)));
        searchSubmitButton.click();

        sleep(3);

        WebElement allowedCheckBox = webDriver.findElement(By.xpath(properties.getProperty(ALLOWED_CHECKBOX_XPATH)));
        allowedCheckBox.click();

        sleep(3);

        WebElement filterFieldBook = webDriver.findElement(By.xpath(properties.getProperty(FILTER_FIELD_BOOK_XPATH)));
        filterFieldBook.click();

        WebElement filterYear = webDriver.findElement(By.xpath(properties.getProperty(FILTER_YEAR_XPATH)));
        filterYear.click();
    }
}
