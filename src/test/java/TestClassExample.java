import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Random;

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
    @Test
    public void firstRegistrationTest()
    {
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
            registerPhoneText1+=r.nextInt(9);
        }
        webElement4.sendKeys(registerPhoneText1);

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
    public void secondRegistrationTest()
    {
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


        //a[href='https://www.flip.kz/user?reg']
    }
}
