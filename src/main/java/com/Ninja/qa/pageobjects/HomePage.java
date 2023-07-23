package com.Ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    //Webelement---Objects
    @FindBy(xpath = "//span[text()='My Account']")
  private   WebElement myAccountDropMenu;

    @FindBy(linkText = "Login")
   private WebElement Loginoption;

    @FindBy(linkText ="Register")
    private WebElement RegisterOption;
    @FindBy(xpath ="//div[@id='search']/descendant::input")
    private WebElement EnterSearchBoxFielddata;

    @FindBy(xpath = "//div[@id='search']/descendant::button")
    private WebElement clickSearchBoxFieldbtn;


    //Create Constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
        //Instead of writingHomePage.class we are using this keyword
        PageFactory.initElements(driver,this);

    }
    //Actions
    public void clickOnMyAccount(){
        myAccountDropMenu.click();
    }
    /*
    Changing Public void ---public login page
    by loginpage as driver
     */
    public LoginPage SelectLoginOption(){

        Loginoption.click();
        return new LoginPage(driver);
    }
    public LoginPage navigateToLoginPage(){
        myAccountDropMenu.click();
        Loginoption.click();
        return new LoginPage(driver);
    }

    public RegisterPage ClickOnRegister()
    {
        RegisterOption.click();
        return new RegisterPage(driver);
    }

    public void enterProductDetails(String productText){
        EnterSearchBoxFielddata.sendKeys(productText);
    }

    public SearchPage Navigatesearch() {
        clickSearchBoxFieldbtn.click();
        clickSearchBoxFieldbtn.click();
        return new SearchPage(driver);
    }
    public RegisterPage navigateRegisterPage(){
        myAccountDropMenu.click();
        RegisterOption.click();
        return new RegisterPage(driver);

    }
    public SearchPage NavigatesearchforAProduct(String productText){
        EnterSearchBoxFielddata.sendKeys(productText);
        clickSearchBoxFieldbtn.click();
        return new SearchPage(driver);
    }


}
