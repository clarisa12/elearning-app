package com.msd.elearningapp;


import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.msd.elearningapp.domain.Student;

@TestMethodOrder(OrderAnnotation.class)
public class ElearningRepositoryRestResource_RestTemplate {
private static Logger logger = Logger.getLogger(ElearningRepositoryRestResource_RestTemplate.class.getName());

	private static String serviceURL = "http://localhost:8080/students";
	//
    private RestTemplate restTemplate = new RestTemplate();
	
    @Test @Order(1)
  	public void test1_GetCollectionResource() throws Exception {
  		logger.info("DEBUG: Junit Spring REST Template TESTING: test_GetResource ...");
  		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("developer", "msd"));
		
		String stringResponse = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class
			).getBody();
		
		logger.info("DEBUG: Junit TESTING Spring REST Template: GetMessage ... " + stringResponse);
  		
  	}
    
    @Test @Order(2)
  	public void test2_DeleteStudent() throws Exception {
    	logger.info("DEBUG: Junit Spring REST Template TESTING: test2_DeleteStudent ...");
    	HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("developer", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		List<Student> students = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Student>>() {}
			).getBody();
  		
  		students.forEach(p -> System.out.println("Student before delete: " + p));
  		
  		// delete requests
  		for (Student p: students) {
  	    	logger.info(">>> TO DELETE: " + serviceURL + "/" + p.getStudId());
  	    	this.restTemplate.exchange(
  	    				serviceURL + "/" + p.getStudId(),
  						HttpMethod.DELETE,
  						new HttpEntity<>(headers),
  						new ParameterizedTypeReference<List<Student>>() {}
  	    			).getBody();
  	    }
  		
  		//
  		students = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Student>>() {}
			).getBody();
  		
  		assertTrue("Fail to delete Students!", students.isEmpty());
    }
    
    @Test @Order(3)
  	public void test3_AddStudent() throws Exception {
  		// addIntoCollection
  		logger.info("DEBUG: Junit TESTING Spring REST Template: test3_AddStudent ...");
  		
  		Integer studentsToAdd = 3;
  		Student student;
  		String resourceString;
  		//Date tomorow = new Date(new Date().getTime() + 1000*60*60*24*1);
  		
  		for (int i=1; i <= studentsToAdd; i++){
  			student = new Student((long) i, "Adam", "Smith", new Date(System.currentTimeMillis() - 99999999), "adamsmith@gmail.com", "P@ssword12", "079398855",
					"Location", (long) i, "FEAA", "IE", null);
  			//
  			HttpHeaders headers = new HttpHeaders();
  			headers.add("Authorization", createAuthHeader("developer", "msd"));
  			headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
  			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
  			//
  			resourceString = this.restTemplate.exchange(
  					serviceURL,
  					HttpMethod.POST,
  					new HttpEntity<>(student, headers),
  					String.class
      			).getBody();
  			logger.info("++++ " + "NEW Resource-Student: " + resourceString);
  		}
  	}
    
    /*
    @Test @Order(4)
  	public void test4_UpdateStudent() throws Exception {
    	logger.info("DEBUG: Junit Spring REST Template TESTING: test4_UpdateStudent ...");
    	HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("developer", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		
		List<Student> students = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Student>>() {}
			).getBody();
  		
  		students.forEach(p -> System.out.println("Student before update: " + p));
  		
  		// update requests
  		
  		String resourceString;
  		for (Student p: students) {
  	    	logger.info(">>> TO UPDATE: " + serviceURL + "/" + p.getStudId());
  	    	p.setPersFirstName(p.getPersFirstName() + ".UPDATED");
  	    	//
  	    	resourceString = this.restTemplate.exchange(
  	    				serviceURL + "/" + p.getStudId(),
  						HttpMethod.PUT,
  						new HttpEntity<>(p, headers),
  						String.class
  	    			).getBody();
  	    	logger.info("#### " + "UPDATED Resource-Student: " + resourceString);
  	    }
  		
  		
  		//List<Student> studentsAfterUpdate = responseResource.getEmbedded().getstudents();
  		//studentsAfterUpdate.forEach(p -> System.out.println("Student after update: " + p));
    }
    */
    
    @Test @Order(4)
  	public void test5_Getstudents() throws Exception {
    	logger.info("DEBUG: Junit Spring REST Template TESTING: test5_Getstudents ...");
    	HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("developer", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		List<Student> students = restTemplate.exchange(
				serviceURL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Student>>() {}
			).getBody();
  		
  		//List<Student> students = studentsResource.getEmbedded().getStudents();
  		students.forEach(p -> System.out.println("students: " + p));
    }
    
    // Utility method for security
    private String createAuthHeader(String username, String password) {
		String auth = username + ":" + password;
        
        byte[] encodedAuth = Base64.getEncoder().encode( 
           auth.getBytes(Charset.forName("US-ASCII")) );
        
        String authHeader = "Basic " + new String( encodedAuth );
        
        return authHeader;
	}
}
