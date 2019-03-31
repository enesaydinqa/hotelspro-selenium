package com.selenium.context.security.proxy.model;

import org.zaproxy.clientapi.core.ApiResponseSet;

public class Script
{
    private String name;
    private String type;
    private String engine;
    private boolean error;
    private String description;

    public Script(ApiResponseSet apiResponseSet)
    {
        name = apiResponseSet.getStringValue("name");
        type = apiResponseSet.getStringValue("type");
        engine = apiResponseSet.getStringValue("engine");
        error = Boolean.valueOf(apiResponseSet.getStringValue("error"));
        description = apiResponseSet.getStringValue("description");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getEngine()
    {
        return engine;
    }

    public void setEngine(String engine)
    {
        this.engine = engine;
    }

    public boolean isError()
    {
        return error;
    }

    public void setError(boolean error)
    {
        this.error = error;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
