package edu.lewisu.cs.api.course;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import edu.lewisu.cs.api.course.CourseFormConstants;

/**
 * Servlet implementation class CourseManagement
 * 
 * Used to expose API functions for editing, creating, and removing various properties of courses in the database
 * In particular, it can act as a decision maker for which of these operations to pass the action onto via redirects
 *
 * @TODO Separate whatever needs to be separated between this and the worker class
 */
@WebServlet("/CourseManagement")
public class CourseManagementService extends javax.servlet.http.HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseManagementService() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
    }

    /**
     * Removes a course with the given primary key, taken from an XML document given to us via POST
     * 
     * @TODO Get the primary key for users from the DB guys and plug it in here
     * 
     * @return status: Returns an integer exit code based on the error it runs into, or lack there-of
     */
    @POST
    @Path("deleteCourse")
    public int deleteCourse() {
            return 0;
    }
    
    /**
     * Modifies a course with the given properties, taken from an XML document given to us via POST
     * 
     * @TODO Get the course properties from the DB guys and plug them in here
     * In addition, we need a parameter for the old primary key (the primary key before editing), along with the new
     * primary key (whatever it will be after editing)
     * 
     * @return status: Returns an integer exit code based on the error it runs into, or lack there-of
     */
    @POST
    @Path("modifyCourse")
    public int modifyCourse() {
            return 0;
    }
}
