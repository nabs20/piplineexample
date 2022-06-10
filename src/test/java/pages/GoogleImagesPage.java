package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleImagesPage {

    WebDriver driver;

/*    By clickOnImageLink = By.linkText("Images");
    By getWebElements = By.xpath("//a/div/img");

    public GoogleImagesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnImageLink_Google()
    {
        driver.findElement(clickOnImageLink).click();
    }

    public List<WebElement> getWebElements()
    {
        return driver.findElements(getWebElements);
    }*/

    @FindBy(linkText = "Images")
    WebElement clickOnImageLink;

    @FindBy(xpath = "//a/div/img")
    List <WebElement> getWebElements;

    public GoogleImagesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnImageLink_Google()
    {
        clickOnImageLink.click();
    }


    public List<WebElement> getWebElements()
    {
        return getWebElements;
    }
}
