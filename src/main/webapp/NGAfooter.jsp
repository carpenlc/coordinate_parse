<%-- 
    Document   : NGAFooter
    Created on : Feb 17, 2009, 4:56:46 PM
    Author     : jenningd
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tr bgcolor="#ffffff">
    <td colspan="2">

        <br clear="all" />
        <!--vv POC Information vv-->
        <!-- This area should contain POC contact information for 
            the cont of the web page. -->
        <div class="poc-info">
            <strong>Point of Contact:</strong><br />
            Keith Bridgman/EGMC6<br />
            phone 636-321-5495, DSN 369-5495<br /> <!-- (include area code, may also include DSN) -->
            <a href="mailto:keith.a.bridgman@nga.mil">keith.a.bridgman@nga.mil</a> <!-- (state network, may be hotlinked if on this network) -->
        </div>
        <!--^^ POC Information ^^-->

        <br clear="all" />
        <!--#config timefmt="%B %d, %Y" -->		 
        <p class="modified">Document last modified 
        <%
            String jspPath = application.getRealPath(request.getServletPath());
            java.io.File jspFile = new java.io.File(jspPath);
            out.println(new java.util.Date(jspFile.lastModified()));
        %>        
        
        </p>
        <!-- Set Location of the footer.ssi -->

        <c:import url="/ssi/footer.ssi" />

        <div class="classif">
            <!--vv Classification vv-->
            <!-- Insert page classification here-->
            UNCLASSIFIED
            <!--^^ Classification ^^-->
	</div>
    </td>    
</tr>
