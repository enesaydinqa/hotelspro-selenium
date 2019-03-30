package context.utils;

import java.io.File;

public class Folder
{
    public void createFolder(String folderPathName)
    {
        File theDir = new File(folderPathName);

        if (!theDir.exists())
        {
            boolean result = false;

            try
            {
                theDir.mkdir();
                result = true;
            }
            catch (SecurityException se)
            {
                //handle it
            }
            if (result)
            {
                System.out.println("DIR created");
            }
        }

    }
}
