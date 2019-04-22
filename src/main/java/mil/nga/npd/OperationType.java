package mil.nga.npd;

import mil.nga.npd.exceptions.InvalidParameterException;

/**
 * Enumeration object containing a list of the operations supported by the 
 * coordinate parse application.
 * 
 * @author L. Craig Carpenter
 */
public enum OperationType {
    PARSE_LATITUDE("ParseLatitude"),
    PARSE_LATITUDES("ParseLatitudes"),
    PARSE_LONGITUDE("ParseLongitude"),
    PARSE_LONGITUDES("ParseLongitudes"),
    PARSE_COORD_PAIR("ParseCoordPair"),
    PARSE_COORD_PAIRS("ParseCoordPairs"),
    GET_ERROR_MESSAGE("GetErrorMessage");

	/**
	 * The internal text field.
	 */
	private final String text;
	
	/**
	 * Default constructor taking the text name of the operation.
	 * @param value The operation requested.
	 */
	OperationType(String value) {
		text = value;
	}
	
	/**
	 * Getter method for the text representation of the enumeration type.
	 * @return The text associate with the enumeration value.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Convert an input String to it's associated enumeration type.  There is 
	 * no default type.
	 * 
	 * @param value Input text information.
	 * @return The matching OperationType.
	 * @throws InvalidParameterException Thrown if the input value is null, or 
	 * cannot be matched to an operation type.
	 */
	public static OperationType fromString(String value) 
			throws InvalidParameterException {
		if ((value != null) && (!value.isEmpty())) {
			for (OperationType type : OperationType.values()) {
				if (value.equalsIgnoreCase(type.getText())) {
					return type;
				}
			}
			throw new InvalidParameterException(
					"Unknown operation requested.  Operation requested [ "
					+ value
					+ " ].");
		}
		throw new InvalidParameterException(
				"Unknown operation requested.  Operation requested [ "
				+ value
				+ " ].");
	}
}
