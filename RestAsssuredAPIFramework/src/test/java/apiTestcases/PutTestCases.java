package apiTestcases;

import io.restassured.response.Response;

import java.io.IOException;


import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Basesetup.TestBase;
import Utility.Readjsondata;

import apiMethods.PutMethod;

public class PutTestCases extends Basesetup.TestBase {

	public TestBase testbase;
	private String MainURL;
	public String apiurl;
	private String URI;
	private Response httpPutResponse;
	private PutMethod httpPost;
	private int putStatusCode;
	private String responseGetbody;
	private JSONObject jsonResponsedata;
	private String updatedAt;

	@BeforeMethod
	public void setUp() throws  IOException {
		testbase = new TestBase();
		MainURL = prop.getProperty("URL");
		URI = MainURL;
	}

	@Parameters({ "putName", "putJob", "putEndurl" })
	@Test(priority = 1)
	public void putMethodStatusCode(String uname, String job, String putEnpoint)
			throws  IOException {
		httpPost = new PutMethod();
		httpPutResponse = httpPost.putCall(URI, uname, job, "/" + putEnpoint);

		putStatusCode = httpPutResponse.getStatusCode();
		System.out.println("Response status code is => " + putStatusCode);
		Assert.assertEquals(RESPONSE_STATUSCODE_200, putStatusCode);

	}

	@Test(priority = 2)
	public void putMethodValidateJsonData() throws  IOException {
		responseGetbody = httpPutResponse.asString();
		System.out.println("ResponseBody is=> " + responseGetbody);

		jsonResponsedata = new JSONObject(responseGetbody);
		updatedAt = Readjsondata.getvalueByJpath(jsonResponsedata, "/updatedAt");

		System.out.println("createdAt datetime is =>" + updatedAt);

		System.out.println("---------------Authentication Testcases------------");
	}

}
