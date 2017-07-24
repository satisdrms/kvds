package com.satisdrms.kvds.test;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.*;
import org.json.JSONException;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import junit.framework.Assert;

public class ReturnStatusTest {

	@Test
	public void givenKeyDoesNotExists_whenValueIsRetrieved_thenNULLIsReceived() throws JSONException {

		String key = "keynotexistyet";
		Response resp = get("http://localhost:8081/api/rest/get/" + key);
		String value = resp.asString();
		// System.out.println("respose code is "+resp.getStatusCode());
		Assert.assertEquals(value.replaceAll("\"", ""), "null");
	}

	@Test
	public void givenKVPairExists_WhenRequestisExpect_ThenresposeisOK() throws JSONException {
		String key = "akey";
		String value = "avalue";
		RestAssured.baseURI = "http://localhost:8081/api/rest/set/" + key;
		Response resp = given().contentType("application/json").body("{\"value\":\"" + value + "\"}").when().post();
		int respCode = resp.getStatusCode();
		Assert.assertEquals(respCode, 204);
	}

	@Test
	public void givenKeyDoesExists_whenValueIsRetrieved_thenValueIsReceived() throws JSONException {

		String key = "akey";
		Response resp = get("http://localhost:8081/api/rest/get/" + key);
		String value = resp.asString();
		// System.out.println("respose code is "+resp.getStatusCode());
		Assert.assertEquals(value.replaceAll("\"", ""), "avalue");
	}

	@Test
	public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() {
		String jsonMimeType = "application/json";
		// Given
		String key = "akey";
		Response resp = get("http://localhost:8081/api/rest/get/" + key);
		String mimeType = resp.getContentType();
		Assert.assertEquals(jsonMimeType, mimeType);
	}

	public void givenRequestWithIncorrectJsonBody_whenRequestIsExecuted_then400BadRequestIsRetured() {
		String key = "1akey";
		String value = "1avalue";
		String appendingJunkToGetBadRequest = "JUNK";
		RestAssured.baseURI = "http://localhost:8081/api/rest/set/" + key;
		Response resp = given().contentType("application/json")
				.body("{\"value\":\"" + value + "\"}" + appendingJunkToGetBadRequest).when().post();
		int respCode = resp.getStatusCode();
		Assert.assertEquals(respCode, 400);
	}

	public void givenCallToApiWithDifferentPort_whenRequestIsExecuted_thenCorrectValueIsReturned() {
		// Calling to a different api server for getting the same value
		String key = "akey";
		Response resp = get("http://localhost:8080/api/rest/get/" + key);
		String value = resp.asString();
		// System.out.println("respose code is "+resp.getStatusCode());
		Assert.assertEquals(value.replaceAll("\"", ""), "avalue");

	}

}
