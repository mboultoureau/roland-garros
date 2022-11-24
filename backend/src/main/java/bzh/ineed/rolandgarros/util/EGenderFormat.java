package bzh.ineed.rolandgarros.util;

import bzh.ineed.rolandgarros.model.EGender;

public class EGenderFormat {
    public static EGender format(String gender) {
        EGender eGender = null;

        switch (gender) {
            case "MALE":
                eGender = EGender.MALE;
                break;
            case "FEMALE":
                eGender = EGender.FEMALE;
                break;
            case "AT_AT":
                eGender = EGender.AT_AT;
                break;
            case "OTHER":
                eGender = EGender.OTHER;
                break;
        }

        return eGender;
    }
}
