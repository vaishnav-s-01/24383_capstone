package com.ecom.StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecom.util.ExtentReportManager;
import com.ecom.main.BasePage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    public static ExtentReports extent = ExtentReportManager.getExtentReports();
    public static ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) {
        test = extent.createTest(scenario.getName());
        test.log(Status.INFO, "Starting Scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario Failed: " + scenario.getName());
            try {
                TakesScreenshot ts = (TakesScreenshot) BasePage.getDriver();
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                String screenshotPath = "target/screenshots/" + scenario.getName() + ".png";
                test.addScreenCaptureFromPath(screenshotPath);
                test.fail("Screenshot attached");
            } catch (Exception e) {
                test.log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
            }
        } else {
            test.log(Status.PASS, "Scenario Passed: " + scenario.getName());
        }

        extent.flush();
    }
}
