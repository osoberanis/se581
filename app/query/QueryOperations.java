package query;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import models.InventoryFile;
import models.InventoryFile.Inventory;

public class QueryOperations
{
	/**
	 * Retrieves the inventory file and places it in memory
	 * @return InventoryFile
	 */
	public static InventoryFile retrieveInventoryFile()
	{
		InventoryFile jsonFile = null;
		ObjectMapper mapper = new ObjectMapper();
		try
        {
	        jsonFile = mapper.readValue(new File("../conf/inventory.json"), InventoryFile.class);
	        
        }
        catch (JsonParseException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return jsonFile;
	}
	
	/**
	 * Query for an item inside the in-memory file
	 * @param jsonFile in-memory file
	 * @param item to query
	 * @return Inventory object
	 */
	public static Inventory queryItem(InventoryFile jsonFile, String item)
	{
		Inventory inventory = null;
		if (jsonFile.getSize() > 0 )
		{
			Iterator<Inventory> it = jsonFile.getInventoryList().iterator();
			while (it.hasNext())
			{
				Inventory tempInventory = it.next();
				
				if (tempInventory.getInventoryID().trim().equalsIgnoreCase(item))
				{
					inventory = tempInventory;
					break;
				}
			}
		}
		return inventory;
	}
	
	/**
	 * Convert an inventory object into a Json String
	 * @param inventory
	 * @return Json String
	 */
	public static String convertInventorytoJson(Inventory inventory)
	{
		String returnJson = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try
        {
	        returnJson = mapper.writeValueAsString(inventory);
        }
        catch (JsonGenerationException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
		return returnJson;
	}
	
	/**
	 * Converts an inventory File into a Json
	 * @param inventories
	 * @return Json String
	 */
	public static String convertInventoriestoJson(InventoryFile inventories)
	{
		String returnJson = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try
        {
	        returnJson = mapper.writeValueAsString(inventories);
        }
        catch (JsonGenerationException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
		return returnJson;
	}
	
	/**
	 * Return an array of items given a field and operations
	 * @param jsonFile in-memory file
	 * @param field the field that will be used to query
	 * @param operation (>,<, =)
	 * @return Inventory array
	 */
	public static ArrayList<Inventory> queryItems(InventoryFile jsonFile, String field, int operation, double valuetoCompare)
	{
		ArrayList<Inventory> inventoryArray = new ArrayList<Inventory>();
		
		if (jsonFile.getInventoryList().size() > 0)
		{
			Iterator<Inventory> it = jsonFile.getInventoryList().iterator();
			while (it.hasNext())
			{
				Inventory tempInventory = it.next();
				double fieldValue = QueryOperations.fieldValue(tempInventory, field);
				if (fieldValue != -1 )
				{
					switch(operation)
					{
						case(Operations.EQUAL):
							
							if (new Double(fieldValue).compareTo(valuetoCompare) == 0)
							{
								inventoryArray.add(tempInventory);
							}
							break;
						case(Operations.GREATERTHAN):
							if (new Double(fieldValue).compareTo(valuetoCompare) > 0)
							{
								inventoryArray.add(tempInventory);
							}
							break;
						case (Operations.LESSTHAN):
							if (new Double(fieldValue).compareTo(valuetoCompare) < 0)
							{
								inventoryArray.add(tempInventory);
							}
							break;
					}
				}
			}
		}
		return inventoryArray;
		
	}
	
	/**
	 * Given a field it finds the appropriate value in the Inventory object.
	 * @param inventory
	 * @param field
	 * @return value
	 */
	public static double fieldValue(Inventory inventory, String field)
	{
		double value = -1.0;
		
		if (field.trim().equalsIgnoreCase("unitprice"))
		{
			value = inventory.getUnitPrice();
		}
		else if (field.trim().equalsIgnoreCase("quantityinstock"))
		{
			value = inventory.getQuantityInStock();
		}
		else if (field.trim().equalsIgnoreCase("inventoryvalue"))
		{
			value = inventory.getInventoryValue();
		}
		else if (field.trim().equalsIgnoreCase("reorderlevel"))
		{
			value = inventory.getReorderLevel();
		}
		else if (field.trim().equalsIgnoreCase("reordertimeindays"))
		{
			value = inventory.getReorderTimeinDays();
		}
		else if (field.trim().equalsIgnoreCase("quantityinreorder"))
		{
			value = inventory.getQuantityInReorder();
		}
		return value;
	}
}
