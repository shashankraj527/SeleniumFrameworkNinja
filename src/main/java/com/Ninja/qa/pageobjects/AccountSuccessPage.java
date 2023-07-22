package com.Ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

    WebDriver driver;

    @FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
    private WebElement accountSuccessPageHeading;

    public AccountSuccessPage(WebDriver driver) {
        this.driver = driver;
        //Instead of writingHomePage.class we are using this keyword
        PageFactory.initElements(driver, this);
    }
    public String retrieveaccountSuccessPageHeading(){
        String accountSuccessPageHeadingText=accountSuccessPageHeading.getText();
        return accountSuccessPageHeadingText;
    }
}
