package com.selenium.tests.web;

import com.selenium.context.annotations.Description;
import com.selenium.context.base.AbstractAnyWorkTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.web.HomePage;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;

@NotThreadSafe
public class Test2 extends AbstractAnyWorkTest
{
    private static final Logger logger = Logger.getLogger(Test2.class);

    private HomePage homePage;

    @Before
    public void before()
    {
        homePage = new HomePage(driver);
    }

    @After
    public void after()
    {
        testUserAccountManager.releaseTestUser(testUser);
    }

    @Test
    @Description("This area test description")
    @Category(ParallelExecutable.class)
    public void testAnywork1() throws IOException
    {
        dbHelper.executeForListInteger("", "");
        driver.navigate().to("http://testkalite.com/adminlogin");

        testUser = testUserAccountManager.getTestUser();

        driver.findElement(By.id("user_login")).sendKeys(testUser.getUsername());
        driver.findElement(By.id("user_pass")).sendKeys(testUser.getPassword());
        driver.findElement(By.id("wp-submit")).click();

        Assert.assertTrue(getCurrentURL().equals("http://testkalite.com/wp-admin/"));

        wait(60);
    }


}
