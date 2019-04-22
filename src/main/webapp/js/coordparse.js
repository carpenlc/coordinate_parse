

/**
 * Returns boolean true if sText is a number; otherwise returns false
 */
function isNumeric(sText)

{
    var ValidChars = "0123456789.";
    var IsNumber=true;
    var Char;


    for (i = 0; i < sText.length && IsNumber == true; i++)
    {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1)
        {
            IsNumber = false;
        }
    }
    return IsNumber;

}


/*
 * Given an input DMS string (strInput) returns decimal degrees.
 * For Latitude values blnLat should be set to true for longitudes set blnLat to false
 */

function parseDMSString(strInput, blnLat) {
    
        
    var strDirChars = "SsNnEeWw";
    var strFieldSep = "-/\\:;)(_ ";
    var strValidChars = strFieldSep + ".0123456789";
        
    var dblDD = 0.0;
        
    try {
        var strUserInput = strInput;
            
        var strDir = "";
            
        var intNumDir = 0;
            
        var strChar = "";
        var strUserInputMod = "";
            
        // Negative or Positive direction indicator can only be first character
        strChar = strUserInput.substring(0,1);
        if (strChar == "+" || strChar == "-") {
            strDir = strChar;
            intNumDir = intNumDir + 1;
            strUserInput = strUserInput.substring(1);
        }
            
            
        // Loop through the input string to see if there are any direction characters
        var i = 0;

        while (i < strUserInput.length) {
            strChar = strUserInput.charAt(i);
            /*
                if (strChar.equals("+") || strChar.equals("-")) {
                    return -1005;
                }
                */
            if (strDirChars.indexOf(strChar) != -1) {
                // Found a direction char
                strDir = strChar;
                intNumDir = intNumDir + 1;
                strUserInputMod = strUserInputMod + "";
            } else {
                strUserInputMod = strUserInputMod + strChar;
            }
            i = i + 1;
        }
            
        strUserInput = strUserInputMod.trim();
            
        // **** Can have only one direction indicator ****
        if (intNumDir > 1) {
            return -1004;
        }
            
        // **** Lat can't be WwEe and Lon can't be SsNn *****
        if (intNumDir > 0) {
            if (blnLat) {
                if ("WwEe".indexOf(strDir) != -1) {
                    return -1018;
                }
            } else {
                if ("NnSs".indexOf(strDir) != -1) {
                    return -1019;
                }
            }
        }
            
        // **** Eliminate the field seps and check for invalid chars ****
        var intNumPeriods = 0;
            
        // Loop through each char in strInput
            
        strUserInputMod = "";
            

        i = 0;
        while (i < strUserInput.length) {
                
            strChar = strUserInput.charAt(i);
                
            // check to make sure the character is valid
            if (strValidChars.indexOf(strChar) == -1) {
                // This is not a valid character
                if (blnLat) {
                    return -1001;
                } else {
                    return -1002;
                }
            }
                
            // Change the field seperators to spaces
            if (strFieldSep.indexOf(strChar) != -1) {
                // This is a field seperator
                if (strUserInputMod.charAt(strUserInputMod.length - 1) != ' ') {
                    strUserInputMod = strUserInputMod + " ";
                }
            } else {
                strUserInputMod = strUserInputMod + strChar;
            }
                
                
            if (strChar == ".") {
                intNumPeriods = intNumPeriods + 1;
            }
                
            i = i + 1;
                
        }
           
        // **** Should be only one decimal point
            
        if (intNumPeriods > 1) {
            return -1003;
        }
            
        strUserInput = strUserInputMod;
            
        // Read Degrees, Minutes, Seconds from userInput
            
        var strDeg;
        var strMin;
        var strSec;
            
        // Split the input based on spaces
        var strParts = [];
            
        if (isNumeric(strUserInput)) {
                
            // DMS without a delimiter
            var strTemp;
                
            if (strUserInput.indexOf(".") != -1) {
                // String contains a decimal point
                strParts = strUserInput.split(".");
                strTemp = strParts[0];
            } else {
                strTemp = strUserInput;
            }
                   
            switch (strTemp.length) {
                case 0:
                case 1:
                case 2:
                    strDeg = strUserInput;
                    strMin = "0";
                    strSec = "0";
                    break;
                case 3:
                    if (blnLat) {
                        // DMM latitude
                        strDeg = strUserInput.substring(0, 1);
                        strMin = strUserInput.substring(1);
                        strSec = "0";
                    } else {
                        // DDD Longitude
                        strDeg = strUserInput;
                        strMin = "0";
                        strSec = "0";
                    }
                    break;
                case 4:
                    // DDMM
                    strDeg = strUserInput.substring(0, 2);
                    strMin = strUserInput.substring(2);
                    strSec = "0";
                    break;
                case 5:
                        
                    if (blnLat) {
                        // DMMSS Latitude
                        strDeg = strUserInput.substring(0, 1);
                        strMin = strUserInput.substring(1, 3);
                        strSec = strUserInput.substring(3);
                    } else {
                        // DDDMM Longitude
                        strDeg = strUserInput.substring(0, 3);
                        strMin = strUserInput.substring(3);
                        strSec = "0";
                    }
                    break;
                case 6:
                    // DDMMSS
                    strDeg = strUserInput.substring(0,2);
                    strMin = strUserInput.substring(2,4);
                    strSec = strUserInput.substring(4);
                    break;
                case 7:
                    if (blnLat) {
                        return -1008;
                    } else {
                        // DDDMMSS
                        strDeg = strUserInput.substring(0,3);
                        strMin = strUserInput.substring(3,5);
                        strSec = strUserInput.substring(5);
                    }
                    break;
                       
                default:
                    if (blnLat) {
                        return -1008;
                    } else {
                        return -1009;
                    }
                           
            }
                
                
                
                
        } else {
            // DMS with delimiter
            strParts = strUserInput.split(" ");
               
            switch (strParts.length) {
                case 2:
                    // D M
                    strDeg = strParts[0];
                    strMin = strParts[1];
                    strSec = "0";
                       
                    break;
                       
                case 3:
                    // D M S
                    strDeg = strParts[0];
                    strMin = strParts[1];
                    strSec = strParts[2];

                    // Minutes cannot be decimal in D M S format
                    if (strMin.indexOf(".") != -1) {
                        return -1011;
                    }


                       
                    break;
                default:
                    // Error
                    return -1007;
                                
            }

            // Degrees should never be decimal in a delimited input
            if (strDeg.indexOf(".") != -1) {
                return -1010;
            }
               
               
        }
            
            
            
        var dblDeg = parseFloat(strDeg);
        var dblMin = parseFloat(strMin);
        var dblSec = parseFloat(strSec);
            
        if (dblMin >= 60) {
            return -1015;
        }
            
        if (dblSec >= 60) {
            return -1014;
        }
            
        dblDD = dblDeg + dblMin / 60.0 + dblSec / 3600.0;
            
        if (strDir != "" && "WwSs-".indexOf(strDir) != -1) {
            dblDD = -dblDD;
        }
            
            
    } catch (err) {
        txt="There was an error on this page.\n\n";
        txt+="Error message: " + err.message + "\n\n";
        txt+="Click OK to continue.\n\n";
        alert(txt);
    }
        
    return dblDD;
    
}

/*
 * This is the main function to call.
 *
 * Converts coordinate string to Decimal Degrees.   This function examines
 * the inputs and if needed calls function to convert DMS to DD.
 *
 * Given a DMS String (strUserInput) convert to decimal degreees.
 * For Latitude values blnLat should be set to true for longitudes set blnLat to false.
 * 
 */

function parseCoordString(strUserInput, blnLat) {



    var dblDD = 0.0;
    var dblMax;
    dblMax = (blnLat) ? 90.0 : 360.0;

    try {

        dblDD = 0.0;

        // Trim off any extra spaces at beginning or end of user input
        strUserInput = strUserInput.trim();

        // Test to see if input is DMS or Decimal Degrees
        var blnDMS = false;

        // If the string starts with two or three zeros assume DMS
        if (blnLat && strUserInput.length >= 2 && strUserInput.substring(0, 2) == "00") {
            blnDMS = true;
        } else if (blnLat && strUserInput.length >= 3 && strUserInput.substring(0, 3) == "000") {
            blnDMS = true;
        } else if (!isNumeric(strUserInput)) {
            blnDMS = true;
        } else {
            var dblUserInput = parseFloat(strUserInput);
            // if the number is > dlbMax assume its DMS number

            if (Math.abs(dblUserInput) > dblMax) {
                blnDMS = true;
            }
        }

        // Parse it

        if (blnDMS) {
            dblDD = parseDMSString(strUserInput, blnLat);
            // Limit the precision to 6 decimal places
            var dblDDMag = Math.abs(dblDD);

            if (dblDDMag > 100) {
                dblDD = dblDD.toPrecision(8);
            } else if (dblDDMag > 10) {
                dblDD = dblDD.toPrecision(7);
            } else {
                dblDD = dblDD.toPrecision(6);
            }

            
        } else {
            dblDD = parseFloat(strUserInput);
            if (blnLat) {
                if (dblDD > dblMax) {
                    dblDD = -1020;
                } else if (dblDD < -90) {
                    dblDD = -1021;
                }
            } else {
                if (dblDD > dblMax) {
                    dblDD = -1022;
                } else if (dblDD < -180) {
                    dblDD = -1023;
                }

            }


        }





    } catch (err) {
        txt="There was an error on this page.\n\n";
        txt+="Error message: " + err.message + "\n\n";
        txt+="Click OK to continue.\n\n";
        alert(txt);
    }

    if (blnLat) {
        if (dblDD > dblMax) {
            // Latitude must be less than 90
            dblDD = -1024;
        }
        if (dblDD < -90 && dblDD > -1000) {
            // Latitude has to be greater than -90; unless error detected
            dblDD = -1025;
        }
    } else {
        if (dblDD > dblMax) {
            // Latitude must be less than 90
            dblDD = -1026;
        }
        if (dblDD < -180 && dblDD > -1000) {
            // Latitude has to be greater than -90; unless error detected
            dblDD = -1027;
        }

    }


    return dblDD;

}

function getParseErrorMsg(dblMsgNum) {

    /*
         * This procedure returns a error message associated with
         * an error code.
         */

    var strErr;

    var intMsgNum = dblMsgNum;

    switch (intMsgNum) {
        case -1001: strErr = "Latitude has an invalid character"; break;
        case -1002: strErr = "Longitude has an invalid character"; break;
        case -1003: strErr = "More than one decimal point"; break;
        case -1004: strErr = "More than one direction indicator"; break;
        case -1005: strErr = "Plus or minus has to be on the far left of string"; break;
        case -1006: strErr = "Direction indicator has to be on left or right"; break;
        case -1007: strErr = "Delimited input request requires three fields; Degrees, Minutes, and Seconds"; break;
        case -1008: strErr = "Invalid Input: Undelimited latitude format must be DDMMSS.SSSS"; break;
        case -1009: strErr = "Invalid Input: Undelimited longitude format must be DDDMMSS.SSSS"; break;
        case -1010: strErr = "In DM format, degrees cannot have a decimal point"; break;
        case -1011: strErr = "In DMS format, minutes cannot have a decimal point"; break;
        case -1012: strErr = "Invalid Longitude (Magnitude > 360)"; break;
        case -1013: strErr = "Invalid Latitude (Magnitude > 90)"; break;
        case -1014: strErr = "Invalid second entry (Must be less than 60)"; break;
        case -1015: strErr = "Invalid minute entry (Must be less that 60)"; break;
        case -1016: strErr = "Program Error ParseDMSString"; break;
        case -1017: strErr = "Program Error ParseInputString"; break;
        case -1018: strErr = "Invalid direction indicator for latitude"; break;
        case -1019: strErr = "Invalid direction indicator for longitude"; break;
        case -1020: strErr = "Invalid Latitude greater than 90"; break;
        case -1021: strErr = "Invalid Latitude less than -90"; break;
        case -1022: strErr = "Invalid Longitude greater than 360"; break;
        case -1023: strErr = "Invalid Longitude less than -180"; break;
        case -1024: strErr = "Invalid Latitude: must be less than or equal to 90"; break;
        case -1025: strErr = "Invalid Latitude: must be greater than or equal to -90"; break;
        case -1026: strErr = "Invalid Longitude: must be less than or equal to 360"; break;
        case -1027: strErr = "Invalid Longitude: must be greater than or equal to -180"; break;
        default: strErr = "Unknown Error"; break;
    }

        
    return strErr;

}



