package apiMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetMethod {

	Response httpGetResponse;

	public Response getCall(String URI, String Endpoint) {

		RestAssured.baseURI = URI;
		httpGetResponse = RestAssured.given().when().get(Endpoint);

		return httpGetResponse;
	}
	
}