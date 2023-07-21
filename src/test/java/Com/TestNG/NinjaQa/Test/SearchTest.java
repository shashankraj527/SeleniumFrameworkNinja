package Com.TestNG.NinjaQa.Test;

import Com.TestNG.NinjaQa.base.Base;
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
        driver.findElement(By.xpath("//div[@id='search']/descendant::input")).sendKeys(dataProp.getProperty("ValidProduct"));
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        Assert.assertTrue(driver.findElement(By.linkText("iPhone")).isDisplayed(),"valid not product displayed");

    }
    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        driver.findElement(By.xpath("//div[@id='search']/descendant::input")).sendKeys(dataProp.getProperty("InvalidProduct"));
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        String actualSearchMsg=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
        Assert.assertEquals(actualSearchMsg,dataProp.getProperty("WarningMessage"),"No product in search results found");
    }
    @Test(priority = 3)
    public void verifySearchWithOutAnyProduct(){
        driver.findElement(By.xpath("//div[@id='search']/descendant::input")).sendKeys("");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        String actualSearchMsg=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
        Assert.assertEquals(actualSearchMsg,dataProp.getProperty("WarningMessage"),"No product in search results found");
    }
}
