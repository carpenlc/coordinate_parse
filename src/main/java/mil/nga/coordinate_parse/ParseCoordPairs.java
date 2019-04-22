
package mil.nga.coordinate_parse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import mil.nga.coordinate_pair.CoordinatePairString;
import mil.nga.security.SecurityElement;


/**
 * <p>Java class for ParseCoordPairs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParseCoordPairs"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="security" type="{mil:nga:security}SecurityElement" minOccurs="0"/&gt;
 *         &lt;element name="coordinatePairStrings" type="{mil:nga:coordinate_pair}CoordinatePairString" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParseCoordPairs", propOrder = {
    "security",
    "coordinatePairStrings"
})
public class ParseCoordPairs {

    protected SecurityElement security;
    protected List<CoordinatePairString> coordinatePairStrings;

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
     * Gets the value of the coordinatePairStrings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coordinatePairStrings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoordinatePairStrings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoordinatePairString }
     * 
     * 
     */
    public List<CoordinatePairString> getCoordinatePairStrings() {
        if (coordinatePairStrings == null) {
            coordinatePairStrings = new ArrayList<CoordinatePairString>();
        }
        return this.coordinatePairStrings;
    }

}
