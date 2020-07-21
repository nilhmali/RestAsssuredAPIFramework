package apiTestcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Basesetup.TestBase;
import Utility.Readjsondata;
import apiMethods.GetMethod;

/*GetTestcases*/

public class Gettestcases extends Basesetup.TestBase {

	public TestBase testbase;
	public String MainURL;
	private String URI;
	public Gettestcases httpget;
	private Response httpGetResponse;
	private Headers allHeaders;
	private String Content_TypeValues = "";

	@BeforeMethod
	public void setUp() throws IOException {
		testbase = new TestBase();
		MainURL = prop.getProperty("URL");
		URI = MainURL;

	}

	@Parameters({ "endUrl" })
	@Test(priority = 1)
	public void getMethodStatusCode(String endpoint) throws  IOException {
		GetMethod httpget = new GetMethod();
		httpGetResponse = httpget.getCall(URI, "/" + endpoint);

		System.out.println("---------------Get Testcases---------");
		try {
			int getStatusCode = httpGetResponse.getStatusCode();
			System.out.println("Response status code is => " + getStatusCode);

			Assert.assertEquals(RESPONSE_STATUSCODE_200, getStatusCode);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	@Test(priority = 2)
	public void getMethodResponseTime() throws  IOException {
		Long getResponseTime = httpGetResponse.timeIn(TimeUnit.MILLISECONDS);
		System.out.println("Take Response Time=> " + getResponseTime);
	}

	@Parameters({ "getMethodArrayData" })
	@Test(priority = 3)
	public void getMethodValidateJsonData(String last_name) throws  IOException {
		String responseGetbody = httpGetResponse.asString();

		System.out.println("ResponseBody is=> " + responseGetbody);
		JSONObject jsonResponsedata = new JSONObject(responseGetbody);

		String jsonLastName = Readjsondata.getvalueByJpath(jsonResponsedata, "/data/" + last_name);
		System.out.println("lastName of id 4 is =>" + jsonLastName);

		Assert.assertEquals(jsonArray_ValidateLastname, jsonLastName);
	}

	@Test(priority = 4)
	public void getMethodValidateHeaders() throws  IOException {
		allHeaders = httpGetResponse.headers();

		// Iterate over all the Headers
		for (Header header : allHeaders) {
			if (header.getName().contains("Content-Type")) {
				System.out.println(header.getName() + " Value: " + header.getValue());
				Content_TypeValues = httpGetResponse.header("Content-Type");
				break;
			}

		}
		Assert.assertEquals(Content_TypeValues, Header_ValidateContentType);

		
	}

}
