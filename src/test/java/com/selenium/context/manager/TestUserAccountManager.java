package com.selenium.context.manager;

import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@Component
public class TestUserAccountManager
{
    private static final Logger logger = LoggerFactory.getLogger(TestUserAccountManager.class);

    private static TestUsers testUsers;

    public TestUserAccountManager()
    {
        ObjectMapper mapper = new ObjectMapper();

        ClassLoader classLoader = getClass().getClassLoader();
        URL en = classLoader.getResource("TestUsers.json");

        try
        {
            testUsers = mapper.readValue(new File(en.toURI()), TestUsers.class);
        }
        catch (IOException e)
        {
            logger.info("IOException--->" + e.toString());
            e.printStackTrace();
        }
        catch (URISyntaxException e)
        {
            logger.info("URISyntaxException--->" + e.toString());
            e.printStackTrace();
        }
    }

    public synchronized TestUser getTestUser()
    {
        logger.info(String.format("Test Users -----> %s userSize ------>", testUsers.getUsers().size()));

        for (TestUser testUser : testUsers.getUsers())
        {
            if (testUser.isAvailability())
            {
                testUser.setAvailability(false);
                logger.info(String.format("{%s} is started to be used ...", testUser.getUsername()));
                return testUser;
            }
        }

        throw new NotFoundException("Test User not Found !!!");
    }

    public synchronized void releaseTestUser(TestUser testUser)
    {
        for (TestUser user : testUsers.getUsers())
        {
            if (user.getUsername().equals(testUser.getUsername()))
            {
                user.setAvailability(true);
                break;
            }
        }

        logger.info(String.format("{%s} is released ...", testUser.getUsername()));
    }
}
