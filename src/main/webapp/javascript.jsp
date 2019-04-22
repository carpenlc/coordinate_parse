<%-- 
    Document   : javascript
    Created on : Sep 16, 2010, 9:47:04 AM
    Author     : jenningd
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
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


        <script type="text/javascript" src="js/coordparse.js"></script>

        <script type="text/javascript">
            function parseLatitude(value) {

                var parsed_value = parseCoordString(document.getElementById("lat").value, true);

                el = document.getElementById("parsed_lat");

                if (parsed_value < -1000) {
                    parsed_value = getParseErrorMsg(parseInt(parsed_value));
                    el.setAttribute("style", "color:red;");
                } else {
                    el.setAttribute("style", "");
                }

                el.innerHTML = parsed_value;




            }

            function parseLongitude(value) {
                var parsed_value = parseCoordString(document.getElementById("lon").value, false)
                el = document.getElementById("parsed_lon");

                if (parsed_value < -1000) {
                    parsed_value = getParseErrorMsg(parseInt(parsed_value));
                    el.setAttribute("style", "color:red;");
                } else {
                    el.setAttribute("style", "");
                }
                el.innerHTML = parsed_value;
            }



        </script>
        <style type="text/css">
            #inputArea
            {
                font-family: Arial, Sans-Serif;
                font-size: 13px;
                background-color: #c6e5f4;
                padding: 10px;
                width: 510px;
                border: solid 1px red;


            }

            #inputArea input, #inputArea textarea
            {
                font-family: Arial, Sans-Serif;
                font-size: 13px;
                margin-bottom: 5px;
                display: block;
                padding: 4px;
                border: solid 2px #55b1de;
                width: 500px;
            }

            span.f1 { margin: 10px; }

        </style>



    </head>
    <body>
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAheader.jsp" />
        </table>
        <div id="content">
            <h1 class="page-title"><img src="icons/agency-bullet.gif" alt="" />
                <!-- Change this to your page title -->
                <%= str%> (Coordinate Parse with Javascript)
            </h1>
            <br clear="all" />



            <p>The <a href="js/coordparse.js">coordparse Javascript</a> converts common NGA
                DMS (Degrees Minute Seconds) formats to decimal degrees.
                This page contains a couple of Javascript functions that call the
                functions in the coordparse Javascript.</p>

            <div id="inputArea">
                <label for="lat"><b>Latitude</b></label>
                <input onkeyup="document.getElementById('parsed_lat').innerHTML = '';" type="text" value="N23 01 25.2" id="lat"/>


                <button class="button3" onclick="parseLatitude();">Parse Latitude</button>

                <span class="f1" id="parsed_lat"></span>
            </div>

            <br/>
            <br/>
            <div id="inputArea">
                <label for="lon"><b>Longitude</b></label>
                <input onkeyup="document.getElementById('parsed_lon').innerHTML = '';" type="text" value="1231211W" id="lon"/>
                <button onclick="parseLongitude();">Parse Longitude</button>

                <span class="f1" id="parsed_lon"></span>

            </div>

            <h3>Usage Notes</h3>
            <p>Enter a DMS coordinate into the text box.  Press the Parse button to convert to Decimal Degrees.</p>
            <p> <i>Supported hemisphere indicators include: </i></p>
            <ul>
                <li>North: +, N, or n </li>
                <li>South: -, S, or s </li>
                <li>East: +, E, or e </li>

                <li>West: -, W, or w </li>
            </ul>

            <p> <i>Undelimited DMS Latitude: (D = Degrees, M = Minutes, S = Seconds) </i></p>

            <ul>
                <li>DDMMSS.SS </li>
                <li>DMMSS.SS </li>

                <li>DDMM.MM </li>
                <li>DMM.MM </li>
                <li>DD.DD </li>
                <li>D.DD </li>
            </ul>

            <p> <i>Undelimited DMS Longitude: (D = Degrees, M = Minutes, S = Seconds) </i></p>
            <ul>

                <li>DDDMMSS.SS</li>
                <li>DDMMSS.SS </li>
                <li>DMMSS.SS </li>
                <li>DDMM.MM </li>
                <li>DMM.MM </li>
                <li>DD.DD </li>
                <li>D.DD </li>
            </ul>


            <p> <i>Delimiters: </i></p>

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
            <p> <i>Example Delimited Formats: </i></p>

            <ul>

                <li>DD MM SS.SS</li>
                <li>DD/MM/SS.SS </li>
                <li>DD(MM.MM </li>
                <li>DDD_MM_SS </li>
            </ul>
            <p> <i>Rules: </i></p>

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
            <p> <i>Example Latitudes: </i></p>

            <ul>
                <li>N23 01 25.2 => 23.023667  </li>
                <li>S1 1 1.5 => -1.017083 </li>

                <li>S0 0 59.8 => -0.016611 </li>
                <li>23 01 25.2N => 23.023667  </li>
                <li>-13 30 30.8 => -13.508556 </li>
                <li>+23/01/25.2 => 23.023667   </li>
                <li>230125.2 => 23.023667  </li>
                <li>+45 00 00 => 45.0 </li>
                <li>9.87 N => 9.87     </li>

                <li>9.87N => 9.87   </li>
                <li>+12-13-45 => 12.229167  </li>
            </ul>
            <p> <i>Example Longitudes: </i></p>

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
                <li>+123-13-45 => 123.229167 </li>
                <li>123 W01 34 => -123.026111</li>
            </ul>


        </div>
        <br clear="all" />
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <jsp:include page="NGAfooter.jsp" />
        </table>

    </body>
</html>
