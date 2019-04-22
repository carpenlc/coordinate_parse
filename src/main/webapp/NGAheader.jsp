<%-- 
    Document   : header
    Created on : Feb 17, 2009, 4:26:08 PM
    Author     : jenningd
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tr bgcolor="#000000">
    <td colspan="2" bgcolor="#000000">
	<div class="classif"><a name="top"></a>
            <!--vv Classification vv-->
            <!-- Insert page classification here-->
                UNCLASSIFIED
            <!--^^ Classification ^^-->
            </div>
            <!-- Set Location of the header.ssi -->
              
            <c:import url="ssi/header.ssi" />

            
	<div id="bread-crumbs">
            <!--vv Bread-crumbs vv-->
            <!-- NGA should always be the first link, the other are up to you,
                but should provide the user context, as to where they are in 
                the site
            -->
            
            <a href="/">Home</a> &gt;
            <a href="/coordinate_parse">CoordinateParseService</a> &gt;
            
            <!-- Add additional bread crumb links as needed -->
            <!-- <a href="">sample</a> &gt; -->
            <!--^^ Bread-crumbs ^^-->            
        </div>        
    </td>    
</tr>
