package Avit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AvicTests {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://avic.ua/");

    }


    @Test(priority = 1)
    public void checkThatUrlContainsSearchQuery(){
        driver.findElement(By.xpath("//input[@id ='input_search']")).clear();
        driver.findElement(By.xpath("//input[@id ='input_search']")).sendKeys("iPhone 11", Keys.ENTER);
        Assert.assertTrue(driver.getCurrentUrl().contains("query=iPhone"));
    }

    @Test
    public void checkThatSearchResultContainThatWord(){
        driver.findElement(By.xpath("//input[@id ='input_search']")).clear();
        driver.findElement(By.xpath("//input[@id ='input_search']")).sendKeys("iPhone 11", Keys.ENTER);
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class ='item-prod col-lg-3']"));
                for (WebElement webElement : elementList) {
                    Assert.assertTrue(webElement.getText().contains("iPhone 12"));
        }
    }

    @AfterMethod
    public void terDown() {
        driver.close();

    }
}
