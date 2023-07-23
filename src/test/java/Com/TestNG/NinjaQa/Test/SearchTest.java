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
    SearchPage sp;
    HomePage homePage;
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
        homePage.searchforAProduct(dataProp.getProperty("ValidProduct"));

        sp=homePage.ClickSearchBoxbutton();

        Assert.assertTrue(sp.DisplayStatusValidProduct(),"valid not product displayed");

    }
    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        HomePage homePage=new HomePage(driver);
        homePage.searchforAProduct(dataProp.getProperty("InValidProduct"));

        sp=homePage.ClickSearchBoxbutton();
        Assert.assertEquals(sp.NoproductmatchesthesearchcriteriaText(),dataProp.getProperty("WarningMessage"),"No product in search results found");
    }
    @Test(priority = 3)
    public void verifySearchWithOutAnyProduct(){
        HomePage homePage=new HomePage(driver);
        homePage.enterProductDetails(dataProp.getProperty(""));
        sp=homePage.ClickSearchBoxbutton();
        Assert.assertEquals(sp.NoproductmatchesthesearchcriteriaText(),dataProp.getProperty("WarningMessage"),"No product in search results found");
    }
}
