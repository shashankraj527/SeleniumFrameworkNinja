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
    WebDriver driver;
    public RegisterClass(){

        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initailizeBrowser(prop.getProperty("browserName"));
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMyAccount();
        homePage.ClickOnRegister();

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void verifyRegisteringAnAccountwithMandatoryFields(){
        RegisterPage reg=new RegisterPage(driver);
        reg.EnterFirstName(dataProp.getProperty("firstName"));
        reg.EnterLastName(dataProp.getProperty("lastName"));
        reg.EnterEmailAddress(Utils.generateEmailWithTimeStamp());
        reg.EnterTelePhone(dataProp.getProperty("telephoneNumber"));
        reg.EnterPasswordField(prop.getProperty("ValidPassword"));
        reg.confirmpasswordfield(prop.getProperty("ValidPassword"));
        reg.Privacypolicyfield();
        reg.ContinueButton();
        AccountSuccessPage asp=new AccountSuccessPage(driver);
        String actualsuccessheading= asp.retrieveaccountSuccessPageHeading();
        Assert.assertEquals(actualsuccessheading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Message:Account Not Created");
    }
    @Test(priority = 2)
    public void verifyRegisteringAnAccountwithAllFields() {
        RegisterPage reg=new RegisterPage(driver);
        reg.EnterFirstName(dataProp.getProperty("firstName"));
        reg.EnterLastName(dataProp.getProperty("lastName"));
        reg.EnterEmailAddress(Utils.generateEmailWithTimeStamp());
        reg.EnterTelePhone(dataProp.getProperty("telephoneNumber"));
        reg.EnterPasswordField(prop.getProperty("ValidPassword"));
        reg.confirmpasswordfield(prop.getProperty("ValidPassword"));
        reg.NewsLetterField();
        reg.Privacypolicyfield();
        reg.ContinueButton();
        AccountSuccessPage asp=new AccountSuccessPage(driver);
        String actualsuccessheading= asp.retrieveaccountSuccessPageHeading();
        Assert.assertEquals(actualsuccessheading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Message:Account Not Created");
    }
    @Test(priority = 3)
    public void verifyRegisteringAnAccountwithExistingEmailAddress() {
        RegisterPage reg=new RegisterPage(driver);
        reg.EnterFirstName(dataProp.getProperty("firstName"));
        reg.EnterLastName(dataProp.getProperty("lastName"));
        reg.EnterEmailAddress(prop.getProperty("ValidEmail"));
        reg.EnterTelePhone(dataProp.getProperty("telephoneNumber"));
        reg.EnterPasswordField(prop.getProperty("ValidPassword"));
        reg.confirmpasswordfield(prop.getProperty("ValidPassword"));
        reg.NewsLetterField();
        reg.Privacypolicyfield();
        reg.ContinueButton();
        AccountSuccessPage asp=new AccountSuccessPage(driver);
        String actualsuccessheading= asp.retrieveaccountSuccessPageHeading();
        String actualWarningElment= reg.retrieveDuplicateEmailWarning();
        Assert.assertTrue(actualWarningElment.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate not displayed");
    }
    @Test(priority = 4)
    public void verifyRegisteringAnAccountWithoutFillingDetails() {
        RegisterPage reg=new RegisterPage(driver);
        reg.ContinueButton();

        String actualPrivacyPolicyWarning = reg.PrivacyPolicyWarningfield();
        Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")));

        String actualFirstNameWarning= reg.FirstNameWarningField();
        Assert.assertEquals(actualFirstNameWarning,dataProp.getProperty("firstNameWarning"),"Message: FirstNameWaring message not displayed");

        String actualLastNameWarning= reg.LastNameWarningField();
        Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("lastNameWarning"),"Message:LastName Warning not displayed");

        String actualEmailWarning=reg.EmailWarningField();
        Assert.assertEquals(actualEmailWarning,dataProp.getProperty("EmailWarning"),"Message:Email Warning not displayed");

        String actualTelephoneWarning=reg.TelephoneField();
        Assert.assertEquals(actualTelephoneWarning,dataProp.getProperty("TelephoneWarning"),"Message:Telephone Warning not displayed");

        String actualPasswordWarning=reg.PasswordWarning();
        Assert.assertEquals(actualPasswordWarning,dataProp.getProperty("PasswordWarning"),"Message:Password Warning not displayed");
    }
}
