package com.selenium.context.annotations;

import java.util.Arrays;

public class DescriptionManager
{

    public DescriptionManager(Object o)
    {
        getDescription(o);
    }

    private void getDescription(Object o)
    {
        Arrays.stream(o.getClass().getDeclaredFields()).parallel().filter(DescriptionField
                -> DescriptionField.isAnnotationPresent(Description.class)).forEach(DescriptionField
                -> {

            Description description = DescriptionField.getAnnotation(Description.class);
            DescriptionField.setAccessible(true);
            try
            {
                if (!description.value().isEmpty())
                    DescriptionField.set(o, description.value());
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        });
    }
}
