package selenium.tests.web;

import context.annotations.Description;
import context.base.AbstractAnyWorkTest;
import context.db.DbHelper;
import context.flag.ParallelExecutable;
import context.manager.TestUser;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.web.HomePage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NetworkTestExample2 extends AbstractAnyWorkTest
{
    private static final Logger logger = Logger.getLogger(NetworkTestExample2.class);

    private HomePage homePage;
    private DbHelper dbHelper;
    private TestUser testUser;

    @Before
    public void before()
    {
        homePage = new HomePage(driver);
        dbHelper = new DbHelper();

        testUser = testUserAccountManager.getTestUser();
    }

    @After
    public void after()
    {
        testUserAccountManager.releaseTestUser(testUser);
    }

    @Test
    @Description("This area test description")
    @Category(ParallelExecutable.class)
    public void testAnywork3()
    {
        logger.info(testUser.getUsername());
    }


}
