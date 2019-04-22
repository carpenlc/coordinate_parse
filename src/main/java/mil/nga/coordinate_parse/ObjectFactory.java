
package mil.nga.coordinate_parse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mil.nga.coordinate_parse package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NgaResource_QNAME = new QName("mil:nga:coordinate_parse", "nga_resource");
    private final static QName _DescriptionDocument_QNAME = new QName("mil:nga:coordinate_parse", "description_document");
    private final static QName _GetErrorMessage_QNAME = new QName("mil:nga:coordinate_parse", "GetErrorMessage");
    private final static QName _GetErrorMessageResponse_QNAME = new QName("mil:nga:coordinate_parse", "GetErrorMessageResponse");
    private final static QName _ParseCoordPair_QNAME = new QName("mil:nga:coordinate_parse", "ParseCoordPair");
    private final static QName _ParseCoordPairResponse_QNAME = new QName("mil:nga:coordinate_parse", "ParseCoordPairResponse");
    private final static QName _ParseCoordPairs_QNAME = new QName("mil:nga:coordinate_parse", "ParseCoordPairs");
    private final static QName _ParseCoordPairsResponse_QNAME = new QName("mil:nga:coordinate_parse", "ParseCoordPairsResponse");
    private final static QName _ParseLatitude_QNAME = new QName("mil:nga:coordinate_parse", "ParseLatitude");
    private final static QName _ParseLatitudeResponse_QNAME = new QName("mil:nga:coordinate_parse", "ParseLatitudeResponse");
    private final static QName _ParseLatitudes_QNAME = new QName("mil:nga:coordinate_parse", "ParseLatitudes");
    private final static QName _ParseLatitudesResponse_QNAME = new QName("mil:nga:coordinate_parse", "ParseLatitudesResponse");
    private final static QName _ParseLongitude_QNAME = new QName("mil:nga:coordinate_parse", "ParseLongitude");
    private final static QName _ParseLongitudeResponse_QNAME = new QName("mil:nga:coordinate_parse", "ParseLongitudeResponse");
    private final static QName _ParseLongitudes_QNAME = new QName("mil:nga:coordinate_parse", "ParseLongitudes");
    private final static QName _ParseLongitudesResponse_QNAME = new QName("mil:nga:coordinate_parse", "ParseLongitudesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mil.nga.coordinate_parse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetErrorMessage }
     * 
     */
    public GetErrorMessage createGetErrorMessage() {
        return new GetErrorMessage();
    }

    /**
     * Create an instance of {@link GetErrorMessageResponse }
     * 
     */
    public GetErrorMessageResponse createGetErrorMessageResponse() {
        return new GetErrorMessageResponse();
    }

    /**
     * Create an instance of {@link ParseCoordPair }
     * 
     */
    public ParseCoordPair createParseCoordPair() {
        return new ParseCoordPair();
    }

    /**
     * Create an instance of {@link ParseCoordPairResponse }
     * 
     */
    public ParseCoordPairResponse createParseCoordPairResponse() {
        return new ParseCoordPairResponse();
    }

    /**
     * Create an instance of {@link ParseCoordPairs }
     * 
     */
    public ParseCoordPairs createParseCoordPairs() {
        return new ParseCoordPairs();
    }

    /**
     * Create an instance of {@link ParseCoordPairsResponse }
     * 
     */
    public ParseCoordPairsResponse createParseCoordPairsResponse() {
        return new ParseCoordPairsResponse();
    }

    /**
     * Create an instance of {@link ParseLatitude }
     * 
     */
    public ParseLatitude createParseLatitude() {
        return new ParseLatitude();
    }

    /**
     * Create an instance of {@link ParseLatitudeResponse }
     * 
     */
    public ParseLatitudeResponse createParseLatitudeResponse() {
        return new ParseLatitudeResponse();
    }

    /**
     * Create an instance of {@link ParseLatitudes }
     * 
     */
    public ParseLatitudes createParseLatitudes() {
        return new ParseLatitudes();
    }

    /**
     * Create an instance of {@link ParseLatitudesResponse }
     * 
     */
    public ParseLatitudesResponse createParseLatitudesResponse() {
        return new ParseLatitudesResponse();
    }

    /**
     * Create an instance of {@link ParseLongitude }
     * 
     */
    public ParseLongitude createParseLongitude() {
        return new ParseLongitude();
    }

    /**
     * Create an instance of {@link ParseLongitudeResponse }
     * 
     */
    public ParseLongitudeResponse createParseLongitudeResponse() {
        return new ParseLongitudeResponse();
    }

    /**
     * Create an instance of {@link ParseLongitudes }
     * 
     */
    public ParseLongitudes createParseLongitudes() {
        return new ParseLongitudes();
    }

    /**
     * Create an instance of {@link ParseLongitudesResponse }
     * 
     */
    public ParseLongitudesResponse createParseLongitudesResponse() {
        return new ParseLongitudesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "nga_resource")
    public JAXBElement<String> createNgaResource(String value) {
        return new JAXBElement<String>(_NgaResource_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "description_document")
    public JAXBElement<String> createDescriptionDocument(String value) {
        return new JAXBElement<String>(_DescriptionDocument_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetErrorMessage }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetErrorMessage }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "GetErrorMessage")
    public JAXBElement<GetErrorMessage> createGetErrorMessage(GetErrorMessage value) {
        return new JAXBElement<GetErrorMessage>(_GetErrorMessage_QNAME, GetErrorMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetErrorMessageResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetErrorMessageResponse }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "GetErrorMessageResponse")
    public JAXBElement<GetErrorMessageResponse> createGetErrorMessageResponse(GetErrorMessageResponse value) {
        return new JAXBElement<GetErrorMessageResponse>(_GetErrorMessageResponse_QNAME, GetErrorMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseCoordPair }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseCoordPair }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseCoordPair")
    public JAXBElement<ParseCoordPair> createParseCoordPair(ParseCoordPair value) {
        return new JAXBElement<ParseCoordPair>(_ParseCoordPair_QNAME, ParseCoordPair.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseCoordPairResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseCoordPairResponse }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseCoordPairResponse")
    public JAXBElement<ParseCoordPairResponse> createParseCoordPairResponse(ParseCoordPairResponse value) {
        return new JAXBElement<ParseCoordPairResponse>(_ParseCoordPairResponse_QNAME, ParseCoordPairResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseCoordPairs }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseCoordPairs }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseCoordPairs")
    public JAXBElement<ParseCoordPairs> createParseCoordPairs(ParseCoordPairs value) {
        return new JAXBElement<ParseCoordPairs>(_ParseCoordPairs_QNAME, ParseCoordPairs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseCoordPairsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseCoordPairsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseCoordPairsResponse")
    public JAXBElement<ParseCoordPairsResponse> createParseCoordPairsResponse(ParseCoordPairsResponse value) {
        return new JAXBElement<ParseCoordPairsResponse>(_ParseCoordPairsResponse_QNAME, ParseCoordPairsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseLatitude }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseLatitude }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseLatitude")
    public JAXBElement<ParseLatitude> createParseLatitude(ParseLatitude value) {
        return new JAXBElement<ParseLatitude>(_ParseLatitude_QNAME, ParseLatitude.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseLatitudeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseLatitudeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseLatitudeResponse")
    public JAXBElement<ParseLatitudeResponse> createParseLatitudeResponse(ParseLatitudeResponse value) {
        return new JAXBElement<ParseLatitudeResponse>(_ParseLatitudeResponse_QNAME, ParseLatitudeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseLatitudes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseLatitudes }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseLatitudes")
    public JAXBElement<ParseLatitudes> createParseLatitudes(ParseLatitudes value) {
        return new JAXBElement<ParseLatitudes>(_ParseLatitudes_QNAME, ParseLatitudes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseLatitudesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseLatitudesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseLatitudesResponse")
    public JAXBElement<ParseLatitudesResponse> createParseLatitudesResponse(ParseLatitudesResponse value) {
        return new JAXBElement<ParseLatitudesResponse>(_ParseLatitudesResponse_QNAME, ParseLatitudesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseLongitude }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseLongitude }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseLongitude")
    public JAXBElement<ParseLongitude> createParseLongitude(ParseLongitude value) {
        return new JAXBElement<ParseLongitude>(_ParseLongitude_QNAME, ParseLongitude.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseLongitudeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseLongitudeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseLongitudeResponse")
    public JAXBElement<ParseLongitudeResponse> createParseLongitudeResponse(ParseLongitudeResponse value) {
        return new JAXBElement<ParseLongitudeResponse>(_ParseLongitudeResponse_QNAME, ParseLongitudeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseLongitudes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseLongitudes }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseLongitudes")
    public JAXBElement<ParseLongitudes> createParseLongitudes(ParseLongitudes value) {
        return new JAXBElement<ParseLongitudes>(_ParseLongitudes_QNAME, ParseLongitudes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseLongitudesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseLongitudesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "mil:nga:coordinate_parse", name = "ParseLongitudesResponse")
    public JAXBElement<ParseLongitudesResponse> createParseLongitudesResponse(ParseLongitudesResponse value) {
        return new JAXBElement<ParseLongitudesResponse>(_ParseLongitudesResponse_QNAME, ParseLongitudesResponse.class, null, value);
    }

}
