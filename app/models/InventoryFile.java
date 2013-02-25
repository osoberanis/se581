package models;

import java.util.ArrayList;
import java.util.Currency;



public class InventoryFile
{
	private int size;
	
	private ArrayList<Inventory> inventoryList = null;
	
	public static class Inventory
	{
		private String inventoryID;
		
		private String itemName;
		
		private String itemDescription;
		
		private double unitPrice;
		
		private int quantityInStock;
		
		private double inventoryValue;
		
		private int reorderLevel;
		
		private int reorderTimeinDays;
		
		private int quantityInReorder;
		
		private boolean isDiscountinue;
		
		/**
		 * Constructor
		 */
		public Inventory() {};

		/**
		 * Return inventory ID
		 * @return ID
		 */
		public String getInventoryID()
		{
			return inventoryID;
		}

		/**
		 * Set inventory ID
		 * @param inventoryID
		 */
		public void setInventoryID(String inventoryID)
		{
			this.inventoryID = inventoryID;
		}

		/**
		 * Return Item Name
		 * @return item name
		 */
		public String getItemName()
		{
			return itemName;
		}

		/**
		 * @param itemName
		 */
		public void setItemName(String itemName)
		{
			this.itemName = itemName;
		}

		/**
		 * @return
		 */
		public String getItemDescription()
		{
			return itemDescription;
		}

		/**
		 * @param itemDescription
		 */
		public void setItemDescription(String itemDescription)
		{
			this.itemDescription = itemDescription;
		}

		/**
		 * @return
		 */
		public double getUnitPrice()
		{
			return unitPrice;
		}

		/**
		 * @param unitPrice
		 */
		public void setUnitPrice(double unitPrice)
		{
			this.unitPrice = unitPrice;
		}

		/**
		 * @return
		 */
		public int getQuantityInStock()
		{
			return quantityInStock;
		}

		/**
		 * @param quantityInStock
		 */
		public void setQuantityInStock(int quantityInStock)
		{
			this.quantityInStock = quantityInStock;
		}

		/**
		 * @return
		 */
		public double getInventoryValue()
		{
			return inventoryValue;
		}

		/**
		 * @param inventoryValue
		 */
		public void setInventoryValue(double inventoryValue)
		{
			this.inventoryValue = inventoryValue;
		}

		/**
		 * @return
		 */
		public int getReorderLevel()
		{
			return reorderLevel;
		}

		/**
		 * @param reorderLevel
		 */
		public void setReorderLevel(int reorderLevel)
		{
			this.reorderLevel = reorderLevel;
		}

		/**
		 * @return
		 */
		public int getReorderTimeinDays()
		{
			return reorderTimeinDays;
		}

		/**
		 * @param reorderTimeinDays
		 */
		public void setReorderTimeinDays(int reorderTimeinDays)
		{
			this.reorderTimeinDays = reorderTimeinDays;
		}

		/**
		 * @return
		 */
		public int getQuantityInReorder()
		{
			return quantityInReorder;
		}

		/**
		 * @param quantityInReorder
		 */
		public void setQuantityInReorder(int quantityInReorder)
		{
			this.quantityInReorder = quantityInReorder;
		}

		/**
		 * @return
		 */
		public boolean isDiscountinue()
		{
			return isDiscountinue;
		}

		/**
		 * @param isDiscountinue
		 */
		public void setDiscountinue(boolean isDiscountinue)
		{
			this.isDiscountinue = isDiscountinue;
		}
	}

	/**
	 * Return the current inventory size
	 * @return the size
	 */
    public int getSize()
    {
	    return this.getInventoryList().size();
    }
    

	/**
	 * @return the inventoryList
	 */
    public ArrayList<Inventory> getInventoryList()
    {
    	if (this.inventoryList == null)
    	{
    		this.setInventoryList(new ArrayList<Inventory>());
    	}
	    return inventoryList;
    }

	/**
	 * @param inventoryList the inventoryList to set
	 */
    public void setInventoryList(ArrayList<Inventory> inventoryList)
    {
	    this.inventoryList = inventoryList;
    }
	
}
