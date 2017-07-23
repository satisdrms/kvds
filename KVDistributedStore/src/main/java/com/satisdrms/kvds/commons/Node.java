package com.satisdrms.kvds.commons;

import java.io.Serializable;

public class Node implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8503029603410523636L;
	String hostName;
	String port;
	String dataStoreLocation;
	boolean alive;

	public Node(String hostName, String port, String dataStoreLocation, Boolean alive) {
		this.hostName = hostName;
		this.port = port;
		this.dataStoreLocation = dataStoreLocation;
		this.alive = alive;
	}

	public String getHostname() {
		return hostName;
	}

	public void setHostname(String hostname) {
		this.hostName = hostname;
	}

	public int getPort() {
		return Integer.parseInt(port);
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatastorelocation() {
		return dataStoreLocation;
	}

	public void setDatastorelocation(String datastorelocation) {
		this.dataStoreLocation = datastorelocation;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
