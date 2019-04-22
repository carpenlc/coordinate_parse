package mil.nga.npd;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.gov.ic.ism.v2.ClassificationType;
import mil.nga.coordinate_pair.CoordinatePair;
import mil.nga.coordinate_pair.CoordinatePairString;
import mil.nga.coordinate_parse.CoordinateParse;
import mil.nga.coordinate_parse.GetErrorMessage;
import mil.nga.coordinate_parse.GetErrorMessageResponse;
import mil.nga.coordinate_parse.ParseCoordPair;
import mil.nga.coordinate_parse.ParseCoordPairResponse;
import mil.nga.coordinate_parse.ParseCoordPairs;
import mil.nga.coordinate_parse.ParseCoordPairsResponse;
import mil.nga.coordinate_parse.ParseLatitude;
import mil.nga.coordinate_parse.ParseLatitudeResponse;
import mil.nga.coordinate_parse.ParseLatitudes;
import mil.nga.coordinate_parse.ParseLatitudesResponse;
import mil.nga.coordinate_parse.ParseLongitude;
import mil.nga.coordinate_parse.ParseLongitudeResponse;
import mil.nga.coordinate_parse.ParseLongitudes;
import mil.nga.coordinate_parse.ParseLongitudesResponse;
import mil.nga.security.SecurityElement;

@WebService(
        serviceName = "coordinate_parse_service", 
        portName = "CoordinateParseServicePort", 
        endpointInterface = "mil.nga.coordinate_parse.CoordinateParse", 
        targetNamespace = "mil:nga:coordinate_parse", 
        wsdlLocation = "WEB-INF/wsdl/coordinate_parse_service.wsdl")
public class CoordinateParseService implements CoordinateParse {

    /**
     * Set up the Logback system for use throughout the class.
     */
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(CoordinateParseService.class);
    
    /**
     * Security element object appended to each return object.
     */
    private SecurityElement secElement;
    
    /**
     * Default constructor.
     */
    public CoordinateParseService () {
        secElement = new SecurityElement();
        secElement.setClassification(ClassificationType.U);
        secElement.getOwnerProducer().add("USA");
    }
    
    /**
     * Simple method to return the error message associated with the input 
     * error code.
     * 
     * @param parameters WSDL-generated object containing the user-supplied 
     * input parameters.
     * @return A response that includes the error message associated with 
     * the input error code.  If the error code is not matched to an existing 
     * message and "Unknown error" message is returned.
     */
    @Override
    public GetErrorMessageResponse getErrorMessage(
            GetErrorMessage parameters) {
        
        GetErrorMessageResponse response = new GetErrorMessageResponse();
        response.setSecurity(secElement);
        
        if (parameters != null) {
            response.setErrorMessage(
                    ErrorMessageType.getErrorMessage(
                            parameters.getErrorNum()));
        }
        else {
            LOGGER.warn("Input parameter object was null.  Response will "
                    + "be [ "
                    + ErrorMessageType.UNKNOWN_ERROR.getErrorMessage()
                    + " ].");
            response.setErrorMessage(
                    ErrorMessageType.UNKNOWN_ERROR.getErrorMessage());
        }
        return response;
    }

    /**
     * Method used to parse a single coordinate pair.
     * 
     * @param parameters WSDL-generated object containing the user-supplied 
     * input parameters.
     * @return A response containing the results of the parsing operation. 
     * @throws UnsupportedOperationException Thrown if there are problems with 
     * the input parameters object.
     */
    @Override
    public ParseCoordPairResponse parseCoordPair(ParseCoordPair parameters) {
        
        ParseCoordPairResponse response = new ParseCoordPairResponse();
        response.setSecurity(secElement);
        
        if (parameters != null) {
            if (parameters.getCoordinatePairString() != null) {
                if ((parameters.getCoordinatePairString().getLatitude() != null) &&
                        (!parameters.getCoordinatePairString().getLatitude().isEmpty())) {
                    if ((parameters.getCoordinatePairString().getLongitude() != null) && 
                            (!parameters.getCoordinatePairString().getLongitude().isEmpty())){
                        
                        CoordsParse    parser = new CoordsParse();
                        CoordinatePair coords = new CoordinatePair();
                        
                        coords.setLatitude(
                                parser.parseCoordString(
                                        parameters.getCoordinatePairString().getLatitude(), 
                                        true));
                        coords.setLongitude(                                
                                parser.parseCoordString(
                                        parameters.getCoordinatePairString().getLongitude(), 
                                        false));
                        response.setCoordinatePair(coords);
                        
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("ParseCoordPair: Input latitude, "
                                + "longitude => [ "
                                + parameters.getCoordinatePairString().getLatitude()
                                + " ,"
                                + parameters.getCoordinatePairString().getLongitude()
                                + " ] parsed to => [ "
                                + coords.getLatitude()
                                + " , "
                                + coords.getLongitude()
                                + " ].");
                        }
                    }
                    else {
                        LOGGER.error("Input longitude value is null, or empty"
                                + ".  UnsupportedOperationException thrown to "
                                + "to the caller.");
                        throw new UnsupportedOperationException(
                                "Caller must provide a longitude value.");
                    }
                }
                else {
                    LOGGER.error("Input latitude value is null, or empty"
                            + ".  UnsupportedOperationException thrown to "
                            + "to the caller.");
                    throw new UnsupportedOperationException(
                            "Caller must provide a latitude value.");
                }
            }
            else {
                LOGGER.error("Input coordinate pair object is null."
                        + ".  UnsupportedOperationException thrown to "
                        + "to the caller.");
                throw new UnsupportedOperationException(
                        "Caller must provide a coordinate pair.");
            }
        }
        return response;
    }

    /**
     * Method used to parse a list of coordinate pair values.
     * 
     * @param parameters WSDL-generated object containing the user-supplied 
     * input parameters.
     * @return A response containing the results of the parsing operation. 
     * @throws UnsupportedOperationException Thrown if there are problems with 
     * the input parameters object.
     */
    @Override
    public ParseCoordPairsResponse parseCoordPairs(ParseCoordPairs parameters) {
        
        ParseCoordPairsResponse response = new ParseCoordPairsResponse();
        CoordsParse             parser   = new CoordsParse();
        
        response.setSecurity(secElement);
        
        if (parameters != null) {
            if ((parameters.getCoordinatePairStrings() != null) && 
                    (parameters.getCoordinatePairStrings().size() > 0)) {
                
                for (CoordinatePairString coords : parameters.getCoordinatePairStrings()) {
                    if ((coords.getLatitude() != null) && 
                            (!coords.getLatitude().isEmpty())) {
                        if ((coords.getLongitude() != null) && 
                                (!coords.getLongitude().isEmpty())) {
                        
                            CoordinatePair coordPair = new CoordinatePair();
                            coordPair.setLatitude(
                                    parser.parseCoordString(
                                            coords.getLatitude(), 
                                            true));
                            coordPair.setLongitude(
                                    parser.parseCoordString(
                                            coords.getLongitude(), 
                                            false));
                            
                            response.getCoordinatePairs().add(coordPair);
                        }
                        else {
                            LOGGER.error("Client supplied an empty longitude "
                                    + "value.");
                            throw new UnsupportedOperationException(
                                    "Null or empty longitude value supplied.");
                        }
                    }
                    else {
                        LOGGER.error("Client supplied an empty latitude "
                                + "value.");
                        throw new UnsupportedOperationException(
                                "Null or empty latitude value supplied.");
                    }
                }
            }
            else {
                LOGGER.error("Input list of coordinate pairs is null or "
                        + "contains no elements.  UnsupportedOperationException" 
                        + " thrown to the caller.");
                throw new UnsupportedOperationException(
                        "Caller must provide a list of coordinate pairs.");
            }
        }
        else {
            LOGGER.error("Input parameters object is null"
                    + ".  UnsupportedOperationException thrown to "
                    + "to the caller.");
            throw new UnsupportedOperationException(
                    "Caller must provide a list of coordinate pairs.");
        }
        
        return response;
    }

    /**
     * Method used to parse a single latitude coordinate.
     * 
     * @param parameters WSDL-generated object containing the user-supplied 
     * input parameters.
     * @return A response containing the results of the parsing operation. 
     * @throws UnsupportedOperationException Thrown if there are problems with 
     * the input parameters object.
     */
    @Override
    public ParseLatitudeResponse parseLatitude(ParseLatitude parameters) {
        
        ParseLatitudeResponse response = new ParseLatitudeResponse();
        response.setSecurity(secElement);
        
        if (parameters != null) {
            if ((parameters.getLatitude() != null) && 
                    (!parameters.getLatitude().isEmpty())) { 
                
                CoordsParse parser = new CoordsParse();
                Double value = parser.parseCoordString(
                        parameters.getLatitude(), 
                        true);
                response.setLatitude(value);
                
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("ParseLatitude: Input latitude => [ "
                        + parameters.getLatitude()
                        + " ] parsed to => [ "
                        + value
                        + " ].");
                }
            }
            else {
                LOGGER.error("Input latitude value is null, or empty"
                        + ".  UnsupportedOperationException thrown to "
                        + "to the caller.");
                throw new UnsupportedOperationException(
                        "Caller must provide a latitude value.");
            }
        }
        else {
            LOGGER.error("Input object is null."
                    + ".  UnsupportedOperationException thrown to "
                    + "to the caller.");
            throw new UnsupportedOperationException(
                    "Caller must provide a populated ParseLatitude object.");
        }
        return response;
    }

    /**
     * Method used to parse a list of latitude String values into a list of 
     * Double values.
     * 
     * @param parameters WSDL-generated object containing the user-supplied 
     * input parameters.
     * @return A response containing the results of the parsing operation. 
     * @throws UnsupportedOperationException Thrown if there are problems with 
     * the input parameters object.
     */
    @Override
    public ParseLatitudesResponse parseLatitudes(ParseLatitudes parameters) {
        
        ParseLatitudesResponse response = new ParseLatitudesResponse();
        CoordsParse            parser   = new CoordsParse();
        
        response.setSecurity(secElement);
        
        if (parameters != null) {
            if ((parameters.getLatitudes() != null) && 
                    (parameters.getLatitudes().size() > 0)) {

                for (String coord : parameters.getLatitudes()) {
                    response.getLatitudes().add(
                            parser.parseCoordString(coord, true));
                }

            }
            else {
                LOGGER.error("Input list of latitude values is null or empty." 
                        + ".  UnsupportedOperationException thrown to "
                        + "to the caller.");
                throw new UnsupportedOperationException(
                        "Caller must provide a list of latitude values.");
            }
        }
        else {
            LOGGER.error("Input object is null."
                    + ".  UnsupportedOperationException thrown to "
                    + "to the caller.");
            throw new UnsupportedOperationException(
                    "Caller must provide a populated ParseLatitudes object.");
        }
        return response;
    }

    /**
     * Method used to parse a single longitude coordinate.
     * 
     * @param parameters WSDL-generated object containing the user-supplied 
     * input parameters.
     * @return A response containing the results of the parsing operation. 
     * @throws UnsupportedOperationException Thrown if there are problems with 
     * the input parameters object.
     */
    @Override
    public ParseLongitudeResponse parseLongitude(ParseLongitude parameters) {
        
        ParseLongitudeResponse response = new ParseLongitudeResponse();
        response.setSecurity(secElement);
        
        if (parameters != null) {
            if ((parameters.getLongitude() != null) && 
                    (!parameters.getLongitude().isEmpty())) { 
                
                CoordsParse parser = new CoordsParse();
                Double value = parser.parseCoordString(
                        parameters.getLongitude(), 
                        false);
                response.setLongitude(value);
                
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("ParseLongitude: Input longitude => [ "
                        + parameters.getLongitude()
                        + " ] parsed to => [ "
                        + value
                        + " ].");
                }
            }
            else {
                LOGGER.error("Input latitude value is null, or empty"
                        + ".  UnsupportedOperationException thrown to "
                        + "to the caller.");
                throw new UnsupportedOperationException(
                        "Caller must provide a latitude value.");
            }
        }
        else {
            LOGGER.error("Input object is null."
                    + ".  UnsupportedOperationException thrown to "
                    + "to the caller.");
            throw new UnsupportedOperationException(
                    "Caller must provide a populated ParseLatitude object.");
        }
        return response;
    }

    /**
     * Method used to parse a list of longitude String values into a list of 
     * Double values.
     * 
     * @param parameters WSDL-generated object containing the user-supplied 
     * input parameters.
     * @return A response containing the results of the parsing operation. 
     * @throws UnsupportedOperationException Thrown if there are problems with 
     * the input parameters object.
     */
    @Override
    public ParseLongitudesResponse parseLongitudes(ParseLongitudes parameters) {
        
        ParseLongitudesResponse response = new ParseLongitudesResponse();
        response.setSecurity(secElement);
        
        if (parameters != null) {
            if ((parameters.getLongitudes() != null) && 
                    (parameters.getLongitudes().size() > 0)) {
                
                CoordsParse parser = new CoordsParse();
                
                for (String coord : parameters.getLongitudes()) {
                    response.getLongitudes().add(
                            parser.parseCoordString(coord, false));
                }

            }
            else {
                LOGGER.error("Input list of longitude values is null or empty." 
                        + ".  UnsupportedOperationException thrown to "
                        + "to the caller.");
                throw new UnsupportedOperationException(
                        "Caller must provide a list of longitude values.");
            }
        }
        else {
            LOGGER.error("Input object is null."
                    + ".  UnsupportedOperationException thrown to "
                    + "to the caller.");
            throw new UnsupportedOperationException(
                    "Caller must provide a populated ParseLongitudes object.");
        }
        return response;
    }

}
