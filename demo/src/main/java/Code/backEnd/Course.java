package Code.backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID uuid;
    private String courseName;
    private String courseSubject;
    private String courseNumber;
    private ArrayList<CourseRequirement> prerequisites;
    private ArrayList<CourseRequirement> corequisites;
    private ArrayList<Semester> courseAvailability;
    private String courseDescription;
    private int courseHours;
    private char minGrade;
    private CourseState courseStatus;

    public Course() {
        this.courseName = "EMPTY CLASS";
        this.courseHours = 3;
        this.courseSubject = "TEST";
        this.courseNumber = "5555";
        this.uuid = UUID.randomUUID();
    }

    public Course(String courseName, String courseSubject, String courseNumber,
            ArrayList<CourseRequirement> prerequisites, ArrayList<CourseRequirement> corequisites,
            ArrayList<Semester> courseAvailability, String courseDescription,
            int courseHours, char minGrade, CourseState courseStatus, UUID uuid) {

        this.courseName = courseName;
        this.courseSubject = courseSubject;
        this.courseNumber = courseNumber;
        if (prerequisites != null)
            this.prerequisites = new ArrayList<>(prerequisites);
        if (corequisites != null)
            this.corequisites = new ArrayList<>(corequisites);
        this.courseAvailability = new ArrayList<>(courseAvailability);
        this.courseDescription = courseDescription;
        this.courseHours = courseHours;
        this.minGrade = minGrade;
        this.courseStatus = courseStatus;
        this.uuid = uuid;
    }

    public Course(String courseName, String courseSubject, String courseNumber, ArrayList<Semester> courseAvailability,
            String courseDescription,
            int courseHours, char minGrade, CourseState courseStatus, UUID uuid) {

        this.courseName = courseName;
        this.courseSubject = courseSubject;
        this.courseNumber = courseNumber;
        this.courseAvailability = new ArrayList<>(courseAvailability);
        this.courseDescription = courseDescription;
        this.courseHours = courseHours;
        this.minGrade = minGrade;
        this.courseStatus = courseStatus;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUuidString() {
        return uuid.toString();
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public String getCourseNumber() {
        if(courseNumber == null){
            courseNumber = "5555";
        }
        return courseNumber;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getCourseHours() {
        
        return courseHours;
    }

    public char getMinGrade() {
        return minGrade;
    }

    public CourseState getCourseStatus() {
        return courseStatus;
    }

    // Setters
    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCourseHours(int courseHours) {
        this.courseHours = courseHours;
    }

    public void setMinGrade(char minGrade) {
        this.minGrade = minGrade;
    }

    public void setCourseStatus(CourseState courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String displayCourse() {
        return "Course Number:" + courseNumber + " Course Name:" + courseName + " Subject: " + courseSubject + "\n";
        // return courseName;
    }


    

    public ArrayList<CourseRequirement> getPrerequisites() {
        return prerequisites;
    }

    public ArrayList<CourseRequirement> getCorequisites() {
        return corequisites;
    }

    public ArrayList<Semester> getCourseAvailability() {
        return courseAvailability;
    }

    // Methods for managing prerequisites and corequisites
    public void addPrerequisite(CourseRequirement prerequisite) {
        if (!prerequisites.contains(prerequisite)) {
            prerequisites.add(prerequisite);
        }
    }

    public void addCorequisite(CourseRequirement corequisite) {
        if (!corequisites.contains(corequisite)) {
            corequisites.add(corequisite);
        }
    }

    public void removePrerequisite(CourseRequirement prerequisite) {
        prerequisites.remove(prerequisite);
    }

    public void removeCorequisite(CourseRequirement corequisite) {
        corequisites.remove(corequisite);
    }

    // Methods for course availability
    public void addSemesterOffered(Semester semester) {
        if (!courseAvailability.contains(semester)) {
            courseAvailability.add(semester);
        }
    }

    public void removeSemesterOffered(Semester semester) {
        courseAvailability.remove(semester);
    }

    public void printSemester() {
        for (Semester semester : courseAvailability) {
            System.out.println(semester.name());
        }
    }

    public String getCourseName() {
        if(courseName == null) {
            return "EMPTY CLASS";
        }else{
            return courseName;
        }
        
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setPrerequisites(ArrayList<CourseRequirement> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setCorequisites(ArrayList<CourseRequirement> corequisites) {
        this.corequisites = corequisites;
    }

    public void setCourseAvailability(ArrayList<Semester> courseAvailability) {
        this.courseAvailability = courseAvailability;
    }

    @Override
    public String toString() {
        return "Course [uuid=" + uuid + ", courseName=" + courseName + ", courseSubject=" + courseSubject
                + ", courseNumber=" + courseNumber + ", prerequisites=" + prerequisites + ", corequisites="
                + corequisites + ", courseAvailability=" + courseAvailability + ", courseDescription="
                + courseDescription + ", courseHours=" + courseHours + ", minGrade=" + minGrade + ", courseStatus="
                + courseStatus + "]";
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}