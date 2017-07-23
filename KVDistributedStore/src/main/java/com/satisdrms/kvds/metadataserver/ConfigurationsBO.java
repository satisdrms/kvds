package com.satisdrms.kvds.metadataserver;

import java.util.LinkedList;
import java.util.List;

import com.satisdrms.kvds.commons.Node;

public class ConfigurationsBO {

	public ConfigurationsBO() {
		nodes = new LinkedList<Node>();
	}

	String datastorehomepath;
	String metadataServerHostName;
	int metadataServerPort;
	List<Node> nodes;
	int timeOut;

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getMetadataServerPort() {
		return metadataServerPort;
	}

	public void setMetadataServerPort(String metadataServerPort) {
		this.metadataServerPort = Integer.parseInt(metadataServerPort);
	}

	public String getMetadataServerHostName() {
		return metadataServerHostName;
	}

	public void setMetadataServerHostName(String metadataServerHostName) {
		this.metadataServerHostName = metadataServerHostName;
	}

	public String getDatastorehomepath() {
		return datastorehomepath;
	}

	public void setDatastorehomepath(String datastorehomepath) {
		this.datastorehomepath = datastorehomepath;
	}

	public void addNode(String nodeHostName, String nodePort, String nodeDataStoreLocation, Boolean nodeIsAlive) {
		Node node = new Node(nodeHostName, nodePort, nodeDataStoreLocation, nodeIsAlive);
		nodes.add(node);

	}

	public int getNumOfNodes() {
		return nodes.size();
	}

	public Node getNodeNumber(int serverNumber) {
		return nodes.get(serverNumber);
	}

}
