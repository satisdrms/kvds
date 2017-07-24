package com.satisdrms.kvds.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class CallTestCases {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		System.out.println("Test cases start");
		ReturnStatusTest reTest = new ReturnStatusTest();
		reTest.givenKeyDoesNotExists_whenValueIsRetrieved_thenNULLIsReceived();
		reTest.givenKVPairExists_WhenRequestisExpect_ThenresposeisOK();
		reTest.givenKeyDoesExists_whenValueIsRetrieved_thenValueIsReceived();
		reTest.givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson();
		reTest.givenRequestWithIncorrectJsonBody_whenRequestIsExecuted_then400BadRequestIsRetured();
		reTest.givenCallToApiWithDifferentPort_whenRequestIsExecuted_thenCorrectValueIsReturned();
		System.out.println("Test cases ended with no failure");
	}

}
