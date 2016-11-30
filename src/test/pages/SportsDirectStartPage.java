package test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


/**
 * Created by Ирина on 06.11.2016.
 */
public class SportsDirectStartPage {
    WebDriver driver;

    @FindBy(how = How.ID, using = "bagQuantityContainer")
    public WebElement viewBag;

    @FindBy(how = How.CLASS_NAME, using = "addToBag")
    public WebElement addToBagButton;

    public SportsDirectStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectProductCategory(String menuName, String categoryName) throws InterruptedException {

        List<WebElement> topMenu = driver.findElements(By.xpath("//nav[@id='topMenu']/ul/li/a"));
        for (WebElement option : topMenu) {
            if (option.getText().equals(menuName)) {
                new Actions(driver).moveToElement(option).perform();
                WebElement menuItem = driver.findElement(By.linkText(categoryName));
                menuItem.click();
                break;
            }
        }
    }

    public void selectItemFromList(String productID) {
        driver.findElement(By.xpath("//li[@li-productid='" + productID + "']")).click();
    }

    public void selectSizeShoes() {
        List<WebElement> allSizes = driver.findElements(By.xpath("//*[@class='sizeButtons hidden-xs']/li"));
        for (WebElement size : allSizes) {
            if (size.getAttribute("title").contains("Click to select")) {
                size.click();
                break;
            }
        }
    }

    public void addToBag() {
        addToBagButton.click();
    }

    public BagPage viewBag() {
        viewBag.click();
        return new BagPage(driver);
    }


}
