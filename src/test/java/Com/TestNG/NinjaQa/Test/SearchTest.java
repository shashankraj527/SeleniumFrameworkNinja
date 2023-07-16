package Com.TestNG.NinjaQa.Test;

import Com.TestNG.NinjaQa.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends Base {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver=initailizeBrowser("edge");


    }
    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct(){
        driver.findElement(By.xpath("//div[@id='search']/descendant::input")).sendKeys("Iphone");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        Assert.assertTrue(driver.findElement(By.linkText("iPhone")).isDisplayed(),"valid not product displayed");

    }
    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        driver.findElement(By.xpath("//div[@id='search']/descendant::input")).sendKeys("fz");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        String actualSearchMsg=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
        Assert.assertEquals(actualSearchMsg,"There is no product that matches the search criteria.","No product in search results found");
    }
    @Test(priority = 3)
    public void verifySearchWithOutAnyProduct(){
        driver.findElement(By.xpath("//div[@id='search']/descendant::input")).sendKeys("");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        String actualSearchMsg=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
        Assert.assertEquals(actualSearchMsg,"There is no product that matches the search criteria.","No product in search results found");
    }
}
