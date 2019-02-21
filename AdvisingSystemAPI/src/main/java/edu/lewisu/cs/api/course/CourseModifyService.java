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
import javax.json.JsonArrayBuilder;

import edu.lewisu.cs.api.course.CourseFormConstants;

/**
 * Servlet implementation class CourseModifyService
 * 
 * Used to expose API functions for editing various properties of courses in the database
 *
 * @TODO Separate whatever needs to be separated between this and the worker class
 */
@Path("/modifyCourse")
public class CourseModifyService {
    /**
     * @author Thomas Lenz <thomas.lenz96@gmail.com>
     * Modifies a course with the given properties, taken from an XML or JSON document given to us via POST
     *
     * @TODO If COURSE_ID is not the primary key, replace OLD_COURSE_ID with the correct primary key, and add another argument for the new primary key
     * 
     * @return response: Returns a JSON object with the information used to modify the course, along with a status code to identify success or failure
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyCourse(@QueryParam(CourseFormConstants.OLD_COURSE_ID) @DefaultValue("") String oldCourseId,
                                 @QueryParam(CourseFormConstants.COURSE_ID) @DefaultValue("") String courseId,
                                 @QueryParam(CourseFormConstants.COURSE_TITLE) @DefaultValue("") String title,
                                 @QueryParam(CourseFormConstants.COURSE_DESC) @DefaultValue("") String desc,
                                 @QueryParam(CourseFormConstants.COURSE_CREDITS) @DefaultValue("0") Integer credits,
                                 @QueryParam(CourseFormConstants.COURSE_PRE_REQS) @DefaultValue("") String preReqs)  {

        // Break the pre-reqs into an array of course prefix's and numbers
        // To do this, split it on strings by optional white space, a comma, and then optional white space
        String preReqRegex = "\\s*,\\s*";
        String[] preReqsArr = splitStringIfNotEmpty(preReqs, preReqRegex);

        // Set our status based on the validity above
        int errorCode = (validateCourseInfo(courseId, title, desc, credits, preReqs) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
        
        // @TODO Create a query to change data on the DB

        // Create a response based on the data we recieved
        // First, we need a dedicated object for our preReqsArray
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for(String preReq : preReqsArr) {
            arrBuilder.add(preReq);
        }

        // Then create the JSON object
        JsonObject jsonObj = Json.createObjectBuilder()
                            .add(CourseFormConstants.OLD_COURSE_ID, oldCourseId)
                            .add(CourseFormConstants.COURSE_ID, courseId)
                            .add(CourseFormConstants.COURSE_TITLE, title)
                            .add(CourseFormConstants.COURSE_DESC, desc)
                            .add(CourseFormConstants.COURSE_CREDITS, credits)
                            .add(CourseFormConstants.COURSE_PRE_REQS, preReqs)
                            .add("coursePreReqsArr", arrBuilder.build())
                            .build();


        // And send a response with our error code (200 for success, 400 for an invalid POST doc) and our JSON object
        return Response.status(errorCode).entity(jsonObj).build();
    }

    /**
     * @author Thomas Lenz <thomas.lenz96@gmail.com>
     * 
     * Safely split a string based on a regex
     * 
     * @TODO Make sure my assumptions are correct here
     */
    private String[] splitStringIfNotEmpty(String stringToSplit, String regex) {
        String[] splitArr = {};

        if(stringToSplit != null) {
            splitArr = stringToSplit.split(regex);
        }

        return splitArr;
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
