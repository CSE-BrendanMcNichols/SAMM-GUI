package backEnd.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import backEnd.Course;
import backEnd.Major;
import backEnd.RequirementType;
//Morgan Made and Ran these Tests
public class MajorTester {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeEach
	public void setup() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}

    @Test
    public void testAddCourseBaseLine(){
        Major major = new Major();
        Course testCourse = new Course();
        major.addCourse(testCourse);
        assertEquals(testCourse , major.getCourses().get(0));
    }

    @Test
    public void testAddCourseNull(){
        Major major = new Major();
        major.addCourse(null);
        assertEquals(0 , major.getCourses().size());
    }

    @Test
    public void testAddCourseDuplicate(){
        Major major = new Major();
        Course testCourse = new Course();
        major.addCourse(testCourse);
        major.addCourse(testCourse);
        assertEquals(1 , major.getCourses().size());
    }

    @Test
    public void testAddCoreReqBaseLine(){
        Major major = new Major();
        major.addCoreReq(RequirementType.ARP, 3);
        assertEquals(1 , major.getCoreReq().size());
    }

    @Test
    public void testAddCoreReqBaseLineType(){
        Major major = new Major();
        major.addCoreReq(RequirementType.ARP, 3);
        assertEquals(RequirementType.ARP, major.getCoreReq().keySet().iterator().next());
    }

    @Test
    public void testAddCoreReqBaseLineHours(){
        Major major = new Major();
        major.addCoreReq(RequirementType.ARP, 3);
        assertEquals(RequirementType.ARP, major.getCoreReq().values().iterator().next());
    }

    @Test
    public void testAddCoreReqNull(){
        Major major = new Major();
        major.addCoreReq(null, 3);
        assertEquals(0 , major.getCoreReq().size());
    }

    @Test
    public void testAddCoreReqDuplicate(){
        Major major = new Major();
        major.addCoreReq(RequirementType.ARP, 3);
        major.addCoreReq(RequirementType.ARP, 3);
        assertEquals(1 , major.getCoreReq().size());
    }

    @Test
    public void testToStringNoValues(){
        Major major = new Major();
        assertEquals("Major [courses=[], coreReq={}, electiveCourses=default name, major=DefaultMajor, uuid=55e3b0d4-473d-407e-9b35-5afd194c7d99]" , major.toString());
    }

    @Test
    public void testToStringCourses(){
        Major major = new Major();
        major.addCourse(new Course());
        assertEquals("Major [courses=[EMPTY CLASS], coreReq={}, electiveCourses=default name, major=DefaultMajor, uuid=55e3b0d4-473d-407e-9b35-5afd194c7d99]" , major.toString());
    }

    @Test
    public void testToStringCoreReq(){
        Major major = new Major();
        major.addCoreReq(RequirementType.ARP, 3);
        assertEquals("Major [courses=[], coreReq={(ARP, 3 Hours)}, electiveCourses=default name, major=DefaultMajor, uuid=55e3b0d4-473d-407e-9b35-5afd194c7d99]" , major.toString());
    }

    @Test
    public void testRemoveCourseBaseLine(){
        Major major = new Major();
        Course testCourse = new Course();
        major.addCourse(testCourse);
        major.removeCourse(testCourse);
        assertEquals(0 , major.getCourses().size());
    }

    @Test
    public void testRemoveCourseNoCourses(){
        Major major = new Major();
        Course testCourse = new Course();
        major.removeCourse(testCourse);
        assertEquals(0 , major.getCourses().size());
    }

    @Test
    public void testRemoveCourseNull(){
        Major major = new Major();
        Course testCourse = new Course();
        major.addCourse(testCourse);
        major.removeCourse(null);
        assertEquals(1 , major.getCourses().size());
    }

    @Test
    public void testRemoveCourseTwoCourses(){
        Major major = new Major();
        Course testCourse = new Course();
        Course testCourse2 = new Course();
        major.addCourse(testCourse);
        major.addCourse(testCourse2);
        major.removeCourse(testCourse2);
        assertEquals(testCourse , major.getCourses().get(0));
    }
    //All test below involve outputing to the console and as such the test don't work properly so manually chack what it calls an error and determine if it truly failed the tests
    //These Tests don't seem to properly catch the errors so manually check if they succeed or fail
    @Test
    public void testDisplayCoreReqBaseLine(){
        Major major = new Major();
        HashMap<RequirementType, Integer> hash = new HashMap<RequirementType, Integer>();
        hash.put(RequirementType.ARP, 3);
        major.setCoreReq(hash);
        major.displaycoreReq();
        assertEquals("ARP 3"+
                    "\n", outputStreamCaptor.toString());
    }

    @Test
    public void testDisplayCoreReqTwoReqs(){
        Major major = new Major();
        HashMap<RequirementType, Integer> hash = new HashMap<RequirementType, Integer>();
        hash.put(RequirementType.ARP, 3);
        hash.put(RequirementType.AIU, 5);
        major.setCoreReq(hash);
        major.displaycoreReq();
        assertEquals("AIU 5"+
                     "\nARP 3"+
                     "\n", outputStreamCaptor.toString());
    }

    @Test
    public void testDisplayCoreReqNoCoreReq(){
        Major major = new Major();
        major.displaycoreReq();
        assertEquals("No Core Reqs"+
                    "\n", outputStreamCaptor.toString());
    }

}
