package backEnd;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This is an ApplicationArea class
 * 
 * @author Sree
 */

public class ApplicationArea {
    private ArrayList<Course> courses;
    private String area;
    private int hours;
    private UUID uuid;

    public ApplicationArea(ArrayList<Course> courses, String area, int hours, UUID uuid){
        this.courses = courses;
        this.area = area;
        this.hours = hours;
        this.uuid = uuid;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ApplicationArea [courses=" + courses + ", area=" + area + ", hours=" + hours + ", uuid=" + uuid + "]";
    }

    
}
