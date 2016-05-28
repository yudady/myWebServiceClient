package com.foya.demo03.client;

import com.foya.demo03.client.wsimport.IMyService;
import com.foya.demo03.client.wsimport.MyService;

public class TestClient01 {

	public static void main(String[] args) {

		MyService msis = new MyService();
		IMyService ms = msis.getMyServicePort();
		System.out.println(ms.minus(29, 11));
		System.out.println("-------------------");
		System.out.println(ms.login("tommy", "123456"));

	}
}
