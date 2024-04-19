package Code.backEnd;

/**
 * This is a data loader for the program
 * 
 * @author Morgan
 */
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.UUID;

public class DataLoader extends DataConstants {

	public static ArrayList<Student> loadStudentsNoAdvisor() {
		ArrayList<Student> students = new ArrayList<Student>();

		try {
			FileReader reader = new FileReader(STUDENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray studentsJSON = (JSONArray) new JSONParser().parse(reader);

			for (int i = 0; i < studentsJSON.size(); i++) {
				JSONObject studentJSON = (JSONObject) studentsJSON.get(i);
				String username = (String) studentJSON.get(USERNAME);
				String applicationArea = (String) studentJSON.get(APPLICATIONAREA);
				String firstName = (String) studentJSON.get(FIRST_NAME);
				String lastName = (String) studentJSON.get(LAST_NAME);
				String password = (String) studentJSON.get(PASSWORD);
				String email = (String) studentJSON.get(EMAIL);
				String uscid = (String) studentJSON.get(USCID);
				UUID uuid = UUID.fromString((String) studentJSON.get(UUIDSTRING));
				Year gradeYear = Year.StringToYear((String) studentJSON.get(GRADEYEAR));
				int credits = ((Long) studentJSON.get(CREDITS)).intValue();
				double overallGrade = (double) studentJSON.get(OVERALLGRADE);
				UUID majorUUID = UUID.fromString((String) studentJSON.get(MAJOR));
				Major major = null;
				if (MajorList.getInstance().findMajor(majorUUID)) {
					major = MajorList.getInstance().getMajor(majorUUID);
				} else {
					System.out.println("Major Id: " + majorUUID + " not found in Major List");
				}

				JSONArray notesJSON = (JSONArray) studentJSON.get(NOTES);
				ArrayList<String> notes = new ArrayList<String>();
				for (int j = 0; j < notesJSON.size(); j++) {
					String note = (String) notesJSON.get(j);
					notes.add(note);
				}
				JSONArray coursesJSON = (JSONArray) studentJSON.get(CURRENTCOURSES);
				ArrayList<Course> currentCourses = new ArrayList<Course>();
				for (int j = 0; j < coursesJSON.size(); j++) {
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if (CourseList.getInstance().findCourse(courseUUID)) {
						currentCourses.add(CourseList.getInstance().getCourse(courseUUID));
					} else {
						// System.out.println("Warning: While loading Student current courses, Course
						// Id: " + courseUUID + " not found in Course List");
					}
				}
				JSONObject completedClassesJSON = (JSONObject) studentJSON.get(COMPLETEDCOURSES);
				HashMap<Course, String> completedCourses = new HashMap<>();
				for (Object key : completedClassesJSON.keySet()) {
					String courseString = ((String) key);
					UUID courseUUID = UUID.fromString(courseString);
					Course course = null;
					if (CourseList.getInstance().findCourse(courseUUID)) {
						course = CourseList.getInstance().getCourse(courseUUID);
					} else {
						// System.out.println("Warning: While loading Student completed courses, Course
						// Id: " + courseUUID + " not found in Course List");
					}
					String grade = (String) (completedClassesJSON.get(courseString));
					completedCourses.put(course, grade);
				}
				JSONArray electivesJSON = (JSONArray) studentJSON.get(CURRENTELECTIVES);
				ArrayList<Elective> currentElectives = new ArrayList<Elective>();
				for (int j = 0; j < electivesJSON.size(); j++) {
					UUID electiveUUID = UUID.fromString((String) electivesJSON.get(j));
					if (Cache.getInstance().findElective(electiveUUID)) {
						currentElectives.add(Cache.getInstance().getElective(electiveUUID));
					} else {
						// System.out.println("Warning: While loading Student current elective courses,
						// Elective Id: " + electiveUUID + " not found in Elective List");
					}
				}
				JSONArray completedElectivesJSON = (JSONArray) studentJSON.get(COMPLETEDELECTIVES);
				ArrayList<Elective> completedElectives = new ArrayList<Elective>();
				System.out.println(completedElectives);

				for (int j = 0; j < completedElectives.size(); j++) {
					UUID electiveUUID = UUID.fromString((String) completedElectivesJSON.get(j));
					if (Cache.getInstance().findElective(electiveUUID)) {
						currentElectives.add(Cache.getInstance().getElective(electiveUUID));
					} else {
						// System.out.println("Warning: While loading Student completed elective
						// courses, Elective Id: " + electiveUUID + " not found in Elective List");
					}
				}

				students.add(new Student(firstName, lastName, uscid, email, username, password,
						gradeYear, major, overallGrade, credits, completedCourses, currentCourses,
						notes, uuid, currentElectives, completedElectives, applicationArea));
			}

			System.out.println("*** Successfully Loaded Students");

			return students;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Advisor> loadAdvisors() {
		ArrayList<Advisor> advisors = new ArrayList<Advisor>();

		try {
			FileReader reader = new FileReader(ADVISOR_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray advisorsJSON = (JSONArray) new JSONParser().parse(reader);

			for (int i = 0; i < advisorsJSON.size(); i++) {
				JSONObject advisorJSON = (JSONObject) advisorsJSON.get(i);
				String username = (String) advisorJSON.get(USERNAME);
				String firstName = (String) advisorJSON.get(FIRST_NAME);
				String lastName = (String) advisorJSON.get(LAST_NAME);
				String password = (String) advisorJSON.get(PASSWORD);
				String email = (String) advisorJSON.get(EMAIL);
				String uscid = (String) advisorJSON.get(USCID);
				String department = (String) advisorJSON.get(DEPARTMENT);
				UUID uuid = UUID.fromString((String) advisorJSON.get(UUIDSTRING));
				JSONArray studentsJSON = (JSONArray) advisorJSON.get(ASSIGNED_STUDENTS);
				ArrayList<Student> assignedStudents = new ArrayList<Student>();
				for (int j = 0; j < studentsJSON.size(); j++) {
					UUID studentUUID = UUID.fromString((String) studentsJSON.get(j));
					if (UserList.getInstance().findStudent(studentUUID)) {
						assignedStudents.add(UserList.getInstance().getStudent(studentUUID));
					} else {
						// System.out.println("Warning: While loading Advisor for Student record,
						// Student Id: " + studentUUID + " from is not in the Student List.");
					}
				}
				advisors.add(new Advisor(firstName, lastName, uscid, email, username, password,
						assignedStudents, uuid, department));
			}
			System.out.println("*** Successfully Loaded Advisors");

			return advisors;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Administrator> loadAdministrators() {

		ArrayList<Administrator> administrators = new ArrayList<Administrator>();

		try {
			FileReader reader = new FileReader(ADMINISTRATOR_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray administratorsJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < administratorsJSON.size(); i++) {
				JSONObject administratorJSON = (JSONObject) administratorsJSON.get(i);
				String firstName = (String) administratorJSON.get(FIRST_NAME);
				String lastName = (String) administratorJSON.get(LAST_NAME);
				String username = (String) administratorJSON.get(USERNAME);
				String password = (String) administratorJSON.get(PASSWORD);
				String email = (String) administratorJSON.get(EMAIL);
				String uscid = (String) administratorJSON.get(USCID);
				UUID uuid = UUID.fromString((String) administratorJSON.get(UUIDSTRING));
				administrators.add(new Administrator(firstName, lastName, uscid, email, username,
						password, uuid));
			}
			System.out.println("*** Successfully Loaded Administrators");

			return administrators;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void loadStudentsAdvisor() {

		try {
			FileReader reader = new FileReader(STUDENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray studentsJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < studentsJSON.size(); i++) {
				JSONObject studentJSON = (JSONObject) studentsJSON.get(i);
				// System.out.println(studentJSON);
				UUID studentUUID = UUID.fromString((String) studentJSON.get(UUIDSTRING));


				Advisor advisor = null;
				String advisorUUIDString = (String)studentJSON.get(ADVISOR);
				if (advisorUUIDString != null) {
					UUID advisorUUID = UUID.fromString(advisorUUIDString);
					if (UserList.getInstance().findAdvisor(advisorUUID)) {
						advisor = UserList.getInstance().getAdvisor(advisorUUID);
					}
				}
		
				Student student = null;
				if (UserList.getInstance().findStudent(studentUUID)) {
					student = UserList.getInstance().getStudent(studentUUID);
					// update the student's Advisor. It will update the central instance of Student
					// in Cache
					student.setAdvisor(advisor);
				} else {
					// System.out.println("Warning: While loading Advisor for Student record,
					// Student Id: " + studentUUID + " is not in the student List.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadCoursesWithRequirements() {
		ArrayList<String> errors = new ArrayList<String>();

		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray coursesJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < coursesJSON.size(); i++) {
				JSONObject courseJSON = (JSONObject) coursesJSON.get(i);
				UUID courseUuid = UUID.fromString((String) courseJSON.get(UUIDSTRING));
				Course course = null;

				// read prerequisites
				JSONArray prereqsJSON = (JSONArray) courseJSON.get(PREREQUISITES);
				ArrayList<CourseRequirement> prerequisites = new ArrayList<CourseRequirement>();
				if (prereqsJSON != null) {
					for (int j = 0; j < prereqsJSON.size(); j++) {
						UUID reqUUID = UUID.fromString((String) prereqsJSON.get(j));
						if (Cache.getInstance().findRequirement(reqUUID)) {
							prerequisites.add(Cache.getInstance().getRequirement(reqUUID));
						} else {
							// System.out.println("Warning: While loading Requirment for Course record,
							// Requirement Id: " + reqUUID + " is not in the Requirement List.");
							errors.add(reqUUID.toString());
						}
					}
				}
				// System.out.println("updating Course Id: " + courseUuid + " with pre and co
				// requisites");

				// read corequisites
				JSONArray coreqsJSON = (JSONArray) courseJSON.get(COREQUISITES);
				ArrayList<CourseRequirement> corequisites = new ArrayList<CourseRequirement>();
				if (coreqsJSON != null) {
					for (int j = 0; j < coreqsJSON.size(); j++) {
						UUID reqUUID = UUID.fromString((String) coreqsJSON.get(j));
						if (Cache.getInstance().findRequirement(reqUUID)) {
							corequisites.add(Cache.getInstance().getRequirement(reqUUID));
						} else {
							// System.out.println("Warning: While loading Requirement for Course record,
							// Requirement Id: " + reqUUID + " is not in the Requirement List.");
							errors.add(reqUUID.toString());
						}
					}
				}
				// System.out.println("updating Course Id: " + courseUuid + " with co
				// requisites" + "\n" );

				// update course with Pre and Co Requisites
				// *This should update the central Cache.getInstance() Course References.*
				if (CourseList.getInstance().findCourse(courseUuid)) {
					// System.out.println("updating Course Id: " + courseUuid + " with pre and co
					// requisites"+ prerequisites + "\n" + corequisites);

					course = CourseList.getInstance().getCourse(courseUuid);
					course.setPrerequisites(prerequisites);
					course.setCorequisites(corequisites);
				} else {
					// System.out.println("Warning: While loading Requirement for course record,
					// Course Id: " + courseUuid + " is not in the Course List.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*** Successfully Loaded Courses with Requirements");

		for (String str : errors) {
			// System.out.println(str);
		}

	}

	/**
	 * Load Courses from json file without requirements
	 * 
	 * @return
	 */
	public static ArrayList<Course> loadCoursesMinusRequirements() {
		// System.out.println("*** Loading Courses without Requirements");

		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray coursesJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < coursesJSON.size(); i++) {
				JSONObject courseJSON = (JSONObject) coursesJSON.get(i);
				UUID uuid = UUID.fromString((String) courseJSON.get(UUIDSTRING));
				String courseName = (String) courseJSON.get(COURSENAME);
				String courseSubject = (String) courseJSON.get(COURSESUBJECT);
				String courseNumber = ((String) courseJSON.get(COURSENUMBER));
				JSONArray semesters = (JSONArray) courseJSON.get(COURSESEMESTER);
				ArrayList<Semester> courseSemester = new ArrayList<Semester>();
				for (int j = 0; j < semesters.size(); j++) {
					String semester = (String) semesters.get(j);
					courseSemester.add(Semester.StringToSemester(semester));
				}

				String courseDescription = (String) courseJSON.get(COURSEDESCRIPTION);
				// System.out.println("*** Loaded " + courseName);
				int courseHours = ((Long) courseJSON.get(COURSEHOURS)).intValue();
				char minGrade = ((String) courseJSON.get(MINGRADE)).charAt(0);
				CourseState courseState = CourseState.StringToCourseState((String) courseJSON.get(COURSESTATUS));

				courses.add(new Course(courseName, courseSubject, courseNumber, courseSemester,
						courseDescription, courseHours, minGrade, courseState, uuid));
			}
			System.out.println("*** Successfully Loaded Courses without Requirements: " + courses.size());

			reader.close();
			return courses;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Load Requirements from database (json file)
	 * 
	 * @return
	 */
	public static ArrayList<CourseRequirement> loadRequirements() {
		ArrayList<CourseRequirement> requirements = new ArrayList<CourseRequirement>();

		try {
			FileReader reader = new FileReader(REQUIREMENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray requirementsJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < requirementsJSON.size(); i++) {
				JSONObject requirementJSON = (JSONObject) requirementsJSON.get(i);
				Boolean eitherOr = (Boolean) requirementJSON.get(EITHEROR);
				RequirementType type = RequirementType.StringToType((String) requirementJSON.get(TYPETEST));
				String requirementFor = (String) requirementJSON.get(REQUIREMENTFOR);
				UUID uuid = UUID.fromString((String) requirementJSON.get(UUIDSTRING));
				JSONArray coursesJSON = (JSONArray) requirementJSON.get(COURSES);
				ArrayList<Course> reqCourses = new ArrayList<Course>();
				for (int j = 0; j < coursesJSON.size(); j++) {
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if (CourseList.getInstance().findCourse(courseUUID)) {
						reqCourses.add(CourseList.getInstance().getCourse(courseUUID));
					} else {
						// System.out.println("Warning: While loading Requirement record, Course Id: " +
						// courseUUID + " is not in the Course List.");
					}
				}
				requirements.add(new CourseRequirement(reqCourses, eitherOr, type, requirementFor, uuid));
			}

			System.out.println("*** Successfully Loaded Requirements");

			return requirements;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Load Electives from database (json file)
	 * 
	 * @return
	 */

	public static ArrayList<Elective> loadElectives() {

		ArrayList<Elective> electives = new ArrayList<Elective>();
		try {
			FileReader reader = new FileReader(ELECTIVE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray electivesJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < electivesJSON.size(); i++) {
				JSONObject electiveJSON = (JSONObject) electivesJSON.get(i);
				String electiveName = (String) electiveJSON.get(ELECTIVENAME);
				int hours = ((Long) electiveJSON.get(HOURS)).intValue();
				UUID uuid = UUID.fromString((String) electiveJSON.get(UUIDSTRING));
				JSONArray coursesJSON = (JSONArray) electiveJSON.get(COURSES);
				ArrayList<Course> courses = new ArrayList<Course>();
				for (int j = 0; j < coursesJSON.size(); j++) {
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if (CourseList.getInstance().findCourse(courseUUID)) {
						courses.add(CourseList.getInstance().getCourse(courseUUID));
					} else {
						// System.out.println("Warning: While loading Elective record, Course Id: " +
						// courseUUID + " is not in the Course List.");
					}
				}
				electives.add(new Elective(courses, electiveName, hours, uuid));
			}
			System.out.println("*** Successfully Loaded Electives");

			return electives;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Load ApplicationArea from database (json file)
	 * 
	 * @return
	 */

	public static ArrayList<ApplicationArea> loadApplicaitonAreas() {
		ArrayList<ApplicationArea> areas = new ArrayList<ApplicationArea>();
		try {
			FileReader reader = new FileReader(AREA_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray areasJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < areasJSON.size(); i++) {
				JSONObject areaJSON = (JSONObject) areasJSON.get(i);
				String area = (String) areaJSON.get(AREANAME);
				int hours = ((Long) areaJSON.get(HOURS)).intValue();
				// System.out.println(areaJSON.get(UUIDSTRING));

				UUID uuid = UUID.fromString((String) areaJSON.get(UUIDSTRING));
				JSONArray coursesJSON = (JSONArray) areaJSON.get(COURSES);
				ArrayList<Course> courses = new ArrayList<Course>();
				for (int j = 0; j < coursesJSON.size(); j++) {
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if (CourseList.getInstance().findCourse(courseUUID)) {
						courses.add(CourseList.getInstance().getCourse(courseUUID));
					} else {
						// System.out.println("Warning: While loading ApplicationArea, Course Id: " +
						// courseUUID + " is not in the Course List.");
					}
				}
				areas.add(new ApplicationArea(courses, area, hours, uuid));
			}
			System.out.println("*** Successfully Loaded ApplicaitonAreas.");

			return areas;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * This method loads the Majors from database(json file)
	 * 
	 * @return
	 */
	public static ArrayList<Major> loadMajors() {

		ArrayList<Major> majors = new ArrayList<Major>();
		try {
			FileReader reader = new FileReader(MAJORS_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray majorsJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < majorsJSON.size(); i++) {
				JSONObject majorJSON = (JSONObject) majorsJSON.get(i);
				// read "major"
				String major = (String) majorJSON.get(MAJOR);
				// read "uuid"
				UUID uuid = UUID.fromString((String) majorJSON.get(UUIDSTRING));
				// read courses UUID array
				JSONArray coursesJSON = (JSONArray) majorJSON.get(COURSES);
				ArrayList<Course> courses = new ArrayList<Course>();
				for (int j = 0; j < coursesJSON.size(); j++) {
					UUID courseUUID = UUID.fromString((String) coursesJSON.get(j));
					if (CourseList.getInstance().findCourse(courseUUID)) {
						courses.add(CourseList.getInstance().getCourse(courseUUID));
					} else {
						// System.out.println("Warning: While loading Major record, Course Id: " +
						// courseUUID + " is not in the Course List.");
					}
				}

				// read elective
				Elective majorElective = new Elective();
				UUID electiveUuid = UUID.fromString((String) majorJSON.get(ELECTIVE_COURSES));
				if (Cache.getInstance().findElective(electiveUuid)) {
					majorElective = Cache.getInstance().getElective(electiveUuid);
				} else {
					// System.out.println("Warning: While loading Major3 record, Elective Id: " +
					// electiveUuid + " is not in the Elective List.");
				}

				// read core req
				JSONObject coreReqJSON = (JSONObject) majorJSON.get(COREREQ);
				HashMap<RequirementType, Integer> coreReq = new HashMap<>();
				for (Object key : coreReqJSON.keySet()) {
					RequirementType coreReqName = RequirementType.StringToType(((String) key));
					int coreReqValue = Integer.parseInt(coreReqJSON.get(coreReqName.toString()).toString());
					coreReq.put(coreReqName, coreReqValue);
				}

				majors.add(new Major(courses, coreReq, majorElective, major, uuid));
			}

			System.out.println("*** Successfully Loaded Majors");

			return majors;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
// Made no changes
