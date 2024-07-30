package org.task.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports setupExtentReport() {
        if (extent == null) {
            sparkReporter = new ExtentSparkReporter("extent-report.html");
            sparkReporter.config().setReportName("Lead API Test Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Lead API Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Tester", "Shanawar Aziz");
        }
        return extent;
    }
}

