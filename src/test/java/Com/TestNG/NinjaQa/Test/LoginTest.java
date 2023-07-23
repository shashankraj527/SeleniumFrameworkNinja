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
    LoginPage loginPage;
    @BeforeMethod
    public void setup(){
        //loadPropertiesfile();
        driver=initailizeBrowser(prop.getProperty("browserName"));
        HomePage homePage=new HomePage(driver);
        loginPage=homePage.navigateToLoginPage();
        /*
        Due Which repeative line
         LoginPage loginPage=new LoginPage(driver);
            Not need because it created globally
         */
         loginPage = homePage.SelectLoginOption();

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

   // @Test(priority=1,dataProvider = "ValidCredentialsSupplier")
    @Test(priority = 1)
    public void TC01verifyloginwithValidCredentials() {
        /*
        LoginPage loginPage;--->Need to set it to Global
         */
        loginPage.login(Utils.generateEmailWithTimeStamp(),dataProp.getProperty("ValidPassword")); /*
        AccountPage accountPage=new AccountPage(driver);
        Not need again now

         */
        //AccountPage accountPage=new AccountPage(driver);
       //Assert.assertTrue(accountPage.getDisplayStatusOfEditAccountInformation(),"Edit your account information is not displayed");

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
        loginPage.login(Utils.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.WarningMessagedisplay().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not dispalyed");
    }
    @Test(priority=3)
    public void TC03VeifyLoginvalidEmailAddressvalidpassword(){
        loginPage.login("",dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.WarningMessagedisplay().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not dispalyed");
    }
    @Test(priority=4)
    public void TC04VeifyLoginvalidEmailAddressvalidpassword(){
        loginPage.login(prop.getProperty("invalidEMail"), dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.WarningMessagedisplay().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not dispalyed");
    }
    @Test(priority=5)
    public void TC05WithoutProvidingCredentials(){

        loginPage.ClickLoginButton();
         Assert.assertTrue(loginPage.WarningMessagedisplay().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not dispalyed");
    }

}

