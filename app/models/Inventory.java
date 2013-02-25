package models;

public class Inventory
{

	private String name;
	
	private String file;
	
	public Inventory() {}
	
	/**
	 * Inventory constructor
	 * @param name
	 * @param file
	 */
	public Inventory(String name, String file)
	{
		this.name = name;
		this.file = file;
	}
	
	/**
	 * Name getter
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Name setter
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * File name getter
	 * @return file name
	 */
	public String getFile()
	{
		return file;
	}

	/**
	 * File name setter
	 * @param file name
	 */
	public void setFile(String file)
	{
		this.file = file;
	}

	

}
