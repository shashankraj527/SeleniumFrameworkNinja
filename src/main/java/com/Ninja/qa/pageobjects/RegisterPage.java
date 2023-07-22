package com.Ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;
    @FindBy(id="input-firstname")
    private WebElement firstnameField;
    @FindBy(id="input-lastname")
    private WebElement lastnamefield;

    @FindBy(id="input-email")
    private WebElement EmailAddressField;

    @FindBy(id="input-telephone")
    private WebElement TelephoneField;

    @FindBy(id="input-password")
    private WebElement PasswordField;

    @FindBy(id="input-confirm")
    private WebElement ConfirmPasswordField;

    @FindBy(name = "agree")
    private WebElement privacypolicyfield;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement ContinueButton;

    @FindBy(xpath="//input[@name='newsletter'][@value=1]")
    private WebElement YesNewsLetter;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement DuplicateWarningMessage;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement Privacypolicyfield;

    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement FirstNameWarningField;

    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    private WebElement LastNameWarningField;

    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement EmailWarningField;

    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    private WebElement TelephoneWarningField;

    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
    private WebElement PasswordWarningField;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        //Instead of writingHomePage.class we are using this keyword
        PageFactory.initElements(driver, this);
    }
        public void EnterFirstName(String firstnameText){
            firstnameField.sendKeys(firstnameText);
        }
        public void EnterLastName(String lastNameText){
            lastnamefield.sendKeys(lastNameText);
        }

        public void EnterEmailAddress(String emailText){
            EmailAddressField.sendKeys(emailText);
        }
        public void EnterTelePhone(String TelephoneText){
            TelephoneField.sendKeys(TelephoneText);
        }
        public void EnterPasswordField(String PasswordText){
            PasswordField.sendKeys(PasswordText);
        }
        public void confirmpasswordfield(String Passwordtxt){
            ConfirmPasswordField.sendKeys(Passwordtxt);
        }
        public void NewsLetterField(){
            YesNewsLetter.click();
        }
        public void Privacypolicyfield(){
            privacypolicyfield.click();
        }
        public void ContinueButton(){
            ContinueButton.click();
        }
        public String retrieveDuplicateEmailWarning(){
           String retrieveDuplicateEmailWarningText=DuplicateWarningMessage.getText();
           return retrieveDuplicateEmailWarningText;
        }
        public String PrivacyPolicyWarningfield(){
          String  PrivacyPolicyWarningfieldText=Privacypolicyfield.getText();
          return PrivacyPolicyWarningfieldText;
        }
        public String  FirstNameWarningField(){
           String FirstNameWarningFieldText=FirstNameWarningField.getText();
           return FirstNameWarningFieldText;
        }
        public String LastNameWarningField(){
        String lastNameWarningFieldText=LastNameWarningField.getText();
        return lastNameWarningFieldText;
        }
        public String EmailWarningField(){
        String EmailWarningFieldText=EmailWarningField.getText();
        return EmailWarningFieldText;
        }

        public String TelephoneField(){
        String TelephoneWarningFieldText=TelephoneWarningField.getText();
        return TelephoneWarningFieldText;
        }
        public String PasswordWarning(){
        String passwordWarningText=PasswordWarningField.getText();
        return passwordWarningText;
        }
    }

