package ecommerceTesting.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	// Create report entry and gets the object out of it
	public static ExtentReports getReportObject() {
		// ExtentReports , ExtentSparkReporter
				String path = System.getProperty("user.dir") + "\\reports\\index.html";
				
				// Generates beautiful HTML reports.
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results");
				reporter.config().setDocumentTitle("Test Results");

				// Main class used to create and manage the report.
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Sangita Thapa");
				return extent;
	}

}
