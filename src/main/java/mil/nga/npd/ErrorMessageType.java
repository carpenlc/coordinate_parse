package mil.nga.npd;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration class holding the Error codes and error messages generated by
 * the coordinate parsing algorithms.
 * 
 * @author L. Craig Carpenter
 */
public enum ErrorMessageType {
    ERROR_MINUS_1001(
            "-1001", 
            "Latitude has an invalid character"),
    ERROR_MINUS_1002(
            "-1002", 
            "Longitude has an invalid character"),
    ERROR_MINUS_1003(
            "-1003", 
            "More than one decimal point"),
    ERROR_MINUS_1004(
            "-1004", 
            "More than one direction indicator"),
    ERROR_MINUS_1005(
            "-1005", 
            "Plus or minus has to be on the far left of string"),
    ERROR_MINUS_1006(
            "-1006", 
            "Direction indicator has to be on left or right"),
    ERROR_MINUS_1007(
            "-1007", 
            "Delimited input request requires three fields; Degrees, Minutes, and Seconds"),
    ERROR_MINUS_1008(
            "-1008", 
            "Invalid Input: Undelimited latitude format must be DDMMSS.SSSS"),
    ERROR_MINUS_1009(
            "-1009", 
            "Invalid Input: Undelimited longitude format must be DDDMMSS.SSSS"),
    ERROR_MINUS_1010(
            "-1010", 
            "In DM format, degrees cannot have a decimal point"),
    ERROR_MINUS_1011(
            "-1011", 
            "In DMS format, minutes cannot have a decimal point"),
    ERROR_MINUS_1012(
            "-1012", 
            "Invalid Longitude (Magnitude > 360)"),
    ERROR_MINUS_1013(
            "-1013", 
            "Invalid Latitude (Magnitude > 90)"),
    ERROR_MINUS_1014(
            "-1014", 
            "Invalid second entry (Must be less than 60)"),
    ERROR_MINUS_1015(
            "-1015", 
            "Invalid minute entry (Must be less that 60)"),
    ERROR_MINUS_1016(
            "-1016", 
            "Program Error ParseDMSString"),
    ERROR_MINUS_1017(
            "-1017", 
            "Program Error ParseInputString"),
    ERROR_MINUS_1018(
            "-1018", 
            "Invalid direction indicator for latitude"),
    ERROR_MINUS_1019(
            "-1019", 
            "Invalid direction indicator for longitude"),
    ERROR_MINUS_1020(
            "-1020", 
            "Invalid Latitude greater than 90"),
    ERROR_MINUS_1021(
            "-1021", 
            "Invalid Latitude less than -90"),
    ERROR_MINUS_1022(
            "-1022", 
            "Invalid Longitude greater than 360"),
    ERROR_MINUS_1023(
            "-1023", 
            "Invalid Longitude less than -180"),           
    ERROR_MINUS_1024(
            "-1024", 
            "Invalid Latitude: must be less than or equal to 90"),
    ERROR_MINUS_1025(
            "-1025", 
            "Invalid Latitude: must be greater than or equal to -90"),
    ERROR_MINUS_1026(
            "-1026", 
            "Invalid Longitude: must be less than or equal to 360"),
    ERROR_MINUS_1027(
            "-1027", 
            "Invalid Longitude: must be greater than or equal to -180"),
    UNKNOWN_ERROR(
            "-9999", 
            "Unknown error.");
    
    /**
     * The error code
     */
    private final String code;
    
    /** 
     * The error message
     */
    private final String message;
    
    /**
     * Thread-safe Map containing the code/message key/value pairs.
     */
    private static final Map<String, String> errorMap =
            Collections.unmodifiableMap(initializeMapping());
    
    /**
     * Default constructor requiring the key/value on construction.
     * 
     * @param code The error code.
     * @param message The error message.
     */
    ErrorMessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    /**
     * Getter method for the text representation of the error code.
     * @return The text associate with the error code.
     */
    public String getErrorCode() {
        return code;
    }
    
    /**
     * Getter method for the text representation of the error message.
     * @return The text associate with the error message.
     */
    public String getErrorMessage() {
        return message;
    }
    
    /**
     * Getter method for the error message associated with the incoming
     * code.
     * @param code The error code.
     * @return The error message associated with the input code.
     */
    public static String getErrorMessage(String code) {
        String message = UNKNOWN_ERROR.getErrorMessage();
        if ((code != null) && (!code.isEmpty())) {
            if (errorMap.get(code.trim()) != null) {
                message = errorMap.get(code.trim());
            }
        }
        return message;
    }
    
    /**
     * Getter method for the error message associated with the incoming
     * code.
     * @param code The error code.
     * @return The error message associated with the input code.
     */
    public static String getErrorMessage(double code) {
        String message = UNKNOWN_ERROR.getErrorMessage();
        // Downcast the double to an int to remove everything after the 
        // decimal.
        String strCode = Integer.toString((int)code);
        if ((strCode != null) && (!strCode.isEmpty())) {
            if (errorMap.get(strCode.trim()) != null) {
                message = errorMap.get(strCode.trim());
            }
        }
        return message;
    }
    
    /**
     * Initialize the key/value Map with the error message information.
     * @return The populated Map.
     */
    private static Map<String, String> initializeMapping() {
        Map<String, String> mapping = new HashMap<String, String>();
        for (ErrorMessageType t : ErrorMessageType.values()) {
            mapping.put(t.getErrorCode(), t.getErrorMessage());
        }
        return mapping;
    }
}
