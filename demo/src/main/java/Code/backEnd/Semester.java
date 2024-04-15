package backEnd;

public enum Semester {
    Fall,
    Spring,
    Summer;

    public static Semester StringToSemester(String semesterString) {
        switch(semesterString) {
            case  "Fall":
                return  Fall;
            case "Spring":
                return Spring;
            case "Summer":
                return Summer;
            default:
                throw new IllegalArgumentException("Invalid semester " + semesterString);
        }
    }

    
}