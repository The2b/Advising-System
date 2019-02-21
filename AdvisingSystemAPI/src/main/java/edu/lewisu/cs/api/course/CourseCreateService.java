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
 * Servlet implementation class CourseManagement
 * 
 * Used to expose API functions for creating courses in the database
 *
 * @TODO Separate whatever needs to be separated between this and the worker class
 */
@Path("/createCourse")
public class CourseCreateService {

    /**
     * @author Thomas Lenz <thomas.lenz96@gmail.com>
     * Creates a course with the given properties, taken from an XML document given to us via POST
     * 
     * @TODO Get the user properties from the DB guys and plug them in here
     * @TODO Make this use CourseFormConstants for QueryParam, if possible
     * 
     * @return response: Returns a JSON object with the information used to create the course, along with a status code to identify success or failure
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCourse(@QueryParam(CourseFormConstants.COURSE_ID) @DefaultValue("") String courseId,
                                 @QueryParam(CourseFormConstants.COURSE_TITLE) @DefaultValue("") String title,
                                 @QueryParam(CourseFormConstants.COURSE_DESC) @DefaultValue("") String desc,
                                 @QueryParam(CourseFormConstants.COURSE_CREDITS) @DefaultValue("0") Integer credits,
                                 @QueryParam(CourseFormConstants.COURSE_PRE_REQS) @DefaultValue("") String preReqs)  {
        // Break the pre-reqs into an array of course prefix's and numbers
        // To do this, split it on strings by optional white space, a comma, and then optional white space
        String preReqRegex = "\\s*,\\s*";
        if(preReqs != null) {
            String[] preReqsArr = preReqs.split(preReqRegex);
        }

        // Set our status based on the validity above
        int errorCode = (validateCourseInfo(courseId, title, desc, credits, preReqs) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);

        JsonObject obj = Json.createObjectBuilder()
                            .add(CourseFormConstants.COURSE_ID, courseId)
                            .add(CourseFormConstants.COURSE_TITLE, title)
                            .add(CourseFormConstants.COURSE_DESC, desc)
                            .add(CourseFormConstants.COURSE_CREDITS, credits)
                            .add(CourseFormConstants.COURSE_PRE_REQS, preReqs)
                            .build();

        return Response.status(errorCode).entity(obj).build();
    }

    /**
     * @author Thomas Lenz <thomas.lenz96@gmail.com>
     *
     * @TODO Make sure this is sufficient
     * @TODO Make sure my assumptions here are correct
     *
     * Validates the following are true, of the parameters given:
     *  courseId is not NULL and is not an empty string
     *  title is not NULL or an empty string
     *  desc is not NULL or an empty string
     *  credits is > 0
     *
     * preReqsArr is just there for completion
     *
     * Note that I am making a point to use && and avoid ||. Almost all CPUs have jump-if-zero as well as jump-if-not-zero.
     *  This means that regardless of the implementation, using && will automatically trigger short-circuit evaluation, while || will not.
     *  For the same reason, check that the values are defined (not null) first
     */
    private boolean validateCourseInfo(String courseId, String title, String desc, Integer credits, String preReqs) {
        return (((courseId != null && !courseId.equals("")) && 
                 (title != null && !title.equals("")) && 
                 (desc != null && !desc.equals("")) && 
                 (credits > 0)) 
                 ? true : false);
    }
}
