package mil.nga.npd;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.gov.ic.ism.v2.ClassificationType;
import mil.nga.coordinate_pair.CoordinatePairString;
import mil.nga.coordinate_parse.GetErrorMessage;
import mil.nga.coordinate_parse.GetErrorMessageResponse;
import mil.nga.coordinate_parse.ObjectFactory;
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
import mil.nga.npd.exceptions.InternalServerErrorException;
import mil.nga.npd.exceptions.InvalidParameterException;

/**
 * The purpose of this class is to translate input GET parameters into 
 * the objects required to call the JAX-WS methods that actually 
 * perform the coordinate parsing.  This functionality was bolt-on
 * after the original bottom-up development that started with the 
 * WSDL.  Further, this class decoupled the parameter parsing logic 
 * from the servlet class to facilitate the development of 
 * jUnit tests.
 *  
 * @author L. Craig Carpenter
 */
public class RESTFilter {

    /**
     * Set up the Logback system for use throughout the class.
     */
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(RESTFilter.class);
    
    /**
     * Parameter that must be supplied with the <code>GetErrorMessage</code>
     * operation.  This is case-sensitive.
     */
    public static final String ERROR_NUMBER_PARAM = "ErrorNum";
    
    /**
     * Parameter that must be supplied with the <code>ParseLatitude</code>
     * operation.  This is case-sensitive.
     */
    public static final String LATITUDE_PARAM = "Latitude";
    
    /**
     * Parameter that must be supplied with the <code>ParseLatitudes</code>
     * operation.  This is case-sensitive.
     */
    public static final String LATITUDES_PARAM = "Latitudes";
    
    /**
     * Parameter that must be supplied with the <code>ParseLongitudes</code>
     * operation.  This is case-sensitive.
     */
    public static final String LONGITUDES_PARAM = "Longitudes";
    
    /**
     * Parameter that must be supplied with the <code>ParseLongitude</code>
     * operation.  This is case-sensitive.
     */
    public static final String LONGITUDE_PARAM = "Longitude";
    
    /**
     * Parameter that must be supplied with the <code>ParseCoordPair</code>
     * operation.  This is case-sensitive.
     */
    public static final String COORD_PAIR_PARAM = "CoordinatePairString";
    
    /**
     * Parameter that must be supplied with the <code>ParseCoordPair</code>
     * operation.  This is case-sensitive.
     */
    public static final String COORD_PAIRS_PARAM = "CoordinatePairStrings";
    
    /**
     * Parameter used to specify the output format.  This is case-sensitive.
     */
    public static final String OUTPUT_FORMAT_PARAM = "outputFormat";
    
    /**
     * Parameter used to specify the operation.  This is case-sensitive.
     */
    public static final String OPERATION_PARAM = "operation";
    
    private final OutputFormatType    outputFormat;
    private final OperationType       operation;
    private final Map<String, Object> params;
    
    /**
     * Default object embedded in all of the calls to the JAX-WS methods.
     */
    private SecurityElement defaultSecElement;
    
    /**
     * Default constructor enforcing the builder creation pattern.
     * 
     * @param builder The builder object.
     */
    protected RESTFilter(RESTFilterBuilder builder) {
        
        operation    = builder.operation;
        outputFormat = builder.outputFormat;
        params       = builder.params;
        
        // Set up the SecurityElement
        defaultSecElement= new SecurityElement();
        defaultSecElement.setClassification(ClassificationType.U);
        defaultSecElement.getOwnerProducer().add("USA");
    }
    
    /**
     * During testing we found that *some* of the parameters  extracted
     * from the servlet parameter map were arrays of strings vs simple
     * String values.  This method was introduced to handle that case.
     * 
     * @param map The input parameter map from the servlet.
     * @param name The name of the requested parameter.
     * @return The requested value.  Null if the value is not found.
     * @throws InvalidParameterException Thrown if an unexpected type is 
     * encountered.
     */
    protected static String getParameter(
            Map<String, Object> 
            map, String name) 
                    throws InvalidParameterException {
        String value = null;
        if (map.get(name) instanceof String) {
            value = (String)map.get(name);
        }
        else if (map.get(name) instanceof String[]) {
            String[] temp = (String[])map.get(name);
            if (temp.length > 0) {
                value = temp[0];
            }
        }
        else {
            if (map.get(name) != null) {
                throw new InvalidParameterException("Map value for key [ "
                        + name
                        + " ] is of an unexpected object type. Object is of "
                        + "type => [ "
                        + map.get(name).getClass().getName()
                        + " ].");
            }
        }
        return value;
    }
    /**
     * Public facing method that executes the requested operation.
     * 
     * @return String representation of the results of the requested 
     * operation.
     * @throws InvalidParameterException Thrown if there are problems with
     * the input parameters.
     */
    public String translate() 
            throws InternalServerErrorException, 
                   InvalidParameterException {
        switch(operation) {
            case GET_ERROR_MESSAGE:
                return executeGetErrorMessage();
            case PARSE_LATITUDE:
                return executeParseLatitude();
            case PARSE_LATITUDES:
                return executeParseLatitudes();
            case PARSE_LONGITUDE:
                return executeParseLongitude();
            case PARSE_LONGITUDES:
                return executeParseLongitudes();
            case PARSE_COORD_PAIR:
                return executeParseCoordPair();
            case PARSE_COORD_PAIRS:
                return executeParseCoordPairs();
            default:
                throw new InvalidParameterException(
                        "Invalid operation. See WSDL for valid operations.");
        }
        //return null;
    }

    /**
     * Parse the client supplied data supplied as URL parameters and call
     * the WSDL-compliant methods to convert the input string-based 
     * coordinates into decimal degree values.  This method parses a 
     * coordinate pair ordered by longitude, latitude.
     * 
     * @return A String based representation (marshaled) version of the
     * output response object.
     * @throws InternalServerErrorException Thrown when problems are 
     * encountered with the JAX-B marshaling/unmarshalling. 
     * @throws InvalidParameterException Thrown if there are issues 
     * with the input data received from the user.
     */
    private String executeParseCoordPair() 
            throws InternalServerErrorException, 
                   InvalidParameterException {
        
        String                 coordPair = getParameter(params, COORD_PAIR_PARAM);
        String                 result    = null;
        StringWriter           sw        = null;
        ParseCoordPair         message   = new ParseCoordPair();
        CoordinateParseService service   = new CoordinateParseService();
        
        if ((coordPair == null) || (coordPair.isEmpty())) {
            throw new InvalidParameterException(
                    "Required parameter for [ "
                    + OperationType.PARSE_COORD_PAIR.getText()
                    + " ] operation => [ "
                    + COORD_PAIR_PARAM 
                    + " ] not supplied.");
        }
        
        try {
            
            // Need to turn input lon,lat into CoordinatePairStr
            String coordinates[] = coordPair.split(","); 
            if (coordinates.length != 2) {
                throw new InvalidParameterException("Invalid value for "
                        + "parameter [ "
                        + COORD_PAIR_PARAM
                        + " ].  Parameter must be longitude, latitude.  "
                        + "Value supplied => [ "
                        + coordPair
                        + " ].");                   
            }
            
            CoordinatePairString cps = new CoordinatePairString();
            cps.setLongitude(coordinates[0].trim());
            cps.setLatitude(coordinates[1].trim());
            
            message.setSecurity(defaultSecElement);
            message.setCoordinatePairString(cps);
            ParseCoordPairResponse response = service.parseCoordPair(message);
            
            // Because we are using JAX-WS generated object classes, they 
            // don't have a XmlRootElement tag.  That means you cannot directly
            // marshal the response from the target service, you must use the 
            // helper classes that were also generated by JAX-WS.
            ObjectFactory objFactory = new ObjectFactory();
            JAXBElement<ParseCoordPairResponse> jaxbResp = 
                    objFactory.createParseCoordPairResponse(response);
            JAXBContext context = JAXBContext.newInstance(
                    "mil.nga.coordinate_parse");
            Marshaller marshaller = context.createMarshaller();
            if (getOutputFormat() == OutputFormatType.JSON) {
                marshaller.setProperty(
                        MarshallerProperties.MEDIA_TYPE, 
                        MediaType.APPLICATION_JSON);
            }
            sw = new StringWriter();
            marshaller.marshal(jaxbResp, sw);
            
            // Return the marshalled String
            result = sw.toString();
        }
        catch (JAXBException jbe) {
            LOGGER.error("Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
            throw new InternalServerErrorException(
                    "Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
        }
        finally {
            if (sw != null) {
                try { sw.close(); } catch (Exception e) {}
            }
        }
        return result;
    }
    
    /**
     * Parse the client supplied data supplied as URL parameters and call
     * the WSDL-compliant methods to convert the input string-based 
     * coordinates into decimal degree values.  This method parses a 
     * list of coordinate pairs ordered by longitude, latitude.
     * 
     * @return A String based representation (marshaled) version of the
     * output response object.
     * @throws InternalServerErrorException Thrown when problems are 
     * encountered with the JAX-B marshaling/unmarshalling. 
     * @throws InvalidParameterException Thrown if there are issues 
     * with the input data received from the user.
     */
    private String executeParseCoordPairs() 
            throws InternalServerErrorException, 
                   InvalidParameterException {
        
        String                 coordPairs = getParameter(params, COORD_PAIRS_PARAM);
        String                 result     = null;
        StringWriter           sw         = null;
        ParseCoordPairs        message    = new ParseCoordPairs();
        CoordinateParseService service    = new CoordinateParseService();
        
        if ((coordPairs == null) || (coordPairs.isEmpty())) {
            throw new InvalidParameterException(
                    "Required parameter for [ "
                    + OperationType.PARSE_COORD_PAIRS.getText()
                    + " ] operation => [ "
                    + COORD_PAIRS_PARAM 
                    + " ] not supplied.");
        }
        
        try {
            
            // Split the input coordinate pairs into an array.
            String coordinates[] = coordPairs.split(","); 
            // Ensure the number of coords is divisible by 2 to ensure pairs.
            if (coordinates.length % 2 != 0) {
                throw new InvalidParameterException("Invalid value for "
                        + "parameter [ "
                        + COORD_PAIRS_PARAM
                        + " ].  Number of coords is [ "
                        + coordinates.length
                        + " ].  Parameter must be a list of pairs ordered "
                        + "by longitude, latitude.  Value supplied => [ "
                        + coordPairs
                        + " ].");                   
            }
            
            int counter = 0;
            while (counter < coordinates.length) {
                CoordinatePairString cps = new CoordinatePairString();
                cps.setLongitude(coordinates[counter].trim());
                cps.setLatitude(coordinates[counter+1].trim());
                message.getCoordinatePairStrings().add(cps);
                counter += 2;
            } 
            message.setSecurity(defaultSecElement);
            ParseCoordPairsResponse response = service.parseCoordPairs(message);
            
            // Because we are using JAX-WS generated object classes, they 
            // don't have a XmlRootElement tag.  That means you cannot directly
            // marshal the response from the target service, you must use the 
            // helper classes that were also generated by JAX-WS.
            ObjectFactory objFactory = new ObjectFactory();
            JAXBElement<ParseCoordPairsResponse> jaxbResp = 
                    objFactory.createParseCoordPairsResponse(response);
            JAXBContext context = JAXBContext.newInstance(
                    "mil.nga.coordinate_parse");
            Marshaller marshaller = context.createMarshaller();
            if (getOutputFormat() == OutputFormatType.JSON) {
                marshaller.setProperty(
                        MarshallerProperties.MEDIA_TYPE, 
                        MediaType.APPLICATION_JSON);
            }
            sw = new StringWriter();
            marshaller.marshal(jaxbResp, sw);
            
            // Return the marshalled String
            result = sw.toString();
        }
        catch (JAXBException jbe) {
            LOGGER.error("Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
            throw new InternalServerErrorException(
                    "Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
        }
        finally {
            if (sw != null) {
                try { sw.close(); } catch (Exception e) {}
            }
        }
        return result;
    }
    
    /**
     * Parse the client supplied data supplied as URL parameters and call
     * the WSDL-compliant methods to convert the input string-based 
     * coordinates into decimal degree values.  This method parses a single 
     * latitude String value.
     * 
     * @return A String based representation (marshaled) version of the
     * output response object.
     * @throws InternalServerErrorException Thrown when problems are 
     * encountered with the JAX-B marshaling/unmarshalling. 
     * @throws InvalidParameterException Thrown if there are issues 
     * with the input data received from the user.
     */
    private String executeParseLatitude() 
            throws InternalServerErrorException, 
                   InvalidParameterException {
        
        String                 latString = getParameter(params, LATITUDE_PARAM);
        String                 result    = null;
        StringWriter           sw        = null;
        ParseLatitude          message   = new ParseLatitude();
        CoordinateParseService service   = new CoordinateParseService();
        
        if ((latString == null) || (latString.isEmpty())) {
            throw new InvalidParameterException(
                    "Required parameter for [ "
                    + OperationType.PARSE_LATITUDE.getText()
                    + " ] operation => [ "
                    + LATITUDE_PARAM 
                    + " ] not supplied.");
        }
        
        try {
            
            message.setSecurity(defaultSecElement);
            message.setLatitude(latString.trim());
            ParseLatitudeResponse response = service.parseLatitude(message);
            
            // Because we are using JAX-WS generated object classes, they 
            // don't have a XmlRootElement tag.  That means you cannot directly
            // marshal the response from the target service, you must use the 
            // helper classes that were also generated.
            ObjectFactory objFactory = new ObjectFactory();
            JAXBElement<ParseLatitudeResponse> jaxbResp = 
                    objFactory.createParseLatitudeResponse(response);
            JAXBContext context = JAXBContext.newInstance(
                    "mil.nga.coordinate_parse");
            Marshaller marshaller = context.createMarshaller();
            if (getOutputFormat() == OutputFormatType.JSON) {
                marshaller.setProperty(
                        MarshallerProperties.MEDIA_TYPE, 
                        MediaType.APPLICATION_JSON);
            }
            sw = new StringWriter();
            marshaller.marshal(jaxbResp, sw);
            
            // Return the marshalled String
            result = sw.toString();
        }
        catch (JAXBException jbe) {
            LOGGER.error("Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
            throw new InternalServerErrorException(
                    "Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
        }
        finally {
            if (sw != null) {
                try { sw.close(); } catch (Exception e) {}
            }
        }
        return result;
    }
    
    /**
     * Parse the client supplied data supplied as URL parameters and call
     * the WSDL-compliant methods to convert the input string-based 
     * coordinates into decimal degree values.  This method parses a list 
     * of latitude String values.
     * 
     * @return A String based representation (marshaled) version of the
     * output response object.
     * @throws InternalServerErrorException Thrown when problems are 
     * encountered with the JAX-B marshaling/unmarshalling. 
     * @throws InvalidParameterException Thrown if there are issues 
     * with the input data received from the user.
     */
    private String executeParseLatitudes() 
            throws InternalServerErrorException, 
                   InvalidParameterException {
        
        String                 latString = getParameter(params, LATITUDES_PARAM);
        String                 result    = null;
        StringWriter           sw        = null;
        ParseLatitudes         message   = new ParseLatitudes();
        CoordinateParseService service   = new CoordinateParseService();
        
        if ((latString == null) || (latString.isEmpty())) {
            throw new InvalidParameterException(
                    "Required parameter for [ "
                    + OperationType.PARSE_LATITUDES.getText()
                    + " ] operation => [ "
                    + LATITUDES_PARAM 
                    + " ] not supplied.");
        }
        
        try {
            
            // Parse the input String.
            String[] coordinateArray = latString.split(","); 
            if ((coordinateArray == null) || (coordinateArray.length < 1)) { 
                throw new InvalidParameterException(
                        "Unable to extract the list of Latitude coordinates "
                        + "from the input data.  Input data => [ "
                        + latString
                        + " ].  Unable to continue.");
            }
            
            // Loop through the input and add them to the message.
            for (String coord : coordinateArray) {
                message.getLatitudes().add(coord.trim());
            }
            
            message.setSecurity(defaultSecElement);
            ParseLatitudesResponse response = service.parseLatitudes(message);
            
            // Because we are using JAX-WS generated object classes, they 
            // don't have a XmlRootElement tag.  That means you cannot directly
            // marshal the response from the target service, you must use the 
            // helper classes that were also generated.
            ObjectFactory objFactory = new ObjectFactory();
            JAXBElement<ParseLatitudesResponse> jaxbResp = 
                    objFactory.createParseLatitudesResponse(response);
            JAXBContext context = JAXBContext.newInstance(
                    "mil.nga.coordinate_parse");
            Marshaller marshaller = context.createMarshaller();
            if (getOutputFormat() == OutputFormatType.JSON) {
                marshaller.setProperty(
                        MarshallerProperties.MEDIA_TYPE, 
                        MediaType.APPLICATION_JSON);
            }
            sw = new StringWriter();
            marshaller.marshal(jaxbResp, sw);
            
            // Return the marshalled String
            result = sw.toString();
        }
        catch (JAXBException jbe) {
            LOGGER.error("Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
            throw new InternalServerErrorException(
                    "Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
        }
        finally {
            if (sw != null) {
                try { sw.close(); } catch (Exception e) {}
            }
        }
        return result;
    }
    
    /**
     * Parse the client supplied data supplied as URL parameters and call
     * the WSDL-compliant methods to convert the input string-based 
     * coordinates into decimal degree values.  This method parses a single 
     * longitude String value.
     * 
     * @return A String based representation (marshaled) version of the
     * output response object.
     * @throws InternalServerErrorException Thrown when problems are 
     * encountered with the JAX-B marshaling/unmarshalling. 
     * @throws InvalidParameterException Thrown if there are issues 
     * with the input data received from the user.
     */
    private String executeParseLongitude() 
            throws InternalServerErrorException, 
                   InvalidParameterException {
        
        String                 lonString = getParameter(params, LONGITUDE_PARAM);
        String                 result    = null;
        StringWriter           sw        = null;
        ParseLongitude         message   = new ParseLongitude();
        CoordinateParseService service   = new CoordinateParseService();
        
        if ((lonString == null) || (lonString.isEmpty())) {
            throw new InvalidParameterException(
                    "Required parameter for [ "
                    + OperationType.PARSE_LONGITUDE.getText()
                    + " ] operation => [ "
                    + LONGITUDE_PARAM 
                    + " ] not supplied.");
        }
        
        try {
            
            message.setSecurity(defaultSecElement);
            message.setLongitude(lonString.trim());
            ParseLongitudeResponse response = service.parseLongitude(message);
            
            // Because we are using JAX-WS generated object classes, they 
            // don't have a XmlRootElement tag.  That means you cannot directly
            // marshal the response from the target service, you must use the 
            // helper classes that were also generated.
            ObjectFactory objFactory = new ObjectFactory();
            JAXBElement<ParseLongitudeResponse> jaxbResp = 
                    objFactory.createParseLongitudeResponse(response);
            JAXBContext context = JAXBContext.newInstance(
                    "mil.nga.coordinate_parse");
            Marshaller marshaller = context.createMarshaller();
            if (getOutputFormat() == OutputFormatType.JSON) {
                marshaller.setProperty(
                        MarshallerProperties.MEDIA_TYPE, 
                        MediaType.APPLICATION_JSON);
            }
            sw = new StringWriter();
            marshaller.marshal(jaxbResp, sw);
            
            // Return the marshalled String
            result = sw.toString();
        }
        catch (JAXBException jbe) {
            LOGGER.error("Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
            throw new InternalServerErrorException(
                    "Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
        }
        finally {
            if (sw != null) {
                try { sw.close(); } catch (Exception e) {}
            }
        }
        return result;
    }
    
    /**
     * Parse the client supplied data supplied as URL parameters and call
     * the WSDL-compliant methods to convert the input string-based 
     * coordinates into decimal degree values.  This method parses a list 
     * of longitude String values.
     * 
     * @return A String based representation (marshaled) version of the
     * output response object.
     * @throws InternalServerErrorException Thrown when problems are 
     * encountered with the JAX-B marshaling/unmarshalling. 
     * @throws InvalidParameterException Thrown if there are issues 
     * with the input data received from the user.
     */
    private String executeParseLongitudes() 
            throws InternalServerErrorException, 
                   InvalidParameterException {
        
        String                 lonString = getParameter(params, LONGITUDES_PARAM);
        String                 result    = null;
        StringWriter           sw        = null;
        ParseLongitudes        message   = new ParseLongitudes();
        CoordinateParseService service   = new CoordinateParseService();
        
        if ((lonString == null) || (lonString.isEmpty())) {
            throw new InvalidParameterException(
                    "Required parameter for [ "
                    + OperationType.PARSE_LONGITUDES.getText()
                    + " ] operation => [ "
                    + LONGITUDES_PARAM 
                    + " ] not supplied.");
        }
        
        try {
            
            // Parse the input String.
            String[] coordinateArray = lonString.split(","); 
            if ((coordinateArray == null) || (coordinateArray.length < 1)) { 
                throw new InvalidParameterException(
                        "Unable to extract the list of Longitude coordinates "
                        + "from the input data.  Input data => [ "
                        + lonString
                        + " ].  Unable to continue.");
            }
            
            // Loop through the input and add them to the message.
            for (String coord : coordinateArray) {
                message.getLongitudes().add(coord.trim());
            }
            
            message.setSecurity(defaultSecElement);
            ParseLongitudesResponse response = service.parseLongitudes(message);
            
            // Because we are using JAX-WS generated object classes, they 
            // don't have a XmlRootElement tag.  That means you cannot directly
            // marshal the response from the target service, you must use the 
            // helper classes that were also generated.
            ObjectFactory objFactory = new ObjectFactory();
            JAXBElement<ParseLongitudesResponse> jaxbResp = 
                    objFactory.createParseLongitudesResponse(response);
            JAXBContext context = JAXBContext.newInstance(
                    "mil.nga.coordinate_parse");
            Marshaller marshaller = context.createMarshaller();
            if (getOutputFormat() == OutputFormatType.JSON) {
                marshaller.setProperty(
                        MarshallerProperties.MEDIA_TYPE, 
                        MediaType.APPLICATION_JSON);
            }
            sw = new StringWriter();
            marshaller.marshal(jaxbResp, sw);
            
            // Return the marshaled String
            result = sw.toString();
        }
        catch (JAXBException jbe) {
            LOGGER.error("Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
            throw new InternalServerErrorException(
                    "Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
        }
        finally {
            if (sw != null) {
                try { sw.close(); } catch (Exception e) {}
            }
        }
        return result;
    }
    
    /**
     * Parse the client supplied data supplied as URL parameters and call
     * the WSDL-compliant methods to look up the error message associated
     * with the error number that was requested by the client.
     * 
     * @return A String based representation (marshaled) version of the
     * output response object.
     * @throws InternalServerErrorException Thrown when problems are 
     * encountered with the JAX-B marshaling/unmarshalling. 
     * @throws InvalidParameterException Thrown if there are issues 
     * with the input data received from the user.
     */
    private String executeGetErrorMessage() 
            throws InternalServerErrorException, 
                   InvalidParameterException {
        
        String                 errorNumber = getParameter(params, ERROR_NUMBER_PARAM);
        String                 result      = null;
        StringWriter           sw          = null;
        GetErrorMessage        message     = new GetErrorMessage();
        CoordinateParseService service     = new CoordinateParseService();
        
        if ((errorNumber == null) || (errorNumber.isEmpty())) {
            throw new InvalidParameterException(
                    "Required parameter for [ "
                    + OperationType.GET_ERROR_MESSAGE.getText()
                    + " ]operation => [ "
                    + ERROR_NUMBER_PARAM 
                    + " ] not supplied.");
        }

        try {
            
            message.setSecurity(defaultSecElement);
            message.setErrorNum(Double.valueOf(errorNumber));
            GetErrorMessageResponse response = 
                    service.getErrorMessage(message);
            
            // Because we are using JAX-WS generated object classes, they 
            // don't have a XmlRootElement tag.  That means you cannot directly
            // marshal the response from the target service, you must use the 
            // helper classes that were also generated.
            ObjectFactory objFactory = new ObjectFactory();
            JAXBElement<GetErrorMessageResponse> jaxbResp = 
                    objFactory.createGetErrorMessageResponse(response);
            JAXBContext context = JAXBContext.newInstance(
                    "mil.nga.coordinate_parse");
            Marshaller marshaller = context.createMarshaller();
            if (getOutputFormat() == OutputFormatType.JSON) {
                marshaller.setProperty(
                        MarshallerProperties.MEDIA_TYPE, 
                        MediaType.APPLICATION_JSON);
            }
            sw = new StringWriter();
            marshaller.marshal(jaxbResp, sw);
            
            // Return the marshalled String
            result = sw.toString();
        }
        catch (JAXBException jbe) {
            LOGGER.error("Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
            throw new InternalServerErrorException(
                    "Unexpected JAXBException raised while marshalling "
                    + "the output object.  Exception message => [ "
                    + jbe.getMessage()
                    + " ].");
        }
        catch (NumberFormatException nfe) {
            LOGGER.error("Unable to convert [ "
                    + errorNumber 
                    + " ] to a double.");
            throw new InvalidParameterException("Input value for param => [ "
                    + ERROR_NUMBER_PARAM
                    + " ] value => [ "
                    + errorNumber 
                    + " ] could not be converted to a double.");
        }
        finally {
            if (sw != null) {
                try { sw.close(); } catch (Exception e) {}
            }
        }
        return result;
    }
    
    /**
     * Getter method for the requested operation.
     * @return The operation format.
     */
    public OperationType getOperation() {
        return operation;
    }
    
    /**
     * Getter method for the requested output format.
     * @return The output format.
     */
    public OutputFormatType getOutputFormat() {
        return outputFormat;
    }
    
    /**
     * Getter method that creates the media type from the output format
     * type.  Used to control whether we output JSON or XML.
     * @return The media type String.
     */
    public String getMediaType() {
        String mediaType = "text/plain";
        switch (getOutputFormat()) {
            case XML:
                mediaType = "application/xml";
                break;
            case JSON:
                mediaType = "application/json";
        }
        return mediaType;
    }
    
    /**
     * Static inner class implementing the builder creation pattern for 
     * objects of type RESTFilter.
     * 
     * @author L. Craig Carpenter
     */
    public static class RESTFilterBuilder {
        
        private Map<String, Object> params;
        private OutputFormatType    outputFormat;
        private OperationType       operation;
        
        /**
         * Setter method for the parameter map.  The map should be a key/value
         * pair map containing the input GET parameters.
         * 
         * @param value The input <code>Map</code> object containing the 
         * input GET parameters.
         * @return A reference to the builder object.
         */
        public RESTFilterBuilder withParameterMap(Map<String, Object> value) {
            params = value;
            return this;
        }
        
        /**
         * Extract the requested operation from input parameters.
         * 
         * @return The <code>OperationType</code> object extracted from the 
         * input parameters.
         * @throws InvalidParameterException Thrown if the operation was 
         * not supplied, or it could not be translated to a supported 
         * operation.
         */
        private OperationType getOperation() throws InvalidParameterException {
            return OperationType.fromString(
                    getParameter(
                        params, 
                        OPERATION_PARAM));
        }
        
        /**
         * Extract the requested output format from input parameters.
         * 
         * @return The OutputFormatType object extracted from the input 
         * parameters.
         * @throws InvalidParameterException Thrown if the operation was 
         * not supplied, or it could not be translated to a supported 
         * operation.
         */
        private OutputFormatType getOutputFormat() throws InvalidParameterException {
            return OutputFormatType.fromString(
                    getParameter(
                            params, 
                            OUTPUT_FORMAT_PARAM));
        }
        
        
        /**
         * Create an instance of the RESTFilter object.
         * 
         * @return A constructed RESTFilter object.
         * @throws InvalidParameterException Thrown if there are problems parsing
         * the input parameters.
         */
        public RESTFilter build() throws InvalidParameterException {
            if ((params == null) || (params.size() == 0)) {
                throw new InvalidParameterException("Input parameter map is null "
                        + "or empty.  Unable to parse the input parameters.");
            }
            operation    = getOperation();
            outputFormat = getOutputFormat();
            return new RESTFilter(this);
        }
    }
}
