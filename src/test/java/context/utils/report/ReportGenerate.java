package context.utils.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import context.annotations.Description;
import context.objects.Configuration;
import org.junit.rules.TestWatcher;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static context.driver.DriverManager.session;

public class ReportGenerate extends TestWatcher
{

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void failed(Throwable e, org.junit.runner.Description description)
    {
        ExtentReports extent = createReport();
        ExtentTest test = extent.startTest(description.getDisplayName());

        String testDescription = getDescription(description);

        test.log(LogStatus.INFO, "Description : " + testDescription);
        test.log(LogStatus.FAIL, "Session id : " + session);
        test.log(LogStatus.FAIL, "Stack Trace : " + e.toString());
        test.log(LogStatus.FAIL, "Page Source File : " + System.getProperty("user.dir") + "/target/PageSource/" + description.getMethodName() + "-DOM.txt");
        flushReports(extent, test);
    }

    @Override
    protected void succeeded(org.junit.runner.Description description)
    {
        ExtentReports extent = createReport();
        ExtentTest test = extent.startTest(description.getDisplayName(), "-");

        String testDescription = getDescription(description);

        test.log(LogStatus.INFO, "Description : " + testDescription);
        test.log(LogStatus.PASS, "Session id : " + session);
        flushReports(extent, test);
    }


    private ExtentReports createReport()
    {
        Date date = new Date();

        Configuration configuration = null;

        try
        {
            configuration = new Configuration();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        ExtentReports extent = new ExtentReports(String.format(configuration.getTestResultReport(),
                dateFormat.format(date)), false);
        extent.config().reportName("Anywork Regression Tests");
        extent.config().reportHeadline("Regression Test Results");
        return extent;
    }

    private void flushReports(ExtentReports extent, ExtentTest test)
    {
        extent.endTest(test);
        extent.flush();
    }

    private String getDescription(org.junit.runner.Description description)
    {
        try
        {
            Method method = description.getTestClass().getMethod(description.getMethodName());

            Description testDescription = method.getAnnotation(Description.class);

            return testDescription.value();
        }
        catch (NoSuchMethodException e1)
        {
            // No Action
        }

        return null;
    }
}
