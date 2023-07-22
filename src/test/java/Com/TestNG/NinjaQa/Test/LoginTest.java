package Com.TestNG.NinjaQa.Test;

import Com.TestNG.NinjaQa.base.Base;
import com.Ninja.qa.pageobjects.AccountPage;
import com.Ninja.qa.pageobjects.HomePage;
import com.Ninja.qa.pageobjects.LoginPage;
import com.Ninja.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class LoginTest extends Base {
    public LoginTest(){
        super();
    }
// Author
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        //loadPropertiesfile();
        driver=initailizeBrowser(prop.getProperty("browserName"));
        HomePage homePage=new HomePage(driver);
       // driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        homePage.clickOnMyAccount();
      //  driver.findElement(By.linkText("Login")).click();
        homePage.SelectLoginOption();

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

   // @Test(priority=1,dataProvider = "ValidCredentialsSupplier")
    @Test(priority = 1)
    public void TC01verifyloginwithValidCredentials() {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
        loginPage.enterPassword(prop.getProperty("ValidPassword"));
        loginPage.ClickLoginButton();
        AccountPage accountPage=new AccountPage(driver);
       Assert.assertTrue(accountPage.getDisplayStatusOfEditAccountInformation(),"Edit your account information is not displayed");

    }
    /*
    @DataProvider(name = "ValidCredentialsSupplier")
    public Object[][] SupplyTestData(){
        Object[][] data=Utils.getTestDataFromExcel("Login");
        return data;
    }

     */


    @Test(priority=2)
    public void TC02loginwithInvalidCredentials() {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.ClickLoginButton();
        String actualwarningmessage = loginPage.WarningMessagedisplay();
        String expectedWarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
       Assert.assertTrue(actualwarningmessage.contains(expectedWarningmessage), "Expected Warning message is not dispalyed");
    }
    @Test(priority=3)
    public void TC03VeifyLoginvalidEmailAddressvalidpassword(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress("");
        loginPage.enterPassword(prop.getProperty("ValidPassword"));
        loginPage.ClickLoginButton();
        String actualwarningmessage = loginPage.WarningMessagedisplay();
        String expectedWarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualwarningmessage.contains(expectedWarningmessage), "Expected Warning message is not dispalyed");
    }
    @Test(priority=4)
    public void TC04VeifyLoginvalidEmailAddressvalidpassword(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.ClickLoginButton();
        String actualwarningmessage = loginPage.WarningMessagedisplay();
        String expectedWarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualwarningmessage.contains(expectedWarningmessage), "Expected Warning message is not dispalyed");
    }
    @Test(priority=5)
    public void TC05WithoutProvidingCredentials(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.ClickLoginButton();
        String actualwarningmessage = loginPage.WarningMessagedisplay();
        String expectedWarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualwarningmessage.contains(expectedWarningmessage), "Expected Warning message is not dispalyed");
    }

}

