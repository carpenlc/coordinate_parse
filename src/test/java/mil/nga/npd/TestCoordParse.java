package mil.nga.npd;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test class for the <code>mil.nga.npd.CoordsParse</code> class.
 * 
 * @author L. Craig Carpenter
 */
public class TestCoordParse extends TestCoordinates {
    
    @Test
    public void testValidLatitudes() {
        CoordsParse parser = new CoordsParse();
        for (String coord : validLatitudes.keySet()) {
            Assert.assertEquals(
                    (double)validLatitudes.get(coord), 
                    parser.parseCoordString(coord, true),
                    0.0);
        }
    }
    
    @Test
    public void testInvalidLatitudes() {
        CoordsParse parser = new CoordsParse();
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1025.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[0], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1024.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[1], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1004.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[2], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1004.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[3], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1018.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[4], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1008.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[5], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1008.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[6], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1014.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[7], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1004.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[8], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1007.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[9], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1001.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[10], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1003.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[11], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1004.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[12], 
                                true)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1018.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLatitudes[13], 
                                true)));
    }
    
    @Test
    public void testValidLongitudes() {
        CoordsParse parser = new CoordsParse();
        for (String coord : validLongitudes.keySet()) {
            Assert.assertEquals(
                    (double)validLongitudes.get(coord), 
                    parser.parseCoordString(coord, false),
                    0.0);
        }
    }
    
    @Test
    public void testInvalidLongitudes() {
        CoordsParse parser = new CoordsParse();
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1027.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLongitudes[0], 
                                false)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1027.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLongitudes[1], 
                                false)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1004.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLongitudes[2], 
                                false)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1019.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLongitudes[3], 
                                false)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1009.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLongitudes[4], 
                                false)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1014.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLongitudes[5], 
                                false)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1004.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLongitudes[6], 
                                false)));
        Assert.assertEquals(
                ErrorMessageType.ERROR_MINUS_1007.getErrorCode(), 
                Integer.toString((int)
                        parser.parseCoordString(
                                invalidLongitudes[7], 
                                false)));
    }
}
