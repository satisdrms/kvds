package com.satisdrms.kvds.metadataserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.satisdrms.kvds.commons.SDTO;
import com.satisdrms.kvds.commons.XMLParser;

public class MetaDataServerProcess extends Thread {
	ConfigurationsBO config;
	File inputPath;

	public MetaDataServerProcess(String inputXML) {
		init(inputXML);
		
	}

	public void init(String inputXML) {
		inputPath = new File(inputXML);
		config = new ConfigurationsBO();
		XMLParser.readXML(inputPath, config);
	}

	public void run() {
		startMDServer();
	}

	@SuppressWarnings("resource")
	public void startMDServer() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(config.getMetadataServerPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {

			try {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				System.out.println("Just connected to " + server.getRemoteSocketAddress());
				ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(server.getInputStream()));
				String key = (String) in.readObject();
				Object nodesList = getNodesForKey(key);
				out.writeObject(nodesList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
 

	public Object getNodesForKey(String s) {
		int hashValue = calAlphaSum(s);
		int server1 = hashValue % config.getNumOfNodes();
		int server2 = (hashValue + 1) % config.getNumOfNodes();
		SDTO nodes = new SDTO();
		nodes.addNodes(config.getNodeNumber(server1));
		nodes.addNodes(config.getNodeNumber(server2));
		return nodes;
	}

	public int calAlphaSum(String s) {
		char[] c = s.toCharArray();
		int hash = 0;
		for (int i = 0; i < c.length; i++) {
			hash = hash + c[i];
		}
		return hash;
	}

	public void validateNodeAlive() {

	}

}
