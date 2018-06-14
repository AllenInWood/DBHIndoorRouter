package servlet.servlets.common;

public class VerifyUtil {
    public static boolean verify(String startRoomNo, String destinationRoomNo) {
        return isValidNo(startRoomNo) && isValidNo(destinationRoomNo);
    }

    private static boolean isValidNo(String roomNo) {
        if (roomNo == null || roomNo == "") {
            return false;
        }
        for (int i = roomNo.length() - 1; i >= 0; i--) {
            if (!Character.isDigit(roomNo.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumericString(String roomInput) {
        for (int i = 0; i < roomInput.length(); i++) {
            if (!Character.isDigit(roomInput.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
