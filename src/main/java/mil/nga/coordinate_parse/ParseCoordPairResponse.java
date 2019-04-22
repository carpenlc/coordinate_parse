
package mil.nga.coordinate_parse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import mil.nga.coordinate_pair.CoordinatePair;
import mil.nga.security.SecurityElement;


/**
 * <p>Java class for ParseCoordPairResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParseCoordPairResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="security" type="{mil:nga:security}SecurityElement"/&gt;
 *         &lt;element name="coordinatePair" type="{mil:nga:coordinate_pair}CoordinatePair"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParseCoordPairResponse", propOrder = {
    "security",
    "coordinatePair"
})
public class ParseCoordPairResponse {

    @XmlElement(required = true)
    protected SecurityElement security;
    @XmlElement(required = true, nillable = true)
    protected CoordinatePair coordinatePair;

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
     * Gets the value of the coordinatePair property.
     * 
     * @return
     *     possible object is
     *     {@link CoordinatePair }
     *     
     */
    public CoordinatePair getCoordinatePair() {
        return coordinatePair;
    }

    /**
     * Sets the value of the coordinatePair property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinatePair }
     *     
     */
    public void setCoordinatePair(CoordinatePair value) {
        this.coordinatePair = value;
    }

}
