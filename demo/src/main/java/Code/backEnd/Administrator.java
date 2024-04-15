package Code.backEnd;

import java.util.ArrayList;
import java.util.UUID;

public class Administrator extends User {


    public Administrator(String firstName, String lastName, String uscid, String email, String username, String password) {
        super(firstName,lastName,uscid,email,username, password, UserType.ADMINISTRATOR );
    }
    public Administrator(String firstName, String lastName, String uscid, String email, String username, String password, UUID uuid) {
        super(uuid,firstName,lastName,uscid,email,username, password, UserType.ADMINISTRATOR);
    }

    private void overrideStudentGrade(String name, char grade) {
        System.out.println("The new grade for " + name + "will be " + grade);
    }

    private void updateMajor(String majorDetails) {
        System.out.println("The new major is " + majorDetails);
    }

    private void overrideCourseReq(Course course, ArrayList<Course> newPreReq) {
        //TODO implement logic
    }

    public boolean riskOfFaliure(Student student) {
        if(student.riskOfFailure()) {
            return true;
        }
        
        return false;
    }
    @Override
    public String toString() {
        return super.toString();
    }


    
}
