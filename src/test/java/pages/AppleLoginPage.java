package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AppleLoginPage {

    WebDriver driver;

    By frame = By.xpath("*//div/iframe[@id='aid-auth-widget-iFrame']");
    By loginTextField = By.id("account_name_text_field");
    By buttonForLoginAndSignIn = By.id("sign-in");
    By passwordTextField =By.id("password_text_field");
    By errorMessage = By.id("errMsg");

    public AppleLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchFrames()
    {
        driver.switchTo().frame(driver.findElement(frame));
    }

    public void inputLogin(String arg)
    {
        driver.findElement(loginTextField).sendKeys(arg);
    }

    public void clickButtonForLoginAndSignIn()
    {
        driver.findElement(buttonForLoginAndSignIn).click();
    }

    public void inputPassword(String arg)
    {
        driver.findElement(passwordTextField).sendKeys(arg);
    }

    public String getErrorMessage()
    {
       return driver.findElement(errorMessage).getText();
    }

}
