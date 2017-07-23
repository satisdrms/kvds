package com.satisdrms.kvds.datastore;

import java.util.HashMap;
import java.util.Map;

public class DataStoreBO {
	Map<String, String> data;

	public Map<String, String> getData() {
		return data;
	}

	public DataStoreBO() {
		data = new HashMap<String, String>();
	}

	public void putKVPair(String key, String value) {
		data.put(key, value);
	}

	public String getValue(String key) {
		return data.get(key);
	}

}
