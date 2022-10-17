package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.WebDriverWaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage {
    private static final WebDriverWaitUtil webDriverWaitUtil = WebDriverWaitUtil.getInstance();
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

    public void addToCart() {
        instance.getAddToCartElement().click();
        webDriverWaitUtil.getWebDriverWait(webDriver).until(ExpectedConditions.elementToBeClickable(instance.getSubmitButton())).click();
    }
}
