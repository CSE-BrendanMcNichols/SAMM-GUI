package backEnd;

public enum RequirementType {
    COREQ,
    PREREQ,
    COREQ_OR_PREREQ,
    CMW,
    ARP,
    SCI,
    GFL,
    GHS,
    GSS,
    AIU,
    CMS,
    INF,
    VSR;

    public static RequirementType StringToType(String typeString) {
        switch(typeString) {
            case  "COREQ":
                return  COREQ;
            case "PREREQ":
                return PREREQ;
            case "COREQ_OR_PREREQ":
                return COREQ_OR_PREREQ;
            case "CMW":
                return CMW;
            case "ARP":
                return ARP;
            case "SCI":
                return SCI;
            case "GFL":
                return GFL;
            case "GHS":
                return GHS;
            case "GSS":
                return GSS;
            case "AIU":
                return AIU;
            case "CMS":
                return CMS;
            case "INF":
                return INF;
            case "VSR":
                return VSR;
            default:
                throw new IllegalArgumentException("Invalid type " + typeString);
        }
    }
}