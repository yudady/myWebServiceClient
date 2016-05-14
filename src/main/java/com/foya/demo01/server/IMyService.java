package com.foya.demo01.server;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService()
public interface IMyService {

	@WebResult(name = "addResult")
	public int add(@WebParam(name = "a") int a, @WebParam(name = "b") int b);

	@WebResult(name = "minusResult")
	public int minus(@WebParam(name = "a") int a, @WebParam(name = "b") int b);

}
