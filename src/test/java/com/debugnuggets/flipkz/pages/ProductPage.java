package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;


public class ProductPage {
    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static ProductPage instance;
    private WebElement addToCartElement;
    private WebElement submitButtonElement;
    private static WebDriver webDriver;
    private final By addToCart = By.xpath("//input[@id='cart_button']");
    private final By submitButton = By.xpath("//input[@name='order_button']");
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
        addToCartElement = webDriver.findElement(addToCart);
        return addToCartElement;
    }

    public WebElement getSubmitButton() {
        submitButtonElement = webDriver.findElement(submitButton);
        return submitButtonElement;
    }
}
