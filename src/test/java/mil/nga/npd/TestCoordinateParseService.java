package mil.nga.npd;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import us.gov.ic.ism.v2.ClassificationType;
import mil.nga.coordinate_pair.CoordinatePair;
import mil.nga.coordinate_pair.CoordinatePairString;
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

public class TestCoordinateParseService extends TestCoordinates {

    @Test
    public void testGetErrorMessage() {
        
        // Set up the SecurityElement
        SecurityElement secElement = new SecurityElement();
        secElement.setClassification(ClassificationType.U);
        secElement.getOwnerProducer().add("USA");
        
        GetErrorMessage message = new GetErrorMessage();
        message.setSecurity(secElement);
        message.setErrorNum(Double.valueOf(-1001));
        
        CoordinateParseService service = new CoordinateParseService();
        GetErrorMessageResponse response = service.getErrorMessage(message);
        Assert.assertEquals(   
                response.getErrorMessage(),
                ErrorMessageType.ERROR_MINUS_1001.getErrorMessage());
        
        message.setErrorNum(Double.valueOf(-1002));
        response = service.getErrorMessage(message);
        Assert.assertEquals(   
                response.getErrorMessage(),
                ErrorMessageType.ERROR_MINUS_1002.getErrorMessage());
        
        message.setErrorNum(Double.valueOf(-1003));
        response = service.getErrorMessage(message);
        Assert.assertEquals(   
                response.getErrorMessage(),
                ErrorMessageType.ERROR_MINUS_1003.getErrorMessage());
        
        message.setErrorNum(Double.valueOf(-1004));
        response = service.getErrorMessage(message);
        Assert.assertEquals(   
                response.getErrorMessage(),
                ErrorMessageType.ERROR_MINUS_1004.getErrorMessage());
        
        message.setErrorNum(Double.valueOf(-1010));
        response = service.getErrorMessage(message);
        Assert.assertEquals(   
                response.getErrorMessage(),
                ErrorMessageType.ERROR_MINUS_1010.getErrorMessage());        
        
        message.setErrorNum(Double.valueOf(-1020));
        response = service.getErrorMessage(message);
        Assert.assertEquals(   
                response.getErrorMessage(),
                ErrorMessageType.ERROR_MINUS_1020.getErrorMessage());
        
        message.setErrorNum(Double.valueOf(1000));
        response = service.getErrorMessage(message);
        Assert.assertEquals(   
                response.getErrorMessage(),
                ErrorMessageType.UNKNOWN_ERROR.getErrorMessage());
        
        message.setErrorNum(Double.valueOf(-1000));
        response = service.getErrorMessage(message);
        Assert.assertEquals(   
                response.getErrorMessage(),
                ErrorMessageType.UNKNOWN_ERROR.getErrorMessage());
    }
    
    @Test
    public void testParseCoordinatePair() {
        
        ParseCoordPairResponse response;
        CoordinateParseService service = new CoordinateParseService();
        
        // Set up the SecurityElement
        SecurityElement secElement = new SecurityElement();
        secElement.setClassification(ClassificationType.U);
        secElement.getOwnerProducer().add("USA");
        ParseCoordPair message = new ParseCoordPair();
        message.setSecurity(secElement);
        
        CoordinatePairString coords = new CoordinatePairString();
        
        // Loop through both lists simultaneously
        Iterator<String> latIterator = validLatitudes.keySet().iterator();
        Iterator<String> lonIterator = validLongitudes.keySet().iterator();
        while (latIterator.hasNext() && lonIterator.hasNext()) {
            String latCoord = latIterator.next();
            String lonCoord = lonIterator.next();
            coords.setLatitude(latCoord);
            coords.setLongitude(lonCoord);
            message.setCoordinatePairString(coords);
            response = service.parseCoordPair(message);
            Assert.assertEquals(
                    validLatitudes.get(latCoord),
                    response.getCoordinatePair().getLatitude(),
                    0.0);
            Assert.assertEquals(
                    validLongitudes.get(lonCoord),
                    response.getCoordinatePair().getLongitude(),
                    0.0);
        }

    }
    
    @Test
    public void testParseCoordinatePairs() {
        
        ParseCoordPairsResponse response;
        CoordinateParseService service = new CoordinateParseService();
        
        // Set up the SecurityElement
        SecurityElement secElement = new SecurityElement();
        secElement.setClassification(ClassificationType.U);
        secElement.getOwnerProducer().add("USA");
        ParseCoordPairs message = new ParseCoordPairs();
        message.setSecurity(secElement);
        
        // Loop through both lists simultaneously
        Iterator<String> latIterator = validLatitudes.keySet().iterator();
        Iterator<String> lonIterator = validLongitudes.keySet().iterator();
        while (latIterator.hasNext() && lonIterator.hasNext()) {
            
            String latCoord = latIterator.next();
            String lonCoord = lonIterator.next();
            
            CoordinatePairString coords = new CoordinatePairString();
            coords.setLatitude(latCoord);
            coords.setLongitude(lonCoord);
            
            message.getCoordinatePairStrings().add(coords);
        }
        
        response = service.parseCoordPairs(message);
        
        // Now we have to test the response.
        latIterator = validLatitudes.keySet().iterator();
        lonIterator = validLongitudes.keySet().iterator();
        Iterator<CoordinatePair> responseIterator = 
                response.getCoordinatePairs().iterator();
        
        while (latIterator.hasNext() && lonIterator.hasNext()) {
            
            String         latCoord = latIterator.next();
            String         lonCoord = lonIterator.next();
            CoordinatePair results  = responseIterator.next();
            
            Assert.assertEquals(
                    validLatitudes.get(latCoord),
                    results.getLatitude(),
                    0.0);
            Assert.assertEquals(
                    validLongitudes.get(lonCoord),
                    results.getLongitude(),
                    0.0);
        }
    }
    
    @Test
    public void testParseLatitudes() {
        
        ParseLatitudesResponse response;
        CoordinateParseService service = new CoordinateParseService();
        
        // Set up the SecurityElement
        SecurityElement secElement = new SecurityElement();
        secElement.setClassification(ClassificationType.U);
        secElement.getOwnerProducer().add("USA");
        ParseLatitudes message = new ParseLatitudes();
        message.setSecurity(secElement);
        
        for (String coord : validLatitudes.keySet()) {
            message.getLatitudes().add(coord);
        }
        response = service.parseLatitudes(message);
        
        // Loop through the responses and see if everything matches
        int counter = 0;
        for (String coord : validLatitudes.keySet()) {
            Assert.assertEquals(
                    validLatitudes.get(coord),
                    response.getLatitudes().get(counter));
            counter++;
        }
    }
    
    @Test
    public void testParseLatitude() {
        
        ParseLatitudeResponse response;
        CoordinateParseService service = new CoordinateParseService();
        
        // Set up the SecurityElement
        SecurityElement secElement = new SecurityElement();
        secElement.setClassification(ClassificationType.U);
        secElement.getOwnerProducer().add("USA");
        ParseLatitude message = new ParseLatitude();
        message.setSecurity(secElement);
        
        for (String coord : validLatitudes.keySet()) {
            message.setLatitude(coord);
            response = service.parseLatitude(message);
            Assert.assertEquals(
                    validLatitudes.get(coord), 
                    response.getLatitude());
        }
    }
    
    @Test
    public void testParseLongitude() {
        
        ParseLongitudeResponse response;
        CoordinateParseService service = new CoordinateParseService();
        
        // Set up the SecurityElement
        SecurityElement secElement = new SecurityElement();
        secElement.setClassification(ClassificationType.U);
        secElement.getOwnerProducer().add("USA");
        ParseLongitude message = new ParseLongitude();
        message.setSecurity(secElement);
        
        for (String coord : validLongitudes.keySet()) {
            message.setLongitude(coord);
            response = service.parseLongitude(message);
            Assert.assertEquals(
                    validLongitudes.get(coord), 
                    response.getLongitude());
        }
    }
    
    @Test
    public void testParseLongitudes() {
        
        ParseLongitudesResponse response;
        CoordinateParseService service = new CoordinateParseService();
        
        // Set up the SecurityElement
        SecurityElement secElement = new SecurityElement();
        secElement.setClassification(ClassificationType.U);
        secElement.getOwnerProducer().add("USA");
        ParseLongitudes message = new ParseLongitudes();
        message.setSecurity(secElement);
        
        for (String coord : validLongitudes.keySet()) {
            message.getLongitudes().add(coord);
        }
        response = service.parseLongitudes(message);
        
        // Loop through the responses and see if everything matches
        int counter = 0;
        for (String coord : validLongitudes.keySet()) {
            Assert.assertEquals(
                    validLongitudes.get(coord),
                    response.getLongitudes().get(counter));
            counter++;
        }
    }
    
}
