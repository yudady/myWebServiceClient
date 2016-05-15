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
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.junit.Test;
import org.w3c.dom.Document;

public class TestSoap {

	private String demo04TargetNamespace = "http://server.demo04.foya.com/";
	private String demo04Name = "MyServiceImplService";

	private String wsdlUrl = "http://localhost:8888/demo04?wsdl";

	@Test
	public void test01() {
		try {
			//1、創建消息工廠
			MessageFactory factory = MessageFactory.newInstance();
			//2、根據消息工廠創建SoapMessage
			SOAPMessage message = factory.createMessage();
			//3、創建SOAPPart
			SOAPPart part = message.getSOAPPart();
			//4、獲取SOAPENvelope
			SOAPEnvelope envelope = part.getEnvelope();
			//5、可以通過SoapEnvelope有效的獲取相應的Body和Header等信息
			SOAPBody body = envelope.getBody();
			//6、根據Qname創建相應的節點(QName就是一個帶有命名空間的)
			QName qname = new QName("http://www.foya.com/webservice", "add", "ns");//<ns:add xmlns="http://java.zttc.edu.cn/webservice"/>
			//如果使用以下方式進行設置，會見<>轉換為&lt;和&gt
			//body.addBodyElement(qname).setValue("<a>1</a><b>2</b>");
			SOAPBodyElement ele = body.addBodyElement(qname);
			ele.addChildElement("a").setValue("22");
			ele.addChildElement("b").setValue("33");

			message.writeTo(this.outXmlFile4Debug("test01Debug"));

			//打印消息信息
			message.writeTo(System.out);

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test02() {
		try {

			//1、創建服務(Service)
			URL url = new URL(wsdlUrl);
			//通過Qname指明服務的具體信息
			QName sname = new QName(demo04TargetNamespace, demo04Name);

			Service service = Service.create(url, sname);

			//2、創建Dispatch
			Dispatch<SOAPMessage> dispatch = service.createDispatch(
					new QName(demo04TargetNamespace, "MyServiceImplPort"), SOAPMessage.class, Service.Mode.MESSAGE);

			//3、創建SOAPMessage
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();

			//4、創建QName來指定消息中傳遞數據
			QName ename = new QName(demo04TargetNamespace, "add", "nn");//<nn:add xmlns="xx"/>
			SOAPBodyElement ele = body.addBodyElement(ename);
			ele.addChildElement("a").setValue("22");
			ele.addChildElement("b").setValue("33");
			msg.writeTo(System.out);

			msg.writeTo(this.outXmlFile4Debug("test02Debug4out"));

			System.out.println("\n invoking.....");

			//5、通過Dispatch傳遞消息,會返回響應消息
			SOAPMessage response = dispatch.invoke(msg);
			response.writeTo(System.out);
			System.out.println();

			//將響應的消息轉換為dom對象
			Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			String str = doc.getElementsByTagName("addResult").item(0).getTextContent();
			System.out.println(str);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private OutputStream outXmlFile4Debug(String fileName) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(
					System.getProperty("user.dir") + "/src/main/java/com/foya/demo04/client/" + fileName + ".xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return out;
	}
}
