package bzh.ineed.rolandgarros.util;

import bzh.ineed.rolandgarros.model.EStatus;

public class EStatusFormat {
    public static EStatus format(String status) {
        EStatus eStatus = null;
        switch (status) {
            case "IN_PROGRESS":
                eStatus = EStatus.IN_PROGRESS;
                break;
            case "PLANNED":
                eStatus = EStatus.PLANNED;
                break;
            case "UNDEFINED":
                eStatus = EStatus.UNDEFINED;
                break;
            case "FINISH":
                eStatus = EStatus.FINISHED;
                break;
        }
        return eStatus;
    }
}
