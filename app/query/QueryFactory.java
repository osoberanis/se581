package query;

import java.util.ArrayList;
import java.util.Iterator;

import play.mvc.Controller;

import models.InventoryFile;
import models.InventoryFile.Inventory;

public class QueryFactory
{
	public static String findItem(String item)
	{
		String json = null;
		InventoryFile inMemoryFile = QueryOperations.retrieveInventoryFile();
		Inventory inventory = QueryOperations.queryItem(inMemoryFile, item);
		if (inventory != null)
		{
			json = QueryOperations.convertInventorytoJson(inventory);	
		}
		return json;
	}
	
	public static String findItems()
	{
		String json = null;
		
		if (Controller.request().queryString().containsKey("field") && 
			Controller.request().queryString().containsKey("operation") &&
			Controller.request().queryString().containsKey("value"))
		{
			// Extract field
			String[] query = Controller.request().queryString().get("field");
			String field = query[0];
			
			// Extract Operation
			query = Controller.request().queryString().get("operation");
			String operationQuery = query[0];
			int operation = 0;
			
			if (operationQuery.trim().equalsIgnoreCase("equals"))
			{
				operation = Operations.EQUAL;
			}
			else if (operationQuery.trim().equalsIgnoreCase("greaterthan"))
			{
				operation = Operations.GREATERTHAN;
			}
			else if (operationQuery.trim().equalsIgnoreCase("lessthan"))
			{
				operation = Operations.LESSTHAN;
			}
			
			// Extract value
			query = Controller.request().queryString().get("value");
			double value = new Double(query[0]).doubleValue();
			
			// Retrieve file and put in memory
			InventoryFile inMemoryFile = QueryOperations.retrieveInventoryFile();
			ArrayList<Inventory> inventoryArray = QueryOperations.queryItems(inMemoryFile, field, operation, value);
			
			if (!inventoryArray.isEmpty())
			{
				InventoryFile resultSet = new InventoryFile();
				
				Iterator<Inventory> it = inventoryArray.iterator();
				while (it.hasNext())
				{
					resultSet.getInventoryList().add(it.next());
				}
				
				json = QueryOperations.convertInventoriestoJson(resultSet);
			}
		}
		
		
		return json;
	}
}
