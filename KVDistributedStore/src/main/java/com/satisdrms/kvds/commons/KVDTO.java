package com.satisdrms.kvds.commons;

import java.io.Serializable;

//This is a Key Value Data Transfer Object for sending data between client and server
public class KVDTO implements Serializable {
	boolean put;
	boolean get;
	String key;
	String value;

	public KVDTO(String key, String value, boolean isPut, boolean isGet) {
		this.key = key;
		this.value = value;
		this.put = isPut;
		this.get = isGet;
	}

	public boolean isPut() {
		return put;
	}

	public void setPut(boolean put) {
		this.put = put;
	}

	public boolean isGet() {
		return get;
	}

	public void setGet(boolean get) {
		this.get = get;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
