package backEnd.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import backEnd.Advisor;
import backEnd.Student;

import java.util.ArrayList;
public class AdvisorTester {
    
    //testing assignedStudents
    @Test
    public void testAdvisorAssignStudentNormal(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        advisor.assignStudent(student);
        assertEquals(student , advisor.getAssignedStudents().get(0));
    }

    @Test
    public void testAdvisorAssignStudentNull(){ //not supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        advisor.assignStudent(null);
        assertEquals(student , advisor.getAssignedStudents().get(0));
    }

    @Test
    public void testAdvisorAssignTwoStudents(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        Student student2 = new Student();
        advisor.assignStudent(student);
        advisor.assignStudent(student2);
        assertEquals(student , advisor.getAssignedStudents().get(0));
        assertEquals(student2 , advisor.getAssignedStudents().get(1));
    }

    //tesing unassignedStudent
    @Test
    public void testAdvisorUnassignStudentNormal(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        ArrayList<Student> assignedStudents = new ArrayList<Student>();
        assignedStudents.add(student);
        advisor.setAssignedStudents(assignedStudents);
        advisor.unAssignStudent(student);
        assertEquals(0 , advisor.getAssignedStudents().size(), "Unassigned the Student: \" + student.getUsername() + \" from advisor\" + advisor.getUsername())");
    }

    @Test
    public void testAdvisorUnassignStudentEmpty(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        ArrayList<Student> assignedStudents = new ArrayList<Student>();
        advisor.setAssignedStudents(assignedStudents);
        advisor.unAssignStudent(student);
        assertEquals(0, advisor.getAssignedStudents().get(0), "There are no students assigned");
    }

    @Test
    public void testAdvisorUnassignFirstStudentOfTwo(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        Student student2 = new Student();
        ArrayList<Student> assignedStudents = new ArrayList<Student>();
        assignedStudents.add(student);
        assignedStudents.add(student2);
        advisor.setAssignedStudents(assignedStudents);
        advisor.unAssignStudent(student);
        assertEquals(student2 , advisor.getAssignedStudents().get(0), "Unassigned the Student: \" + student.getUsername() + \" from advisor\" + advisor.getUsername())");
    }

    @Test
    public void testAdvisorUnassignLastStudentOfThree(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        ArrayList<Student> assignedStudents = new ArrayList<Student>();
        assignedStudents.add(student);
        assignedStudents.add(student2);
        assignedStudents.add(student3);
        advisor.setAssignedStudents(assignedStudents);
        advisor.unAssignStudent(student3);
        assertEquals(2 , advisor.getAssignedStudents().size(), "Unassigned the Student: \" + student.getUsername() + \" from advisor\" + advisor.getUsername())");
    }

    @Test
    public void testMakeNote(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student = new Student();
        advisor.assignStudent(student);
        advisor.makeNote(student, "");
        assertEquals("" , advisor.getAssignedStudents().get(0).getNotes().get(0), "Notes correctly added");
    }

    @Test
    public void testMakeNotetoUnAssignedStudent(){ //supposed to pass
        Advisor advisor = new Advisor();
        Student student1 = new Student();
        student1.setUsername("testStudent");
        advisor.assignStudent(student1);

        Student student2 = new Student();
        advisor.makeNote(student2, "trying to add note to unassigned student ");

        Student testStudent = advisor.searchStudentByUserName("testStudent");
        assertTrue(testStudent.getNotes().isEmpty());
    }
   
}
