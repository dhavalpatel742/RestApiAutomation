package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase{
	
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
	
	@Test(priority=1)
	public void getTest() throws ClientProtocolException, IOException {
		
				restClient = new RestClient();
				closeableHttpResponese = restClient.get(url);
				
				System.out.println("--------------------------------------Get Call Without Headers---------------------------------------");
				System.out.println("");
				
				System.out.println("-------------------------------Get Status Code--------------------------------------");

				//Get Status Code
				int statuscode = closeableHttpResponese.getStatusLine().getStatusCode();
				System.out.println("Status code ----->"+statuscode);
				Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "Status code 200 not matched");
				
				System.out.println("-----------------------------Json String----------------------------------------");
				System.out.println("");
				//Json String
				String responseString = EntityUtils.toString(closeableHttpResponese.getEntity(),"UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API-------->"+responseJson);
				System.out.println("");
				
				System.out.println("-----------------------------Single Value Assertion----------------------------------------");

				
				//Single Value Assertion
				String perpage = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("per page---->"+perpage);
				Assert.assertEquals(Integer.parseInt(perpage) , 6);
				
				String total = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Total Value---->"+total);
				Assert.assertEquals(Integer.parseInt(total), 12);
				
				System.out.println("-------------------------------Get the value from JSON array--------------------------------------");
				
				//Get the value from JSON array
				String last_name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
				
				System.out.println("Last Name : "+last_name);
				System.out.println("id : "+id);
				System.out.println("Avatar : "+avatar);
				System.out.println("First Name : "+first_name);
				System.out.println("Email : "+email);
				
				System.out.println("-----------------------------Headers----------------------------------------");

				System.out.println("");
				//Headers
				Header[] headerArray = closeableHttpResponese.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				
				for(Header header : headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers---------->"+allHeaders);
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				
		
	}
	
	
	@Test(priority=2)
	public void getTestwithHeaders() throws ClientProtocolException, IOException {
		
				restClient = new RestClient();
				
				HashMap<String, String> headerMap = new HashMap<String, String>();
				headerMap.put("Content-Type", "application/json");
			//	headerMap.put("username", "dk@gmail.com");
			//	headerMap.put("password", "test131");
				
				closeableHttpResponese = restClient.get(url);
				
				System.out.println("--------------------------------------Get Call With Headers---------------------------------------");
				System.out.println("");
				System.out.println("-------------------------------Get Status Code--------------------------------------");

				//Get Status Code
				int statuscode = closeableHttpResponese.getStatusLine().getStatusCode();
				System.out.println("Status code ----->"+statuscode);
				Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "Status code 200 not matched");
				
				System.out.println("-----------------------------Json String----------------------------------------");
				System.out.println("");
				//Json String
				String responseString = EntityUtils.toString(closeableHttpResponese.getEntity(),"UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API-------->"+responseJson);
				System.out.println("");
				
				System.out.println("-----------------------------Single Value Assertion----------------------------------------");

				
				//Single Value Assertion
				String perpage = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("per page---->"+perpage);
				Assert.assertEquals(Integer.parseInt(perpage) , 6);
				
				String total = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Total Value---->"+total);
				Assert.assertEquals(Integer.parseInt(total), 12);
				
				System.out.println("-------------------------------Get the value from JSON array--------------------------------------");
				
				//Get the value from JSON array
				String last_name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
				
				System.out.println("Last Name : "+last_name);
				System.out.println("id : "+id);
				System.out.println("Avatar : "+avatar);
				System.out.println("First Name : "+first_name);
				System.out.println("Email : "+email);
				
				System.out.println("-----------------------------Headers----------------------------------------");

				System.out.println("");
				//Headers
				Header[] headerArray = closeableHttpResponese.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				
				for(Header header : headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers---------->"+allHeaders);
				System.out.println("");
				System.out.println("-----------------------------------------------------------------------------");
				
		
	}
}
