package Code.backEnd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Manages a collection of users, including students, advisors, and
 * administrators.
 * Provides functionality to add, update, and delete users, and to retrieve
 * users by username or USC ID.
 */
public class UserList {
    private static UserList userList; // Singleton instance of UserList
    private ArrayList<User> users = new ArrayList<>(); // List to store all users

    private static ArrayList<Student> students = new ArrayList<Student>();
    private static ArrayList<Advisor> advisors = new ArrayList<Advisor>();
    private static ArrayList<Administrator> administrators = new ArrayList<Administrator>();

    /**
     * Private constructor to prevent instantiation.
     * Initializes the list of users.
     */
    private UserList() {
        // users = new ArrayList<>();
        // loadUsers();
    }

    /**
     * Returns the singleton instance of UserList.
     * 
     * @return The singleton instance.
     */
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    public void initializeAdminstrators() {
        administrators = DataLoader.loadAdministrators();
        if (administrators != null)
            administrators.forEach(this::addUser);

    }

    public void initializeStudentsNoAdvisor() {
        students = DataLoader.loadStudentsNoAdvisor();
        if (students != null)
            students.forEach(this::addUser);

    }

    public void initializeAdvisors() {
        advisors = DataLoader.loadAdvisors();
        if (advisors != null)
            advisors.forEach(this::addUser);

    }

    /**
     * Loads users from the data source into the users list.
     */
    private void loadUsers() {

        administrators = DataLoader.loadAdministrators();
        // students = DataLoader.loadStudents();
        advisors = DataLoader.loadAdvisors();

        if (students != null)
            students.forEach(this::addUser);
        if (advisors != null)
            advisors.forEach(this::addUser);
        if (administrators != null)
            administrators.forEach(this::addUser);
    }

    /**
     * Retrieves a user by their username.
     * 
     * @param username The username of the user.
     * @return The user with the specified username, or null if not found.
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            //System.out.println("Login user " + user.getUsername());
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Retrieves a user by their USC ID.
     * 
     * @param uscid The USC ID of the user.
     * @return The user with the specified USC ID, or null if not found.
     */
    public User getUserByUscId(String uscid) {
        for (User user : users) {
            if (user.getUscid().equalsIgnoreCase(uscid)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a new user to the list if they don't already exist.
     * 
     * @param user The user to add.
     * @return true if the user was added, false if the Error::: User already
     *         exists.
     */
    public boolean addUser(User user) {
        if (getUserByUsername(user.getUsername()) != null || getUserByUscId(user.getUscid()) != null) {
            System.out.println("Error::: User already exists." + user.getUsername());
            return false;
        }
        users.add(user);
        return true;
    }

    /**
     * Adds a new student to the list if they don't already exist.
     */
    public boolean addStudent(Student student) {
        if (getUserByUsername(student.getUsername()) != null || getUserByUscId(student.getUscid()) != null) {
            System.out.println("Error::: User already exists." + student.getUsername());
            return false;
        }
        students.add(student);
        System.out.println(
                "Student:" + student.getFirstName() + " " + student.getLastName() + " added successfully " + student);
        // also add to usersList.
        addUser(student);
        return true;
    }

    /**
     * Adds a new Advisor to the list if they don't already exist.
     */
    public boolean addAdvisor(Advisor advisor) {
        if (getUserByUsername(advisor.getUsername()) != null || getUserByUscId(advisor.getUscid()) != null) {
            System.out.println("Error::: User already exists." + advisor.getUsername());
            return false;
        }
        advisors.add(advisor);
        System.out.println("Advisor:" + advisor.getFirstName() + " " + advisor.getLastName() + " added successfully.");
        // also add to usersList.
        addUser(advisor);
        return true;
    }

    /**
     * Adds a new Advisor to the list if they don't already exist.
     */
    public boolean addAdministrator(Administrator administrator) {
        if (getUserByUsername(administrator.getUsername()) != null
                || getUserByUscId(administrator.getUscid()) != null) {
            System.out.println("Error::: User already exists." + administrator.getUsername());
            return false;
        }
        administrators.add(administrator);
        System.out.println("Administrator:" + administrator.getFirstName() + " " + administrator.getLastName()
                + " added successfully.");
        // also add to usersList.
        addUser(administrator);
        return true;
    }

    /**
     * Deletes a user by their username.
     * 
     * @param username The username of the user to delete.
     * @return true if the user was deleted, false otherwise.
     */
    public boolean deleteUserByUsername(String username) {
        return users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
    }

    /**
     * Deletes a user by their USC ID.
     * 
     * @param uscid The USC ID of the user to delete.
     * @return true if the user was deleted, false otherwise.
     */
    public boolean deleteUserByUscId(String uscid) {
        return users.removeIf(user -> user.getUscid().equals(uscid));
    }

    /**
     * Updates the details of an existing user.
     * 
     * @param updatedUser The user with updated details.
     * @return true if the user was updated, false if the user was not found.
     */
    public boolean updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUuid().equals(updatedUser.getUuid())) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Boolean findStudent(UUID uuid) {
        for (Student student : students) {
            if (student.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Student getStudent(UUID uuid) {
        for (Student student : students) {
            if (student.getUuid().equals(uuid)) {
                return student;
            }
        }
        return null;
    }

    public Boolean findAdvisor(UUID uuid) {
        for (Advisor advisor : advisors) {
            if (advisor.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Advisor getAdvisor(UUID uuid) {
        for (Advisor advisor : advisors) {
            if (advisor.getUuid().equals(uuid)) {
                return advisor;
            }
        }
        return null;
    }

    public Boolean findAdministrator(UUID uuid) {
        for (Administrator administrator : administrators) {
            if (administrator.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Administrator getAdministrator(UUID uuid) {
        for (Administrator administrator : administrators) {
            if (administrator.getUuid().equals(uuid)) {
                return administrator;
            }
        }
        return null;
    }

        public void displayStudents() {
            for (Student student : students) {
                System.out.println(student.displayStudent());
            }
        }

        public void clear() {
            if (this.users != null) {
                this.users.clear();
            }
    } // Add this closing brace to complete the class body
}