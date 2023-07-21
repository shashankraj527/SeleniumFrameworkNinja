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
    public RegisterClass(){

        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initailizeBrowser(prop.getProperty("browserName"));
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.linkText("Register")).click();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void verifyRegisteringAnAccountwithMandatoryFields(){
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataProp.getProperty("firstName"));
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(dataProp.getProperty("lastName"));
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utils.generateEmailWithTimeStamp());
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(dataProp.getProperty("telephoneNumber"));
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String actualsuccessheading=driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).getText();
        Assert.assertEquals(actualsuccessheading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Message:Account Not Created");
        System.out.println("actualsuccessheading");
    }
    @Test(priority = 2)
    public void verifyRegisteringAnAccountwithAllFields() {
       driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataProp.getProperty("firstName"));
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(dataProp.getProperty("lastName"));
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utils.generateEmailWithTimeStamp());
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(dataProp.getProperty("telephoneNumber"));
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.xpath("//label[text()='Yes']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String actualsuccessheading = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).getText();
        Assert.assertEquals(actualsuccessheading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Message:Account Not Created");
        System.out.println("actualsuccessheading");
    }
    @Test(priority = 3)
    public void verifyRegisteringAnAccountwithExistingEmailAddress() {
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataProp.getProperty("firstName"));
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(dataProp.getProperty("lastName"));
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("ValidEmail"));
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(dataProp.getProperty("telephoneNumber"));
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.xpath("//label[text()='Yes']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String actualWarningElment=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        Assert.assertTrue(actualWarningElment.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate not displayed");
    }
    @Test(priority = 4)
    public void verifyRegisteringAnAccountWithoutFillingDetails() {
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")));

        String actualFirstNameWarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
        Assert.assertEquals(actualFirstNameWarning,dataProp.getProperty("firstNameWarning"),"Message: FirstNameWaring message not displayed");

        String actualLastNameWarning=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
        Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("lastNameWarning"),"Message:LastName Warning not displayed");

        String actualEmailWarning=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
        Assert.assertEquals(actualEmailWarning,dataProp.getProperty("EmailWarning"),"Message:Email Warning not displayed");

        String actualTelephoneWarning=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
        Assert.assertEquals(actualTelephoneWarning,dataProp.getProperty("TelephoneWarning"),"Message:Telephone Warning not displayed");

        String actualPasswordWarning=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
        Assert.assertEquals(actualPasswordWarning,dataProp.getProperty("PasswordWarning"),"Message:Password Warning not displayed");
    }
}
