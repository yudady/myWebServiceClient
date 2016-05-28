package com.foya.demo04.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.junit.Test;
import org.w3c.dom.Document;

public class TestSoapSAAJ {

	private String demo04TargetNamespace = "http://server.demo04.foya.com/";
	private String demo04Name = "MyServiceImplService";

	private String wsdlUrl = "http://localhost:8888/demo04?wsdl";

	private String nsPrefix = "ns";

	@Test
	public void test01() {
		try {
			// 1、創建消息工廠
			MessageFactory factory = MessageFactory.newInstance();
			// 2、根據消息工廠創建SoapMessage
			SOAPMessage message = factory.createMessage();
			// 3、創建SOAPPart
			SOAPPart part = message.getSOAPPart();
			// 4、獲取SOAPENvelope
			SOAPEnvelope envelope = part.getEnvelope();
			// 5、可以通過SoapEnvelope有效的獲取相應的Body和Header等信息
			SOAPBody body = envelope.getBody();
			// 6、根據Qname創建相應的節點(QName就是一個帶有命名空間的)
			QName qname = new QName("http://www.foya.com/webservice", "add", nsPrefix);// <ns:add
																						// xmlns="http://java.zttc.edu.cn/webservice"/>
			// 如果使用以下方式進行設置，會見<>轉換為&lt;和&gt
			// body.addBodyElement(qname).setValue("<a>1</a><b>2</b>");
			SOAPBodyElement ele = body.addBodyElement(qname);

			ele.addChildElement("a").setValue("22");
			ele.addChildElement("b").setValue("33");

			message.writeTo(this.outXmlFile4Debug("test01Debug"));

			// 打印消息信息
			message.writeTo(System.out);

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test01Prefix() {
		try {
			// 1、創建消息工廠
			MessageFactory factory = MessageFactory.newInstance();
			// 2、根據消息工廠創建SoapMessage
			SOAPMessage message = factory.createMessage();
			// 3、創建SOAPPart
			SOAPPart part = message.getSOAPPart();
			// 4、獲取SOAPENvelope
			SOAPEnvelope envelope = part.getEnvelope();
			// 5、可以通過SoapEnvelope有效的獲取相應的Body和Header等信息
			SOAPBody body = envelope.getBody();
			// 6、根據Qname創建相應的節點(QName就是一個帶有命名空間的)
			QName qname = new QName("http://www.foya.com/webservice", "add", nsPrefix);// <ns:add
			// xmlns="http://java.zttc.edu.cn/webservice"/>
			// 如果使用以下方式進行設置，會見<>轉換為&lt;和&gt
			// body.addBodyElement(qname).setValue("<a>1</a><b>2</b>");
			SOAPBodyElement ele = body.addBodyElement(qname);

			// prefix
			ele.addChildElement("a", "nn").setValue("22");
			ele.addChildElement("b", "nn").setValue("33");

			message.writeTo(this.outXmlFile4Debug("test01DebugPrefix"));

			// 打印消息信息
			message.writeTo(System.out);

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDispatch() {
		try {

			// 1、創建服務(Service)
			URL url = new URL(wsdlUrl);
			// 通過Qname指明服務的具體信息
			QName sname = new QName(demo04TargetNamespace, demo04Name);

			Service service = Service.create(url, sname);

			// 2、創建Dispatch
			QName portName = new QName(demo04TargetNamespace, "MyServiceImplPort"); // 要執行的哪個方法

			/**
			 * Dispatch API 可以在 PAYLOAD 或 MESSAGE 方式下發送數據。
			 *
			 * 使用 PAYLOAD 方式時，Dispatch client 僅負責提供 <soap:Body> 的內容，而 JAX-WS 在
			 * <soap:Envelope> 元素中包含輸入有效內容。
			 *
			 * 使用 MESSAGE 方式時，Dispatch client 負責提供整個 SOAP 包絡。
			 */
			Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);

			// 3、創建SOAPMessage
			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage msg = factory.createMessage();

			SOAPPart soapPart = msg.getSOAPPart();

			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPBody body = envelope.getBody();

			// 4、創建QName來指定消息中傳遞數據
			QName ename = new QName(demo04TargetNamespace, "add", "nn");// <nn:add
																		// xmlns="xx"/>
			SOAPBodyElement ele = body.addBodyElement(ename);

			ele.addChildElement("a").setValue("22");
			ele.addChildElement("b").setValue("33");

			msg.writeTo(System.out);

			msg.writeTo(this.outXmlFile4Debug("test02Debug4out"));

			System.out.println("\n invoking.....");

			// 5、通過Dispatch傳遞消息,會返回響應消息
			SOAPMessage response = dispatch.invoke(msg);
			response.writeTo(System.out);
			System.out.println();

			// 將響應的消息轉換為dom對象
			Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			String str = doc.getElementsByTagName("addResult").item(0).getTextContent();
			System.out.println(str);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSOAPConnectionFactory() {
		try {

			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// 3、創建SOAPMessage
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPPart soapPart = soapMessage.getSOAPPart();
			SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
			//soapEnvelope.setPrefix("soap-ENV");
			soapEnvelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
			soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			soapEnvelope.addNamespaceDeclaration("enc", "http://schemas.xmlsoap.org/soap/encoding/");
			soapEnvelope.addNamespaceDeclaration("env", "http://schemas.xmlsoap.org/soap/envelop/");

			/**
			 * look debug file
			 */
			soapEnvelope.addNamespaceDeclaration(nsPrefix, demo04TargetNamespace);

			SOAPBody body = soapEnvelope.getBody();
			//body.setPrefix("B");
			// 6、根據Qname創建相應的節點(QName就是一個帶有命名空間的)
			QName qname = new QName(demo04TargetNamespace, "add", nsPrefix);// <ns:add
			SOAPBodyElement ele = body.addBodyElement(qname);
			ele.addChildElement("a").setValue("22");
			ele.addChildElement("b").setValue("33");

			// debug file
			soapMessage.writeTo(outXmlFile4Debug("test09"));

			soapMessage.saveChanges();
			System.out.println("----------SOAP Request------------");
			soapMessage.writeTo(System.out);

			/**
			 * hit soapRequest to the server to get response
			 */
			SOAPMessage soapResponse = soapConnection.call(soapMessage, wsdlUrl);

			/**
			 * send
			 */
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			Source sourceContent = soapResponse.getSOAPPart().getContent();
			System.out.println("\n----------SOAP Response-----------");
			StreamResult result = new StreamResult(System.out);
			transformer.transform(sourceContent, result);
			soapConnection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private OutputStream outXmlFile4Debug(String fileName) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(System.getProperty("user.dir")
					+ "/src/main/java/com/foya/demo04/client/tmp/" + fileName + ".xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return out;
	}
}
