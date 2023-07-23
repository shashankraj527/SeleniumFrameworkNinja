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
   public WebDriver driver;
    SearchPage sp;
    HomePage homePage;
    public  SearchTest(){
        super();
    }
    @BeforeMethod
    public void setup(){
        driver=initailizeBrowser(prop.getProperty("browserName"));
         homePage=new HomePage(driver);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct(){

      sp=homePage.NavigatesearchforAProduct(dataProp.getProperty("ValidProduct"));
        Assert.assertTrue(sp.DisplayStatusValidProduct(),"valid not product displayed");

    }
    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){

       sp= homePage.NavigatesearchforAProduct(dataProp.getProperty("InValidProduct"));
       //Assert.assertEquals(sp.NoproductmatchesthesearchcriteriaText(),dataProp.getProperty("WarningMessage"),"No product in search results found");
        Assert.assertEquals(sp.NoproductmatchesthesearchcriteriaText(),"abcdefghi","No product in search results found");

    }
    @Test(priority = 3,dependsOnMethods ={"verifySearchWithInvalidProduct"})
    public void verifySearchWithOutAnyProduct(){
        sp= homePage.NavigatesearchforAProduct("");
       Assert.assertEquals(sp.NoproductmatchesthesearchcriteriaText(),dataProp.getProperty("WarningMessage"),"No product in search results found");
    }
}
