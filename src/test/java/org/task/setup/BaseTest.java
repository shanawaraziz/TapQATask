package org.task.setup;

import com.aventstack.extentreports.ExtentReports;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.task.utils.ExtentReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected static ExtentReports extent;

    @BeforeClass
    protected static void setup() {
        extent = ExtentReporter.setupExtentReport();
        RestAssured.baseURI = "https://api.tap.company/v3/lead";
    }

    @AfterSuite
    protected void tearDownReport() {
        extent.flush();
    }

    protected static RequestSpecification getRequestSpecification() {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer sk_test_iLDcrMyT3xl6quaeOVhkFvdz")
                .contentType("application/json");
    }
}
