package Com.TestNG.NinjaQa.base;

import com.Ninja.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {
    WebDriver driver;

    public Properties prop;
    public Properties dataProp;
  //  public void loadpropertiesfile(){
  public  Base(){
         prop=new Properties();
        File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\Ninja\\qa\\config\\config.properties");

        dataProp=new Properties();

        File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\Ninja\\qa\\testdata\\testdata.properties");
        try {
            FileInputStream datafis = new FileInputStream(dataPropFile);
            dataProp.load(datafis);
        }catch (Throwable e){
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public WebDriver initailizeBrowser(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGE_LOAD_TIME));
       // driver.get("https://tutorialsninja.com/demo/");
            driver.get(prop.getProperty("url"));
        return driver;

    }

}
