package com.satisdrms.kvds.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.satisdrms.kvds.requesthandler.RequestHandlerProcess;

@Path("/set")
public class KVPost {

	@Path("/{key}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)

	public void postKVPair(@PathParam("key") String key, ValueDTO val) {
		String inputXML = "C:\\rest\\DataStoreConfig.xml";
		System.out.println("value from json is " + val.value);
		RequestHandlerProcess r = new RequestHandlerProcess(inputXML);
		r.putKV(key, val.value);

	}
}
