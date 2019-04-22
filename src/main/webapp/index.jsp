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
        <%= str%>
		</h1>
	<br clear="all" />        
        <a href="description_document.jsp">Description Document</a><br/>
        <a href="nga_resource.xml">Resource XML</a><br/>
        <a href="ddms.xml">DDMS XML</a><br/>
        <a href="example_restful_calls.jsp">Example RESTful Calls</a><br/>
        <a href="query_form.jsp">Query Form</a><br/>
        <a href="javascript.jsp">Coordinate Parse in Javascript</a><br/>
        <a href="coordinate_parse_service?wsdl">WSDL</a><br/>
      
        </div>	
        <br clear="all" />
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAfooter.jsp" />
        </table>
        
    </body>
</html>
