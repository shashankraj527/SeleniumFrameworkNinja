package Com.TestNG.NinjaQa.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class LoginTest {
// Author
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        String browsername="firefox";
        if(browsername.equals("chrome")){
            driver=new ChromeDriver();

            }
            else if(browsername.equals("firefox"))
            {
                driver=new FirefoxDriver();
            }
        else if(browsername.equals("edge"))
        {
            driver=new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Login")).click();

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

    @Test(priority=1)
    public void TC01verifyloginwithValidCredentials() {
        driver.findElement(By.id("input-email")).sendKeys("kalyanshaan2@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit Your Account information when not displayed ");



    }

    @Test(priority=2)
    public void TC02loginwithInvalidCredentials() {
        driver.findElement(By.id("input-email")).sendKeys("kalyanshaan25" + generateTimeStamp() + "@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        String actualwarningmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedWarnifmessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualwarningmessage.contains(expectedWarnifmessage), "Expected Warning message is not dispalyed");

    }
    @Test(priority=3)
    public void TC03VeifyLoginvalidEmailAddressinvalidpassword(){
        driver.findElement(By.id("input-email")).sendKeys("kalyanshaan2501@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("abcd");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        String actualwarningmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedWarnifmessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualwarningmessage.contains(expectedWarnifmessage), "Expected Warning message is not dispalyed");

    }
    @Test(priority=4)
    public void TC04VeifyLogininvalidEmailAddressvalidpassword(){
        driver.findElement(By.id("input-email")).sendKeys("abcd@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        String actualwarningmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedWarnifmessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualwarningmessage.contains(expectedWarnifmessage), "Expected Warning message is not dispalyed");
    }
    @Test(priority=5)
    public void TC05WithoutProvidingCredentials(){
        driver.findElement(By.id("input-email")).sendKeys("");
        driver.findElement(By.id("input-password")).sendKeys("");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        String actualwarningmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedWarnifmessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualwarningmessage.contains(expectedWarnifmessage), "Expected Warning message is not dispalyed");

    }
    public String generateTimeStamp() {
        Date date = new Date();
        return date.toString().replace(" ", "_").replace(":", "_");

    }
}

