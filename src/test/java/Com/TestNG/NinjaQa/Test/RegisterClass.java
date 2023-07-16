package Com.TestNG.NinjaQa.Test;

import Com.TestNG.NinjaQa.base.Base;
import com.Ninja.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterClass extends Base {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver=initailizeBrowser("firefox");
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Register")).click();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void verifyRegisteringAnAccountwithMandatoryFields(){
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Prabha");
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("as");
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utils.generateEmailWithTimeStamp());
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123456987");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String actualsuccessheading=driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).getText();
        Assert.assertEquals(actualsuccessheading,"Your Account Has Been Created!","Message:Account Not Created");
        System.out.println("actualsuccessheading");
    }
    @Test(priority = 2)
    public void verifyRegisteringAnAccountwithAllFields() {
       driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Prabha");
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("as");
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utils.generateEmailWithTimeStamp());
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123456987");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345");
        driver.findElement(By.xpath("//label[text()='Yes']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String actualsuccessheading = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).getText();
        Assert.assertEquals(actualsuccessheading, "Your Account Has Been Created!", "Message:Account Not Created");
        System.out.println("actualsuccessheading");
    }
    @Test(priority = 3)
    public void verifyRegisteringAnAccountwithExistingEmailAddress() {
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Prabha");
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("as");
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("kalyanshaan25@gmail.com");
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123456987");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345");
        driver.findElement(By.xpath("//label[text()='Yes']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String actualWarningElment=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        Assert.assertTrue(actualWarningElment.contains("Warning: E-Mail Address is already registered!"),"Warning message regarding duplicate not displayed");
    }
    @Test(priority = 4)
    public void verifyRegisteringAnAccountWithoutFillingDetails() {
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"));

        String actualFirstNameWarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
        Assert.assertEquals(actualFirstNameWarning,"First Name must be between 1 and 32 characters!","Message: FirstNameWaring message not displayed");

        String actualLastNameWarning=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
        Assert.assertEquals(actualLastNameWarning,"Last Name must be between 1 and 32 characters!","Message:LastName Warning not displayed");

        String actualEmailWarning=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
        Assert.assertEquals(actualEmailWarning,"E-Mail Address does not appear to be valid!","Message:Email Warning not displayed");

        String actualTelephoneWarning=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
        Assert.assertEquals(actualTelephoneWarning,"Telephone must be between 3 and 32 characters!","Message:Telephone Warning not displayed");

        String actualPasswordWarning=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
        Assert.assertEquals(actualPasswordWarning,"Password must be between 4 and 20 characters!","Message:Password Warning not displayed");
    }
}
