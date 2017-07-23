package com.satisdrms.kvds.commons;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.satisdrms.kvds.metadataserver.ConfigurationsBO;

import org.w3c.dom.Element;

public class XMLParser {
	public static void readXML(File inputPath, ConfigurationsBO config) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputPath);
			doc.getDocumentElement().normalize();
			Element datastore = (Element) doc.getElementsByTagName("datastore").item(0);
			config.setTimeOut(
					Integer.parseInt(doc.getElementsByTagName("timeout").item(0).getTextContent()));

			config.setDatastorehomepath(datastore.getElementsByTagName("datastorehomepath").item(0).getTextContent());
			
			Element metadataserver = (Element) doc.getElementsByTagName("metadataserver").item(0);
			config.setMetadataServerHostName(metadataserver.getElementsByTagName("hostname").item(0).getTextContent());
			config.setMetadataServerPort(metadataserver.getElementsByTagName("port").item(0).getTextContent());

			Element nodeElement = (Element) datastore.getElementsByTagName("nodes").item(0);
			NodeList nodes = nodeElement.getElementsByTagName("node");

			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				String nodeHostName = node.getElementsByTagName("hostname").item(0).getTextContent();
				String nodePort = node.getElementsByTagName("port").item(0).getTextContent();
				String nodeDataStoreLocation = node.getElementsByTagName("datastorelocation").item(0).getTextContent();
				Boolean nodeIsAlive = new Boolean(node.getElementsByTagName("alive").item(0).getTextContent());
				config.addNode(nodeHostName, nodePort, nodeDataStoreLocation, nodeIsAlive);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
