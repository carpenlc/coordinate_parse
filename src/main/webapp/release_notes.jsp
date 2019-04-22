<%-- 
    Document   : release_notes
    Created on : Feb 17, 2009, 5:38:47 PM
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
        <%= str%> (Release Notes)
		</h1>
	<br clear="all" />        
        <p>
            <ul>
                <li>Release 1.4 - October 2010
                    <ul>
                        <li>Fixed multi-threaded error associated with the REST interface.</li>
                        <li>Removed parenthesis from JSON response without callback.</li>
                    </ul>
                 
                <li>Release 1.3 - July 2009
                <ul>
                    <li>Changed coordinate pair schema elements from
                    'sequence' to 'all' and switched order to longitude,latitude in schema.</li>
                    <li>Applied fix to CoordParse library and updated zip file for download.</li>
                    <li>Updated the service documentation pages.</li>
                    <li>Compiled using JDK 6. </li>
                </ul>
                <li>Release 1.2 - January 2009
                <ul>
                    <li>Updated WSDL with namespaces (xsd, wsdl)</li>
                    <li>Moved NGA Resource to nga_resource.xml file</li>
                    <li>Added coordinate_parse_servlet (RESTful interface)</li>
                    <li>Converted description document to HTML format</li> 
                </ul>
                </li>                
                <li>Release 1.1 - Feb 2008
                <ul>
                    <li>Converted service to JAX-WS</li>
                </ul>
                </li>  

            </ul>
                
                
        </p>

        </div>	
        <br clear="all" />
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAfooter.jsp" />
        </table>
        
    </body>
</html>
