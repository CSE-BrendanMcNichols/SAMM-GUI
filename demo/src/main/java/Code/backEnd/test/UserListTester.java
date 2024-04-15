package backEnd.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import backEnd.*;

public class UserListTester {

    private UserList userList;
    private UUID studentUuid = UUID.randomUUID();
    private UUID advisorUuid = UUID.randomUUID();
    private UUID adminUuid = UUID.randomUUID();

    @BeforeEach
    public void setup() {
        userList = UserList.getInstance();
        // Assuming a clear method is implemented to reset userList state
        userList.clear();

        // Initialize the userList with basic users for testing
        Student student = new Student("Alice", "Example", "123456789", "alice@example.edu", "aliceStudent", "password1", Year.Sophomore, null, new Major("Computer Science"), 3.5, 30, null, null, new ArrayList<>(), studentUuid, new ArrayList<>(), new ArrayList<>(), "Software Engineering");
        userList.addUser(student);

        Advisor advisor = new Advisor("Bob", "Example", "987654321", "bob@example.edu", "bobAdvisor", "password2", "Engineering");
        advisor.setUuid(advisorUuid);
        userList.addUser(advisor);

        Administrator admin = new Administrator("Charlie", "Example", "1122334455", "charlie@example.edu", "charlieAdmin", "password3", adminUuid);
        userList.addUser(admin);
    }

    @AfterEach
    public void tearDown() {
        // Clear userList after each test
        userList.clear();
    }

    @Test
    public void testCreateUser_NotExists() {
        Advisor newAdvisor = new Advisor("Diana", "Advisor", "2233445566", "diana@example.edu", "dianaAdvisor", "password4", "Science");
        boolean userCreated = userList.addUser(newAdvisor);
        assertTrue("User should be added successfully", userCreated);
    }

    @Test
    public void testCreateUser_Exists() {
        Student duplicateStudent = new Student("Alice", "Example", "123456789", "alice@example.edu", "aliceStudent", "password1", Year.Sophomore, null, new Major("Computer Science"), 3.5, 30, null, null, new ArrayList<>(), studentUuid, new ArrayList<>(), new ArrayList<>(), "Software Engineering");
        boolean userCreated = userList.addUser(duplicateStudent);
        assertFalse("Duplicate user should not be added", userCreated);
    }

    @Test
    public void testDeleteUser_Exists() {
        boolean userDeleted = userList.deleteUserByUsername("aliceStudent");
        assertTrue("User should be deleted successfully", userDeleted);
    }

    @Test
    public void testDeleteUser_NotExists() {
        boolean userDeleted = userList.deleteUserByUsername("nonexistentUser");
        assertFalse("Nonexistent user should not be deleted", userDeleted);
    }

    @Test
    public void testFindUserByUsername_Exists() {
        User foundUser = userList.getUserByUsername("aliceStudent");
        assertNotNull("User should be found by username", foundUser);
    }

    @Test
    public void testFindUserByUsername_NotExists() {
        User foundUser = userList.getUserByUsername("nonexistentUser");
        assertNull("Nonexistent user should not be found", foundUser);
    }

    @Test
    public void testUpdateUser_Exists() {
        User userToUpdate = userList.getUserByUsername("bobAdvisor");
        assertNotNull("User to update should exist", userToUpdate);

        userToUpdate.setEmail("updated@example.edu");
        boolean updated = userList.updateUser(userToUpdate);
        assertTrue("User should be updated successfully", updated);

        User updatedUser = userList.getUserByUsername("bobAdvisor");
        assertEquals("User email should be updated", "updated@example.edu", updatedUser.getEmail());
    }

    @Test
    public void testUpdateUser_NotExists() {
        User nonExistentUser = new Administrator("NonExistent", "User", "0000000000", "nonexistent@example.edu", "nonexistentUser", "password", UUID.randomUUID());
        boolean updated = userList.updateUser(nonExistentUser);
        assertFalse("Nonexistent user should not be updated", updated);
    }

}
