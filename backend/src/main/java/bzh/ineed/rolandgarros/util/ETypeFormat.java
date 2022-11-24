package bzh.ineed.rolandgarros.util;

import bzh.ineed.rolandgarros.model.EType;

public class ETypeFormat {

    public static EType format(String type) {
        EType eType = null;
        switch (type) {
            case "SIMPLE_MEN":
                eType = EType.SIMPLE_MEN;
                break;
            case "SIMPLE_WOMEN":
                eType = EType.SIMPLE_WOMEN;
                break;
            case "DOUBLE_MEN":
                eType = EType.DOUBLE_MEN;
                break;
            case "DOUBLE_WOMEN":
                eType = EType.DOUBLE_WOMAN;
                break;
            case "MIXED":
                eType = EType.MIXED;
                break;
        }
        return eType;
    }
}
