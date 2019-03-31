package com.selenium.context.manager;

import org.springframework.stereotype.Component;

@Component
public class TestUser
{
    private String username;
    private String password;
    private boolean availability;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isAvailability()
    {
        return availability;
    }

    public void setAvailability(boolean availability)
    {
        this.availability = availability;
    }

    @Override
    public String toString()
    {
        return "TestUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", availability=" + availability +
                '}';
    }

}
