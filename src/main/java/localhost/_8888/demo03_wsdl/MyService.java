
package localhost._8888.demo03_wsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MyService", targetNamespace = "http://localhost:8888/demo03?wsdl", wsdlLocation = "http://localhost:8888/demo03?wsdl")
public class MyService
    extends Service
{

    private final static URL MYSERVICE_WSDL_LOCATION;
    private final static WebServiceException MYSERVICE_EXCEPTION;
    private final static QName MYSERVICE_QNAME = new QName("http://localhost:8888/demo03?wsdl", "MyService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8888/demo03?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MYSERVICE_WSDL_LOCATION = url;
        MYSERVICE_EXCEPTION = e;
    }

    public MyService() {
        super(__getWsdlLocation(), MYSERVICE_QNAME);
    }

    public MyService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MYSERVICE_QNAME, features);
    }

    public MyService(URL wsdlLocation) {
        super(wsdlLocation, MYSERVICE_QNAME);
    }

    public MyService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MYSERVICE_QNAME, features);
    }

    public MyService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IMyService
     */
    @WebEndpoint(name = "MyServicePort")
    public IMyService getMyServicePort() {
        return super.getPort(new QName("http://localhost:8888/demo03?wsdl", "MyServicePort"), IMyService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IMyService
     */
    @WebEndpoint(name = "MyServicePort")
    public IMyService getMyServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://localhost:8888/demo03?wsdl", "MyServicePort"), IMyService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MYSERVICE_EXCEPTION!= null) {
            throw MYSERVICE_EXCEPTION;
        }
        return MYSERVICE_WSDL_LOCATION;
    }

}
