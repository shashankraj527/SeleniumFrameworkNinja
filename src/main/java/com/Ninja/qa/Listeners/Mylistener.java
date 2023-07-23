package com.Ninja.qa.Listeners;

import com.Ninja.qa.utils.ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.helpers.SubstituteLogger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class Mylistener implements ITestListener {
    ExtentReports extentReport;
    ExtentTest extentTest;
    @Override
    public void onStart(ITestContext context){
         extentReport = ExtentReport.generateExtentReport();
    System.out.println("Execution of Project Tests Started");
    }
    @Override
    public void onTestStart(ITestResult result) {
     String testName= result.getName();
         extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO,testName+" Started executing ");
     System.out.println(testName+"Started executing");
    }
    @Override
    public  void onTestSuccess(ITestResult result) {
        String testName=result.getName();
         extentTest.log(Status.PASS,testName+" got successfully executed ");
        System.out.println(testName+"got successfully executed");
    }

    @Override
    public  void onTestFailure(ITestResult result) {
        String testName=result.getName();
        WebDriver driver=null;
        try {
            driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenShotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
        try {
            FileHandler.copy(srcScreenshot, new File(destinationScreenShotPath));
        }catch (IOException e){
            e.printStackTrace();
        }
        extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,testName+"got failed ");
    }
    @Override
    public void onTestSkipped(ITestResult result){
        String testName=result.getName();
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.SKIP,testName+" got SKIP ");

    }
    @Override
    public void onFinish(ITestContext context){
        extentReport.flush();

    }

}
