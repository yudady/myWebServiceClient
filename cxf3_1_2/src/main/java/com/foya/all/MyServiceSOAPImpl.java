
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.foya.all;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.2
 * 2016-05-29T11:40:28.647+08:00
 * Generated source version: 3.1.2
 * 
 */

@javax.jws.WebService(
                      serviceName = "MyService",
                      portName = "MyServiceSOAP",
                      targetNamespace = "http://www.madbit.org/MyService/",
                      wsdlLocation = "file:MyService.wsdl",
                      endpointInterface = "com.foya.all.MyService")
                      
public class MyServiceSOAPImpl implements MyService {

    private static final Logger LOG = Logger.getLogger(MyServiceSOAPImpl.class.getName());

    /* (non-Javadoc)
     * @see com.foya.all.MyService#sum(com.foya.all.SumRequest  parameters )*
     */
    public com.foya.all.SumResponse sum(SumRequest parameters) { 
        LOG.info("Executing operation sum");
        System.out.println(parameters);
        try {
            com.foya.all.SumResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}