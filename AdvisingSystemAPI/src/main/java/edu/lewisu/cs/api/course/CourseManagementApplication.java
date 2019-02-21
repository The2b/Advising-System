package edu.lewisu.cs.api.course;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.Set;
import java.util.HashSet;

import edu.lewisu.cs.api.course.CourseCreateService;
import edu.lewisu.cs.api.user.UserManagementService;
import edu.lewisu.cs.api.sched.ScheduleBuilderService;

@ApplicationPath("/CourseManagement/*")
public class CourseManagementApplication extends javax.ws.rs.core.Application {
   public Set<Class<?>> getClasses() {
      Set<Class<?>> s = new HashSet<Class<?>>();
      s.add(CourseCreateService.class);
      s.add(CourseRemoveService.class);
      s.add(CourseModifyService.class);

      return s;
    }
}
