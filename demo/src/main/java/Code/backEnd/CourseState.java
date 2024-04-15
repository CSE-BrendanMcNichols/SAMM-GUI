package Code.backEnd;

public enum CourseState {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED;
    
    public static CourseState StringToCourseState(String statusString) {
        switch(statusString) {
            case  "NOT_STARTED":
                return  NOT_STARTED;
            case "IN_PROGRESS":
                return IN_PROGRESS;
            case "COMPLETED":
                return COMPLETED;
            default:
                throw new IllegalArgumentException("Invalid semester " + statusString);
        }
    }
}
