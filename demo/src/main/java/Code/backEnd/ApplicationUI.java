package Code.backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.JSONObject;

public class ApplicationUI {
    private static ApplicationFacade applicationFacade = ApplicationFacade.getInstance();
    private static boolean loggedIn = false;
    private static Scanner scanner = new Scanner(System.in);
    private static ApplicationUI applicationUI;
    private static User user = null;
    private static Student student = null;
    private static Student selectedStudent = null;
    private static Advisor advisor = null;
    private static ApplicationArea applicationArea = null;
    private CourseList courseList = CourseList.getInstance();


    public static void main(String[] args) {
        /*
        applicationFacade = ApplicationFacade.getInstance();
        User user = null;
        Student student = null;
        Advisor advisor = null;
        ApplicationArea applicationArea = null;
        
        while (true) {
            System.out.println("\nWelcome to the Application\n");
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. View Student Information as advisor");
            System.out.println("4. Check progress as student");
            System.out.println("5. Display roadmap as student");
            System.out.println("6. Display remaining requirements");
            System.out.println("7. Display application areas as student");
            System.out.println("8. Logout");

            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    user = login();
                    if(user.getType() == UserType.STUDENT){
                        student = applicationFacade.loginStudent(user.getUuid());
                    }
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    viewStudentInfo(user);
                    break;
                case 4:
                    //checkProgress(user);
                    if(user.getType() == UserType.STUDENT) {
                        student = applicationFacade.loginStudent(user.getUuid());
                        displayCompletedClasses(student);
                        displayCurrentClasses(student);
                    } else {
                        System.out.println("You need to log in as a student first");
                    }
                    break;
                case 5:
                    displayRoadmap(user);
                    System.out.println("Would you like to check the courses for any of the requirements? Y/N");
                    String check = scanner.next();
                    if(check.equalsIgnoreCase("N")){
                        break;
                    }
                    System.out.println("Please enter the Code for the requirement");
                    check = scanner.next();
                    displayRequirement(RequirementType.StringToType(check));
                    System.out.println("Would you Like to add a course? Y/N ");
                    check = scanner.next();
                    if(check.equalsIgnoreCase("N")){
                        break;
                    }
                    System.out.println("Please Enter The Course Subject");
                    String subject = scanner.next();
                    System.out.println("Please Enter The Course Number");
                    int number = scanner.nextInt();
                    student.updateCurrentCourses(CourseList.getInstance().getCourse(subject, Integer.toString(number)));
                    break;
                case 6:
                System.out.println("Please enter the Code for the requirement");
                check = scanner.next();
                displayRequirement(RequirementType.StringToType(check));
                System.out.println("Would you Like to add a course? Y/N ");
                check = scanner.next();
                if(check.equalsIgnoreCase("N")){
                    break;
                }
                System.out.println("Please Enter The Course Subject");
                subject = scanner.next();
                System.out.println("Please Enter The Course Number");
                number = scanner.nextInt();
                student.updateCurrentCourses(CourseList.getInstance().getCourse(subject, Integer.toString(number)));
                break;
                case 7:
                    applicationArea(user);
                    break;
                case 8:
                    logout();
                    saveData();
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
            }
            
   
        }
        */

    }


    public static ApplicationUI getInstance() {
        if (applicationUI == null) {
            applicationUI = new ApplicationUI();
        }
        return applicationUI;
    }

    public static void saveData() {
        System.out.print("Saving Information...");
        DataWriter.saveCourses(CourseList.getInstance().getCourses());
        DataWriter.saveAdvisors(UserList.getInstance().getAdvisors());
        DataWriter.saveStudents(UserList.getInstance().getStudents());
        
        System.out.print("Your are successfully logged out! Good Bye!");
    }

    public static void TEST(){
        System.out.println("TEST");
    }

    private static void checkProgress(User student){
        //Student.checkProgress(student);
    }
    
    
    /*
    public static void displayCompletedClasses(Student student) {
        HashMap<Course, String> completedCourses = student.getCompletedCourses();
        System.out.println("Completed Classes:");
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            System.out.println("Course ID: " + course.getCourseNumber() + ", Name: " + course.getCourseName() + ", Grade: " + grade);
        }
    }
    */

    public static void displayCompletedClasses(Student student) {
        HashMap<Course, String> completedCourses = student.getCompletedCourses();
        if (completedCourses == null) {
            System.out.println("No completed courses found for the student.");
            return;
        }
        System.out.println("Completed Classes:");
        for (Map.Entry<Course, String> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            if (course == null) {
                System.out.println("Null course found in completed courses map.");
                continue; // Skip this iteration if course is null
            }
            System.out.println("Course ID: " + course.getCourseNumber() + ", Name: " + course.getCourseName() + ", Grade: " + grade);
        }
    }
    

    public static void displayCurrentClasses(Student student) {
        ArrayList<Course> currentCourses = student.getCurrentCourses();
        System.out.println("\nCurrent Classes:");
        for (Course course : currentCourses) {
            System.out.println("Course ID: " + course.getCourseNumber() + ", Name: " + course.getCourseName());
        }
    }    


    private static void displayRoadmap(User student){
        Student.generateSemesterPlan(student);
    }

    public static void setSelectedStudent(Student tempStudent){
        selectedStudent = tempStudent;
    }

    private static void applicationArea(User user) {
        System.out.println("Possible Application Areas: ");
        System.out.println("\n Science \n Math \n Digital Design \n Robotics \n Speech \n");
        System.out.println("Please enter which application area you would like to choose: ");
        
        Student student = UserList.getInstance().getStudent(user.getUuid());

        String applicationInput = scanner.nextLine();
        student.setApplicationArea(applicationInput);
        System.out.println("Would you like to see your application area? Y/N");
        String check = scanner.nextLine();
        if(check.equalsIgnoreCase("Y")); {
            //System.out.println(ApplicationArea.getArea());
        }
    }

    private static User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = applicationFacade.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful! You are logged in as " + UserType.getTypeString(user.getType()));
            loggedIn = true;
            return user;
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
        return null;
        
    }

    public static Boolean advisorLogin(String username, String password) {

        User user = applicationFacade.loginUser(username, password);
        if (user != null && user.getType() == UserType.ADVISOR) {
            loggedIn = true;
            advisor = applicationFacade.loginAdvisor(user.getUuid());
            System.out.println("Login successful! You are logged in as " + UserType.getTypeString(user.getType()));
            return true;
        } else {
            System.out.println("Login failed. Please check your credentials.");
            return false;
        }
        
    }

    public static Boolean studentLogin(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        User user = applicationFacade.loginUser(username, password);
        if (user != null && user.getType() == UserType.STUDENT) {
            loggedIn = true;
            student = applicationFacade.loginStudent(user.getUuid());
            System.out.println("Login successful! You are logged in as " + UserType.getTypeString(user.getType()));
            return true;
        } else {
            System.out.println("Login failed. Please check your credentials.");
            return false;
        }
        
    }



    public static Boolean retrieveStudent(String username, String password) {

        User user = applicationFacade.loginUser(username, password);
        if (user != null && user.getType() == UserType.ADVISOR) {
            loggedIn = true;
            advisor = applicationFacade.loginAdvisor(user.getUuid());
            System.out.println("Login successful! You are logged in as " + UserType.getTypeString(user.getType()));
            return true;
        } else {
            System.out.println("Login failed. Please check your credentials.");
            return false;
        }
        
    }

    public Boolean SendMessage(String email, String message){
        if(applicationFacade.SendMessage(email, message))
            return true;
        return false;
    }

    public boolean createAdvisor(String firstName, String lastName,
            String uscid, String email, String username, String password, String department) {

        User user = applicationFacade.createAdvisor(firstName, lastName, uscid, email, username, password,
                department);
        if (user != null) {
            System.out.println("Advisor successfully created. You can now login.");
            DataWriter.saveAdvisors(UserList.getInstance().getAdvisors());
            return true;
        } else {
            System.out.println("Advisor creation failed. User may already exist.");
            return false;
        }

    }

    public boolean createStudent(String firstName, String lastName,
            String uscid, String email, String username, String password,
            String majorName, String applicationArea, String year) {

        User user = applicationFacade.createStudent(firstName, lastName, uscid, email, username, password,
                majorName, applicationArea, year);
        if (user != null) {
            System.out.println("Student successfully created. You can now login.");
            DataWriter.saveStudents(UserList.getInstance().getStudents());
            return true;
        } else {
            System.out.println("Student creation failed. User may already exist.");
            return false;
        }

    }

    public boolean createAccount(String firstName, String lastName,
            String uscid, String email, String username, String password, String department,
            UserType userType) {

        User user = applicationFacade.registerUser(userType, firstName, lastName, uscid, email, username, password,
                department);
        if (user != null) {
            System.out.println("Account successfully created. You can now login.");
            return true;
        } else {
            System.out.println("Account creation failed. User may already exist.");
            return false;
        }

    }

    private static void createAccount() {
        System.out.println("Enter username for your account.");
        String username = scanner.nextLine();

        System.out.println("Enter password for your account.");
        String password = scanner.nextLine();
        // Logic to add it to dataloader
        System.out.println("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter USC ID: ");
        String uscid = scanner.nextLine();

        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.println("Are you a Student, Advisor, or Administrator?");
        String userTypeString = scanner.nextLine();
        UserType userType = UserType.valueOf(userTypeString.toUpperCase());
        
        System.out.println("Enter Department: ");
        String department = scanner.nextLine();

        User user = applicationFacade.registerUser(userType, firstName, lastName, uscid, email, username, password,
                department);
        if (user != null) {
            System.out.println("Account successfully created. You can now login.");
        } else {
            System.out.println("Account creation failed. User may already exist.");
        }
    }

    /**
     * View Student info
     * @param advisor
     */
    private static void viewStudentInfo(User loggedInUser) {
        if (loggedIn == false) {
            System.out.println("Please Login to proceed.");
            return;
        }
        //System.out.println("loggedInUser" + loggedInUser);

        if (loggedInUser.getType() == UserType.ADVISOR) {
            Advisor advisor = UserList.getInstance().getAdvisor(loggedInUser.getUuid());
            while (true) {
                System.out.println("Which Student you would like to work with ? Enter his/her USCID:");
                UserList.getInstance().displayStudents();
                String studentId = scanner.nextLine();

                // Search for the student id in the cache.
                User user = UserList.getInstance().getUserByUscId(studentId);
                //System.out.println("Matching User " + user);
                if (user == null) {
                    System.out.println("\nStudent Id:" + studentId + " not found in the database. Please Retry!");
                    continue;
                } else {
                    Student student = UserList.getInstance().getStudent(user.getUuid());
                    student.setAdvisor(advisor);
                    advisor.assignStudent(student);

                    System.out.println("\nYour are now assigned as an Advisor to Student - " + student.getFirstName() + " "
                            + student.getLastName());
                    System.out.println("\nWould like to see current progress of " + student.getFirstName() + " "
                            + student.getLastName() + " ?");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        student.viewCurrentSchedule();
                        System.out.println(
                                "\nWould like to add a note for " + student.getFirstName() + student.getLastName() + " ?");
                        response = scanner.nextLine();
                        if (response.equalsIgnoreCase("yes")) {
                            System.out.println(
                                "\nPlease Enter notes");
                            String note = scanner.nextLine();
                            //String note = "You have taken 2 elective stats classes but havent declared stats as your application area. I reccomment making stats your application area.";
                            student.addNotes(note);                        
                        } else {
                            System.out.println("Good bye!");
                        }
                        break;
                    } else {
                        System.out.println("Good bye!");
                        break;
                    }
                }
            }
        }
    }

    /**
     * Save the info and logout
     */
    private static void logout() {
        loggedIn = false;
    }

    private static void displayRequirement(RequirementType type){
        applicationFacade.displayRequirement(type);
    }

    public Advisor getAdvisor(){
        return advisor;
    }

    public Student getStudent(){
        return student;
    }

    public Student getSelectedStudent(){
        return selectedStudent;
    }

    public ArrayList<Student> getStudents(String fName, String lName){
        ArrayList<Student> students = applicationFacade.getStudents(fName,lName);
        return students;
    }

    public Student findStudent(String fName, String lName){
        return applicationFacade.findStudent(fName, lName);
    }

    public ArrayList<Student> getStudents(String name){
        ArrayList<Student> students = applicationFacade.getStudents(name);
        return students;
    }

    public void createCourse(String name, String code, String number, String description, ArrayList<String> availible){
        Course course = new Course(name, code, number, description, availible);
        courseList.createCourse(course);
    }

    

}