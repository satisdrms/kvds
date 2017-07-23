package com.satisdrms.kvds.requesthandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.satisdrms.kvds.commons.KVDTO;
import com.satisdrms.kvds.commons.Node;
import com.satisdrms.kvds.commons.SDTO;
import com.satisdrms.kvds.commons.XMLParser;
import com.satisdrms.kvds.metadataserver.ConfigurationsBO;

public class RequestHandlerProcess {
	ConfigurationsBO config;
	File inputPath;
	SDTO sdto;

	public RequestHandlerProcess(String inputXML) {
		inputPath = new File(inputXML);
		config = new ConfigurationsBO();
		XMLParser.readXML(inputPath, config);

	}

	public void putKV(String key, String value) {
		KVDTO kv = new KVDTO(key, value, true, false);
		connectToMetaDataServerGetNodesForKey(kv);
		connectToDataStoreAndPutKVPair(kv);
	}

	public String getValue(String key) {
		KVDTO kv = new KVDTO(key, null, false, true);
		connectToMetaDataServerGetNodesForKey(kv);
		String value = connectToDataStoreAndGetValue(key);
		return value;
	}

	public void checkNodeActive(String node) {

	}

	public void connectToMetaDataServerGetNodesForKey(KVDTO kv) {
		SDTO nodesList = null;
		String host = config.getMetadataServerHostName();
		int port = config.getMetadataServerPort();

		Socket s = null;
		try {
			// s = new Socket(host, port);
			s = new Socket();
			s.connect(new InetSocketAddress(host, port), config.getTimeOut());
			ObjectOutputStream out = new ObjectOutputStream((s.getOutputStream()));
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			out.writeObject((Object) kv.getKey());
			nodesList = (SDTO) in.readObject();
			s.close();
			out.close();
			in.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdto = nodesList;
	}

	public void connectToDataStoreAndPutKVPair(KVDTO kv) {
		for (Node node : sdto.getNodes()) {
			String host = node.getHostname();
			int port = node.getPort();
			Socket s = null;
			try {
				// s = new Socket(host, port);
				s = new Socket();
				s.connect(new InetSocketAddress(host, port), config.getTimeOut());

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

	public String connectToDataStoreAndGetValue(String key) {
		KVDTO kv = new KVDTO(key, null, false, true);
		for (Node node : sdto.getNodes()) {
			String host = node.getHostname();
			int port = node.getPort();
			Socket s = null;

			try {
				// s = new Socket(host, port);
				s = new Socket();
				s.connect(new InetSocketAddress(host, port), config.getTimeOut());
				ObjectOutputStream out = new ObjectOutputStream((s.getOutputStream()));
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
				out.writeObject((Object) kv);
				kv = (KVDTO) in.readObject();
				out.close();
				in.close();
				s.close();

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			break;
		}
		return kv.getValue();

	}

}
