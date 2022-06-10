package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import library.SelectBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AppleLoginPage;
import pages.AppleMainPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AppleTest {

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    WebDriver driver;
    AppleMainPage appleMainPage;
    AppleLoginPage appleLoginPage;

    @BeforeSuite
    public void reportSetUp()
    {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output" +
                "/AppleReport.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "igor.home-server.local");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Igor Adulyan");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting Apple login report");
        htmlReporter.config().setReportName("Apple main and login pages report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }


    @BeforeTest
    public void setUpBrowser()
    {
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("http://apple.com");
    }

    @Test(priority = 1)
    public void click_on_bag_test() throws IOException {
        test = extent.createTest("click_on_bag_test", "Pass");
        test.addScreenCaptureFromPath("/Users/igoradulyan/SpingPerScholas/POMExample/test-output/screenshots/login_page.png");
        appleMainPage = new AppleMainPage(driver);
        appleMainPage.clickOnShoppingBag();
        appleMainPage.clickOnSignInLink();
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/screenshots/main_page.jpg"));
    }

    @Test(priority = 2)
    public void login_into_account_with_wrong_password_test() throws IOException {
        test = extent.createTest("login_into_account_with_wrong_password_test", "Pass");
        test.addScreenCaptureFromPath("/Users/igoradulyan/SpingPerScholas/POMExample/test-output/screenshots/main_page.png");
        appleLoginPage = new AppleLoginPage(driver);
        appleLoginPage.switchFrames();
        appleLoginPage.inputLogin("i.adulian@gmail.com");
        appleLoginPage.clickButtonForLoginAndSignIn();
        appleLoginPage.inputPassword("edefrfendenjdb");
        appleLoginPage.clickButtonForLoginAndSignIn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errMsg")));

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/screenshots/login_page.jpg"));

        String expected = "Your Apple ID or password was incorrect.";
        Assert.assertEquals(expected, appleLoginPage.getErrorMessage());
    }
    @AfterSuite
    public void tearDown()
    {

        extent.flush();
    }

}
