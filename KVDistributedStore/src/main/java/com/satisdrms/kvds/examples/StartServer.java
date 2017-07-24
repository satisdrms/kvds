package com.satisdrms.kvds.examples;

import com.satisdrms.kvds.datastore.DataStoreProcess;
import com.satisdrms.kvds.metadataserver.MetaDataServerProcess;

public class StartServer {
	public static void main(String[] args) {
		String inputXML = "src/main/resources/DataStoreConfig.xml";

		MetaDataServerProcess mds = new MetaDataServerProcess(inputXML);
		DataStoreProcess ds1 = new DataStoreProcess(4001);
		DataStoreProcess ds2 = new DataStoreProcess(4002);
		DataStoreProcess ds3 = new DataStoreProcess(4003);
		DataStoreProcess ds4 = new DataStoreProcess(4004);
		mds.start();
		ds1.start();
		ds2.start();
		ds3.start();
		ds4.start();

	}
}
