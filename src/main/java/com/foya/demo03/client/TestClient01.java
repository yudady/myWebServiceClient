package com.foya.demo03.client;


public class TestClient01 {
	public static void main(String[] args) {
		localhost._8888.demo03_wsdl.MyService msis = new localhost._8888.demo03_wsdl.MyService();
		localhost._8888.demo03_wsdl.IMyService ms = msis.getMyServicePort();
		System.out.println(ms.minus(29, 11));
		System.out.println("-------------------");
		System.out.println(ms.login("tommy", "123456"));

	}
}
