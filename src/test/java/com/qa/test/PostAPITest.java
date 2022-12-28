package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{
	
	TestBase testbase;
	//Properties prop;
	String endpointurl;
	String serviceUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponese;

	@BeforeMethod
	public void setup() {
		
		testbase = new TestBase();
		//prop = new Properties();
		endpointurl = prop.getProperty("URL");
		serviceUrl = prop.getProperty("serviceURL");
		
		url = endpointurl + serviceUrl;
		
	}
	
	@Test
	public void postAPITest() throws StreamWriteException, DatabindException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//	headerMap.put("username", "dk@gmail.com");
		//	headerMap.put("password", "test131");
		
		//Jackson API
		//To convert java to JSON
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Dhaval","Var");
		
		//object to JSON file
		mapper.writeValue(new File("C:\\Selenium_Workspace\\RestApiAutomation\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//object JSON to String
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println("");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("UsersJSONString----->"+usersJsonString);
		System.out.println("");
		
		closeableHttpResponese = restClient.post(url, usersJsonString, headerMap);
		
		System.out.println("--------------------------------------POST Call With Headers---------------------------------------");
		System.out.println("");
		System.out.println("-------------------------------Get Status Code--------------------------------------");

		//Get Status Code
		int statuscode = closeableHttpResponese.getStatusLine().getStatusCode();
		System.out.println("Status code ----->"+statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_201, "Status code 200 not matched");
		
		//fetch JSON Response
		System.out.println("-----------------------------Json String----------------------------------------");
		System.out.println("");
		//Json String
		String responseString = EntityUtils.toString(closeableHttpResponese.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API-------->"+responseJson);
		System.out.println("");
		
		//validate the JSON response
		System.out.println("-----------------------------Validate Json Response----------------------------------------");
		//JSON to java
		Users usersResObj = mapper.readValue(responseString, Users.class);
		System.out.println(usersResObj);
		
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());
		
		System.out.println("");
		
	}
	
}
