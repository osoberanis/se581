
package publish;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import models.InventoryFile;

/**
 * @author osoberan
 * 
 */
public class PublishFactory
{
	/**
	 * Publish a new file to a repository
	 * @param newfile
	 * @return job id
	 */
	public static boolean publishFile(File newfile)
	{
		boolean result = false;
		try
        {
	        InventoryFile inMemoryFile = PublishOperations.readFile(newfile);
	        result = PublishOperations.writeFile(inMemoryFile);
        }
        catch (InvalidFormatException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return result;
	}
}
