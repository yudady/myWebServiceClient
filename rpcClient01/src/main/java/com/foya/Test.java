package com.foya;

public class Test {

	public static void main(String[] args) {

		StockQuote stockQuote = new StockQuote();
		StockQuoteSoap port = stockQuote.getPort(StockQuoteSoap.class);
		String quote = port.getQuote("FB");
		System.out.println(quote);






	}

}
