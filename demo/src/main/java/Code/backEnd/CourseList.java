package backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class CourseList {
    private ArrayList<Course> courses;

    private static CourseList courseList;

    private CourseList() {
        // Initialize course from database
        courses = DataLoader.loadCoursesMinusRequirements();
    }

    public static CourseList getInstance() {
        if (courseList == null) {
            courseList = new CourseList();
        }
        return courseList;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Add/create a new course to the list
     * 
     * @param course
     * @return
     */
    public boolean createCourse(Course course) {
        if (course == null) {
            System.out.println("Invalid Parameter");
            return false;
        }
        // if not found in the current list, add to the list
        if (findCourse(course.getUuid()) == false) {
            courses.add(course);
            System.out.println("Course  " + course.getCourseName() + " added successfully.");
            //System.out.println(courses);
            return true;
        } else {
            System.out.println("Course: " + course.getCourseName() + " already exists.");
        }
        return false;

    }

    /**
     * Delete a course from courselist
     * 
     * @param course
     * @return
     */
    public boolean deleteCourse(Course pCourse) {
        if (pCourse == null) {
            System.out.println("Invalid Parameter");
            return false;
        }
        if (findCourse(pCourse.getUuid())) {
            for (Course course : courses) {
                if (course.getUuid().equals(pCourse.getUuid())) {
                    System.out.println("Course  " + course.getCourseName() + " deleted successfully.");
                    return true;
                }
            }
            return false;
        } else {
            System.out.println("Course: " + pCourse.getCourseName() + "do not exists and cannot be deleted");
            return false;
        }
    }

    /**
     * edit an existing course
     * 
     * @param course
     * @return
     */
    public boolean editCourse(Course pCourse) {
        if (pCourse == null) {
            System.out.println("Invalid Parameter");
            return false;
        }
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.getUuid().equals(pCourse.getUuid())) {
                // matching course id found
                courses.set(i, pCourse);
                System.out.println("Course: " + pCourse.getCourseName() + "updated with new information");
                return true;
            }
        }
        return false;
    }

    /**
     * Find if a course with the given uuid exists
     * 
     * @param uuid
     * @return
     */
    public Boolean findCourse(UUID uuid) {
        for (Course course : courses) {
            if (course.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * find if a course with the given name exist
     * 
     * @param name
     * @return
     */
    public Course findCourseByName(String name) {
        for (Course course : courses) {
            if (course.getCourseName().equals(name)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Get course by UUID
     * 
     * @param uuid
     * @return
     */

    public Course getCourse(UUID uuid) {
        for (Course course : courses) {
            if (course.getUuid().equals(uuid)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Get Course by course Subject and course number
     * 
     * @param courseSubject
     * @param courseNumber
     * @return
     */
    public Course getCourse(String courseSubject, String courseNumber) {
        for (Course course : courses) {
            if (course.getCourseSubject().equals(courseSubject) &&
                    course.getCourseNumber().equals(courseNumber)) {
                return course;
            }
        }
        return null;
    }
}
