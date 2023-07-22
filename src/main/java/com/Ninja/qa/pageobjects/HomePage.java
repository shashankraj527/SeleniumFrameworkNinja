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
    public void SelectLoginOption(){

        Loginoption.click();
        
    }

    public void ClickOnRegister(){
        RegisterOption.click();
    }

    public void enterProductDetails(String productText){
        EnterSearchBoxFielddata.sendKeys(productText);
    }

    public void ClickSearchBoxbutton() {
        clickSearchBoxFieldbtn.click();
    }
}
