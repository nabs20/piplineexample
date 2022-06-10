package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import library.SelectBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GoogleImagesPage;
import pages.GoogleSearchPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GoogleSearchTest {

    WebDriver driver;
    GoogleSearchPage googleSearchPage;
    GoogleImagesPage googleImagesPage;

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;


    public static List<WebElement> pics = new ArrayList<>();

    @BeforeSuite
    public void setUpReport()
    {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/GoogleReport.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "igor.home-server.local");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Igor Adulyan");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting Google download pictures report");
        htmlReporter.config().setReportName("Google Search and Download Pictures Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

    }

    @BeforeTest
    public void launchBrowser(){

        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://google.com");
    }

    @Test(priority = 1)
    public void search_product_on_google_test()
    {
        test = extent.createTest("search_product_on_google_test", "Test Passed");
        googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.inputIntoSearchTextField("sunrise picture");
    }


    @Test(priority = 2)
    public void get_pics_from_google_test() throws IOException {

        test = extent.createTest("get_pics_from_google_test", "Test Passed");
        googleImagesPage = new GoogleImagesPage(driver);
        googleImagesPage.clickOnImageLink_Google();
        pics = googleImagesPage.getWebElements();

        Assert.assertTrue(pics.size() > 0);

        WebElement webElement = pics.get(0);
        String attribute = webElement.getAttribute("src");
        Assert.assertNotNull(attribute);
        System.out.println(attribute);
        Reporter.log(attribute);

        driver.get(attribute);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("img")));

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/img.png"));

    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}
