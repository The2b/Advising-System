
package edu.lewisu.cs.api.course;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletResponse;
import javax.json.Json;
import javax.json.JsonObject;

import edu.lewisu.cs.api.course.CourseFormConstants;

/**
 * Servlet implementation class CourseRemoveService
 * 
 * Used to expose API functions for creating courses in the database
 *
 * @TODO Separate whatever needs to be separated between this and the worker class
 */
@Path("/removeCourse")
public class CourseRemoveService {

    /**
     * @author Thomas Lenz <thomas.lenz96@gmail.com>
     * Removes a course with the given Course ID
     * 
     * @TODO Replace COURSE_ID with the primary key of the table
     * 
     * @return response: Returns a JSON object with the information used to remove the course, along with a status code to identify success or failure
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCourse(@QueryParam(CourseFormConstants.COURSE_ID) @DefaultValue("") String courseId)  {
        // Set our status based on the validity above
        int errorCode = (!courseId.equals("") ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
        
        // @TODO Create a query to remove data from the DB

        // Create JSON object to return
        JsonObject jsonObj = Json.createObjectBuilder().add(CourseFormConstants.COURSE_ID, courseId).build();

        // And send a response with our error code (200 for success, 400 for an invalid POST doc) and our JSON object
        return Response.status(errorCode).entity(jsonObj).build();
    }
}
