package Movieflix_group.MovieFlix_Restful;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.web.messenger.database.MessageDAO;
import com.web.messenger.model.MessengerBean;

@Path("titles")
public class MyResource {
	
	
	/* For getting all the titles */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMovies() {
    	MessageDAO dao = new MessageDAO();
    	List<MessengerBean> list;
    	list = dao.getAllTitles();
    	Gson gson = new Gson();
    	String allTitles = gson.toJson(list);
        return allTitles;
    }
    
    /* For adding a new title */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public String addTitle(MessengerBean msgBean){
    	System.out.println("in add title" + msgBean.getCountry());
    	MessageDAO dao = new MessageDAO();
    	boolean result = dao.addNewTitle(msgBean);
    	if(result==true){
        	return "Inserted new title"	;
    	}
    	else{
    		return "Failed to Add";
    	}
    }
    
    /* For getting a single title */
    @GET
    @Path("/{imdbID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOneTitle(@PathParam("imdbID") String imdbID) {
    	MessageDAO dao = new MessageDAO();
    	List<MessengerBean> list;
    	MessengerBean msgBean = dao.getTitle(imdbID);
    	Gson gson = new Gson();
    	String uniqueTitle = gson.toJson(msgBean);
        return uniqueTitle;
    }
    
    /* For Updating an existing title */
    @PUT
    @Path("/{imdbID}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public String updateTitle(@PathParam("imdbID") String imdbID, MessengerBean msgBean){
    	System.out.println("in update title" + msgBean.getCountry());
    	msgBean.setImdbID(imdbID);
    	MessageDAO dao = new MessageDAO();
    	boolean result = dao.updateTitleDao(msgBean);
    	if(result==true){
        	return "Updated title"	;
    	}
    	else{
    		return "Update Failed";
    	}
    }
    
    @DELETE
    @Path("/{imdbID}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public String deleteTitle(@PathParam("imdbID") String imdbID, MessengerBean msgBean){
    	msgBean.setImdbID(imdbID);
    	MessageDAO dao = new MessageDAO();
    	boolean result = dao.deleteTitleDao(msgBean);
    	if(result==true){
        	return "Deleted title"	;
    	}
    	else{
    		return "Deletion Failed";
    	}
    	
    	
    }
    
    
}
