package mil.nga.npd;

import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import mil.nga.coordinate_parse.GetErrorMessageResponse;
import mil.nga.coordinate_parse.ParseCoordPairResponse;
import mil.nga.coordinate_parse.ParseCoordPairsResponse;
import mil.nga.coordinate_parse.ParseLatitudeResponse;
import mil.nga.coordinate_parse.ParseLatitudesResponse;
import mil.nga.coordinate_parse.ParseLongitudeResponse;
import mil.nga.coordinate_parse.ParseLongitudesResponse;
import mil.nga.npd.exceptions.InternalServerErrorException;
import mil.nga.npd.exceptions.InvalidParameterException;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;
import org.junit.Assert;

public class TestRestFilter extends TestCoordinates {

    @Test
    public void testConstruction() throws InvalidParameterException {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operation", "ParseLatitudes");
        params.put("outputFormat", "xml");
        RESTFilter filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        Assert.assertEquals(
                OperationType.PARSE_LATITUDES,
                filter.getOperation());
        Assert.assertEquals(
                OutputFormatType.XML,
                filter.getOutputFormat());
        
        // See if case matters
        params = new HashMap<String, Object>();
        params.put("operation", "parselatitudes");
        params.put("outputFormat", "json");
        filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        Assert.assertEquals(
                OperationType.PARSE_LATITUDES,
                filter.getOperation());
        Assert.assertEquals(
                OutputFormatType.JSON,
                filter.getOutputFormat());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testGetErrorMessage() 
            throws InternalServerErrorException, 
                   InvalidParameterException, 
                   JAXBException  {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operation", "GetErrorMessage");
        params.put("outputFormat", "xml");
        params.put(RESTFilter.ERROR_NUMBER_PARAM, "-1001");
        RESTFilter filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        String responseStr = filter.translate();
        JAXBContext context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        Unmarshaller unMarshaller = context.createUnmarshaller();

        JAXBElement<GetErrorMessageResponse> jaxBResponse = 
                (JAXBElement<GetErrorMessageResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        GetErrorMessageResponse response = jaxBResponse.getValue();
        
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1001.getErrorMessage(), 
                response.getErrorMessage());
        
        // Try a JSON version
        params = new HashMap<String, Object>();
        params.put("operation", "GetErrorMessage");
        params.put("outputFormat", "json");
        params.put(RESTFilter.ERROR_NUMBER_PARAM, "-1002");
        filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        responseStr = filter.translate();
        System.out.println(responseStr);

        unMarshaller = context.createUnmarshaller();
        unMarshaller.setProperty(
                MarshallerProperties.MEDIA_TYPE, 
                MediaType.APPLICATION_JSON);
        jaxBResponse = 
                (JAXBElement<GetErrorMessageResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        response = jaxBResponse.getValue();
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1002.getErrorMessage(), 
                response.getErrorMessage());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testParseCoordPairs() 
            throws InternalServerErrorException, 
                   InvalidParameterException, 
                   JAXBException {
    
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_COORD_PAIR.getText());
        params.put("outputFormat", "xml");
        params.put(RESTFilter.COORD_PAIR_PARAM, "W123 01 25.2, N23 01 25.2,");
        String responseStr = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build()
                .translate();
        
        JAXBContext context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        Unmarshaller unMarshaller = context.createUnmarshaller();

        JAXBElement<ParseCoordPairResponse> jaxBResponse = 
                (JAXBElement<ParseCoordPairResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        ParseCoordPairResponse response = jaxBResponse.getValue();
        Assert.assertEquals(
                23.023668, 
                response.getCoordinatePair().getLatitude(),
                0.0);
        Assert.assertEquals(
                -123.023664, 
                response.getCoordinatePair().getLongitude(),
                0.0);

        // Try a JSON version
        params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_COORD_PAIR.getText());
        params.put("outputFormat", "json");
        params.put(RESTFilter.COORD_PAIR_PARAM, "W123 01 25.2, N23 01 25.2,");
        responseStr = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build()
                .translate();
        
        context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        unMarshaller = context.createUnmarshaller();
        unMarshaller.setProperty(
                MarshallerProperties.MEDIA_TYPE, 
                MediaType.APPLICATION_JSON);
        jaxBResponse = 
                (JAXBElement<ParseCoordPairResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        response = jaxBResponse.getValue();
        Assert.assertEquals(
                23.023668, 
                response.getCoordinatePair().getLatitude(),
                0.0);
        Assert.assertEquals(
                -123.023664, 
                response.getCoordinatePair().getLongitude(),
                0.0);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testParseCoordPair() 
            throws InternalServerErrorException, 
                   InvalidParameterException, 
                   JAXBException {
    
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_COORD_PAIRS.getText());
        params.put("outputFormat", "xml");
        params.put(RESTFilter.COORD_PAIRS_PARAM, 
                "W123 01 25.2, N23 01 25.2, E37 45 59.9, S1 1 1.5, W1 8 0.1, S0 0 59.8");
        String responseStr = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build()
                .translate();
        
        JAXBContext context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        Unmarshaller unMarshaller = context.createUnmarshaller();

        JAXBElement<ParseCoordPairsResponse> jaxBResponse = 
                (JAXBElement<ParseCoordPairsResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        ParseCoordPairsResponse response = jaxBResponse.getValue();

        Assert.assertEquals(
                23.023668, 
                response.getCoordinatePairs().get(0).getLatitude(),
                0.0);
        Assert.assertEquals(
                -123.023664, 
                response.getCoordinatePairs().get(0).getLongitude(),
                0.0);
        Assert.assertEquals(
                -1.017083, 
                response.getCoordinatePairs().get(1).getLatitude(),
                0.0);
        Assert.assertEquals(
                37.76664, 
                response.getCoordinatePairs().get(1).getLongitude(),
                0.0);
        Assert.assertEquals(
                -0.016611, 
                response.getCoordinatePairs().get(2).getLatitude(),
                0.0);
        Assert.assertEquals(
                -1.133361, 
                response.getCoordinatePairs().get(2).getLongitude(),
                0.0);
        
        // Try a JSON version
        params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_COORD_PAIRS.getText());
        params.put("outputFormat", "json");
        params.put(RESTFilter.COORD_PAIRS_PARAM, 
                "W123 01 25.2, N23 01 25.2, E37 45 59.9, S1 1 1.5, W1 8 0.1, S0 0 59.8");
        responseStr = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build()
                .translate();
        
        context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        unMarshaller = context.createUnmarshaller();
        unMarshaller.setProperty(
                MarshallerProperties.MEDIA_TYPE, 
                MediaType.APPLICATION_JSON);
        jaxBResponse = 
                (JAXBElement<ParseCoordPairsResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        Assert.assertEquals(
                23.023668, 
                response.getCoordinatePairs().get(0).getLatitude(),
                0.0);
        Assert.assertEquals(
                -123.023664, 
                response.getCoordinatePairs().get(0).getLongitude(),
                0.0);
        Assert.assertEquals(
                -1.017083, 
                response.getCoordinatePairs().get(1).getLatitude(),
                0.0);
        Assert.assertEquals(
                37.76664, 
                response.getCoordinatePairs().get(1).getLongitude(),
                0.0);
        Assert.assertEquals(
                -0.016611, 
                response.getCoordinatePairs().get(2).getLatitude(),
                0.0);
        Assert.assertEquals(
                -1.133361, 
                response.getCoordinatePairs().get(2).getLongitude(),
                0.0);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testParseLatitude() 
            throws InternalServerErrorException, 
                   InvalidParameterException, 
                   JAXBException {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operation", "ParseLatitude");
        params.put("outputFormat", "xml");
        params.put(RESTFilter.LATITUDE_PARAM, "N23 01 25.2");
        String responseStr  = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build()
                .translate();
        System.out.println(responseStr);
        JAXBContext context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        Unmarshaller unMarshaller = context.createUnmarshaller();

        JAXBElement<ParseLatitudeResponse> jaxBResponse = 
                (JAXBElement<ParseLatitudeResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        ParseLatitudeResponse response = jaxBResponse.getValue();
        
        Assert.assertEquals(
                23.023668, 
                response.getLatitude(),
                0.0);
        
        params = new HashMap<String, Object>();
        params.put("operation", "ParseLatitude");
        params.put("outputFormat", "json");
        params.put(RESTFilter.LATITUDE_PARAM, "N23 01 25.2");
        responseStr = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build()
                .translate();
         System.out.println(responseStr);
        context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        unMarshaller = context.createUnmarshaller();
        unMarshaller.setProperty(
                MarshallerProperties.MEDIA_TYPE, 
                MediaType.APPLICATION_JSON);
        jaxBResponse = 
                (JAXBElement<ParseLatitudeResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        response = jaxBResponse.getValue();
        
        Assert.assertEquals(
                23.023668, 
                response.getLatitude(),
                0.0);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testParseLatitudes() 
            throws InternalServerErrorException, 
                   InvalidParameterException, 
                   JAXBException  {
        
        // Values copied from superclass.
        List<String> latitudes = Arrays.asList(
                "N23 01 25.2",
                "S1 1 1.5",
                "S0 0 59.8",
                "23 01 25.2N",
                "-13 30 30.8",
                "+23/01/25.2",
                "230125.2",
                "+45 00 00-",
                "9.87 N",
                "9.87N",
                "+12-13-45-",
                "23.5",
                "-83.5",
                "0");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_LATITUDES.getText());
        params.put("outputFormat", "xml");
        params.put(RESTFilter.LATITUDES_PARAM, String.join(",", latitudes));
        
        RESTFilter filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        String responseStr = filter.translate();
        // Un-marshal the String back to an object to test the results.
        JAXBContext context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        Unmarshaller unMarshaller = context.createUnmarshaller();

        JAXBElement<ParseLatitudesResponse> jaxBResponse = 
                (JAXBElement<ParseLatitudesResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        ParseLatitudesResponse response = jaxBResponse.getValue();
        
    
        int counter = 0;
        for (double value : response.getLatitudes()) {
            Assert.assertEquals(
                super.validLatitudes.get(latitudes.get(counter)),
                value,
                0.0);
            counter++;
        }
        
        params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_LATITUDES.getText());
        params.put("outputFormat", "json");
        params.put(RESTFilter.LATITUDES_PARAM, String.join(",", latitudes));
        filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        responseStr = filter.translate();
        
        // Un-marshal the String back to an object to test the results.
        context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        unMarshaller = context.createUnmarshaller();
        unMarshaller.setProperty(
                MarshallerProperties.MEDIA_TYPE, 
                MediaType.APPLICATION_JSON);
        jaxBResponse = 
                (JAXBElement<ParseLatitudesResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        response = jaxBResponse.getValue();
        
        counter = 0;
        for (double value : response.getLatitudes()) {
            Assert.assertEquals(
                super.validLatitudes.get(latitudes.get(counter)),
                value,
                0.0);
            counter++;
        }
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testParseLongitude() 
            throws InternalServerErrorException, 
                   InvalidParameterException, 
                   JAXBException  {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_LONGITUDE.getText());
        params.put("outputFormat", "xml");
        params.put(RESTFilter.LONGITUDE_PARAM, "W123 01 25.2");
        RESTFilter filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        String responseStr = filter.translate();
        // Un-marshal the String back to an object to test the results.
        JAXBContext context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        Unmarshaller unMarshaller = context.createUnmarshaller();

        JAXBElement<ParseLongitudeResponse> jaxBResponse = 
                (JAXBElement<ParseLongitudeResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        ParseLongitudeResponse response = jaxBResponse.getValue();
        
        Assert.assertEquals(
                -123.023664, 
                response.getLongitude(),
                0.0);
        
        params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_LONGITUDE.getText());
        params.put("outputFormat", "json");
        params.put(RESTFilter.LONGITUDE_PARAM, "W123 01 25.2");
        filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        responseStr = filter.translate();
        
        // Un-marshal the String back to an object to test the results.
        context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        unMarshaller = context.createUnmarshaller();
        unMarshaller.setProperty(
                MarshallerProperties.MEDIA_TYPE, 
                MediaType.APPLICATION_JSON);
        jaxBResponse = 
                (JAXBElement<ParseLongitudeResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        response = jaxBResponse.getValue();
        
        Assert.assertEquals(
                -123.023664, 
                response.getLongitude(),
                0.0);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testParseLongitudes() 
            throws InternalServerErrorException, 
                   InvalidParameterException, 
                   JAXBException  {
        
        // Values copied from superclass.
        List<String> longitudes = Arrays.asList(
                "W123 01 25.2", 
                "W123 01 25.2", 
                "E37 45 59.9", 
                "W1 8 0.1", 
                "123 01 25.2W", 
                "179 59 59.999E", 
                "0 0 0.00000W", 
                "-123/01/25.2",
                "1230125.2", 
                "+45 00 00-", 
                "123.34E", 
                "123.34 E",
                "+123-13-45-");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_LONGITUDES.getText());
        params.put("outputFormat", "xml");
        params.put(RESTFilter.LONGITUDES_PARAM, String.join(",", longitudes));
        
        RESTFilter filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        String responseStr = filter.translate();
        // Un-marshal the String back to an object to test the results.
        JAXBContext context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        Unmarshaller unMarshaller = context.createUnmarshaller();

        JAXBElement<ParseLongitudesResponse> jaxBResponse = 
                (JAXBElement<ParseLongitudesResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        ParseLongitudesResponse response = jaxBResponse.getValue();
        
        
        int counter = 0;
        for (double value : response.getLongitudes()) {
            Assert.assertEquals(
                super.validLongitudes.get(longitudes.get(counter)),
                value,
                0.0);
            counter++;
        }
        
        params = new HashMap<String, Object>();
        params.put("operation", OperationType.PARSE_LONGITUDES.getText());
        params.put("outputFormat", "json");
        params.put(RESTFilter.LONGITUDES_PARAM, String.join(",", longitudes));
        filter = new RESTFilter.RESTFilterBuilder()
                .withParameterMap(params)
                .build();
        responseStr = filter.translate();
        
        // Un-marshal the String back to an object to test the results.
        context = JAXBContext.newInstance(
                "mil.nga.coordinate_parse");
        unMarshaller = context.createUnmarshaller();
        unMarshaller.setProperty(
                MarshallerProperties.MEDIA_TYPE, 
                MediaType.APPLICATION_JSON);
        jaxBResponse = 
                (JAXBElement<ParseLongitudesResponse>)
                    unMarshaller.unmarshal(new StringReader(responseStr));

        response = jaxBResponse.getValue();
        
        counter = 0;
        for (double value : response.getLongitudes()) {
            Assert.assertEquals(
                super.validLongitudes.get(longitudes.get(counter)),
                value,
                0.0);
            counter++;
        }

    }
}
