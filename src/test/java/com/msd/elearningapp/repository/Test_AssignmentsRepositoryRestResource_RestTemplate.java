package com.msd.elearningapp.repository;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.msd.elearningapp.domain.*;
@TestMethodOrder(OrderAnnotation.class)
public class Test_AssignmentsRepositoryRestResource_RestTemplate {
/*private static Logger logger = Logger.getLogger(Test_AssignmentsRepositoryRestResource_RestTemplate.class.getName());

	private static String serviceURL = "http://localhost:8080/scrum/data/assignments";
	//
    private RestTemplate restTemplate = new RestTemplate();
	
    @Test @Order(1)
  	public void test1_GetCollectionResource() throws Exception {
  		logger.info("DEBUG: Junit Spring REST Template TESTING: test_GetResource ...");
  		HttpHeaders headers = new HttpHeaders();
		
		
		String stringResponse = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class
			).getBody();
		
		logger.info("DEBUG: Junit TESTING Spring REST Template: GetMessage ... " + stringResponse);
  		
  	}
    
    @Test @Order(2)
  	public void test2_DeleteAssignment() throws Exception {
    	logger.info("DEBUG: Junit Spring REST Template TESTING: test2_DeleteAssignment ...");
    	HttpHeaders headers = new HttpHeaders();
		
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		List<Assignment> assignments = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Assignment>>() {}
			).getBody();
  		
  		assignments.forEach(a -> System.out.println("Assignment before delete: " + a));
  		
  		// delete requests
  		for (Assignment a: assignments) {
  	    	logger.info(">>> TO DELETE: " + serviceURL + "/" + a.getAssigId());
  	    	this.restTemplate.exchange(
  	    				serviceURL + "/" + a.getAssigId(),
  						HttpMethod.DELETE,
  						new HttpEntity<>(headers),
  						new ParameterizedTypeReference<List<Assignment>>() {}
  	    			).getBody();
  	    }
  		
  		//
  		assignments = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Assignment>>() {}
			).getBody();
  		
  		assertTrue("Fail to delete Assignments!", assignments.isEmpty());
    }
    
    @Test @Order(3)
  	public void test3_AddAssignment() throws Exception {
  		// addIntoCollection
  		logger.info("DEBUG: Junit TESTING Spring REST Template: test3_AddAssignment ...");
  		
  		Integer assignmentsToAdd = 3;
  		Assignment assignment;
  		String resourceString;
  		Date tomorow = new Date(new Date().getTime() + 1000*60*60*24*1);
  		
  		for (int i=1; i <= assignmentsToAdd; i++){
  			assignment = new Assignment();
  			assignment.setAssigDateEnd(tomorow);
  			//
  			HttpHeaders headers = new HttpHeaders();
  			
  			headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
  			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
  			//
  			resourceString = this.restTemplate.exchange(
  					serviceURL,
  					HttpMethod.POST,
  					new HttpEntity<>(assignment, headers),
  					String.class
      			).getBody();
  			logger.info("++++ " + "NEW Resource-Assignment: " + resourceString);
  		}
  	}
    
    @Test @Order(4)
  	public void test4_UpdateAssignment() throws Exception {
    	logger.info("DEBUG: Junit Spring REST Template TESTING: test4_UpdateAssignment ...");
    	HttpHeaders headers = new HttpHeaders();
		
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		List<Assignment> assignments = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Assignment>>() {}
			).getBody();
  		
  		assignments.forEach(a -> System.out.println("Assignment before update: " + a));
  		
  		// update requests
  		String resourceString;
  		for (Assignment a: assignments) {
  	    	logger.info(">>> TO UPDATE: " + serviceURL + "/" + a.getAssigId());
  	    	a.setAssigName(a.getAssigName() + ".UPDATED");
  	    	//
  	    	resourceString = this.restTemplate.exchange(
  	    				serviceURL + "/" + a.getAssigId(),
  						HttpMethod.PUT,
  						new HttpEntity<>(a, headers),
  						String.class
  	    			).getBody();
  	    	logger.info("#### " + "UPDATED Resource-Assignment: " + resourceString);
  	    }
  		
  		//List<Assignment> assignmentsAfterUpdate = responseResource.getEmbedded().getassignments();
  		//assignmentsAfterUpdate.forEach(a -> System.out.println("Assignment after update: " + a));
    }
    
    
    @Test @Order(5)
  	public void test5_Getassignments() throws Exception {
    	logger.info("DEBUG: Junit Spring REST Template TESTING: test5_Getassignments ...");
    	HttpHeaders headers = new HttpHeaders();
		
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		List<Assignment> assignments = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Assignment>>() {}
			).getBody();
  		
  		//List<Assignment> assignments = assignmentsResource.getEmbedded().getAssignments();
  		assignments.forEach(a -> System.out.println("assignments: " + a));
    }
  */  
}
