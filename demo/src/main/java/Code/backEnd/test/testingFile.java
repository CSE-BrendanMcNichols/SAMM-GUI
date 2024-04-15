package backEnd.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backEnd.Course;
import backEnd.CourseState;
import backEnd.CourseRequirement;
import backEnd.Semester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * possible tests:
 * assertEquals(val1, val2);
 * assertFalse(val);
 * assertTrue(val);
 * assertSame(val1, val2);
 * assertNotSame(val1, val2);
 * assertNull(val);
 * assertNotNull(val);
 */

public class testingFile {
    @Test
    public void makeNullCourse() {
        Course course = new Course(null, null, null, null, null, null, null, 0, 'C', null, null);

        assertNull(course.getCourseNumber());
        assertNull(course.getCourseName());
        assertNull(course.getCourseSubject());
        assertNull(course.getCourseDescription());
        assertNull(course.getCourseHours());
        assertNull(course.getMinGrade());
        assertNull(course.getCourseStatus());
        assertNull(course.getUuid());
        // made by Matt
    }

    @Test
    public void getCourseNullUuid() {
        Course course = new Course();
        course.setUuid(null);
        assertNull(course.getUuid());
        // made by Matt
    }

    @Test
    public void displayCourseWithNullCourseNumber() {
        Course course = new Course();
        course.setCourseNumber(null);
        course.setCourseName("hi");
        course.setCourseSubject("weeee");
        assertNull(course.getCourseNumber());
        // made by Matt
    }

    @Test
    public void displayCourseWithNullCourseName() {
        Course course = new Course();
        course.setCourseNumber("jeerwrwrfr");
        course.setCourseName(null);
        course.setCourseSubject("null");

        assertNull(course.getCourseName());

        // made by Matt
    }

    @Test
    public void displayCourseWithNullCourseSubject() {
        Course course = new Course();
        course.setCourseNumber("NULL");
        course.setCourseName("error");
        course.setCourseSubject(null);
        assertNull(course.getCourseSubject());
        // made by Matt
    }

    @Test
    public void courseAddNullPrerequisite() {
        Course course = new Course();
        course.addPrerequisite(null);
        assertNull(course.getPrerequisites());
        // made by Matt
    }

    @Test
    public void courseAddNullCorequisite() {
        Course course = new Course();
        course.addCorequisite(null);
        assertNull(course.getCorequisites());
        // made by Matt
    }

    @Test
    public void courseRemovePrerequisiteThatItDoesntHave() {
        Course course = new Course();
        CourseRequirement requirement1 = new CourseRequirement(null, null, null, null, null);
        CourseRequirement requirement2 = new CourseRequirement(null, null, null, null, null);
        course.addPrerequisite(requirement1);
        course.addPrerequisite(requirement2);
        CourseRequirement requirement3 = new CourseRequirement(null, null, null, null, null);
        course.removePrerequisite(requirement3);
        assertEquals(2, course.getPrerequisites().size());
        // made by Matt
    }

    @Test
    public void testMinGradeWithCharThatIsntAthroughF() {
        Course course = new Course();
        course.setMinGrade('R');
        assertEquals('F', course.getMinGrade());
        // by Matt
    }

    @Test
    public void courseRemoveCorequisiteThatItDoesntHave() {
        Course course = new Course();
        CourseRequirement requirement1 = new CourseRequirement(null, null, null, null, null);
        CourseRequirement requirement2 = new CourseRequirement(null, null, null, null, null);
        course.addCorequisite(requirement1);
        course.addCorequisite(requirement2);
        CourseRequirement requirement3 = new CourseRequirement(null, null, null, null, null);
        course.removeCorequisite(requirement3);
        assertEquals(2, course.getCorequisites().size());
        // made by Matt
    }

    @Test
    public void addAndPrintNullSemester() {
        Course course = new Course();
        course.addSemesterOffered(null);
        assertNull(course.getCourseAvailability());
        // By Matt
    }

    @Test
    public void courseRemoveSemesterThatItDoesntHave() {
        Course course = new Course();
        course.addSemesterOffered(Semester.Fall);
        course.addSemesterOffered(Semester.Summer);
        assertEquals(2, course.getCourseAvailability().size());
        // by Matt
    }

}