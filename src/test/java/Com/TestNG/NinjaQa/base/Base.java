package Com.TestNG.NinjaQa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Base {
    WebDriver driver;

    public WebDriver initailizeBrowser(String browserName){

        if(browserName.equals("chrome")){
            driver=new ChromeDriver();

        }
        else if(browserName.equals("firefox"))
        {
            driver=new FirefoxDriver();
        }
        else if(browserName.equals("edge"))
        {
            driver=new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");

        return driver;

    }

}
