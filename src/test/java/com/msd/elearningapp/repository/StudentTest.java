package com.msd.elearningapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.msd.elearningapp.ElearningappApplication;
import com.msd.elearningapp.domain.Student;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElearningappApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentTest {
    
	 @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }

     @Test
     public void contextLoads() {

     }
     @Test
     public void testGetAllStudents() {
     HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students",
        HttpMethod.GET, entity, String.class);  
        //assertNotNull(response.getBody());
    }

    @Test
    public void testGetStudentById() {
        Student student = restTemplate.getForObject(getRootUrl() + "/Students/1", Student.class);
        System.out.println(student.getPersFirstName());
        //assertNotNull(student);
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        
        student.setPersAdress("Adress1");
		student.setPersDoB(null);
		student.setPersEmail("flname@provider.com");
		student.setPersFirstName("FirstName");
		student.setPersLastName("LastName");
		student.setPersNum("zz");
		student.setPersPhone("+4071111111");
		student.setStudFaculty("FEAA");
		student.setStudSpecialization("IE");;
        
        
        ResponseEntity<Student> postResponse = restTemplate.postForEntity(getRootUrl() + "/students", student, Student.class);
        //assertNotNull(postResponse);
        //assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateStudent() {
        int id = 1;
        Student student = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        student.setPersAdress("Adress1upd");
		student.setPersDoB(null);
		student.setPersEmail("flname@provider.com");
		student.setPersFirstName("FirstName");
		student.setPersLastName("LastName");
		student.setPersNum("zz");
		student.setPersPhone("+4071111111");
		student.setStudFaculty("FEAA");
		student.setStudSpecialization("IE");
		
        restTemplate.put(getRootUrl() + "/students/" + id, student);
        Student updatedStudent = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
        //assertNotNull(updatedStudent);
    }
/*
    @Test
    public void testDeleteStudent() {
         int id = 1;
         Student student = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
         assertNotNull(student);
         restTemplate.delete(getRootUrl() + "/students/" + id);
         try {
        	 student = restTemplate.getForObject(getRootUrl() + "/students/" + id, Student.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
    */
}
