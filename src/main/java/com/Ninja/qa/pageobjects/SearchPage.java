package com.Ninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;
    @FindBy(linkText = "iPhone")
    private WebElement ValidProductSearch;
    @FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
    private WebElement  Noproductmatchesthesearchcriteria;
    public SearchPage(WebDriver driver){
        this.driver=driver;
        //Instead of writingHomePage.class we are using this keyword
        PageFactory.initElements(driver,this);

    }

    public String NoproductmatchesthesearchcriteriaText(){
       String NoProductText= Noproductmatchesthesearchcriteria.getText();
       return NoProductText;
    }

    public boolean DisplayStatusValidProduct(){
          boolean displayStatus=ValidProductSearch.isDisplayed();
          return displayStatus;
    }



}
