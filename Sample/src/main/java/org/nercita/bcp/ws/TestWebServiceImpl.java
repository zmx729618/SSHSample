package org.nercita.bcp.ws;

public class TestWebServiceImpl implements TestWebService{

	@Override
	public String sayWS() {
		System.out.println("测试调用WS");
		return "hello WebService!";
	}



}
