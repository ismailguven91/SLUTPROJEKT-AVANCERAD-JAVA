package com.emailApi.EmailCheckerApi;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EmailModel {
	
	String query;
	String format;
	String server_status;
	String email_status;
	String professional;
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getServer_status() {
		return server_status;
	}

	public void setServer_status(String server_status) {
		this.server_status = server_status;
	}

	public String getEmail_status() {
		return email_status;
	}

	public void setEmail_status(String email_status) {
		this.email_status = email_status;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public void getApi(String emailInput) throws UnirestException, ParseException {

		//HÄMTAR API
		HttpResponse<JsonNode> apiResponse = Unirest
				.get("https://email-verifier-completely-free.p.rapidapi.com/email-verification/"+emailInput)
				.header("X-RapidAPI-Key", "29769a47d4msh6d483c9ef8feeaap11d38bjsnce91b4921f8e")
				.header("X-RapidAPI-Host", "email-verifier-completely-free.p.rapidapi.com").asJson();
		
		//PRINTAR STATUS FRÅN API:ET
		//System.out.println(apiResponse.getStatus());
		//PRINTAR API-RESPONEN 
		//System.out.println(apiResponse.getBody()); 
		
		JSONParser parser = new JSONParser();
		
		JSONObject object = (JSONObject) parser.parse(apiResponse.getBody().toString());
		//System.out.println(object.get("response"));
		
		String responseObject = object.get("response").toString();
		
		JSONObject object2 = (JSONObject) parser.parse(responseObject);
		
		String query = object2.get("query").toString();
		String format = object2.get("format").toString();
		String server_status = object2.get("server_status").toString();
		String email_status = object2.get("email_status").toString();
		String professional = object2.get("professional").toString();
		
		setQuery(query);
		setFormat(format);
		setServer_status(server_status);
		setEmail_status(email_status);
		setProfessional(professional);
		

		
		
		
	}
	
	

	
	
}
