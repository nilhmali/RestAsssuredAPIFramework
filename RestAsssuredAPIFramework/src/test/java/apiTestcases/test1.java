package apiTestcases;

import io.restassured.parsing.Parser;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class test1 {

	public static void main(String[] a)
	{
		RestAssured.defaultParser = Parser.JSON;
		RestAssured.baseURI = "https://reqres.in/api/users/4";
		Response httpGetResponse = RestAssured.given().when().get();
		
		
		int getStatusCode = httpGetResponse.getStatusCode();
		System.out.println("Response status code is => " + getStatusCode);
		
		String responseGetbody = httpGetResponse.asString();

		System.out.println("ResponseBody is=> " + responseGetbody);
		List<String> jsonResponse = httpGetResponse.jsonPath().getList("$");
	System.out.println(jsonResponse.size());
	}
}
