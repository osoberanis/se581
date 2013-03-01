package memory;

import models.InventoryFile;

public class MemorySingleton
{
	 /* Here is the instance of the Singleton */
	 private static MemorySingleton instance = null;
	 
	 /* Inventory Memory */
	 private InventoryFile inventory = null;
	
	
	 /* Prevent direct access to the constructor */
	 private MemorySingleton() 
	 {
		 super();
	 }


	/**
	 * Return an instance of the memory singleton
	 * @return MemorySingleton
	 */
	public static MemorySingleton getInstance() 
	{
		if(instance == null)
		{
            instance = new MemorySingleton();
        } 

        return instance;
	 }


	/**
	 * Gets the current inventory file from Memory
	 * @return the inventory
	 */
    public InventoryFile getInventory()
    {
	    return this.inventory;
    }


	/**
	 * Sets the current inventory file in memory
	 * @param inventory the inventory to set
	 */
    public void setInventory(InventoryFile inventory)
    {
	    this.inventory = inventory;
    }
	
	
	
}
