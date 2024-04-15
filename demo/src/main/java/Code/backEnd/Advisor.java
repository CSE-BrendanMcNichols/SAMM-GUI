package Code.backEnd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This is a Advisor class
 * 
 * @author sree
 */
public class Advisor extends User {

    private ArrayList<Student> assignedStudents = new ArrayList<Student>();
    private String department = "Computer Science";
    private UUID uuid;

    public Advisor() {
        assignedStudents = new ArrayList<Student>();
        department = "Computer Science";
        uuid = UUID.randomUUID();
    }

    /**
     * Constuctor without UUID. Super constructor will create one
     */
    public Advisor(String firstName, String lastName, String uscid, String email, String username, String password,
            String department) {
        super(firstName, lastName, uscid, email, username, password, UserType.ADVISOR);
        this.department = department;
    }

    /**
     * Constructor with Assigned Students
     */
    public Advisor(String firstName, String lastName, String uscid, String email, String username, String password,
            ArrayList<Student> pAssignedStudents, String department) {
        super(firstName, lastName, uscid, email, username, password, UserType.ADVISOR);
        this.department = department;
        // if the parameter is not null then accept it
        if (pAssignedStudents != null) {
            this.assignedStudents = pAssignedStudents;
        }
    }

    /**
     * Constructor with UUID, assignedStudents and Department as well
     * 
     * @param firstName
     * @param lastName
     * @param uscid
     * @param email
     * @param username
     * @param password
     * @param assignedStudents
     * @param uuid
     * @param department
     */
    public Advisor(String firstName, String lastName, String uscid, String email, String username, String password,
            ArrayList<Student> pAssignedStudents, UUID uuid, String department) {
        super(uuid, firstName, lastName, uscid, email, username, password, UserType.ADVISOR);
        this.department = department;
        if (pAssignedStudents != null) {
            this.assignedStudents = pAssignedStudents;
        }
    }

    /**
     * Getter method for assignedStudents
     * 
     * @return
     */
    public ArrayList<Student> getAssignedStudents() {
        return assignedStudents;
    }

    /**
     * Setter methods for assigned Students
     * 
     * @param pAssignedStudents
     */
    public void setAssignedStudents(ArrayList<Student> pAssignedStudents) {
        if (pAssignedStudents != null) {
            this.assignedStudents = pAssignedStudents;
        }
    }

    /**
     * Assign one student
     * 
     * @param student
     */
    public void assignStudent(Student student) {

        // safe
        if (assignedStudents == null) {
            assignedStudents = new ArrayList<Student>();
        }
        if (student != null)
            this.assignedStudents.add(student);
    }

    /**
     * Assign one student
     * 
     * @param student
     */
    public void unAssignStudent(Student pStudent) {
        boolean unAssigned = false;
        for (Student student : this.assignedStudents) {
            if (student.getUuid().equals(pStudent.getUuid())) {
                this.assignedStudents.remove(student);
                unAssigned = true;
                System.out.println(
                        "Unassigned the Student: " + pStudent.getUsername() + " from advisor" + this.getUsername());
                break;
            }
        }
        if (unAssigned == false) {
            System.out.println(
                    "Unable to unassign the Student: " + pStudent.getUsername() + " from advisor" + this.getUsername()
                            + " since this student was never assigned to this advisor.");
        }
    }

    /**
     * This methods returns the matching Student object
     * 
     * @param IdOrName
     * @param name
     * @return
     */
    public Student searchStudent(String IdorUserName, String name) {
        for (Student student : assignedStudents) {
            if (student.getUsername().equals(IdorUserName) || student.getUsername().equals(name)) {
                // System.out.println("Returning Matching Student for " + IdorUserName);
                return student;
            }
        }
        System.out.println("It looks like Student: " + IdorUserName + " or " + name
                + " is not an assinged student for Advisor: " + this.getUsername());
        return null;
    }

    /**
     * This methods returns the matching Student object
     * 
     * @param IdOrName
     * @param name
     * @return
     */
    public Student searchStudentByUserName(String userName) {
        return findAssignedStudent(userName);
    }

    


    /**
     * This methods updates the grade for the given student
     * 
     * @param student
     * @param course
     * @param grade
     * 
     */

    // TODO: is this method required at all ?
    public void updateStudentGrade(Student pStudent, Course pCourse, char pGrade) {
        if (pStudent == null || pCourse == null) {
            System.out.println(
                    "updateStudentGrade::Invalid Student " + pStudent + " or Invalid Course " + pCourse);
            return;
        }

        Student assignedStudent = findAssignedStudent(pStudent);
        Course studentCourse = findStudentCourse(assignedStudent, pCourse);
        if (studentCourse != null) {
            // studentCourse.setUserGrade(pGrade);
            System.out.println(
                    "updateStudentGrade::Successfully updated Student: " + assignedStudent.getUsername()
                            + "'s grade with " + pGrade
                            + " for the course:"
                            + studentCourse.getCourseName());
        }

    }

    /**
     * This method updates the student Credits
     */
    // TODO : why is pCourse passed as a parameter - not used
    public void updateStudentCredits(Student pStudent, Course pCourse, int credits) {
        if (pStudent == null) {
            System.out.println(
                    "updateStudentCredits::Invalid Student " + pStudent);
            return;
        }

        Student student = findAssignedStudent(pStudent);
        if (student != null) {
            student.setCredits(credits);
            System.out.println("updateStudentCredits::Succesfully updated Student: " + student.getUsername()
                    + "'s credits with " + credits);
        }
    }

    /**
     * Checks if the given student has a risk of failing
     * 
     * @param username
     * @return
     */
    public boolean riskOfFailure(String username) {

        Student student = findAssignedStudent(username);
        if (student == null) {
            System.out.println(
                    "riskOfFailure::Student: " + username + " is not an assignedStudent");
            return false;
        }
        if (student.getCredits() < 1) {
            return true;
        }
        return false;
    }

    /**
     * Makes notes for the given student
     * 
     * @param username
     * @return
     */
    public void makeNote(Student pStudent, String note) {
        if (pStudent == null) {
            System.out.println(
                    "makeNote::Invalid Student: " + pStudent);
            return;
        }

        Student student = findAssignedStudent(pStudent);
        if (student != null && note != null) {
            ArrayList<String> notes = student.getNotes();
            if (notes == null) {
                notes = new ArrayList<String>();
            }
            notes.add(note);
            // TODO: instead of doing all of these things here, correct the Student.setNotes
            // method to take a note string and append the new notes in the setNotes method
            student.setNotes(notes);
        } else {
            System.out.println(
                    "makeNote::Student: " + pStudent.getUsername() + " is not an assignedStudent");
        }
    }

    public void trackStudents() {
        // TODO: stub method
    }

    /**
     * Returns the matching student
     * Helper method
     * 
     * @param pStudent
     * @return
     */
    private Student findAssignedStudent(Student pStudent) {
        if (pStudent != null) {
            return findAssignedStudent(pStudent.getUsername());
        }
        return null;
    }

    /**
     * Returns the matching student
     * Helper method
     * 
     * @param pStudent
     * @return
     */
    private Student findAssignedStudent(String username) {
        boolean matchingStudentFound = false;
        for (Student student : assignedStudents) {
            if (student.getUsername().equals(username)) {
                matchingStudentFound = true;
                return student;
            }
        }
        if (!matchingStudentFound) {
            System.out.println("It looks like Student: " + username
                    + " is not an assinged for Advisor: " + this.getUsername());
        }
        return null;
    }

    /**
     * Returns the matching student
     * Helper method
     * 
     * @param pStudent
     * @param pCourse
     * @return
     */

    private Course findStudentCourse(Student pStudent, Course pCourse) {
        // Check if the student or the course is null
        if (pStudent == null || pCourse == null) {
            System.out.println("Invalid request: Student and/or Course is null.");
            return null;
        }

        // Check if there are no courses assigned to the student
        if (pStudent.getCurrentCourses().isEmpty()) {
            System.out.println("No courses assigned to Student: " + pStudent.getUsername());
            return null;
        }

        boolean matchingCourseFound = false;
        for (Course course : pStudent.getCurrentCourses()) {
            if (course.getCourseNumber().equals(pCourse.getCourseNumber())) {
                matchingCourseFound = true;
                // Found the matching Course
                return course;
            }
        }

        if (!matchingCourseFound) {
            System.out.println("It looks like the Course: " + pCourse.getCourseName()
                    + " is not assigned to Student: " + pStudent.getUsername());
        }

        return null;
    }

    public void displayStudents() {
        for (Student student : assignedStudents) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }

    @Override
    public String toString() {
        return "Advisor [UUID:" + uuid + "\n" + super.toString() + "]";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}