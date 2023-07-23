package Com.TestNG.NinjaQa.Test;

import Com.TestNG.NinjaQa.base.Base;
import com.Ninja.qa.pageobjects.AccountSuccessPage;
import com.Ninja.qa.pageobjects.HomePage;
import com.Ninja.qa.pageobjects.RegisterPage;
import com.Ninja.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterClass extends Base {
   public WebDriver driver;
    RegisterPage reg;
    AccountSuccessPage asp;
    public RegisterClass(){

        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initailizeBrowser(prop.getProperty("browserName"));
        HomePage homePage=new HomePage(driver);
        homePage.navigateRegisterPage();
        reg = homePage.ClickOnRegister();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void verifyRegisteringAnAccountwithMandatoryFields(){
       asp= reg.RegisterWithMAdatory(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utils.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("ValidPassword"),prop.getProperty("ValidPassword"));
        String actualsuccessheading= asp.retrieveaccountSuccessPageHeading();
        Assert.assertEquals(actualsuccessheading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Message:Account Not Created");

    }
    @Test(priority = 2)
    public void verifyRegisteringAnAccountwithAllFields() {
        asp= reg.RegisterWithMAdatory(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utils.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("ValidPassword"),prop.getProperty("ValidPassword"));
        String actualsuccessheading= asp.retrieveaccountSuccessPageHeading();

        Assert.assertEquals(asp.retrieveaccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Message:Account Not Created");
    }
    @Test(priority = 3)
    public void verifyRegisteringAnAccountwithExistingEmailAddress() {
        asp= reg.RegisterWithMAdatory(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"), prop.getProperty("ValidEmail"), dataProp.getProperty("telephoneNumber"),prop.getProperty("ValidPassword"),prop.getProperty("ValidPassword"));
        Assert.assertTrue(reg.retrieveDuplicateEmailWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate not displayed");
    }
    @Test(priority = 4)
    public void verifyRegisteringAnAccountWithoutFillingDetails() {

        reg.ContinueButton();
        Assert.assertTrue(reg.DisplayStatusofWariningMessage(dataProp.getProperty("privacyPolicyWarning"),dataProp.getProperty("firstNameWarning"),dataProp.getProperty("lastNameWarning"),dataProp.getProperty("EmailWarning"),dataProp.getProperty("TelephoneWarning"),dataProp.getProperty("PasswordWarning")));
        }
}
