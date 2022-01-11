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
import com.msd.elearningapp.domain.Student;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ElearningRepositoryRestResource_SpringBootTest{
	private static Logger logger = Logger.getLogger(ElearningRepositoryRestResource_SpringBootTest.class.getName());

	private static String serviceURL = "http://localhost:8080/students";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void test1_GetStudents() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test_GetStudent ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		// List<Documentation> documentations = restTemplate.exchange(serviceURL,
		// HttpMethod.GET,
		// new HttpEntity<>(headers), new
		// ParameterizedTypeReference<List<Documentation>>() {
		// }).getBody();
		ResponseEntity<List<Student>> allDocumentations = restTemplate.exchange(serviceURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Student>>() {
				});
		List<Student> students = allDocumentations.getBody();
		students.forEach(s -> System.out.println("Student before update: " + s));

		// List<Documentation> documentations =
		// documentationsResource.getEmbedded().getDocumentations();
		students.forEach(s -> System.out.println("documentations: " + s));
	}

	@Test
	@Order(2)
	public void test2_DeleteStudent() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test_DeleteStudent ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		// List<Documentation> documentations = restTemplate.exchange(serviceURL,
		// HttpMethod.GET,
		// new HttpEntity<>(headers), new
		// ParameterizedTypeReference<List<Documentation>>() {
		// }).getBody();
		// documentations.forEach(p -> System.out.println("Documentation before delete:
		// " + p));
		ResponseEntity<List<Student>> allstudents = restTemplate.exchange(serviceURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Student>>() {
				});
		List<Student> students = allstudents.getBody();
		students.forEach(s -> System.out.println("Student before update: " + s));

		// delete requests
		for (Student s : students) {
			logger.info(">>> TO DELETE: " + serviceURL + "/" + s.getStudId());
			this.restTemplate.exchange(serviceURL + "/" + s.getStudId(), HttpMethod.DELETE, new HttpEntity<>(headers),
					new ParameterizedTypeReference<List<Student>>() {
					}).getBody();
		}
		//
		students = restTemplate.exchange(serviceURL, HttpMethod.GET, new HttpEntity<>(headers),
				new ParameterizedTypeReference<List<Student>>() {
				}).getBody();
		assertTrue("Fail to delete Student!", students.isEmpty());
	}

	@Test
	@Order(3)
	public void test3_AddStudent() throws Exception {
		// addIntoCollection
		logger.info("DEBUG: Junit TESTING Spring REST Template: test_AddStudent ...");

		Integer studentsToAdd = 3;
		Student student;
		String resourceString;
		for (int i = 1; i <= studentsToAdd; i++) {
			student = new Student((long) i, "Adam", "Smith", new Date(System.currentTimeMillis() - 99999999), "adamsmith@gmail.com", "P@ssword12", "079398855",
					"Location", (long) i, "FEAA", "IE", null);
			//
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", createAuthHeader("msd", "msd"));
			headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			//
			resourceString = this.restTemplate
					.exchange(serviceURL, HttpMethod.POST, new HttpEntity<>(student, headers), String.class).getBody();
			logger.info("++++ " + "New Student: " + resourceString);
		}
	}

	@Test
	@Order(4)

	public void test4_UpdateStudent() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test_UpdateStudent ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		// List<Documentation> documentations = restTemplate.exchange(serviceURL,
		// HttpMethod.GET,
		// new HttpEntity<>(headers), new
		// ParameterizedTypeReference<List<Documentation>>() {
		// }).getBody();

		ResponseEntity<List<Student>> allDocumentations = restTemplate.exchange(serviceURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Student>>() {
				});
		List<Student> documentations = allDocumentations.getBody();
		documentations.forEach(d -> System.out.println("Student before update: " + d));

		// update requests
		String resourceString;
		for (Student d : documentations) {
			logger.info(">>> TO UPDATE: " + serviceURL + "/" + d.getStudId());
			d.setPersFirstName(d.getPersFirstName() + ".UPDATED");
			//
			resourceString = this.restTemplate.exchange(serviceURL + "/" + d.getStudId(), HttpMethod.PUT,
					new HttpEntity<>(d, headers), String.class).getBody();
			logger.info("#### " + "UPDATED Student: " + resourceString);
		}

		// List<Documentation> documentationsAfterUpdate =
		// responseResource.getEmbedded().getdocumentations();
		// documentationsAfterUpdate.forEach(p -> System.out.println("Documentation
		// after update: " + p));
	}

	@Test
	@Order(5)

	public void test5_GetDocumentations() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test_GetDocumentations ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		// List<Documentation> documentations = restTemplate.exchange(serviceURL,
		// HttpMethod.GET,
		// new HttpEntity<>(headers), new
		// ParameterizedTypeReference<List<Documentation>>() {
		// }).getBody();
		ResponseEntity<List<Student>> allDocumentations = restTemplate.exchange(serviceURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Student>>() {
				});
		List<Student> documentations = allDocumentations.getBody();
		documentations.forEach(d -> System.out.println("Documentation before update: " + d));

		// List<Documentation> documentations =
		// documentationsResource.getEmbedded().getDocumentations();
		documentations.forEach(p -> System.out.println("documentations: " + p));
	}

	@Test
	@Order(6)
	public void test6_DeleteDocumentation() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test_DeleteDocumentation ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		// List<Documentation> documentations = restTemplate.exchange(serviceURL,
		// HttpMethod.GET,
		// new HttpEntity<>(headers), new
		// ParameterizedTypeReference<List<Documentation>>() {
		// }).getBody();
		// documentations.forEach(p -> System.out.println("Documentation before delete:
		// " + p));
		ResponseEntity<List<Documentation>> allDocumentations = restTemplate.exchange(serviceURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Documentation>>() {
				});
		List<Documentation> documentations = allDocumentations.getBody();
		documentations.forEach(d -> System.out.println("Documentation before update: " + d));

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

	@Test
	@Order(7)
	public void test7_AddDocumentation() throws Exception {
		// addIntoCollection
		logger.info("DEBUG: Junit TESTING Spring REST Template: test_AddDocumentation ...");

		Documentation documentation;
		String resourceString;

		documentation = new Documentation((long) 100, "DocumentationTest", "");
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

	@Test
	@Order(8)

	public void test8_UpdateDocumentation() throws Exception {
		logger.info("DEBUG: Junit Spring REST Template TESTING: test_UpdateDocumentation ...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createAuthHeader("msd", "msd"));
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		// List<Documentation> documentations = restTemplate.exchange(serviceURL,
		// HttpMethod.GET,
		// new HttpEntity<>(headers), new
		// ParameterizedTypeReference<List<Documentation>>() {
		// }).getBody();

		ResponseEntity<List<Documentation>> allDocumentations = restTemplate.exchange(serviceURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Documentation>>() {
				});
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

	// Utility method for security
	private String createAuthHeader(String username, String password) {
		String auth = username + ":" + password;

		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));

		String authHeader = "Basic " + new String(encodedAuth);

		return authHeader;
	}
}