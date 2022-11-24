package bzh.ineed.rolandgarros.util;

import bzh.ineed.rolandgarros.model.ERound;

public class ERoundFormat {
    public static ERound format(String round) {
        ERound eRound = null;
        switch (round) {
            case "FIRST_ROUND":
                eRound = ERound.FIRST_ROUND;
                break;
            case "SECOND_ROUND":
                eRound = ERound.SECOND_ROUND;
                break;
            case "THIRD_ROUND":
                eRound = ERound.THIRD_ROUND;
                break;
            case "SIXTEENTH_ROUND":
                eRound = ERound.SIXTEENTH_ROUND;
                break;
            case "QUART_FINAL":
                eRound = ERound.QUART_FINAL;
                break;
            case "SEMI_FINAL":
                eRound = ERound.SEMI_FINAL;
                break;
            case "FINAL_ROUND":
                eRound = ERound.FINAL_ROUND;
                break;
        }
        return eRound;
    }
}
