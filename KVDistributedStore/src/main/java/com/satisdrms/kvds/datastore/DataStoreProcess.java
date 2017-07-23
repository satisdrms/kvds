package com.satisdrms.kvds.datastore;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import com.satisdrms.kvds.commons.KVDTO;
import com.satisdrms.kvds.commons.Node;
import com.satisdrms.kvds.commons.SDTO;
import com.satisdrms.kvds.commons.XMLParser;
import com.satisdrms.kvds.metadataserver.ConfigurationsBO;

public class DataStoreProcess extends Thread {
	DataStoreBO dbo;
	int port;
	ConfigurationsBO config;
	File inputPath;

	public DataStoreProcess(String inputXML, int port) {
		this.port = port;
		inputPath = new File(inputXML);
		init();
	}

	private void init() {
		dbo = new DataStoreBO();
		config = new ConfigurationsBO();
		XMLParser.readXML(inputPath, config);
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
				System.out.println("DataStore server started and listening on port " + port);
				Socket dsServer = dsServerSocket.accept();
				System.out.println("Just connected to " + dsServer.getRemoteSocketAddress());
				ObjectOutputStream out = new ObjectOutputStream(dsServer.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(dsServer.getInputStream()));
				KVDTO dataPack = (KVDTO) in.readObject();
				if (dataPack.isPut()) {
					storeKVPairAndDistribute(dataPack.getKey(), dataPack.getValue(), dataPack);
					System.out.println(
							"Data with key " + dataPack.getKey() + " is stored on " + dsServer.getLocalSocketAddress());
				}
				if (dataPack.isGet()) {
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

	public void storeKVPairAndDistribute(String key, String value, KVDTO dataPack) {
		dbo.putKVPair(key, value);
		connectToDataStoreAndPutKVPair(dataPack);
	}

	public void connectToDataStoreAndPutKVPair(KVDTO kv) {
		if (kv == null)
			return;
		SDTO sdto = kv.getSDTO();
		if (sdto.isDistributionOver()) {

			Node node = sdto.getAndRemoveANode();
			String nextHost = node.getHostname();
			int nextPort = node.getPort();
			Socket s = null;
			try {
				// s = new Socket(host, port);
				s = new Socket();
				s.connect(new InetSocketAddress(nextHost, nextPort), config.getTimeOut());

				ObjectOutputStream out = new ObjectOutputStream((s.getOutputStream()));
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
				out.writeObject((Object) kv);
				out.close();
				in.close();
				s.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getValue(String key) {
		return dbo.getValue(key);
	}

	public boolean returnNodeAlive() {
		return true;
	}
}
