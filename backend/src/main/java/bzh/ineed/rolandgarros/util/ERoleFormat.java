package bzh.ineed.rolandgarros.util;

import bzh.ineed.rolandgarros.model.ERole;

public class ERoleFormat {
    public static ERole format(String role) {
        ERole eRole = null;
        switch (role) {
            case "ROLE_USER":
                eRole = ERole.ROLE_USER;
                break;
            case "ROLE_EDITOR_PLAYER":
                eRole = ERole.ROLE_EDITOR_PLAYER;
                break;
            case "ROLE_EDITOR_MATCH":
                eRole = ERole.ROLE_EDITOR_MATCH;
                break;
            case "ROLE_ADMIN":
                eRole = ERole.ROLE_ADMIN;
                break;
        }
        return eRole;
    }
}
