package com.ecom.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/Features",
    glue = "com.ecom.StepDefinition",
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports/cucumber.json"},
    monochrome = true,
    tags = "@registration"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
