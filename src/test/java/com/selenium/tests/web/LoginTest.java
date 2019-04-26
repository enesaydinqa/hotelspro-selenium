package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoginTest extends AbstractHotelsProTest
{
    private String invalidLoginMessage = "Lütfen geçerli kullanıcı adı ve şifre ile giriş yapın. Kullanıcı adı ve şifrenin büyük-küçük harfe duyarlı olduğunu unutmayın.";

    @Test
    @ExpectedResult("Kullanıcının yanlış login email veya yanlış şifre ile login olmaması gereklidir. Bu durumda sistem uyarı göstermelidir.")
    @Category(ParallelExecutable.class)
    public void testInvalidMailAddressTryLogin()
    {
        String randomEmail = RandomStringUtils.randomAlphanumeric(5).concat("@").concat(RandomStringUtils.randomAlphabetic(4)).concat(".com");

        invalidTryLogin(randomEmail, configuration.getTestEmailPassword());
    }

    @Test
    @ExpectedResult("Kullanıcının yanlış login email veya yanlış şifre ile login olmaması gereklidir. Bu durumda sistem uyarı göstermelidir.")
    @Category(ParallelExecutable.class)
    public void testInvalidUserIdTryLogin()
    {
        String randomUserId = RandomStringUtils.randomNumeric(5);

        invalidTryLogin(randomUserId, configuration.getTestEmailPassword());
    }

    @Test
    @ExpectedResult("Kullanıcının yanlış login email veya yanlış şifre ile login olmaması gereklidir. Bu durumda sistem uyarı göstermelidir.")
    @Category(ParallelExecutable.class)
    public void testInvalidPasswordTryLogin()
    {
        String randomPassword = RandomStringUtils.randomAlphabetic(10);

        invalidTryLogin(configuration.getTestEmailPassword(), randomPassword);
    }

    @Test
    @ExpectedResult("Kullanıcının login email adresi veya User ID ve şifresi ile başarılı bir şekilde login olması gereklidir.")
    @Category(ParallelExecutable.class)
    public void testEmailWithLogin()
    {
        login(configuration.getTestEmail(), configuration.getTestEmailPassword());
    }

    @Test
    @ExpectedResult("Kullanıcının login email adresi veya User ID ve şifresi ile başarılı bir şekilde login olması gereklidir.")
    @Category(ParallelExecutable.class)
    public void testUserIdlWithLogin()
    {
        login(configuration.getTestUserId(), configuration.getTestEmailPassword());
    }

    private void invalidTryLogin(String username, String password)
    {
        login(username, password);
        sleep(5);
        Assert.assertTrue("not visible or bad invalid message", isTextDisplayedOnPage(invalidLoginMessage));
    }
}
