package NET.webserviceX.www;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;


public class TestClient {

	static String specLocal = "http://localhost:8080/TopDownWS/services/StockQuoteSoap";
	static String spec12 = "http://www.webservicex.net/stockquote.asmx";
	static String specGet = "http://www.webservicex.net/stockquote.asmx";
	static String specPost = "http://www.webservicex.net/stockquote.asmx";


	//static String specStockt = "http://www.webservicex.net/stockquote.asmx";


	static String specQ = "http://www.webserviceX.NET/GetQuote";
	static String specStockt = "http://www.webservicex.net/stockquote.asmx";


	static String wsdlLoc = "http://www.webservicex.net/stockquote.asmx?wsdl";

	public static void main(String[] args) {
		try {
			//StockQuoteSoapStub stub = new StockQuoteSoapStub(new URL(specStockt), new StockQuoteLocator(wsdlLoc, new javax.xml.namespace.QName("http://www.webserviceX.NET/","StockQuote","tns")));

			StockQuoteLocator lo = new StockQuoteLocator();

			StockQuoteSoapStub stub = new StockQuoteSoapStub(new URL(specStockt), lo);
			String re = stub.getQuote("FB");

			System.out.println(re);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
