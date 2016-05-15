package com.foya.demo02.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import com.foya.demo02.server.IMyService;
import com.foya.demo02.server.MyServiceImplService;

public class TestClient03 {
	public static void main(String[] args) {
		try {
			// 創建訪問wsdl服務地址的url
			URL url = new URL("http://localhost:8888/demo02?wsdl");
			// 通過Qname指明服務的具體信息
			QName sname = new QName("http://server.demo02.foya.com/", "MyServiceImplService");

			MyServiceImplService msis = new com.foya.demo02.server.MyServiceImplService(url, sname);
			IMyService ms = msis.getMyServiceImplPort();
			System.out.println(ms.minus(29, 11));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
