package Code.backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This is a Major class
 * 
 * @author Sree
 */
public class Major {

    private ArrayList<Course> courses;
    private HashMap<RequirementType, Integer> coreReq;
    private Elective electiveCourses;
    private String major;
    private UUID uuid;

    /**
     * this is dummy constructor. delete this after implementing the full
     * constructor
     * 
     * @param major
     */
    public Major(String major) {
        this.major = major;
    }

    public Major() {
        this.major = "DefaultMajor";
        this.uuid = UUID.randomUUID();
        this.courses = new ArrayList<Course>();
        this.coreReq = new HashMap<RequirementType, Integer>();
        this.electiveCourses = new Elective();
    }

    /**
     * This is the constructor
     * 
     * @param courses
     * @param coreReq
     * @param electiveCourses
     * @param major
     * @param uuid
     */
    public Major(ArrayList<Course> courses, HashMap<RequirementType, Integer> coreReq, Elective electiveCourses,
            String major, UUID uuid) {
        if (courses == null)
            courses = new ArrayList<Course>();
        else
            this.courses = courses;

        if (coreReq == null)
            coreReq = new HashMap<RequirementType, Integer>();
        else
            this.coreReq = coreReq;

        this.electiveCourses = electiveCourses;
        this.major = major;
        this.uuid = uuid;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public HashMap<RequirementType, Integer> getCoreReq() {
        return coreReq;
    }

    public void setCoreReq(HashMap<RequirementType, Integer> coreReq) {
        this.coreReq = coreReq;
    }

    public Elective getElectiveCourses() {
        return electiveCourses;
    }

    public void setElectiveCourses(Elective electiveCourses) {
        if(electiveCourses != null){
            this.electiveCourses = electiveCourses;
        } else {
            System.out.println("Null is not a valide Elective");
        }
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    // TODO - implement the rest of the methods from the UML diagram
    /*
     * + Major(ArrayList<Course> courses, HashMap<RequirementType, int> coreReq,
     * Elelctive electiveCourses, String major) Major
     * + addCourse(Course course): void
     * + addCoreReq(): void
     * + addElective(Course course): void
     * + toString(): String
     * + printRoadmap(): void
     * - removeCourse(Course course)
     */

    public void addCourse(Course course) {
        if(course != null){
            courses.add(course);
        } else {
            System.out.println("Error null is not a course");
        }
    }

    public void addCoreReq(RequirementType type, Integer hours) {
        if( type != null && hours != null){
            coreReq.put(type, hours);
        } else {
        System.out.println("null is not valid");
        }
    }

    @Override
    public String toString() {
        return "Major [courses=" + courses + ", coreReq=" + coreReq + ", electiveCourses=" + electiveCourses
                + ", major=" + major + ", uuid=" + uuid + "]";
    }

    public void printRoadmap() {
        System.out.println("printRoadmap called");
    }

    public void removeCourse(Course course) {
        for (int i = 0; i < courses.size(); i++){
            if(courses.get(i) == course){
                courses.remove(i);
                return;
            }
        }
        System.out.println("Course does not exist");
    }

    public void displaycoreReq() {
        for (Map.Entry<RequirementType, Integer> entry : coreReq.entrySet()) {
            RequirementType key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println(key + " " + value);
        }
    }

    

    
}
