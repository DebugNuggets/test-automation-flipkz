package com.debugnuggets.flipkz.guest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static com.debugnuggets.flipkz.constants.NameConstants.FIND_BUTTON_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.SEARCH_INPUT_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.SEARCH_KEY_EXAMPLE;
import static com.debugnuggets.flipkz.constants.NameConstants.WWW_FLIP_KZ;

public class FilterSearchTests {

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
    public void addFilterToSearch() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(WWW_FLIP_KZ);

        WebElement searchInput = webDriver.findElement(By.xpath(SEARCH_INPUT_XPATH));
        searchInput.sendKeys(SEARCH_KEY_EXAMPLE);
        WebElement searchSubmitButton = webDriver.findElement(By.xpath(FIND_BUTTON_XPATH));
        searchSubmitButton.click();

        sleep(3);

        WebElement allowedCheckBox = webDriver.findElement(By.xpath("//label[@for='filter-field-i101']"));
        allowedCheckBox.click();

        sleep(3);

        WebElement filterFieldBook = webDriver.findElement(By.xpath("//a[@data-filter-field-sections-id='1']"));
        filterFieldBook.click();

        WebElement filterYear = webDriver.findElement(By.xpath("//label[@for='filter-field-a6151-19']"));
        filterYear.click();
    }
}
