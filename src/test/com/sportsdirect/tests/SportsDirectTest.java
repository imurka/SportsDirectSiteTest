package test.com.sportsdirect.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.steps.WebSteps;

import java.util.concurrent.TimeUnit;

/**
 * Created by Iryna on 09.11.2016.
 */
public class SportsDirectTest {
    WebDriver driver;
    WebSteps webSteps;


    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:/Homework/SportsDirectTest/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.sportsdirect.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webSteps = new WebSteps(driver);
    }

    @AfterClass
    public void shutDown() {
        if (driver != null) {
//            driver.quit();
            driver.quit();
        }
    }

    @Test
    public void verifyTotalPriceOfItemsInBag() throws InterruptedException {
        webSteps.selectRegion();
        webSteps.waitForAdvert("advertPopup");
        webSteps.addItemToBag("LADIES", "Running Shoes", "21400379");
        webSteps.waitForAdvert("BasketModal");
        Assert.assertTrue(webSteps.verifyItemIsAddedToBag("21400379"));
        webSteps.addItemToBag("KIDS", "Tennis Shoes", "03503102");
        Assert.assertTrue(webSteps.verifyItemIsAddedToBag("03503102"));
        webSteps.addQuantityOfItemInBag("21400379", 1);
        Assert.assertTrue(webSteps.verifyTotalPriceByProductID("21400379", 49.98));
    }

}
