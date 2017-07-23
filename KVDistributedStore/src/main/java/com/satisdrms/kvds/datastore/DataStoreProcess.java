package com.satisdrms.kvds.datastore;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import com.satisdrms.kvds.commons.KVDTO;

public class DataStoreProcess extends Thread {
	DataStoreBO dbo;
	int port;

	public DataStoreProcess(int port) {
		this.port = port;
		init();
	}

	private void init() {
		dbo = new DataStoreBO();
	}

	public void run() {
		startDSServer();
	}

	@SuppressWarnings("resource")
	private void startDSServer() {
		ServerSocket dsServerSocket = null;
		try {
			dsServerSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			try {
				// System.out.println("DataStore server started and listening on port " + port);
				Socket dsServer = dsServerSocket.accept();
				// System.out.println("Just connected to " + dsServer.getRemoteSocketAddress());
				ObjectOutputStream out = new ObjectOutputStream(dsServer.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(dsServer.getInputStream()));
				KVDTO dataPack = (KVDTO) in.readObject();
				// System.out.println("Received KV Pair ");
				if (dataPack.isPut()) {
					// System.out.println("Putting data to the server");
					storeKVPair(dataPack.getKey(), dataPack.getValue());
					// System.out.println(dataPack.getKey() + " KV IS " + dataPack.getValue());
				}
				if (dataPack.isGet()) {
					// System.out.println("Getting data from the server");
					dataPack.setValue(getValue(dataPack.getKey()));
					out.writeObject(dataPack);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void storeKVPair(String key, String value) {
		dbo.putKVPair(key, value);
	}

	public String getValue(String key) {
		return dbo.getValue(key);
	}

	public boolean returnNodeAlive() {
		return true;
	}
}
