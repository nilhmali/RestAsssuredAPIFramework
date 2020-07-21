package apiMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AutheticationGetMethod {

	Response httpGetResponse;

	public Response getCallWithvalid(String URI,String validAccessToken,String endPoint) {
		
		RestAssured.baseURI = URI;
		
		httpGetResponse = RestAssured.given().auth().oauth2(validAccessToken).get(endPoint);
				
		return httpGetResponse;
	}

	public Response getCallWithInvalid(String URI,String inValidAccessToken,String endPoint) {

		RestAssured.baseURI = URI;
		httpGetResponse = RestAssured.given().auth().oauth2(inValidAccessToken).get(endPoint);
		return httpGetResponse;
	}


}