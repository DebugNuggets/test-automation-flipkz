package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class AddressFormPage {
    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static AddressFormPage instance;
    public static WebDriver webDriver;

    private WebElement fullNameElement;
    private WebElement cityElement;
    private WebElement addressElement;
    private WebElement buildingElement;
    private WebElement flatElement;
    private WebElement entranceElement;
    private WebElement floorElement;
    private WebElement postalCodeElement;
    private WebElement addressButton;
    private WebElement submitButton;

    public AddressFormPage(WebDriver webDriver)
    {
        AddressFormPage.webDriver = webDriver;
    }

    public static AddressFormPage getInstance(WebDriver webDriver)
    {
        if (instance == null) {
            instance = new AddressFormPage(webDriver);
        }
        return instance;
    }

    public WebElement getFullNameElement() {
        fullNameElement = webDriver.findElement(By.xpath(properties.getProperty(FULL_NAME_XPATH)));
        return fullNameElement;
    }

    public WebElement getCityElement() {
        cityElement = webDriver.findElement(By.xpath(properties.getProperty(CITY_XPATH)));
        return cityElement;
    }

    public WebElement getAddressElement() {
        addressElement = webDriver.findElement(By.xpath(properties.getProperty(ADDRESS_XPATH)));
        return addressElement;
    }

    public WebElement getBuildingElement() {
        buildingElement = webDriver.findElement(By.xpath(properties.getProperty(BUILDING_XPATH)));
        return buildingElement;
    }

    public WebElement getFlatElement() {
        flatElement = webDriver.findElement(By.xpath(properties.getProperty(FLAT_XPATH)));
        return flatElement;
    }

    public WebElement getEntranceElement() {
        entranceElement = webDriver.findElement(By.xpath(properties.getProperty(ENTRANCE_XPATH)));
        return entranceElement;
    }

    public WebElement getFloorElement() {
        floorElement = webDriver.findElement(By.xpath(properties.getProperty(FLOOR_XPATH)));
        return floorElement;
    }

    public WebElement getAddressButton() {
        addressButton = webDriver.findElement(By.xpath(properties.getProperty(SUBMIT_ORDER_1_XPATH)));
        return addressButton;
    }

    public WebElement getPostalCodeElement() {
        postalCodeElement = webDriver.findElement(By.xpath(properties.getProperty(POSTAL_CODE_XPATH)));
        return postalCodeElement;
    }

    public WebElement getSubmitButton() {
        submitButton = webDriver.findElement(By.xpath(properties.getProperty(DEFAULT_SUBMIT_XPATH)));
        return submitButton;
    }
}
