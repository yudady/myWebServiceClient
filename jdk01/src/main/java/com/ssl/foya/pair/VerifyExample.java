package com.ssl.foya.pair;

import java.io.File;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class VerifyExample {
	public static void main(String unused[]) {
		org.apache.xml.security.Init.init();
		String signatureFileName = "SignedRequest.xml";
		javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);
		try {
			org.apache.xml.security.Init.init();
			File f = new File(signatureFileName);
			System.out.println("Verifying " + signatureFileName);
			javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());
			org.w3c.dom.Document doc = db.parse(new java.io.FileInputStream(f));
			Element sigElement = null;
			NodeList nodes = doc.getElementsByTagNameNS(org.apache.xml.security.utils.Constants.SignatureSpecNS,
					"Signature");
			if (nodes.getLength() != 0) {
				System.out.println("Found " + nodes.getLength() + " Signature elements.");
				sigElement = (Element) nodes.item(0);
				XMLSignature signature = new XMLSignature(sigElement, "");
				KeyInfo ki = signature.getKeyInfo();
				if (ki != null) {
					if (ki.containsX509Data()) {
						System.out.println("Could find a X509Data element in the KeyInfo");
					}
					X509Certificate cert = signature.getKeyInfo().getX509Certificate();
					if (cert != null) {
						System.out.println("The XML signature is "
								+ (signature.checkSignatureValue(cert) ? "valid (good)" : "invalid !!!!! (bad)"));
					} else {
						System.out.println("Did not find a Certificate");
						PublicKey pk = signature.getKeyInfo().getPublicKey();
						if (pk != null) {
							System.out.println("The XML signatur is "
									+ (signature.checkSignatureValue(pk) ? "valid (good)" : "invalid !!!!! (bad)"));
						} else {
							System.out.println("Did not find a public key, so I can't check the signature");
						}
					}
				} else {
					System.out.println("Did not find a KeyInfo");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}