package com.selenium.context.utils.report;

import org.apache.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class StatusRule extends TestWatcher
{
    private static final Logger logger = Logger.getLogger(StatusRule.class);

    @Override
    protected void starting(Description description)
    {
        logger.info("=================================================================");
        logger.info(String.format("TEST STARTED ... -> {%s}", description.getMethodName()));
        logger.info("=================================================================");
    }

    @Override
    protected void succeeded(Description description)
    {
        logger.info("=================================================================");
        logger.info(String.format("TEST PASSED ... -> {%s}", description.getMethodName()));
        logger.info("=================================================================");
    }
}
