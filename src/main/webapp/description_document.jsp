<%-- 
    Document   : index
    Created on : Feb 17, 2009, 5:02:56 PM
    Author     : jenningd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
        String str = pageContext.getServletContext().getInitParameter("SERVICE_VERSION");
        if (str == null) {
            str = "SERVICE_VERSION not set in web.xml";
        }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NGA:(U) <%= str%> (U)</title>
        <!-- Configure location of agency.css -->
   
        <style type='text/css' media='screen,projection'>
            @import 'css/agency.css';
        </style>
      
    </head>
    <body>
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAheader.jsp" />
        </table>
        <div id="content">
        <h1 class="page-title"><img src="icons/agency-bullet.gif" alt="" /> 
        <!-- Change this to your page title -->
        <%= str%> (Description Document)
		</h1>
	<br clear="all" />     
        
        <p>
            <b>Version:</b>
            <%= str%> [
            <a href="release_notes.jsp">Release Notes</a>
            ]
        </p>
        <p>
            <b>Context Path:</b><br />
        coordinate_parse</p>
        <p>
            <b>Service Name (lower_case):</b><br />
        coordinate_parse_service</p>
        <p>
            <b>Service Name (UpperCamelCase):</b><br />
        CoordinateParseService</p>
        <p>
            <b>Implementation:</b><br />
            Web Service was created using NetBeans 6.5 integrated development environment
            (IDE). The web service was built to be deployed on Sun Java System Application
            Server 9.1.
        </p>
        <p>
            <b>Description:</b><br />
            This service can parse a coordinate string with coordinate specified 
            in many different degree minute second (DMS) formats to decimal degrees.
        </p>
        <p> The service could be used by applications requiring an 
        occasional conversion from DMS to DD.  Applications that 
        frequently require conversions should integrate DMS to DD 
        conversion into their applications.
        The Netbeans Java application used by the CoordinateParseService to convert 
        from DMS to DD can be found in the <a href="CoordParse.zip">
        CoordParse.zip</a> file. 
        </p>
        
            
        <p>
        <b>Testing:</b><br /></p>
        <p> The code was tested with an assortment of DMS formats and values.  
            The program converted the values accurately.  
        </p>
        <p>
            The WS-I monitor tool was used to collect calls to the web service. The log
            file and WSDL were then analyzed using the WS-I Analyzer tool. The web service 
            passed the WS-I Simple SOAP Binding 1.0 and Basic Profile 1.1 tests.  The log file
            and analyzer report are included in the testing artifacts.
        </p>
        <p>
            Test clients were built using NetBeans 5.5, NetBeans 6.x, and Visual Basic
            2005 Express Edition using the WSDL. Test clients were also built in 
            other programming languages to demonstrate that the service can be 
            called manually.
        </p>
        <p>
        <b>Development:</b><br /></p>
        <p>
            The web service was created in a Java Web Application. The web
            service was created from a WSDL file. The web service was created 
            using the JAX-WS libraries. The service includes configurable logging 
            (log4j) and error trapping.
        </p>
        <p>
        <b>Functional Requirements:</b></p>
        <br />
        <ul>
            <li>All coordinate values must be specified in decimal degrees and 
                are assumed to be WGS-84.   Valid latitude values range from -90 to 
            90.  Valid longitude values range from -180 to 180.</li>
            <li>The service shall parse a number of valid DMS formats 
            (See DMS Formats) </li>
            <li>The service shall provide an operation to parse a single latitude </li>
            <li>The service shall provide an operation to parse a multiple latitude</li>
            <li>The service shall provide an operation to parse a single longitude</li>
            <li>The service shall provide an operation to parse a multiple longitudes</li>
            <li>The service shall provide an operation to parse a single Coordinate Pair</li>
            <li>The service shall provide an operation to parse a multiple Coordinate Pairs</li>
            <li>f an error is detected the service shall return a value of less 
                than -1000.  The service shall provide an operation to return an 
            error message corresponding to a given error number.</li>
            <li>The Web Service shall be WS-I Compliant </li>
            <li>The Web Service shall include the minimal security tag with 
                attributes of the Intelligence Community Information Security 
            Marking (IC-ISM) implementation guide </li>
        </ul>
        <p>
        <b>Web Service Operations:</b><br />
        <ul>
            <li>parseLatitude : Returns latitude in Decimal Degrees.</li>
            <ul>
                <li>Inputs</li>
                <ul>
                    <li>latitude, String, latitude in DMS format</li>
                    <li>security, SecurityElement, optional</li>                
                </ul>
                <li>Outputs</li>
                <ul>                
                    <li>latitude, String, latitude in DD or error number.</li>
                    <li>security, SecurityElement, mandatory</li>
                </ul>
            </ul>
                
            <li>parseLatitudes : Returns latitudes in Decimal Degrees.</li>
            <ul>
                <li>Inputs</li>
                <ul>
                    <li>latitudes, Array of Strings, latitudes in DMS format</li>
                    <li>security, SecurityElement, optional</li>                
                </ul>
                <li>Outputs</li>
                <ul>                
                    <li>latitudes, Array of Strings, latitudes in DD or error numbers.</li>
                    <li>security, SecurityElement, mandatory</li>
                </ul>
            </ul>
                
            <li>parseLongitude : Returns longitude in Decimal Degrees.</li>
            <ul>
                <li>Inputs</li>
                <ul>
                    <li>longitude, String, longitude in DMS format</li>
                    <li>security, SecurityElement, optional</li>                
                </ul>
                <li>Outputs</li>
                <ul>                
                    <li>longitude, String, longitude in DD or error number.</li>
                    <li>security, SecurityElement, mandatory</li>
                </ul>
            </ul>
                
            <li>parseLongitudes : Returns longitudes in Decimal Degrees.</li>
            <ul>
                <li>Inputs</li>
                <ul>
                    <li>longitudes, Array of Strings, longitudes in DMS format</li>
                    <li>security, SecurityElement, optional</li>                
                </ul>
                <li>Outputs</li>
                <ul>                
                    <li>longitudes, Array of Strings, longitudes in DD or error numbers.</li>
                    <li>security, SecurityElement, mandatory</li>
                </ul>
            </ul>
                
            <li>parseCoordPair : Returns Coorindate Pair in Decimal Degrees.</li>
            <ul>
                <li>Inputs</li>
                <ul>
                    <li>coordPairString, CoordPairString, Coordinate in DMS format</li>
                    <li>security, SecurityElement, optional</li>                
                </ul>
                <li>Outputs</li>
                <ul>                
                    <li>coordPair, CoordPair, Coordinate in DD or error number.</li>
                    <li>security, SecurityElement, mandatory</li>
                </ul>
            </ul>
                
            <li>parseCoordPairs : Returns Coordinate Pairs in Decimal Degrees.</li>
            <ul>
                <li>Inputs</li>
                <ul>
                    <li>coordPairStrings, Array of CoordPairString, Coordinates in DMS format</li>
                    <li>security, SecurityElement, optional</li>                
                </ul>
                <li>Outputs</li>
                <ul>                
                    <li>coordPairs, Array of CoordPair, Coordinates in DD or error number.</li>
                    <li>security, SecurityElement, mandatory</li>
                </ul>
            </ul>
                
        </ul>
        <br />
        <p />
        <b>Usage Notes</b><br />
        <p> <i>Supported hemisphere indicators include: </i>    
            <ul>
                <li>North: +, N, or n </li>
                <li>South: -, S, or s </li>
                <li>East: +, E, or e </li>
                <li>West: -, W, or w </li>        
            </ul>
        </p>
        <p> <i>Undelimited DMS Latitude: (D = Degrees, M = Minutes, S = Seconds) </i>    
            <ul>
                <li>DDMMSS.SS </li>
                <li>DMMSS.SS </li>
                <li>DDMM.MM </li>
                <li>DMM.MM </li>        
                <li>DD.DD </li>        
                <li>D.DD </li>        
            </ul>
        </p>
        <p> <i>Undelimited DMS Longitude: (D = Degrees, M = Minutes, S = Seconds) </i>    
            <ul>
                <li>DDDMMSS.SS</li>
                <li>DDMMSS.SS </li>
                <li>DMMSS.SS </li>
                <li>DDMM.MM </li>
                <li>DMM.MM </li>        
                <li>DD.DD </li>        
                <li>D.DD </li>        
            </ul>
        </p>
        <p> <i>Delimiters: </i>    
            <ul>
                <li>-   (dash)</li>
                <li>/   (slash) </li>
                <li>\   (backslash) </li>
                <li>:  (colon) </li>
                <li>;  (semicolon) </li>        
                <li>)  (close bracket) </li>        
                <li>(  (open bracket)</li>    
                <li>_  (underscore)</li>
                <li>  (space)</li>
            </ul>
        </p>    
        <p> <i>Example Delimited Formats: </i>    
            <ul>
                <li>DD MM SS.SS</li>
                <li>DD/MM/SS.SS </li>
                <li>DD(MM.MM </li>
                <li>DDD_MM_SS </li>
            </ul>
        </p>       
        <p> <i>Rules: </i>    
            <ul>
                <li>Only one decimal point is allowed </li>
                <li>Accuracy is limited to 14 to 15 significant digits </li>
                <li>Only one direction indicator is allowed </li>
                <li>Latitude must be between -90 to 90 </li>
                <li>Longitude must be between -180 to 360 </li>
                <li>The + or – hemisphere indicators must be at the beginning; all others must be at the beginning or end </li>
                <li>Only one hemisphere indicator is allowed </li>
                <li>No hemisphere indicator is assumed to be North (latitude) or East for (longitude) </li>        
            </ul>
        </p>     
        <p> <i>Example Latitudes: </i>    
            <ul>
                <li>N23 01 25.2 => 23.023667  </li>
                <li>S1 1 1.5 => -1.017083 </li>
                <li>S0 0 59.8 => -0.016611 </li>
                <li>23 01 25.2N => 23.023667  </li>
                <li>-13 30 30.8 => -13.508556 </li>
                <li>+23/01/25.2 => 23.023667   </li>
                <li>230125.2 => 23.023667  </li>
                <li>+45 00 00- => 45.0 </li>        
                <li>9.87 N => 9.87     </li>
                <li>9.87N => 9.87   </li>
                <li>+12-13-45- => 12.229167  </li>   
            </ul>
        </p>       
        <p> <i>Example Longitudes: </i>    
            <ul>
                <li>W123 01 25.2 => -123.023667  </li>
                <li>E37 45 59.9 => 37.766639 </li>
                <li>W1 8 0.1 => -1.133361 </li>
                <li>123 01 25.2W =>-123.023667   </li>
                <li>179 59 59.999E =>180.000000  </li>
                <li>0 0 0.00000W =>-0.0   </li>
                <li>-123/01/25.2 =>-123.023667    </li>
                <li>1230125.2 => 123.023667 </li>        
                <li>+45 00 00- => 45.0      </li>
                <li>123.34E => 123.34   </li>
                <li>123.34 E => 123.34     </li>   
                <li>+123-13-45- => 123.229167 </li>
                <li>123 W01 34 => -123.026111</li>
            </ul>
        </p>       
            
            
        <p>
        <b>Message Sequence Diagram:</b><br />
        <img src="coordinate_parse_service.png" />
        </div>	
        <br clear="all" />
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAfooter.jsp" />
        </table>
        
    </body>
</html>
