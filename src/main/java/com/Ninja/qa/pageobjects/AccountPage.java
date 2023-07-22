package com.Ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage {
    WebDriver driver;
    @FindBy(linkText ="Edit your account information")
    private WebElement edityouraccountinformationoption;

            //COnstructor
    public AccountPage(WebDriver driver){
        this.driver=driver;
    }
    //Action
    public boolean getDisplayStatusOfEditAccountInformation(){
        boolean displaystatus= edityouraccountinformationoption.isDisplayed();
        return displaystatus;
    }
}
