package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.ADD_PRODUCT_XPATH;
import static com.debugnuggets.flipkz.constants.NameConstants.SUBMIT_XPATH;

public class ProductPage {
    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static ProductPage instance;
    private WebElement addToCartElement;
    private WebElement submitButton;
    private static WebDriver webDriver;
    public ProductPage(WebDriver webDriver)
    {
        ProductPage.webDriver = webDriver;
    }

    public static ProductPage getInstance(WebDriver webDriver) {
        if (instance == null) {
            instance = new ProductPage(webDriver);
        }
        return instance;
    }

    public WebElement getAddToCartElement() {
        addToCartElement = webDriver.findElement(By.xpath(properties.getProperty(ADD_PRODUCT_XPATH)));
        return addToCartElement;
    }

    public WebElement getSubmitButton() {
        submitButton = webDriver.findElement(By.xpath(properties.getProperty(SUBMIT_XPATH)));
        return submitButton;
    }
}
