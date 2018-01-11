package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CommonAPI {
    public static WebDriver driver = null;

    @Test
    public void setUp()throws InterruptedException{
        System.setProperty("webdriver.gecko.driver","../Generic/drivers/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(35,TimeUnit.SECONDS);

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Book");

        Thread.sleep(5000);
        Assert.assertTrue(driver.getTitle().matches("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more"), "Invalid Credentials");
        System.out.println("Search successful.");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @DataProvider(name = "testdata")
    public Object[][] TestDataFeed(){
        ReadExcelFile config = new ReadExcelFile("");
        int rows = config.getRowCount(0);
        Object[][] credentials = new Object[rows][2];
        for (int i = 0; i < rows; i++){
            credentials[i][0] = config.getData(0,i,0);
            credentials[i][1] = config.getData(0,i,0);
        }
        return credentials;
    }
}