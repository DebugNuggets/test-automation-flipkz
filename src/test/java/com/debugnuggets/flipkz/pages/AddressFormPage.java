package com.debugnuggets.flipkz.pages;

import com.debugnuggets.flipkz.util.DBUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static com.debugnuggets.flipkz.constants.NameConstants.*;


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
    private static final String SELECT_EVERYTHING_RIGHT = "select * from user_credential where id = 1";
    private static final String SELECT_EVERYTHING_WRONG = "select * from user_credential where id = 2";
    private static List<Map<String, Object>> resultOfQuery;
    private static DBUtil dbUtil = DBUtil.getInstance();

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

    public void submitRightAddress() {
        resultOfQuery = dbUtil.getQueryResultMap(SELECT_EVERYTHING_RIGHT);
        instance.getFullNameElement().sendKeys((String) resultOfQuery.get(0).get("full_name"));
        instance.getCityElement().clear();
        instance.getCityElement().sendKeys((String) resultOfQuery.get(0).get("city"));
        instance.getAddressElement().sendKeys((String) resultOfQuery.get(0).get("address"));
        instance.getBuildingElement().sendKeys((String) resultOfQuery.get(0).get("building"));
        instance.getFlatElement().sendKeys((String) resultOfQuery.get(0).get("flat"));
        instance.getEntranceElement().sendKeys((String) resultOfQuery.get(0).get("entrance"));
        instance.getFloorElement().sendKeys((String) resultOfQuery.get(0).get("floor"));
        instance.getPostalCodeElement().clear();
        instance.getPostalCodeElement().sendKeys((String) resultOfQuery.get(0).get("postal_code"));
        instance.getAddressButtonElement().click();
        instance.getSubmitButton().click();
    }

    public void submitWrongAddress()
    {
        resultOfQuery = dbUtil.getQueryResultMap(SELECT_EVERYTHING_WRONG);
        instance.getFullNameElement().sendKeys((String) resultOfQuery.get(0).get("full_name"));
        instance.getCityElement().clear();
        instance.getCityElement().sendKeys((String) resultOfQuery.get(0).get("city"));
        instance.getAddressElement().sendKeys((String) resultOfQuery.get(0).get("address"));
        instance.getBuildingElement().sendKeys((String) resultOfQuery.get(0).get("building"));
        instance.getFlatElement().sendKeys((String) resultOfQuery.get(0).get("flat"));
        instance.getEntranceElement().sendKeys((String) resultOfQuery.get(0).get("entrance"));
        instance.getFloorElement().sendKeys((String) resultOfQuery.get(0).get("floor"));
        instance.getPostalCodeElement().clear();
        instance.getPostalCodeElement().sendKeys((String) resultOfQuery.get(0).get("postal_code"));
        instance.getAddressButtonElement().click();
    }
}