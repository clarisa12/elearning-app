package com.msd.elearningapp;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.msd.elearningapp.domain.Documentation;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ElearningSpringBootTests {
	private static Logger logger = Logger.getLogger(ElearningSpringBootTests.class.getName());

	private static String serviceURL = "http://localhost:8080/api/documentations";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test_AddDocumentation() throws Exception {
		// addIntoCollection
		logger.info("DEBUG: Junit TESTING Spring REST Template: test3_AddDocumentation ...");

		Documentation documentation;
		String resourceString;

		documentation = new Documentation((long) 100, "Documentation_SpringBoot_Data_REST_Service_", "");
		//
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		//
		resourceString = this.restTemplate
				.exchange(serviceURL, HttpMethod.POST, new HttpEntity<>(documentation, headers), String.class)
				.getBody();
		logger.info("++++ " + "NEW Resource-Documentation: " + resourceString);

	}   
	/*
	@Test
	public void test2_DeleteDocumentation() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test2_DeleteDocumentation ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		List<Documentation> documentations = restTemplate.exchange(serviceURL, HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<List<Documentation>>() {
				}).getBody();

		documentations.forEach(p -> System.out.println("Documentation before delete: " + p));

		// delete requests
		for (Documentation p : documentations) {
			logger.info(">>> TO DELETE: " + serviceURL + "/" + p.getIdDoc());
			this.restTemplate.exchange(serviceURL + "/" + p.getIdDoc(), HttpMethod.DELETE, new HttpEntity<>(headers),
					new ParameterizedTypeReference<List<Documentation>>() {
					}).getBody();
		}

		//
		documentations = restTemplate.exchange(serviceURL, HttpMethod.GET, new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Documentation>>() {
				}).getBody();

		assertTrue("Fail to delete Documentations!", documentations.isEmpty());
	}
*/
	
	@Test

	public void test_UpdateDocumentation() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test4_UpdateDocumentation ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		//List<Documentation> documentations = restTemplate.exchange(serviceURL, HttpMethod.GET,
			//	new HttpEntity<>(headers), new ParameterizedTypeReference<List<Documentation>>() {
			//	}).getBody();
		
		ResponseEntity<List<Documentation>>allDocumentations = restTemplate.exchange(serviceURL, HttpMethod.GET, new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Documentation>>() {});
		List<Documentation> documentations = allDocumentations.getBody();
		documentations.forEach(d -> System.out.println("Documentation before update: " + d));

		// update requests
		String resourceString;
		for (Documentation d : documentations) {
			logger.info(">>> TO UPDATE: " + serviceURL + "/" + d.getIdDoc());
			d.setDocBody(d.getDocBody() + ".UPDATED");
			//
			resourceString = this.restTemplate.exchange(serviceURL + "/" + d.getIdDoc(), HttpMethod.PUT,
					new HttpEntity<>(d, headers), String.class).getBody();
			logger.info("#### " + "UPDATED Resource-Documentation: " + resourceString);
		}

		// List<Documentation> documentationsAfterUpdate =
		// responseResource.getEmbedded().getdocumentations();
		// documentationsAfterUpdate.forEach(p -> System.out.println("Documentation
		// after update: " + p));
	}
	/*
	@Test
	
	public void test_Getdocumentations() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test5_Getdocumentations ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		List<Documentation> documentations = restTemplate.exchange(serviceURL, HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<List<Documentation>>() {
				}).getBody();

		// List<Documentation> documentations =
		// documentationsResource.getEmbedded().getDocumentations();
		documentations.forEach(p -> System.out.println("documentations: " + p));
	}
*/
	// Utility method for security
	private String createAuthHeader(String username, String password) {
		String auth = username + ":" + password;

		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));

		String authHeader = "Basic " + new String(encodedAuth);

		return authHeader;
	} 
}