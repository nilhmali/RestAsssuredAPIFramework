package apiMethods;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Utility.PutData_Pojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PutMethod {

	JSONObject jsonPutData;
	ObjectMapper mapper;
	PutData_Pojo users;
	String categoryJson;

	public Response putCall(String URI, String name, String Job, String putEnpoint)
			throws JsonGenerationException, JsonMappingException, IOException {
		String jsonDataStored = System.getProperty("user.dir") + "/Resources/PutPojo.json";
		mapper = new ObjectMapper();
		PutData_Pojo users = new PutData_Pojo(name, Job);

		// Write object data into .json file
		mapper.writeValue(new File(jsonDataStored), users);

		// Object Mapper to serialize Java objects into JSON
		categoryJson = mapper.writeValueAsString(users);

		RestAssured.baseURI = URI;
		Response httpPutResponse = RestAssured.given().body(categoryJson).when().put(putEnpoint);

		return httpPutResponse;

	}
}