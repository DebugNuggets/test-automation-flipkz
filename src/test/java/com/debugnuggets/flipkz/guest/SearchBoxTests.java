package com.debugnuggets.flipkz.guest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class SearchBoxTests {

    private WebDriver webDriver;

    @Test
    public void searchItemTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(WWW_FLIP_KZ);
        WebElement searchInput = webDriver.findElement(By.xpath(SEARCH_INPUT_XPATH));
        searchInput.sendKeys(SEARCH_KEY_EXAMPLE);
        WebElement searchSubmitButton = webDriver.findElement(By.xpath(FIND_BUTTON_XPATH));
        searchSubmitButton.click();
    }
}
