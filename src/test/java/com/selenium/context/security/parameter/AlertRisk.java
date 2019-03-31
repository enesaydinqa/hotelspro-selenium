package com.selenium.context.security.parameter;

public enum AlertRisk
{
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    public String riskName;

    AlertRisk(String riskName)
    {
        this.riskName = riskName;
    }
}
