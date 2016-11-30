package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Created by Ирина on 30.11.2016.
 */
public class BagPage {
    WebDriver driver;

    public BagPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void updateBag() {
        driver.findElement(By.xpath("//a[@class='NewUpdateQuant']")).click();
    }

    public void addItemToExistingProduct(String productId, int quantity) {
        List<WebElement> listProductsInBag = driver.findElements(By.className("productTitle"));
        for (WebElement el : listProductsInBag) {
            if (el.getAttribute("href").contains(productId)) {
                for (int i = 0; i < quantity; i++) {
                    el.findElement(By.xpath("//a[@class='BasketQuantBut s-basket-plus-button']")).click();
                }
            }
        }
    }

    public double getTotalPriceExistingProduct(String productId) {
        double value = 0;
        List<WebElement> listProducts = driver.findElements(By.className("productTitle"));
        for (WebElement item : listProducts) {
            if (item.getAttribute("href").contains(productId)) {
                value = Double.parseDouble(item.findElement(By.xpath("//*[@class='itemtotalprice']/span[2]")).getText().substring(1));
            }
        }
        return value;
    }

}

