package Code.backEnd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This is a MajorList singleton class
 * 
 * @author Sree
 */

public class MajorList {

    // Singleton instance
    private static MajorList majorList;

    // List of majors
    private ArrayList<Major> majors;

    /*
     * private constructor to intialize the MajorList.
     * It loads the majors using DataLoader
     */
    private MajorList() {
        majors = DataLoader.loadMajors();
        // just safe side
        if (majors == null) {
            majors = new ArrayList<Major>();
        }
    }

    /**
     * 
     * @return singleton Instance
     */
    public static MajorList getInstance() {
        if (majorList == null) {
            majorList = new MajorList();
        }
        return majorList;
    }

    /**
     * This method adds the given Major object to the list
     * 
     * @param major
     */

    public void addMajor(Major major) {
        if (major != null) {
            this.majors.add(major);
        }
    }

    /**
     * Return the matching major object
     * 
     * @param majorName
     * @return
     */
    public Major getMajor(String majorName) {
        for (Major major : majors) {
            if (major.getMajor().equals(majorName)) {
                return major;
            }
        }
        return null;
    }

    /**
     * This methods finds and replaces old Major with new Major object
     * 
     * @param major
     */
    public void editMajor(Major major) {
        // First find the matching major object
        int mIndex = -1;
        for (int i = 0; i < majors.size(); i++) {
            Major m = (Major) majors.get(i);
            if (m.getMajor().equals(major.getMajor())) {
                mIndex = i;
                break;
            }
        }
        // now replace the old object with new object
        if (mIndex != -1) {
            majors.set(mIndex, major);
        }

    }


    

    public ArrayList<Major> getMajors() {
        return majors;
    }

    /**
     * This method will use DataWriter to store the updated major objects to json file
     */
    public void saveMajors() {
        DataWriter.saveMajors(majors);
    }

     public Boolean findMajor(UUID uuid) {
        for (Major major : majors) {
            if (major.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
    

    public  Major getMajor(UUID uuid) {
        for (Major major : majors) {
            if (major.getUuid().equals(uuid)) {
                return major;
            }
        }
        return null;
    }

}