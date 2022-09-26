package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.*;

public class AddressFormPage {
    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static AddressFormPage instance;
    private final WebElement fullNameElement;
    private final WebElement cityElement;
    private final WebElement addressElement;
    private final WebElement buildingElement;
    private final WebElement flatElement;
    private final WebElement entranceElement;
    private final WebElement floorElement;
    private final WebElement postalCodeElement;
    private final WebElement addressButton;
    private final WebElement submitButton;

    public AddressFormPage(WebDriver webDriver)
    {
        fullNameElement = webDriver.findElement(By.xpath(properties.getProperty(FULL_NAME_XPATH)));
        cityElement = webDriver.findElement(By.xpath(properties.getProperty(CITY_XPATH)));
        addressElement = webDriver.findElement(By.xpath(properties.getProperty(ADDRESS_XPATH)));
        buildingElement = webDriver.findElement(By.xpath(properties.getProperty(BUILDING_XPATH)));
        flatElement = webDriver.findElement(By.xpath(properties.getProperty(FLAT_XPATH)));
        entranceElement = webDriver.findElement(By.xpath(properties.getProperty(ENTRANCE_XPATH)));
        floorElement = webDriver.findElement(By.xpath(properties.getProperty(FLOOR_XPATH)));
        postalCodeElement = webDriver.findElement(By.xpath(properties.getProperty(POSTAL_CODE_XPATH)));
        addressButton = webDriver.findElement(By.xpath(properties.getProperty(SUBMIT_ORDER_1_XPATH)));
        submitButton = webDriver.findElement(By.xpath(properties.getProperty(DEFAULT_SUBMIT_XPATH)));
    }

    public static AddressFormPage getInstance(WebDriver webDriver)
    {
        if (instance == null) {
            instance = new AddressFormPage(webDriver);
        }
        return instance;
    }

    public WebElement getFullNameElement() {
        return fullNameElement;
    }

    public WebElement getCityElement() {
        return cityElement;
    }

    public WebElement getAddressElement() {
        return addressElement;
    }

    public WebElement getBuildingElement() {
        return buildingElement;
    }

    public WebElement getFlatElement() {
        return flatElement;
    }

    public WebElement getEntranceElement() {
        return entranceElement;
    }

    public WebElement getFloorElement() {
        return floorElement;
    }

    public WebElement getAddressButton() {
        return addressButton;
    }

    public WebElement getPostalCodeElement() {
        return postalCodeElement;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }
}
