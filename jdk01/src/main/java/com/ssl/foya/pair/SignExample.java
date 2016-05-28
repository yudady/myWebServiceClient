package com.ssl.foya.pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SignExample {
	public static void main(String unused[]) throws Exception {
		// Initialize the library
		org.apache.xml.security.Init.init();
		// The file from which we will load the request SOAP message
		String fileName = "Request.xml";
		// Store the signed request here
		String signatureFileName = "SignedRequest.xml";
		// Keystore parameters
		String keystoreType = "JKS";
		String keystoreFile = "C:/xml-security-1_0_4/data/org/apache/xml/security/samples/input/keystore.jks";
		String keystorePass = "xmlsecurity";
		String privateKeyAlias = "test";
		String privateKeyPass = "xmlsecurity";
		String certificateAlias = "test";
		// Load the keystore
		KeyStore ks = KeyStore.getInstance(keystoreType);
		FileInputStream fis = new FileInputStream(keystoreFile);
		ks.load(fis, keystorePass.toCharArray());
		// And get the private key that will be used to sign the request
		PrivateKey privateKey = (PrivateKey) ks.getKey(privateKeyAlias, privateKeyPass.toCharArray());
		// Load the SOAP request
		javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);
		javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
		db.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());
		org.w3c.dom.Document doc = db.parse(new java.io.FileInputStream(new File(fileName)));
		// Look for the SOAP header
		Element headerElement = null;
		NodeList nodes = doc.getElementsByTagNameNS("http://schemas.xmlsoap.org/soap/envelope/", "Header");
		if (nodes.getLength() == 0) {
			System.out.println("Adding a SOAP Header Element");
			headerElement = doc.createElementNS("http://schemas.xmlsoap.org/soap/envelope/", "Header");
			nodes = doc.getElementsByTagNameNS("http://schemas.xmlsoap.org/soap/envelope/", "Envelope");
			if (nodes != null) {
				Element envelopeElement = (Element) nodes.item(0);
				headerElement.setPrefix(envelopeElement.getPrefix());
				envelopeElement.appendChild(headerElement);
			}
		} else {
			System.out.println("Found " + nodes.getLength() + " SOAP Headerelements.");
			headerElement = (Element) nodes.item(0);
		}

		// Create an XMLSignature instance
		XMLSignature sig = new XMLSignature(doc, "", XMLSignature.ALGO_ID_SIGNATURE_DSA);
		headerElement.appendChild(sig.getElement());
		// Specify the transforms
		Transforms transforms = new Transforms(doc);
		transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
		transforms.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);
		sig.addDocument("", transforms, org.apache.xml.security.utils.Constants.ALGO_ID_DIGEST_SHA1);
		// Add the certificate and public key information from the keystore;
		// this will be needed by the verifier
		X509Certificate cert = (X509Certificate) ks.getCertificate(certificateAlias);
		sig.addKeyInfo(cert);
		sig.addKeyInfo(cert.getPublicKey());
		System.out.println("Starting to sign SOAP Request");
		sig.sign(privateKey);
		System.out.println("Finished signing");
		// Dump the signed request to file
		FileOutputStream f = new FileOutputStream(new File(signatureFileName));
		XMLUtils.outputDOMc14nWithComments(doc, f);
		f.close();
		System.out.println("Wrote signature to " + signatureFileName);
	}
}
