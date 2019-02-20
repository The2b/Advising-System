package edu.lewisu.cs.api.sched;

import java.io.IOException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import edu.lewisu.cs.worker.Schedule;

/**
 * Servlet implementation class ScheduleBuilder
 */
@WebServlet("/ScheduleBuilder")
public class ScheduleBuilderService extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleBuilderService() {
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
	 * Builds a schedule based on the constraints given by the POST document
	 * 
	 * @TODO Get the various constratints from the spreadsheet and add them here
	 */
	@POST
	@Path("sched/build")
	public Schedule buildSchedule() {
		return new Schedule();
	}
}
