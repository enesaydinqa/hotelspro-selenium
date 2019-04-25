package com.selenium.pages;


public enum UrlFactory
{

    MAIN_URL("https://www2.hotelspro.com"),
    SIGNUP(MAIN_URL, "/agency/signup/");

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
