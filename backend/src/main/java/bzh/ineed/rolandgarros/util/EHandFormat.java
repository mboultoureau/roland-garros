package bzh.ineed.rolandgarros.util;

import bzh.ineed.rolandgarros.model.EHand;

public class EHandFormat {
    public static EHand format(String hand) {
        EHand eHand = null;

        switch (hand) {
            case "LEFT":
                eHand = EHand.LEFT;
                break;
            case "RIGHT":
                eHand = EHand.RIGHT;
                break;
            case "AMBIDEXE":
                eHand = EHand.AMBIDEXE;
                break;
        }

        return eHand;
    }

}
