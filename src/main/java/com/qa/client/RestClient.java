package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	//GET call without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
	
		//GET Method
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //GET Request
		CloseableHttpResponse closeableHttpResponese = httpClient.execute(httpget);//hit the url
		
		return closeableHttpResponese;
		
		
	}
	
	//GET call with Headers
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
		//GET Method
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //GET Request
		
		//Adding Headers
		for(Map.Entry<String, String> entry : headerMap.entrySet() ) {
			
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponese = httpClient.execute(httpget);//hit the url
		
		return closeableHttpResponese;
		
	}
	
	
	//POST METHOD
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url); // post call
		httppost.setEntity(new StringEntity(entityString)); // for payload
		
		//for headers
		for(Map.Entry<String, String> entry : headerMap.entrySet() ) {
			
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponese = httpClient.execute(httppost);//hit the url
		return closeableHttpResponese;
	}

}
