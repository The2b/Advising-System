package edu.lewisu.cs.api.user;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Servlet implementation class UserManagement
 * 
 * Used to expose API functions for editing, creating, and removing various properties of users in the database
 */
@WebServlet("/UserManagement")
public class UserManagementService extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public UserManagementService() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * Creates a user with the given properties, taken from an XML document given to us via POST
	 * 
	 * @TODO Get the user properties from the DB guys and plug them in here
	 * 
	 * @return status: Returns an integer exit code based on the error it runs into, or lack there-of
	 */
        @GET
	@Path("createUser")
	public String createUser() {
		return ("createUser called");
		//return 0;
	}

	/**
	 * Removes a user with the given primary key, taken from an XML document given to us via POST
	 * 
	 * @TODO Get the primary key for users from the DB guys and plug it in here
	 * 
	 * @return status: Returns an integer exit code based on the error it runs into, or lack there-of
	 */
	@GET
	@Path("deleteUser")
	public int deleteUser() {
		return 0;
	}
	
	/**
	 * Modifies a user with the given properties, taken from an XML document given to us via POST
	 * 
	 * @TODO Get the user properties from the DB guys and plug them in here
	 * In addition, we need a parameter for the old primary key (the primary key before editing), along with the new
	 * primary key (whatever it will be after editing)
	 * 
	 * @return status: Returns an integer exit code based on the error it runs into, or lack there-of
	 */
	@GET
	@Path("modifyUser")
	public int modifyUser() {
		return 0;
	}
}
