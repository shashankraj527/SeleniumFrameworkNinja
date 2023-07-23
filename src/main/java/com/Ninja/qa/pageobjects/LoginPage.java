package com.Ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    @FindBy(id="input-email")
    private WebElement emailAddressField;

    @FindBy(id="input-password")
   private  WebElement passwordField;

    @FindBy(xpath="//input[@value='Login']")
   private  WebElement ClickLoginButton;
    @FindBy(xpath="//div[contains(@class , 'alert-dismissible')]")
    private WebElement NomatchforEMailPasswordWarning;


    //Constructor
    public LoginPage(WebDriver driver){
        this.driver=driver;
        //Instead of writingHomePage.class we are using this keyword
        PageFactory.initElements(driver,this);
    }
    //Actions
     public void enterEmailAddress(String emailtext){
         emailAddressField.sendKeys(emailtext);
     }
     public void enterPassword(String Passwordtxt){
         passwordField.sendKeys(Passwordtxt);

     }
     /*
     Changing Void to AccountPage
     Due Which Line WIll get reduced in  loginTest
      */
     public AccountPage ClickLoginButton(){

        ClickLoginButton.click();
        return new AccountPage(driver);
     }
     public AccountPage login(String emailtxt,String Passwordtxt){
         emailAddressField.sendKeys(emailtxt);
         passwordField.sendKeys(Passwordtxt);
         ClickLoginButton.click();
         return new AccountPage(driver);
     }
    public String WarningMessagedisplay(){
        String WarningText=NomatchforEMailPasswordWarning.getText();
        return WarningText;
    }
}
