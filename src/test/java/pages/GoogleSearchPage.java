package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {

    WebDriver driver;

    By searchTextField = By.name("q");

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputIntoSearchTextField(String arg1)
    {
        driver.findElement(searchTextField).sendKeys(arg1 + Keys.ENTER);
    }
}
