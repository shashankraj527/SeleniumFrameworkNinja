package com.Ninja.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReport {

    public static ExtentReports generateExtentReport()  {
        ExtentReports extentReports=new ExtentReports();

        File externtReportFile=new File(System.getProperty("user.dir")+"\\Test-Output\\ExtentReports\\extentreports.html");
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(externtReportFile);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Ninja QA Results ");
        sparkReporter.config().setDocumentTitle("TN Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");

        extentReports.attachReporter(sparkReporter);

        Properties configProp=new Properties();
        File configPropfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\Ninja\\qa\\config\\config.properties");
        try {
            FileInputStream ConfigParser = new FileInputStream(configPropfile);
            configProp.load(ConfigParser);
        }catch (Throwable e){
            e.printStackTrace();
        }
        extentReports.setSystemInfo("Application URL", configProp.getProperty("url"));
        extentReports.setSystemInfo("Browser Name",configProp.getProperty("browserName"));
        extentReports.setSystemInfo("Email",configProp.getProperty("ValidEmail"));
        extentReports.setSystemInfo("Password",configProp.getProperty("ValidPassword"));
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("Username",System.getProperty("user.name"));
        extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
        return extentReports;
    }
}
