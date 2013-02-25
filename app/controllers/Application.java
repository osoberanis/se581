package controllers;

import java.io.File;

import models.Inventory;

import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import views.html.index;

public class Application extends Controller 
{
	
  /**
   * Defines a form wrapping the Inventory class.
   */ 
  final static Form<Inventory> inventoryForm = form(Inventory.class);
	
  public static Result index() 
  {
	 return ok(index.render(inventoryForm));
  }
  
  
  
}