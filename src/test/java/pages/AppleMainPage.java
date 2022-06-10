package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppleMainPage {

    WebDriver driver;

    @FindBy(linkText = "Shopping Bag")
    WebElement shoppingBag;

    @FindBy(linkText = "Sign in")
    WebElement signInLink;

    /**
     * Note: with using page factory you need
     * 1) to add driver as constructor argument
     * then add PageFactory into constructor
     * 2) initialize factory and provide 2 arguments
     *
     */
    public AppleMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnShoppingBag()
    {
        shoppingBag.click();
    }

    public void clickOnSignInLink()
    {
        signInLink.click();
    }
}
