package context.driver;

import context.objects.Configuration;
import net.lightbody.bmp.BrowserMobProxy;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class DriverManager
{
    protected static RemoteWebDriver driver;
    protected static BrowserMobProxy proxy;
    protected static Configuration configuration;
    public static String session;

    protected void createDriver(Boolean withProxy)
    {
    }

    public RemoteWebDriver getDriver(Boolean withProxy) throws Exception
    {
        if (driver == null)
        {
            configuration = new Configuration();

            ClassLoader classLoader = getClass().getClassLoader();

            createDriver(withProxy);

            PropertyConfigurator.configure(classLoader.getResource("log4j.properties").getPath());
        }
        return driver;
    }
}
