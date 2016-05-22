package com.foya.demo03.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.foya.demo03.client.wsimport.IMyService;

public class TestClient02 {

	/**
	 * copy from MyService.java
	 */
	private final static QName MYSERVICE_QNAME = new QName("http://localhost:8888/demo03?wsdl", "MyService");

	public static void main(String[] args) throws MalformedURLException {

		/**
		 * tcpmon (listen:9999 ,host:localhost port:8888 )
		 *
		 *
		 * wsdlDocumentLocation 只要把這個port改為代理的port(8888->9999)
		 */
		URL wsdlDocumentLocation = new URL("http://localhost:9999/demo03?wsdl");
		Service service = Service.create(wsdlDocumentLocation, MYSERVICE_QNAME);
		IMyService ms = service.getPort(IMyService.class);
		System.out.println(ms.minus(29, 11));
		System.out.println("-------------------");
		System.out.println(ms.login("tommy", "123456"));

	}
}
