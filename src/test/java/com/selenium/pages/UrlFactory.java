package com.selenium.pages;


public enum UrlFactory
{

    MAIN_URL_FIRST("https://www2.hotelspro.com"),
    MAIN_URL_SECOND("https://www.hotelspro.com"),
    PASSWORD_RESET(MAIN_URL_SECOND,"/account/password_reset/"),
    SIGN_UP(MAIN_URL_FIRST, "/agency/signup/");

    //-----

    public final String pageUrl;

    UrlFactory(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }

    UrlFactory(UrlFactory baseUrl, String pageUrl)
    {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }

}
