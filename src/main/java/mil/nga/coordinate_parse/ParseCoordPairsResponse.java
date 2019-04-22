
package mil.nga.coordinate_parse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import mil.nga.coordinate_pair.CoordinatePair;
import mil.nga.security.SecurityElement;


/**
 * <p>Java class for ParseCoordPairsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParseCoordPairsResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="security" type="{mil:nga:security}SecurityElement"/&gt;
 *         &lt;element name="coordinatePairs" type="{mil:nga:coordinate_pair}CoordinatePair" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParseCoordPairsResponse", propOrder = {
    "security",
    "coordinatePairs"
})
public class ParseCoordPairsResponse {

    @XmlElement(required = true)
    protected SecurityElement security;
    @XmlElement(nillable = true)
    protected List<CoordinatePair> coordinatePairs;

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
     * Gets the value of the coordinatePairs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coordinatePairs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoordinatePairs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoordinatePair }
     * 
     * 
     */
    public List<CoordinatePair> getCoordinatePairs() {
        if (coordinatePairs == null) {
            coordinatePairs = new ArrayList<CoordinatePair>();
        }
        return this.coordinatePairs;
    }

}
