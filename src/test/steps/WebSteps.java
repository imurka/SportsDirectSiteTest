package test.steps;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.pages.BagPage;
import test.pages.SportsDirectStartPage;

import java.util.List;


/**
 * Created by Iryna on 09.11.2016.
 */

public class WebSteps {
    WebDriver driver;
    SportsDirectStartPage sportsDirectStartPage;
    BagPage bag;

    public WebSteps(WebDriver driver) {
        this.driver = driver;
        sportsDirectStartPage = new SportsDirectStartPage(driver);
        bag = new BagPage(driver);
    }

    public void addItemToBag(String menuName, String categoryName, String productID) throws InterruptedException {
        sportsDirectStartPage.selectProductCategory(menuName, categoryName);
        sportsDirectStartPage.selectItemFromList(productID);
        sportsDirectStartPage.selectSizeShoes();
        sportsDirectStartPage.addToBag();
    }

    public void addQuantityOfItemInBag(String productID, int quantity) throws InterruptedException {
        sportsDirectStartPage.viewBag();
        bag.addItemToExistingProduct(productID, quantity);
        bag.updateBag();
    }

    public boolean verifyTotalPriceByProductID(String productID, double expectedTotalPrice) {
        double actualResult = bag.getTotalPriceExistingProduct(productID);
        return expectedTotalPrice == actualResult;
    }

    public void selectRegion() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("CountryRedirectStayFlag")));
        driver.findElement(By.id("CountryRedirectStayFlag")).click();
    }


    public void waitForAdvert(String advertName) {
        try {
            (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='" + advertName + "']")));
        } catch (TimeoutException exception) {
            WebElement element = driver.findElement(By.id("" + advertName + ""));
            Actions actions = new Actions(driver);
            actions.moveToElement(element, 1, 1).click().build().perform();
        }
    }

    public boolean verifyItemIsAddedToBag(String productId) {
        sportsDirectStartPage.viewBag();
        List<WebElement> listProductsInBag = driver.findElements(By.className("productTitle"));
        for (WebElement el : listProductsInBag) {
            if (el.getAttribute("href").contains(productId)) {
                return true;
            }
        }
        return false;
    }
}

