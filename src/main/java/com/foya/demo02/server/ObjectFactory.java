
package com.foya.demo02.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.foya.demo02.server package. 
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

    private final static QName _MinusResponse_QNAME = new QName("http://server.demo02.foya.com/", "minusResponse");
    private final static QName _AddResponse_QNAME = new QName("http://server.demo02.foya.com/", "addResponse");
    private final static QName _Minus_QNAME = new QName("http://server.demo02.foya.com/", "minus");
    private final static QName _Add_QNAME = new QName("http://server.demo02.foya.com/", "add");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.foya.demo02.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MinusResponse }
     * 
     */
    public MinusResponse createMinusResponse() {
        return new MinusResponse();
    }

    /**
     * Create an instance of {@link Minus }
     * 
     */
    public Minus createMinus() {
        return new Minus();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MinusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.demo02.foya.com/", name = "minusResponse")
    public JAXBElement<MinusResponse> createMinusResponse(MinusResponse value) {
        return new JAXBElement<MinusResponse>(_MinusResponse_QNAME, MinusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.demo02.foya.com/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Minus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.demo02.foya.com/", name = "minus")
    public JAXBElement<Minus> createMinus(Minus value) {
        return new JAXBElement<Minus>(_Minus_QNAME, Minus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.demo02.foya.com/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

}
