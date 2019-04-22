<%-- 
    Document   : example_restful_calls
    Created on : Feb 17, 2009, 5:33:24 PM
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
    
        <script LANGUAGE="JavaScript">
            function JumpTo(page) { 
                window.location.href = "coordinate_parse/coordinate_parse_servlet?" + page;
            }
        </script>            
    </head>
    <body>
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAheader.jsp" />
        </table>
        <div id="content">
        <h1 class="page-title"><img src="icons/agency-bullet.gif" alt="" /> 
        <!-- Change this to your page title -->
        <%= str%> (Example RESTful Calls)
		</h1>
	<br clear="all" />      
      <table border="1" cellpadding="10" cellspacing="1">
          <tr><td colspan="2" align="center"><b>GetErrorMessage</b></td> </tr>
          <tr><td><b>Request Description</b></td><td><b>URL</b></td></tr>
          <tr>                            
              <td>Get Error Message for Error Number -1002.</td>
              <td>
                  <a href="coordinate_parse_servlet?operation=GetErrorMessage&ErrorNum=-1002">XML</a>
                  <a href="coordinate_parse_servlet?operation=GetErrorMessage&ErrorNum=-1002&outputFormat=JSON">JSON</a>                  
              </td>
          </tr>
          <tr>
              <td>Get Error Message for Error Number -1013.</td>
              <td>
                  <a href="coordinate_parse_servlet?operation=GetErrorMessage&ErrorNum=-1013">XML</a>
                  <a href="coordinate_parse_servlet?operation=GetErrorMessage&ErrorNum=-1013&outputFormat=JSON">JSON</a>                  
              </td>
          </tr>      
          
          <tr><td colspan="2" align="center"><b>ParseCoordPair</b></td> </tr>
          <tr><td><b>Request Description</b></td><td><b>URL</b></td></tr>
          <tr>                            
              <td>Parse Coordinate Pair 1231212.4W,233445.8S</td>
              <td>
                  <a href="coordinate_parse_servlet?operation=ParseCoordPair&CoordinatePairString=1231212.4W,233445.8S">XML</a>
                  <a href="coordinate_parse_servlet?operation=ParseCoordPair&CoordinatePairString=1231212.4W,233445.8S&outputFormat=JSON">JSON</a>                  
                
              </td>
          </tr>
          
          <tr><td colspan="2" align="center"><b>ParseCoordPairs</b></td> </tr>
          <tr><td><b>Request Description</b></td><td><b>URL</b></td></tr>
          <tr>                            
              <td>Parse Coordinate Pairs 1231212.4W,233445.8S,531212.4W,53445.8S,223112.4W,333445.8S</td>
              <td>
                  <a href="coordinate_parse_servlet?operation=ParseCoordPairs&CoordinatePairStrings=1231212.4W,233445.8S,531212.4W,53445.8S,223112.4W,333445.8S">XML</a>
                  <a href="coordinate_parse_servlet?operation=ParseCoordPairs&CoordinatePairStrings=1231212.4W,233445.8S,531212.4W,53445.8S,223112.4W,333445.8S&outputFormat=JSON">JSON</a>                  
                
              </td>
          </tr>
          
          <tr><td colspan="2" align="center"><b>ParseLatitude</b></td> </tr>
          <tr><td><b>Request Description</b></td><td><b>URL</b></td></tr>
          <tr>                            
              <td>Parse Latitude 233445.8S</td>
              <td>
                  <a href="coordinate_parse_servlet?operation=ParseLatitude&Latitude=233445.8S">XML</a>
                  <a href="coordinate_parse_servlet?operation=ParseLatitude&Latitude=233445.8S&outputFormat=JSON">JSON</a>                  
                
              </td>
          </tr>

          <tr><td colspan="2" align="center"><b>ParseLatitudes</b></td> </tr>
          <tr><td><b>Request Description</b></td><td><b>URL</b></td></tr>
          <tr>                            
              <td>Parse Latitudes 233445.8S,333445.8S,433445.8S</td>
              <td>
                  <a href="coordinate_parse_servlet?operation=ParseLatitudes&Latitudes=233445.8S,333445.8S,433445.8S">XML</a>
                  <a href="coordinate_parse_servlet?operation=ParseLatitudes&Latitudes=233445.8S,333445.8S,433445.8S&outputFormat=JSON">JSON</a>                  
                
              </td>
          </tr>
          
          <tr><td colspan="2" align="center"><b>ParseLongitude</b></td> </tr>
          <tr><td><b>Request Description</b></td><td><b>URL</b></td></tr>
          <tr>                            
              <td>Parse Longitude 1231212.4E</td>
              <td>
                  <a href="coordinate_parse_servlet?operation=ParseLongitude&Longitude=1231212.4E">XML</a>
                  <a href="coordinate_parse_servlet?operation=ParseLongitude&Longitude=1231212.4E&outputFormat=JSON">JSON</a>                  
                
              </td>
          </tr>

          <tr><td colspan="2" align="center"><b>ParseLongitudes</b></td> </tr>
          <tr><td><b>Request Description</b></td><td><b>URL</b></td></tr>
          <tr>                            
              <td>Parse Longitudes 1231212.4W,531212.4W,831212.4W</td>
              <td>
                  <a href="coordinate_parse_servlet?operation=ParseLongitudes&Longitudes=1231212.4W,531212.4W,831212.4W">XML</a>
                  <a href="coordinate_parse_servlet?operation=ParseLongitudes&Longitudes=1231212.4W,531212.4W,831212.4W&outputFormat=JSON">JSON</a>                 
              </td>
          </tr>
          
      </table>   
        </div>	
        <br clear="all" />
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAfooter.jsp" />
        </table>        
    </body>
</html>

