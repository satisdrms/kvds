package com.satisdrms.kvds.examples;

import java.io.File;

import com.satisdrms.kvds.commons.XMLParser;
import com.satisdrms.kvds.metadataserver.ConfigurationsBO;
import com.satisdrms.kvds.metadataserver.MetaDataServerProcess;
import com.satisdrms.kvds.requesthandler.RequestHandlerProcess;

public class TestCaller2 {
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
		System.out.println("Putting first value");
		r.putKV("2", "data");
		System.out.println("Putting second value");
		// r.putKV("key", "values");
		 r.putKV("4", "frame");
		 System.out.println("all done");
		System.out.println(r.getValue("4"));
	}

	private static void xmlWork() {
		String inputXML = "src/main/resources/DataStoreConfig.xml";
		File inputPath = new File(inputXML);
		ConfigurationsBO config = new ConfigurationsBO();

		XMLParser.readXML(inputPath, config);
		System.out.println(config.getTimeOut());
	}

}

 