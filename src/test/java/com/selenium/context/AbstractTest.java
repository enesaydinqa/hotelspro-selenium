package com.selenium.context;

import com.selenium.context.configuration.AppConfig;
import com.selenium.context.db.DbHelper;
import com.selenium.context.manager.TestUser;
import com.selenium.context.manager.TestUserAccountManager;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("default")
@ContextConfiguration(classes = AppConfig.class)
public abstract class AbstractTest
{

    @Autowired
    protected TestUserAccountManager testUserAccountManager;

    @Autowired
    protected TestUser testUser;

    @Autowired
    protected DbHelper dbHelper;

}
