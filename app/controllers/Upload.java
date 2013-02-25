
package controllers;



import java.io.File;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.index;

import models.Inventory;

import publish.PublishFactory;

/**
 * @author osoberan
 *
 */
public class Upload extends Controller
{
	
	final static Form<Inventory> inventoryForm = form(Inventory.class);
	
    /**
     * Handle the form submission.
     */
    public static Result submit() 
    {
    	String inventoryName = form().bindFromRequest().get("name");
    	
    	Inventory requestInventory = new Inventory();
    	requestInventory.setName(inventoryName);

    	if (inventoryName != null && inventoryName.length() > 0)
    	{
	    	MultipartFormData body = request().body().asMultipartFormData();
	    	FilePart inventory = body.getFile("inventory");
	    	
	    	if (inventory != null) 
	    	{
	    	    String fileName = inventory.getFilename();
	    	    String contentType = inventory.getContentType();
	    	    File file = inventory.getFile();
	    	    
	    	    boolean isFileUploaded = PublishFactory.publishFile(file);
	    	    
	    	    if (isFileUploaded)
	    	    {
	    	    	return ok("File uploaded");
	    	    }
	    	    else
	    	    {
	    	    	return ok("File was not uploaded try again.");
	    	    }
	    	} 
	    	else 
	    	{
	    	    flash("error", "Missing file");
	    	    return ok(index.render(inventoryForm));    
	    	}
    	}
    	else
    	{
    		 flash("error", "Missing Inventory Name");
	    	 return redirect(routes.Application.index());
    	}
    }
}
