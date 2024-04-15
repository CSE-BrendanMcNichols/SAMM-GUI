package backEnd;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class will save the data to respective json files.
 */

public class DataWriter {
    /**
     * This method will save students list in a json file
     * 
     * @param studentList
     */
    @SuppressWarnings("unchecked")
    public static void saveStudents(ArrayList<Student> studentList) {
        // Convert ArrayList to JSON
        JSONArray studentsJSONArray = new JSONArray();
        for (Student student : studentList) {
            JSONObject studentJSON = new JSONObject();

            studentJSON.put(DataConstants.UUIDSTRING, student.getUuid().toString());
            studentJSON.put(DataConstants.FIRST_NAME, student.getFirstName());
            studentJSON.put(DataConstants.LAST_NAME, student.getLastName());
            studentJSON.put(DataConstants.USERNAME, student.getUsername());
            studentJSON.put(DataConstants.PASSWORD, student.getPassword());
            studentJSON.put(DataConstants.EMAIL, student.getEmail());
            studentJSON.put(DataConstants.USCID, student.getUscid());
            studentJSON.put(DataConstants.GRADEYEAR, student.getGradeYear().toString());
            studentJSON.put(DataConstants.CREDITS, student.getCredits());
            studentJSON.put(DataConstants.OVERALLGRADE, student.getOverallGrade());
            studentJSON.put(DataConstants.APPLICATIONAREA, student.getApplicationArea());

            // Only store the Advisor UUID/
            if (student.getAdvisor() != null) {
                studentJSON.put(DataConstants.ADVISOR, student.getAdvisor().getUuid().toString());
            }

            // Only store the Major Name
            // Todo. When reading from JSON file, it has to look up this id with the Major
            // List and update Student with Major Object reference
            if (student.getMajor() != null) {
                studentJSON.put(DataConstants.MAJOR, student.getMajor().getUuid());
            }

            if (student.getCompletedCourses() != null) {
                // getUUIDMap(student.getCompletedCourses());
                studentJSON.put(DataConstants.COMPLETEDCOURSES, getUUIDMap(student.getCompletedCourses()));
            }

            // Convert the Objects to UUID arraylist and store
            if (student.getCurrentCourses() != null) {
                List<String> currentCourseUuids = student.getCurrentCourses().stream().map(Course::getUuidString)
                        .collect(Collectors.toList());
                studentJSON.put(DataConstants.CURRENTCOURSES, currentCourseUuids);
            }

            // Convert the Objects to UUID arraylist and store
            if (student.getCompletedElectives() != null) {

                List<String> completedElectiveUuids = student.getCompletedElectives().stream()
                        .map(Elective::getUuidString).collect(Collectors.toList());
                studentJSON.put(DataConstants.COMPLETEDELECTIVES, completedElectiveUuids);
            }

            if (student.getCurrentElectives() != null) {

                List<String> currentElectiveUuids = student.getCurrentElectives().stream().map(Elective::getUuidString)
                        .collect(Collectors.toList());
                studentJSON.put(DataConstants.CURRENTELECTIVES, currentElectiveUuids);
            }

            studentJSON.put(DataConstants.NOTES, student.getNotes());

            studentsJSONArray.add(studentJSON);
        }

        // Write JSON to file
        try (FileWriter file = new FileWriter(DataConstants.STUDENT_FILE_NAME)) {
            file.write(prettyPrint(studentsJSONArray));
            file.flush();
            System.out.println("Studnets JSON data is written to the file " + DataConstants.STUDENT_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static HashMap<String, String> getUUIDMap(HashMap<Course, String> completedCourses) {

        // Create a new HashMap<CourseID, String>
        HashMap<String, String> newMap = new HashMap<>();

        // Iterate over the entries of the original map
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String value = entry.getValue();

            // Add the entry to the new map
            newMap.put(course.getUuidString(), value);
        }
        return newMap;
    }

    /**
     * This method will save advisors list in a json file
     * 
     * @param advisorList
     */
    @SuppressWarnings("unchecked")
    public static void saveAdvisors(ArrayList<Advisor> advisorList) {

        // Convert ArrayList to JSON
        JSONArray advisorsJSONArray = new JSONArray();
        for (Advisor advisor : advisorList) {
            JSONObject advisorJSON = new JSONObject();
            advisorJSON.put(DataConstants.FIRST_NAME, advisor.getFirstName());
            advisorJSON.put(DataConstants.LAST_NAME, advisor.getLastName());
            advisorJSON.put(DataConstants.USERNAME, advisor.getUsername());
            advisorJSON.put(DataConstants.PASSWORD, advisor.getPassword());
            advisorJSON.put(DataConstants.EMAIL, advisor.getEmail());
            advisorJSON.put(DataConstants.USCID, advisor.getUscid());
            advisorJSON.put(DataConstants.UUIDSTRING, advisor.getUuid().toString());

            // Convert the Student Objects to Student String arraylist and store
            advisorJSON.put(DataConstants.ASSIGNED_STUDENTS, getStudentJSONArray(advisor.getAssignedStudents()));

            advisorsJSONArray.add(advisorJSON);
        }

        // Write JSON to file
        try (FileWriter file = new FileWriter(DataConstants.ADVISOR_FILE_NAME)) {
            file.write(prettyPrint(advisorsJSONArray));
            file.flush();
            System.out.println("Advisors JSON data is written to the file " + DataConstants.ADVISOR_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will save administrators list in a json file
     * 
     * @param administratorList
     */
    @SuppressWarnings("unchecked")
    public static void saveAdministrators(ArrayList<Administrator> administratorList) {
        // Convert ArrayList to JSON
        JSONArray administratorsJSONArray = new JSONArray();
        for (Administrator administrator : administratorList) {
            JSONObject administratorJSON = new JSONObject();

            administratorJSON.put(DataConstants.FIRST_NAME, administrator.getFirstName());
            administratorJSON.put(DataConstants.LAST_NAME, administrator.getLastName());
            administratorJSON.put(DataConstants.USERNAME, administrator.getUsername());
            administratorJSON.put(DataConstants.PASSWORD, administrator.getPassword());
            administratorJSON.put(DataConstants.EMAIL, administrator.getEmail());
            administratorJSON.put(DataConstants.USCID, administrator.getUscid());
            administratorJSON.put(DataConstants.UUIDSTRING, administrator.getUuid().toString());

            administratorsJSONArray.add(administratorJSON);
        }

        // Write JSON to file
        try (FileWriter file = new FileWriter(DataConstants.ADMINISTRATOR_FILE_NAME)) {
            file.write(prettyPrint(administratorsJSONArray));
            file.flush();
            System.out.println(
                    "Administrators JSON data is written to the file " + DataConstants.ADMINISTRATOR_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will save Majors list in a json file
     * 
     * @param majorList
     */
    @SuppressWarnings("unchecked")
    public static void saveMajors(ArrayList<Major> majorList) {
        // Convert ArrayList to JSON
        JSONArray jsonArray = new JSONArray();
        for (Major major : majorList) {
            JSONObject majorJSON = new JSONObject();
            majorJSON.put(DataConstants.MAJOR, major.getMajor());
            // TODO convert CoreREq to Hashmap
            majorJSON.put(DataConstants.COREREQ, major.getCoreReq());

            majorJSON.put(DataConstants.COURSES, getCourseJSONArray(major.getCourses()));

            // TODO : Electives
            majorJSON.put(DataConstants.ELECTIVE_COURSES, major.getElectiveCourses());
            majorJSON.put(DataConstants.MAJOR, major.getMajor());

            jsonArray.add(majorJSON);

        }

        // Write JSON to file
        try (FileWriter file = new FileWriter(DataConstants.MAJORS_FILE_NAME)) {
            file.write(prettyPrint(jsonArray));
            file.flush();
            System.out.println("Majors JSON data is written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }

    /**
     * This method will save Courses list in a json file
     * 
     * @param courseList
     */
    @SuppressWarnings("unchecked")
    public static void saveCourses(ArrayList<Course> courses) {
        // Convert ArrayList to JSON
        JSONArray coursesJSONArray = new JSONArray();
        for (Course course : courses) {
            JSONObject courseJSON = new JSONObject();

            courseJSON.put(DataConstants.UUIDSTRING, course.getUuid().toString());
            courseJSON.put(DataConstants.COURSENAME, course.getCourseName());
            courseJSON.put(DataConstants.COURSESUBJECT, course.getCourseSubject());
            courseJSON.put(DataConstants.COURSENUMBER, course.getCourseNumber());

            List<String> semesterStrings = course.getCourseAvailability().stream().map(Semester::toString)
                    .collect(Collectors.toList());
            courseJSON.put(DataConstants.COURSESEMESTER, semesterStrings);


            // Convert the Objects to UUID arraylist and store
            if (course.getPrerequisites() != null) {
                List<String> uuids = course.getPrerequisites().stream().map(CourseRequirement::getUuidString)
                        .collect(Collectors.toList());
                courseJSON.put(DataConstants.PREREQUISITES, uuids);
            }

            // Convert the Objects to UUID arraylist and store
            if (course.getCorequisites() != null) {
                List<String> uuids = course.getCorequisites().stream().map(CourseRequirement::getUuidString)
                        .collect(Collectors.toList());
                courseJSON.put(DataConstants.COREQUISITES, uuids);
            }

            courseJSON.put(DataConstants.COURSEDESCRIPTION, course.getCourseDescription());
            courseJSON.put(DataConstants.COURSEHOURS, course.getCourseHours());
            courseJSON.put(DataConstants.MINGRADE, "" + course.getMinGrade());
            courseJSON.put(DataConstants.COURSESTATUS, "" + course.getCourseStatus());

            // Note: User Grade and course STATUS not required for the list of courses
            // TODO : need to store pre and co requisites
            // System.out.println("added courseJSON");

            coursesJSONArray.add(courseJSON);
        }

        // Write JSON to file
        try (FileWriter file = new FileWriter(DataConstants.COURSE_FILE_NAME)) {

            // System.out.println("JSon output:\n" + prettyPrint(coursesJSONArray));

            file.write(prettyPrint(coursesJSONArray));
            file.flush();
            System.out.println("Courses JSON data is written to the file: " + DataConstants.COURSE_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method converts Course Objects to JSON Array
     * 
     * @param completedClasses
     * @return
     */
    private static JSONArray getCourseJSONArray(ArrayList<Course> courses) {

        ArrayList<String> list = new ArrayList<String>();
        for (Course course : courses) {
            list.add(course.getCourseSubject());
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);

        return jsonArray;

    }

    /**
     * This method converts Student Objects to JSON Array
     * 
     * @param students
     * @return
     */
    private static JSONArray getStudentJSONArray(ArrayList<Student> students) {
        ArrayList<String> list = new ArrayList<String>();
        for (Student student : students) {
            list.add(student.getUuid().toString());
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);

        return jsonArray;
    }

    private static String prettyPrint(JSONArray jsonArray) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonArray);

    }

}