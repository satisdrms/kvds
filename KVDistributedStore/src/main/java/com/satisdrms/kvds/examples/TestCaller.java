package com.satisdrms.kvds.examples;

import java.io.File;

import com.satisdrms.kvds.commons.XMLParser;
import com.satisdrms.kvds.metadataserver.ConfigurationsBO;
import com.satisdrms.kvds.metadataserver.MetaDataServerProcess;
import com.satisdrms.kvds.requesthandler.RequestHandlerProcess;

public class TestCaller {
	static String inputXML = "src/main/resources/DataStoreConfig.xml";

	public static void main(String[] args) {
		// xmlWork();
		e2e();
		// mdstst();

	}

	private static void mdstst() {
		MetaDataServerProcess md = new MetaDataServerProcess(inputXML);
		md.getNodesForKey("sathishsss");
	}

	private static void e2e() {
		RequestHandlerProcess r = new RequestHandlerProcess(inputXML);
		r.putKV("sathish", "data");
		// r.putKV("key", "values");
		// r.putKV("kanaga", "subra");
		System.out.println(r.getValue("sathish"));
	}

	private static void xmlWork() {
		String inputXML = "src/main/resources/DataStoreConfig.xml";
		File inputPath = new File(inputXML);
		ConfigurationsBO config = new ConfigurationsBO();

		XMLParser.readXML(inputPath, config);
		System.out.println(config.getTimeOut());
	}

}
