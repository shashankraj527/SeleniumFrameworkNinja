package Com.TestNG.NinjaQa.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class LoginTest {
// Author

    @Test(priority=1)
    public void TC01verifyloginwithValidCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("kalyanshaan25@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit Your Account information when not displayed ");

        driver.quit();

    }

    @Test(priority=2)
    public void TC02loginwithInvalidCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("kalyanshaan25" + generateTimeStamp() + "@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        String actualwarningmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedWarnifmessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualwarningmessage.contains(expectedWarnifmessage), "Expected Warning message is not dispalyed");
        driver.quit();
    }
    @Test(priority=3)
    public void TC03VeifyLoginvalidEmailAddressinvalidpassword(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("kalyanshaan2501@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("abcd");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        String actualwarningmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedWarnifmessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualwarningmessage.contains(expectedWarnifmessage), "Expected Warning message is not dispalyed");
        driver.quit();
    }
    @Test(priority=4)
    public void TC04VeifyLogininvalidEmailAddressvalidpassword(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("abcd@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        String actualwarningmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedWarnifmessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualwarningmessage.contains(expectedWarnifmessage), "Expected Warning message is not dispalyed");
        driver.quit();
    }
    @Test(priority=5)
    public void TC05WithoutProvidingCredentials(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("");
        driver.findElement(By.id("input-password")).sendKeys("");
        driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]")).click();
        String actualwarningmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedWarnifmessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualwarningmessage.contains(expectedWarnifmessage), "Expected Warning message is not dispalyed");
        driver.quit();
    }
    public String generateTimeStamp() {
        Date date = new Date();
        return date.toString().replace(" ", "_").replace(":", "_");

    }
}

