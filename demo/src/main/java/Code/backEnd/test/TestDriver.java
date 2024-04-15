package backEnd.test;

import backEnd.ApplicationFacade;
import backEnd.Major;

public class TestDriver {
    private ApplicationFacade facade;

    TestDriver() {
        // facade = new SchedulingFacade;
    }

    public void run() {

    }

    public static void main(String[] args) {

        Major major = new Major();
        System.out.println(major);
    }

}