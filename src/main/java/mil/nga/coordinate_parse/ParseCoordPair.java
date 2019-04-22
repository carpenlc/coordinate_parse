
package mil.nga.coordinate_parse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import mil.nga.coordinate_pair.CoordinatePairString;
import mil.nga.security.SecurityElement;


/**
 * <p>Java class for ParseCoordPair complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParseCoordPair"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="security" type="{mil:nga:security}SecurityElement" minOccurs="0"/&gt;
 *         &lt;element name="coordinatePairString" type="{mil:nga:coordinate_pair}CoordinatePairString" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParseCoordPair", propOrder = {
    "security",
    "coordinatePairString"
})
public class ParseCoordPair {

    protected SecurityElement security;
    protected CoordinatePairString coordinatePairString;

    /**
     * Gets the value of the security property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityElement }
     *     
     */
    public SecurityElement getSecurity() {
        return security;
    }

    /**
     * Sets the value of the security property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityElement }
     *     
     */
    public void setSecurity(SecurityElement value) {
        this.security = value;
    }

    /**
     * Gets the value of the coordinatePairString property.
     * 
     * @return
     *     possible object is
     *     {@link CoordinatePairString }
     *     
     */
    public CoordinatePairString getCoordinatePairString() {
        return coordinatePairString;
    }

    /**
     * Sets the value of the coordinatePairString property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinatePairString }
     *     
     */
    public void setCoordinatePairString(CoordinatePairString value) {
        this.coordinatePairString = value;
    }

}
