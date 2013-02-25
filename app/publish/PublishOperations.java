package publish;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import models.InventoryFile;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;

public class PublishOperations
{
	/**
	 * Read a given spread sheet and converts its data into a model object
	 * @param file
	 * @return InventoryFile model
	 * @throws InvalidFormatException if file is not an MS Office Spreadsheet
	 */
	public static InventoryFile readFile(File file) throws InvalidFormatException, IOException

	{
		InventoryFile inventoryModel = null;
		
		if (file != null)
		{
			try
            {
	            Workbook wb = WorkbookFactory.create(file);
	            Sheet sheet = wb.getSheetAt(0);
	            int rowCount = 0;
	            inventoryModel = new InventoryFile();
	            
	            for (Row row : sheet) 
	            {
	            	++rowCount;
	            	if (rowCount > 4)  // this check is to avoid the header row
	            	{
	            		int cellCount = 0;
	           
	            		InventoryFile.Inventory inventory = new InventoryFile.Inventory();
		            	for (Cell cell : row) 
		            	{
		            		  ++cellCount;
		            		  
		            		  if (cellCount > 1)
		            		  {
		            			  
				            	  CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
				                  System.out.print(cellRef.formatAsString());
				                  System.out.print(" - ");
				                  
				                  switch (cellCount)
				                  {
				                	  case 2:
				                		  // Collect Inventory ID
				                		  if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				                		  {
				                			  inventory.setInventoryID(cell.getRichStringCellValue().getString());
				                		  }
				                		  break;
				                	  case 3:
				                		  // Collect Item Name
				                		  if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				                		  {
				                			  inventory.setItemName(cell.getRichStringCellValue().getString());
				                		  }
				                		  break;
				                	  case 4:
				                		  // Collect Item Description
				                		  if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				                		  {
				                			  inventory.setItemDescription(cell.getRichStringCellValue().getString());
				                		  }
				                		  break;
				                	  case 5:
				                		  // Collect Unit Price
				                		  if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				                		  {
				                			  inventory.setUnitPrice(cell.getNumericCellValue());
				                		  }
				                		  break;
				                	  case 6:
				                		  // Collect Quantity in Stock
				                		  if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				                		  {
				                			  inventory.setQuantityInStock(new Double(cell.getNumericCellValue()).intValue());
				                		  }
				                		  break;
				                	  case 7:
				                		  // Collect Inventory Value
				                		  if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				                		  {
				                			  inventory.setInventoryValue(new Double(cell.getNumericCellValue()).intValue());
				                		  }
				                		  break;
				                	  case 8:
				                		  // Collect Reorder Level
				                		  if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				                		  {
				                			  inventory.setReorderLevel(new Double(cell.getNumericCellValue()).intValue());
				                		  }
				                		  break;
				                	  case 9:
				                		  // Collect Reorder Time in Days
				                		  if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				                		  {
				                			  inventory.setReorderTimeinDays(new Double(cell.getNumericCellValue()).intValue());
				                		  }
				                		  break;
				                	  case 10:
				                		  // Collect Quantity in Order
				                		  if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				                		  {
				                			  inventory.setQuantityInReorder(new Double(cell.getNumericCellValue()).intValue());
				                		  }
				                		  break;
				                	  case 11:
				                		  // Collect Discontinue items
				                		  if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
				                		  {
				                			  inventory.setDiscountinue(cell.getBooleanCellValue());
					                	  }
				                		  
				                		  if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				                		  {
				                			  if (cell.getStringCellValue().equalsIgnoreCase("YES"))
				                			  {
				                				  inventory.setDiscountinue(true);
				                			  }
				                			  else
				                			  {
				                				  inventory.setDiscountinue(false);
				                			  }
				                		  }
				                		  break;
				         
				                  }
		            		  }
		            	}
		            	if (inventory.getInventoryID() != null)
		            	{
		            		inventoryModel.getInventoryList().add(inventory);
		            	}
	            	}
	           }
            }
            catch (InvalidFormatException e)
            {
	           throw e;
            }
            catch (IOException e)
            {
	            throw e;
            }
		}
		return inventoryModel;
	}
	
	/**
	 * Write the given in memory file to a file a JSon format
	 * @param file in-memory file
	 * @return success flag, true for success, false for failure
	 */
    public static boolean writeFile(InventoryFile inventoryFile)
	{
		boolean result = false;
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try
        {
	        String json = mapper.writeValueAsString(inventoryFile);
	        mapper.writerWithDefaultPrettyPrinter();
	        File file = PublishOperations.createFile();
	        
	        FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
			result = true;
        }
        catch (JsonGenerationException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        result = false;
        }
        catch (JsonMappingException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        result = false;
        }
        catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        result = false;
        }

		return result;
	}
	
	/**
	 * Create file to store the inventory
	 * @return inventory
	 */
	public static File createFile()
	{
		File file = new File("./conf/inventory.json");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) 
		{
			try
            {
	            file.createNewFile();
            }
            catch (IOException e)
            {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }
		}
		
		return file;
	}
}
