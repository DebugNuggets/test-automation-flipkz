package com.debugnuggets.flipkz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AddressFormPage {
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
    private WebElement addressButtonElement;
    private WebElement submitButton;

    private final By fullName = By.xpath("//input[@name='fullName']");
    private final By city = By.xpath("//input[@name='city']");
    private final By address = By.xpath("//input[@id='address1']");
    private final By building = By.xpath("//input[@id='building']");
    private final By flat = By.xpath("//input[@id='flat']");
    private final By entrance = By.xpath("//input[@id='entrance']");
    private final By floor = By.xpath("//input[@id='floor']");
    private final By postalCode = By.xpath("//input[@id='post']");
    private final By addressButton = By.xpath("//input[@name='address_butt']");
    private final By submit = By.xpath("//button[@type='submit']");

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
        fullNameElement = webDriver.findElement(fullName);
        return fullNameElement;
    }

    public WebElement getCityElement() {
        cityElement = webDriver.findElement(city);
        return cityElement;
    }

    public WebElement getAddressElement() {
        addressElement = webDriver.findElement(address);
        return addressElement;
    }

    public WebElement getBuildingElement() {
        buildingElement = webDriver.findElement(building);
        return buildingElement;
    }

    public WebElement getFlatElement() {
        flatElement = webDriver.findElement(flat);
        return flatElement;
    }

    public WebElement getEntranceElement() {
        entranceElement = webDriver.findElement(entrance);
        return entranceElement;
    }

    public WebElement getFloorElement() {
        floorElement = webDriver.findElement(floor);
        return floorElement;
    }

    public WebElement getAddressButtonElement() {
        addressButtonElement = webDriver.findElement(addressButton);
        return addressButtonElement;
    }

    public WebElement getPostalCodeElement() {
        postalCodeElement = webDriver.findElement(postalCode);
        return postalCodeElement;
    }

    public WebElement getSubmitButton() {
        submitButton = webDriver.findElement(submit);
        return submitButton;
    }
}