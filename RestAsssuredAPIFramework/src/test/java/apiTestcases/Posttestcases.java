package apiTestcases;

import io.restassured.response.Response;
import junit.framework.Assert;
import java.io.IOException;


import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Basesetup.TestBase;
import Utility.Readjsondata;
import apiMethods.PostMethod;

public class Posttestcases extends Basesetup.TestBase {

	public TestBase testbase;
	private String MainURL;
	public String apiurl;
	private String URI;
	private Response httpPostResponse;
	private PostMethod httpPost;

	@BeforeMethod
	public void setUp() throws  IOException {
		testbase = new TestBase();
		MainURL = prop.getProperty("URL");
		URI = MainURL;
	}

	@Parameters({ "uname", "job" })
	@Test(priority = 1)
	public void postMethodstatuscode(String uname, String job) throws  IOException {

		httpPost = new PostMethod();

		httpPostResponse = httpPost.postCall(URI, uname, job);

		int postStatusCode = httpPostResponse.getStatusCode();
		System.out.println("Response status code is => " + postStatusCode);

		Assert.assertEquals(RESPONSE_STATUSCODE_201_CREATED, postStatusCode);
	}

	@Test(priority = 2)
	public void postMethodValidateJsonData() throws  IOException {
		String responseGetbody = httpPostResponse.asString();
		System.out.println("ResponseBody is=> " + responseGetbody);

		JSONObject jsonResponsedata = new JSONObject(responseGetbody);
		String createdAt = Readjsondata.getvalueByJpath(jsonResponsedata, "/createdAt");

		// Assert.assertEquals("2019-12-18T08:51:03.928Z", createdAt);

		System.out.println("createdAt datetime is =>" + createdAt);

	}

}
