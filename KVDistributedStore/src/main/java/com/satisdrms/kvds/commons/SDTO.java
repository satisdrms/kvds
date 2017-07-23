package com.satisdrms.kvds.commons;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8481185671449011654L;
	List<Node> dataStoreNodes;

	public SDTO() {
		dataStoreNodes = new LinkedList<Node>();
	}

	public void addNodes(Node node) {
		dataStoreNodes.add(node);
	}

	public List<Node> getNodes() {
		return dataStoreNodes;
	}

	public Node getAndRemoveANode() {
		return dataStoreNodes.remove(0);
	}

	public boolean isDistributionOver() {
		if (dataStoreNodes.size() > 0)
			return true;
		else
			return false;
	}

	public void print() {
		for (Node n : dataStoreNodes) {
			System.out.println(n.hostName + "-" + n.port);
		}

	}

}
