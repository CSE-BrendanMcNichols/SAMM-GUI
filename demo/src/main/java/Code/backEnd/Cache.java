package backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class Cache {

    private static Cache cacheInstance; // Singleton instance of Cache

    private static UserList userListInsance;
    private static MajorList majorListInstance;
    private static CourseList courseListInstance;

    private static ArrayList<Elective> electiveList = new ArrayList<Elective>();
    private static ArrayList<ApplicationArea> areaList = new ArrayList<ApplicationArea>();

    private static ArrayList<CourseRequirement> requirementList = new ArrayList<CourseRequirement>();

    public static Cache getInstance() {
        if (cacheInstance == null) {
            cacheInstance = new Cache();
        }
        return cacheInstance;
    }

    /**
     * Private constructor to prevent instantiation.
     * Initializes the list of users.
     */
    private Cache() {
        userListInsance = UserList.getInstance();
    }

    public void initializeCourses() {
        courseListInstance = CourseList.getInstance();
        for (Course course : courseListInstance.getCourses()) {
            // System.out.println(course + "\n");
        }
    }

    public void initializeRequirements() {
        requirementList = DataLoader.loadRequirements();

        for (CourseRequirement req : requirementList) {
            // System.out.println(req+"\n");
        }

    }

    public void initializeCourseRequirements() {
        DataLoader.loadCoursesWithRequirements();
        for (Course course : courseListInstance.getCourses()) {
            // System.out.println(course+"\n");
        }

    }

    public void initializeElectives() {
        electiveList = DataLoader.loadElectives();
        for (Elective elective : electiveList) {
            // System.out.println(elective+"\n");
        }
    }

    public void initializeApplicationAreas() {
        areaList = DataLoader.loadApplicaitonAreas();
        for (ApplicationArea area : areaList) {
            //System.out.println(area+"\n");
        }
    }

    public void initializeMajors() {
        majorListInstance = MajorList.getInstance();
        for (Major major : majorListInstance.getMajors()) {
            // System.out.println(major+"\n");
        }
    }

    public void initializeAdminstrators() {
        userListInsance.initializeAdminstrators();
        for (User user : userListInsance.getUsers()) {
            // System.out.println(user+"\n");
        }
    }

    public void initializeStudentsNoAdvisor() {
        userListInsance.initializeStudentsNoAdvisor();
        for (Student student : userListInsance.getStudents()) {
            //System.out.println("student: " + student.getUuid().toString()+"\n");
        }
    }

    public void initializeAdvisors() {
        userListInsance.initializeAdvisors();
        for (Advisor advisor : userListInsance.getAdvisors()) {
            //System.out.println("Advisor: " + advisor.getUuid().toString()+"\n");
        }
    }

    public void initializeStudentsAdvisor() {
        DataLoader.loadStudentsAdvisor();
        for (Student student : userListInsance.getStudents()) {
            //System.out.println("student: " + student.getUuid().toString()+"\n");
            //System.out.println("advisor: " + student.getAdvisor().getUuid().toString()+"\n");
            
        }

    }

    public Boolean findElective(UUID uuid) {
        for (Elective elective : electiveList) {
            if (elective.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Elective getElective(UUID uuid) {
        for (Elective elective : electiveList) {
            if (elective.getUuid().equals(uuid)) {
                return elective;
            }
        }
        return null;
    }

    public Boolean findRequirement(UUID uuid) {
        for (CourseRequirement requirement : requirementList) {
            if (requirement.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public CourseRequirement getRequirement(UUID uuid) {
        for (CourseRequirement requirement : requirementList) {
            if (requirement.getUuid().equals(uuid)) {
                return requirement;
            }
        }
        return null;
    }

    public CourseRequirement getRequirementByType(RequirementType type) {
        for (CourseRequirement requirement : requirementList) {
            if (requirement.getType().equals(type)) {
                return requirement;
            }
        }
        return null;
    }

}