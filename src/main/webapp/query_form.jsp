<%-- 
    Document   : query_form
    Created on : Feb 17, 2009, 5:36:14 PM
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
        <%= str%> (Query Form)
		</h1>
	<br clear="all" />        

        <p>This HTML form calls the RESTFul interface of the service.</p>
        <form action="coordinate_parse_servlet?operation=GetErrorMessage" method="POST">
            <table cellpadding="2">
            <tr><td colspan="2"><b> GetErrorMessage </b></td></tr>
            <tr>
                <td>
                    Error Number:                    
                </td>
                <td>
                    <input type="text" name="ErrorNum" value="-1001" />
                </td>
            </tr>
            <tr>
                <td>
                    OutputFormat:
                </td>
                <td>
                    <input type="radio" name="outputFormat" value="XML" checked="checked" />XML
                    <input type="radio" name="outputFormat" value="JSON" />JSON
                </td>
            </tr>
                        
            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="GetErrorMessage" />
                </td>                
            </tr>
            
            </table>
        </form>
        <br/>
        <form action="coordinate_parse_servlet?operation=ParseCoordPair" method="POST">
            <table cellpadding="2">
            <tr><td colspan="2"><b> ParseCoordinatePair </b></td></tr>                
            <tr>
                <td>
                    CoordinatePairString (lon,lat):                    
                </td>
                <td>
                    <input type="text" size="40" name="CoordinatePairString" value="1231212.4W,233445.8S" />
                </td>
            </tr>
            <tr>
                <td>
                    OutputFormat:
                </td>
                <td>
                    <input type="radio" name="outputFormat" value="XML" checked="checked" />XML
                    <input type="radio" name="outputFormat" value="JSON" />JSON
                </td>
            </tr>
                        
            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="ParseCoordPair" />
                </td>                
            </tr>
            
            </table>
        </form>
        <br/>
        
        <form action="coordinate_parse_servlet?operation=ParseCoordPairs" method="POST">
            <table cellpadding="2">
            <tr><td colspan="2"><b> ParseCoordinatePairs </b></td></tr>                
            <tr>
                <td>
                    CoordinatePairStrings <br/>
                    (lon,lat,lon,lat,...,lon,lat):                    
                </td>
                <td>
                    <TEXTAREA NAME="CoordinatePairStrings" COLS=80 ROWS=3>1231212.4W,233445.8S,531212.4W,53445.8S,223112.4W,333445.8S</TEXTAREA>
                </td>                

            </tr>
            <tr>
                <td>
                    OutputFormat:
                </td>
                <td>
                    <input type="radio" name="outputFormat" value="XML" checked="checked" />XML
                    <input type="radio" name="outputFormat" value="JSON" />JSON
                </td>
            </tr>
                        
            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="ParseCoordPairs" />
                </td>                
            </tr>
            
            </table>
        </form>
        <br/>
        
        <form action="coordinate_parse_servlet?operation=ParseLatitude" method="POST">
            <table cellpadding="2">
            <tr><td colspan="2"><b> ParseLatitude </b></td></tr>                
            <tr>
                <td>
                    latitude:                    
                </td>
                <td>
                    <input type="text" size="40" name="Latitude" value="233445.8S" />
                </td>                

            </tr>
            <tr>
                <td>
                    OutputFormat:
                </td>
                <td>
                    <input type="radio" name="outputFormat" value="XML" checked="checked" />XML
                    <input type="radio" name="outputFormat" value="JSON" />JSON
                </td>
            </tr>
                        
            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="ParseLatitude" />
                </td>                
            </tr>
            
            </table>
        </form>
        <br/>
        
        <form action="coordinate_parse_servlet?operation=ParseLatitudes" method="POST">
            <table cellpadding="2">
            <tr><td colspan="2"><b> ParseLatitudes </b></td></tr>                
            <tr>
                <td>
                    latitudes:                    
                </td>
                <td>
                    <TEXTAREA NAME="Latitudes" COLS=80 ROWS=3>233445.8S,333445.8S,433445.8S</TEXTAREA>
                </td>                

            </tr>
            <tr>
                <td>
                    OutputFormat:
                </td>
                <td>
                    <input type="radio" name="outputFormat" value="XML" checked="checked" />XML
                    <input type="radio" name="outputFormat" value="JSON" />JSON
                </td>
            </tr>
                        
            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="ParseLatitudes" />
                </td>                
            </tr>
            
            </table>
        </form>
        <br/>
        
        <form action="coordinate_parse_servlet?operation=ParseLongitude" method="POST">
            <table cellpadding="2">
            <tr><td colspan="2"><b> ParseLongitude </b></td></tr>                
            <tr>
                <td>
                    longitude:                    
                </td>
                <td>
                    <input type="text" size="40" name="Longitude" value="1231212.4E" />
                </td>                

            </tr>
            <tr>
                <td>
                    OutputFormat:
                </td>
                <td>
                    <input type="radio" name="outputFormat" value="XML" checked="checked" />XML
                    <input type="radio" name="outputFormat" value="JSON" />JSON
                </td>
            </tr>
                        
            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="ParseLongitude" />
                </td>                
            </tr>
            
            </table>
        </form>
        <br/>
        
        <form action="coordinate_parse_servlet?operation=ParseLongitudes" method="POST">
            <table cellpadding="2">
            <tr><td colspan="2"><b> ParseLongitudes </b></td></tr>                
            <tr>
                <td>
                    longitudes:                    
                </td>
                <td>
                    <TEXTAREA NAME="Longitudes" COLS=80 ROWS=3>1231212.4W,531212.4W,831212.4W</TEXTAREA>
                </td>                

            </tr>
            <tr>
                <td>
                    OutputFormat:
                </td>
                <td>
                    <input type="radio" name="outputFormat" value="XML" checked="checked" />XML
                    <input type="radio" name="outputFormat" value="JSON" />JSON
                </td>
            </tr>
                        
            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="ParseLongitudes" />
                </td>                
            </tr>
            
            </table>
        </form>
        <br/>        


        </div>	
        <br clear="all" />
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAfooter.jsp" />
        </table>
        
    </body>
</html>

