package backEnd;

public enum Year {
    Freshman,
    Sophomore,
    Junior,
    Senior;


    public static Year StringToYear(String yearString) {
        switch(yearString) {
            case  "Freshman":
                return  Freshman;
            case "Sophomore":
                return Sophomore;
            case "Junior":
                return Junior;
            case "Senior":
                return Senior;
            default:
                throw new IllegalArgumentException("Invalid year " + yearString);
        }
    }
}