package com.debugnuggets.flipkz;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

import static com.debugnuggets.flipkz.util.BaseTest.extent;

public class TestClassExample {
    private WebDriver webDriver;
    private String urlFlip = "https://www.flip.kz/";
    private String registerForward = "Войти";
    private String registerXpath = "//div[@data-url='/user?reg']";
    private String registerNameText = "";
    private String registerNameXpath = "//input[@name='name']";
    private String registerPhoneText1 = "8";
    private String registerPhoneText2 = "+7";
    private String registerPhoneXpath = "//input[@name='phone']";
    private String registerEmailText = "";
    private String registerEmailXpath = "//input[@name='email']";
    private String registerPasswordText = "";
    private String registerPasswordXpath = "//input[@id='password_reg']";
    private String emailEnding = "@gmail.com";
    private String space = " ";
    private String workingNumber = "+7 705 296 60 66";

    ExtentHtmlReporter htmlReporter;

    ExtentReports reports;

    ExtentTest test;

    @BeforeTest
    public void startReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

//    @Test
//    public void firstRegistrationTest()
//    {
//        test = extent.createTest("Test Case 1", "The test case 1 has passed");
//
//        WebDriverManager.chromedriver().setup();
//        webDriver = new ChromeDriver();
//        webDriver.navigate().to(urlFlip);
//        WebElement webElement1 =  webDriver.findElement(By.linkText(registerForward));
//        webElement1.click();
//
//        WebElement webElement2 = webDriver.findElement(By.xpath(registerXpath));
//        webElement2.click();
//
//        Random r = new Random();
//        WebElement webElement3 = webDriver.findElement(By.xpath(registerNameXpath));
//        for (int i = 0; i<7; i++)
//        {
//            char c = (char)(r.nextInt(26) + 'a');
//            registerNameText+=c;
//        }
//        webElement3.sendKeys(registerNameText);
//
//        WebElement webElement4 = webDriver.findElement(By.xpath(registerPhoneXpath));
//        for (int i = 0; i<10; i++)
//        {
//            registerPhoneText1+=r.nextInt(9);
//        }
//        webElement4.sendKeys(registerPhoneText1);
//
//        WebElement webElement5 = webDriver.findElement(By.xpath(registerEmailXpath));
//        for (int i = 0; i<7; i++)
//        {
//            char c = (char)(r.nextInt(26) + 'a');
//            registerEmailText+=c;
//        }
//        registerEmailText+=emailEnding;
//        webElement5.sendKeys(registerEmailText);
//
//        WebElement webElement6 = webDriver.findElement(By.xpath(registerPasswordXpath));
//        for (int i = 0; i<7; i++)
//        {
//            char c = (char)(r.nextInt(26) + 'a');
//            registerPasswordText+=c;
//        }
//        webElement6.sendKeys(registerPasswordText);
//
//        //a[href='https://www.flip.kz/user?reg']
//    }
    @Test
    public void secondRegistrationTest()
    {
        test = extent.createTest("Test Case 2", "The test case 2 has passed");

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(urlFlip);
        WebElement webElement1 =  webDriver.findElement(By.linkText(registerForward));
        webElement1.click();

        WebElement webElement2 = webDriver.findElement(By.xpath(registerXpath));
        webElement2.click();

        Random r = new Random();
        WebElement webElement3 = webDriver.findElement(By.xpath(registerNameXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerNameText+=c;
        }
        webElement3.sendKeys(registerNameText);

        WebElement webElement4 = webDriver.findElement(By.xpath(registerPhoneXpath));
        for (int i = 0; i<10; i++)
        {
            registerPhoneText2+=r.nextInt(9);
        }
        webElement4.sendKeys(registerPhoneText2);

        WebElement webElement5 = webDriver.findElement(By.xpath(registerEmailXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerEmailText+=c;
        }
        registerEmailText+=emailEnding;
        webElement5.sendKeys(registerEmailText);

        WebElement webElement6 = webDriver.findElement(By.xpath(registerPasswordXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerPasswordText+=c;
        }
        webElement6.sendKeys(registerPasswordText);


        //a[href='https://www.flip.kz/user?reg']
    }

    @Test
    public void thirdRegistrationTest()
    {
        test = extent.createTest("Test Case 3", "The test case 3 has passed");

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(urlFlip);
        WebElement webElement1 =  webDriver.findElement(By.linkText(registerForward));
        webElement1.click();

        WebElement webElement2 = webDriver.findElement(By.xpath(registerXpath));
        webElement2.click();

        Random r = new Random();
        WebElement webElement3 = webDriver.findElement(By.xpath(registerNameXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerNameText+=c;
        }
        webElement3.sendKeys(registerNameText);

        WebElement webElement4 = webDriver.findElement(By.xpath(registerPhoneXpath));
        for (int i = 0; i<10; i++)
        {
            if(i==0 || i==3 || i==6 || i==8)
                registerPhoneText2+=space;
            registerPhoneText2+=r.nextInt(9);

        }
        webElement4.sendKeys(registerPhoneText2);

        WebElement webElement5 = webDriver.findElement(By.xpath(registerEmailXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerEmailText+=c;
        }
        registerEmailText+=emailEnding;
        webElement5.sendKeys(registerEmailText);

        WebElement webElement6 = webDriver.findElement(By.xpath(registerPasswordXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerPasswordText+=c;
        }
        webElement6.sendKeys(registerPasswordText);


        //a[href='https://www.flip.kz/user?reg']
    }

    @Test
    public void fourthRegistrationTest()
    {
        test = extent.createTest("Test Case 4", "The test case 4 has passed");

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(urlFlip);
        WebElement webElement1 =  webDriver.findElement(By.linkText(registerForward));
        webElement1.click();

        WebElement webElement2 = webDriver.findElement(By.xpath(registerXpath));
        webElement2.click();

        Random r = new Random();
        WebElement webElement3 = webDriver.findElement(By.xpath(registerNameXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerNameText+=c;
        }
        webElement3.sendKeys(registerNameText);

        WebElement webElement4 = webDriver.findElement(By.xpath(registerPhoneXpath));
        webElement4.sendKeys(workingNumber);

        WebElement webElement5 = webDriver.findElement(By.xpath(registerEmailXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerEmailText+=c;
        }
        registerEmailText+=emailEnding;
        webElement5.sendKeys(registerEmailText);

        WebElement webElement6 = webDriver.findElement(By.xpath(registerPasswordXpath));
        for (int i = 0; i<7; i++)
        {
            char c = (char)(r.nextInt(26) + 'a');
            registerPasswordText+=c;
        }
        webElement6.sendKeys(registerPasswordText);

    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getTestName());
        }
        else {
            test.log(Status.SKIP, result.getTestName());
        }
    }

    @AfterTest
    public void tearDown() {
        extent.flush();
    }
}
