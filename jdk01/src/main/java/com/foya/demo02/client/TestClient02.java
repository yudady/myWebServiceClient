package com.foya.demo02.client;

import com.foya.demo02.server.IMyService;
import com.foya.demo02.server.MyServiceImplService;

public class TestClient02 {
	public static void main(String[] args) {
		MyServiceImplService msis = new com.foya.demo02.server.MyServiceImplService();
		IMyService ms = msis.getMyServiceImplPort();
		System.out.println(ms.minus(29, 11));
	}
}
