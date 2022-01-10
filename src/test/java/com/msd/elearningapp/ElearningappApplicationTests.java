package com.msd.elearningapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.msd.elearningapp.domain.Documentation;
import com.msd.elearningapp.domain.Student;

@SpringBootTest
class ElearningappApplicationTests {
	private static Logger logger = Logger.getLogger(ElearningappApplicationTests.class.getName());

	@Test
	void contextLoads() {
	}

	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	@BeforeAll
	public static void createValidator() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@AfterAll
	public static void close() {
		validatorFactory.close();
	}

	@Test
	void correctData() {

		Documentation documentation = new Documentation();
		documentation.setDocBody("this");
		documentation.setDocObs("that");
		Set<ConstraintViolation<Documentation>> violations = validator.validate(documentation);

		assertEquals(violations.size(), 0);

	}

	@Test
	void checkBlankData() {
		Documentation documentation = new Documentation();
		documentation.setDocBody("");
		documentation.setDocObs("that");
		Set<ConstraintViolation<Documentation>> violations = validator.validate(documentation);
		assertEquals(violations.size(), 1);
		ConstraintViolation<Documentation> violation = violations.iterator().next();
		assertEquals("docBody", violation.getPropertyPath().toString());
		assertEquals("", violation.getInvalidValue());

	}

	@Test
	void checkEmailValid() {
		Student student = new Student();
		student.setPersFirstName("FirstName");
		student.setPersFirstName("LastName");
		student.setPersAdress("adress");
		student.setPersDoB(new Date((System.currentTimeMillis())));
		student.setPersEmail("michaelscott@gmail.com");
		student.setPersPhone("+407123456789");
		student.setStudFaculty("FEAA");
		student.setStudSpecialization("IE");

		Set<ConstraintViolation<Student>> violations = validator.validate(student);

		assertEquals(violations.size(), 1);

		ConstraintViolation<Student> violation = violations.iterator().next();
		assertEquals("The format of email is invalid", violation.getMessage());
		assertEquals("email", violation.getPropertyPath().toString());
		assertEquals("mscott.com", violation.getInvalidValue());

	}

	/*

	@Test void checkDOBValid() { Student student = new Student();
	 * student.setPersFirstName("FirstName"); student.setPersFirstName("LastName");
	 * student.setPersAdress("adress"); student.setPersEmail("flname@email.com");
	 * student.setPersPassword("studpass123");
	 * student.setPersPhone("+407123456789"); student.setStudFaculty("FEAA");
	 * student.setStudSpecialization("IE"); student.setPersDoB(new
	 * Date(System.currentTimeMillis() + 100000));
	 * 
	 * Set<ConstraintViolation<Student>> violations = validator.validate(student);
	 * 
	 * assertEquals(violations.size(), 1);
	 * 
	 * ConstraintViolation<Student> violation = violations.iterator().next();
	 * assertEquals("must be a past date", violation.getMessage());
	 * assertEquals("birthDate", violation.getPropertyPath().toString());
	 * 
	 * }*/
}
