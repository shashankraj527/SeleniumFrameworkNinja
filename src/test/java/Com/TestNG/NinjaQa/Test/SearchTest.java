package Com.TestNG.NinjaQa.Test;

import Com.TestNG.NinjaQa.base.Base;
import com.Ninja.qa.pageobjects.HomePage;
import com.Ninja.qa.pageobjects.SearchPage;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends Base {
    WebDriver driver;

    public  SearchTest(){
        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initailizeBrowser(prop.getProperty("browserName"));


    }
    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct(){
        HomePage homePage=new HomePage(driver);
        homePage.enterProductDetails(dataProp.getProperty("ValidProduct"));
        homePage.ClickSearchBoxbutton();
        SearchPage sp=new SearchPage(driver);
        Assert.assertTrue(sp.DisplayStatusValidProduct(),"valid not product displayed");

    }
    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        HomePage homePage=new HomePage(driver);
        homePage.enterProductDetails(dataProp.getProperty("InValidProduct"));
        homePage.ClickSearchBoxbutton();
        SearchPage sp=new SearchPage(driver);
        String actualSearchMsg= sp.NoproductmatchesthesearchcriteriaText();
        Assert.assertEquals(actualSearchMsg,dataProp.getProperty("WarningMessage"),"No product in search results found");
    }
    @Test(priority = 3)
    public void verifySearchWithOutAnyProduct(){
        HomePage homePage=new HomePage(driver);
        homePage.enterProductDetails(dataProp.getProperty(""));
        homePage.ClickSearchBoxbutton();
        SearchPage sp=new SearchPage(driver);
        String actualSearchMsg= sp.NoproductmatchesthesearchcriteriaText();
        Assert.assertEquals(actualSearchMsg,dataProp.getProperty("WarningMessage"),"No product in search results found");
    }
}
