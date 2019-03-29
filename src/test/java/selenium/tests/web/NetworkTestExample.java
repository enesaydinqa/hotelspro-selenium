package selenium.tests.web;

import context.annotations.Description;
import context.base.AbstractAnyWorkTest;
import context.db.DbHelper;
import context.flag.ParallelExecutable;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.web.HomePage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NetworkTestExample extends AbstractAnyWorkTest
{
    private static final Logger logger = Logger.getLogger(NetworkTestExample.class);

    HomePage homePage;
    DbHelper dbHelper;

    @Before
    public void init() throws Exception
    {
        super.init(true);

        homePage = new HomePage(driver);
        dbHelper = new DbHelper();
    }

    @Test
    @Description("This area test description")
    @Category(ParallelExecutable.class)
    public void testAnywork()
    {
        dbHelper.execute(String.class, "asdsadsasadasd", "asdasdsad");
    }

}
