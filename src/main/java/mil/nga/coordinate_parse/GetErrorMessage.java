
package mil.nga.coordinate_parse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import mil.nga.security.SecurityElement;


/**
 * <p>Java class for GetErrorMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetErrorMessage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="security" type="{mil:nga:security}SecurityElement" minOccurs="0"/&gt;
 *         &lt;element name="errorNum" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetErrorMessage", propOrder = {
    "security",
    "errorNum"
})
public class GetErrorMessage {

    protected SecurityElement security;
    protected Double errorNum;

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
     * Gets the value of the errorNum property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getErrorNum() {
        return errorNum;
    }

    /**
     * Sets the value of the errorNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setErrorNum(Double value) {
        this.errorNum = value;
    }

}
