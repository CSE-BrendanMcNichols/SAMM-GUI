package Code.backEnd;

public enum UserType {
    STUDENT,
    ADVISOR,
    ADMINISTRATOR;

    public static String getTypeString(UserType userType) {
        switch (userType) {
            case STUDENT:
                return "Student";
            case ADVISOR:
                return "Advisor";
            case ADMINISTRATOR:
                return "Administrator";

        }
        return "";
    }

}
