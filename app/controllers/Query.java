package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import query.QueryFactory;

public class Query extends Controller
{
	 /**
	  * Finds a single item
	  * @param item
	  * @return
	  */
	public static Result find(String item) 
	 {
		String json = QueryFactory.findItem(item);
		if (json != null)
		{
			return ok(json);
		}
		else
		{
			return badRequest("Item: " + item + " was not found.");
		}
	 }
	 
	 /**
	  * Finds multiple items given a query
	  * @return Json
	  */
	public static Result complexQuery()
	 {
		 String json = QueryFactory.findItems();
		 
		 if (json != null)
		 {
			 return ok(json);
		 }
		 else
		 {
			 return badRequest("Query did not find any items.");
		 }
	 }
}
