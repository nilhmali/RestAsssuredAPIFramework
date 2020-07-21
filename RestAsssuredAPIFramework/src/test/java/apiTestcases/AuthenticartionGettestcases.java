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
import apiMethods.AutheticationGetMethod;

public class AuthenticartionGettestcases extends Basesetup.TestBase {

	public TestBase testbase;
	private String serviceurl;
	private String URI;
	private Response httpGetResponse;
	private Headers allHeaders;
	private String Content_TypeValues = "";
	private AutheticationGetMethod httpget;

	@BeforeMethod
	public void setUp() throws  IOException {
		testbase = new TestBase();
		serviceurl = prop.getProperty("AuthenticationURI");
		URI = serviceurl;

	}

	 @Parameters({"ValidAccessToken","authEndPoint"})
	@Test(priority = 1)
	public void getMethodStatusCode(String ValidAccessToken,String endPoint ) throws  IOException {
		httpget = new AutheticationGetMethod();
		httpGetResponse = httpget.getCallWithvalid(URI,ValidAccessToken,endPoint);
		
		int getStatusCode = httpGetResponse.getStatusCode();
		System.out.println("Response status code is => " + getStatusCode);

	}

	@Test(priority = 2)
	public void getMethodResponseTime() throws  IOException {
		Long getResponseTime = httpGetResponse.timeIn(TimeUnit.MILLISECONDS);
		System.out.println("Take Response Time=> " + getResponseTime);
	}

	@Parameters("validDataFault")
	@Test(priority = 3)
	public void getMethodValidateJsonData(String Fault) throws  IOException {
		String responseGetbody = httpGetResponse.asString();
		System.out.println("Return ResponseBody of Valid User=> " + responseGetbody);

		JSONObject jsonResponsedata = new JSONObject(responseGetbody);
		String validAutheticationPass = Readjsondata.getvalueByJpath(jsonResponsedata, "/_meta/success");

		System.out.println("Login with valid user =>" + validAutheticationPass);
		String ReturnJsondata_validAuthetication = "true";

		Assert.assertEquals(ReturnJsondata_validAuthetication, validAutheticationPass);

	}

	@Parameters({"InValidAccessToken","authEndPoint"})
	@Test(priority = 4)
	public void getMethodStatusCodeInvalid(String InValidAccessToken,String endPoint ) throws  IOException {
		httpget = new AutheticationGetMethod();
		httpGetResponse = httpget.getCallWithInvalid(URI,InValidAccessToken,endPoint);

		int getStatusCode = httpGetResponse.getStatusCode();
		System.out.println("Response status code is => " + getStatusCode);

	}

	@Parameters("invalidDatafault")
	@Test(priority = 5)
	public void getMethodValidateJsonDataForInvalid(String fault) throws  IOException {
		String responseGetbody = httpGetResponse.asString();
		System.out.println("Return ResponseBody of InValid User=> " + responseGetbody);

		JSONObject jsonResponsedata = new JSONObject(responseGetbody);
		String failed = Readjsondata.getvalueByJpath(jsonResponsedata, "/_meta/success");

		System.out.println("Login with Invalid user=>" + failed);
		String ReturnJsondata_InvalidAuthetication1 = "false";
		Assert.assertEquals(ReturnJsondata_InvalidAuthetication1, failed);

	}

	@Test(priority = 6)
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
