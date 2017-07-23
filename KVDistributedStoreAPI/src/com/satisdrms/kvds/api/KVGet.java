package com.satisdrms.kvds.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.satisdrms.kvds.requesthandler.RequestHandlerProcess;

@Path("/get")
public class KVGet {

	@Path("/{key}")
	@GET
	@Consumes
	@Produces("application/json")
	public String getValue(@PathParam("key") String key) {
		String inputXML = "C:\\rest\\DataStoreConfig.xml";
		RequestHandlerProcess r = new RequestHandlerProcess(inputXML);
		return '"' + r.getValue(key) + '"';
	}

}
