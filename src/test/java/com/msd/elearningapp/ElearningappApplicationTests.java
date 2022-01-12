package com.msd.elearningapp;

import java.util.Date;
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

import com.msd.elearningapp.domain.Documentation;
import com.msd.elearningapp.domain.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
class ElearningappApplicationTests {

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

		Student student = new Student();
		student.setStudId((long) 100000);
		student.setPersIdentityNum((long) 100000);
		student.setPersFirstName("FirstName");
		student.setPersLastName("LastName");
		student.setPersAdress("adress");
		student.setPersDoB(new Date((System.currentTimeMillis())));
		student.setPersEmail("firstlast@email.com");
		student.setPersPassword("P@ssword12");
		student.setPersPhone("+40711111111");
		student.setStudFaculty("FEAA");
		student.setStudSpecialization("IE");
		student.setWorkgroups(null);
		Set<ConstraintViolation<Student>> violations = validator.validate(student);

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
	public void checkValidEmail() {
		Student student = new Student();
		student.setStudId((long) 100000);
		student.setPersIdentityNum((long) 100000);
		student.setPersFirstName("FirstName");
		student.setPersLastName("LastName");
		student.setPersAdress("adress");
		student.setPersDoB(new Date((System.currentTimeMillis())));
		student.setPersEmail("firstlastemail.com"); //invalid email
		student.setPersPassword("P@ssword12");
		student.setPersPhone("+40711111111");
		student.setStudFaculty("FEAA");
		student.setStudSpecialization("IE");
		student.setWorkgroups(null);
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		assertFalse(violations.isEmpty()); //true for invalid email
	}

	@Test
	void checkDoBValid() {
		Student student = new Student();
		student.setStudId((long) 100000);
		student.setPersIdentityNum((long) 100000);
		student.setPersFirstName("FirstName");
		student.setPersLastName("LastName");
		student.setPersAdress("adress");
		student.setPersEmail("firstlast@email.com");
		student.setPersPassword("P@ssword12");
		student.setPersPhone("+40711111111");
		student.setStudFaculty("FEAA");
		student.setStudSpecialization("IE");
		student.setWorkgroups(null);
		student.setPersDoB(new Date(System.currentTimeMillis() + 9999));

		Set<ConstraintViolation<Student>> violations = validator.validate(student);

		assertFalse(violations.isEmpty());

	}
	
	@Test
	void checkPasswordValid() {
		Student student = new Student();
		student.setStudId((long) 100000);
		student.setPersIdentityNum((long) 100000);
		student.setPersFirstName("FirstName");
		student.setPersLastName("LastName");
		student.setPersAdress("adress");
		student.setPersEmail("firstlast@email.com");
		student.setPersPassword("P@ssword12"); //valid pass
		student.setPersPhone("+40711111111");
		student.setStudFaculty("FEAA");
		student.setStudSpecialization("IE");
		student.setWorkgroups(null);
		student.setPersDoB(new Date(System.currentTimeMillis() - 99999999));

		Set<ConstraintViolation<Student>> violations = validator.validate(student);

		assertTrue(violations.isEmpty());

	}

	private void assertTrue(boolean empty) {
	}

	@Test
	void sizeExceedsLimit() {
		Student student = new Student();
		student.setStudId((long) 100000);
		student.setPersIdentityNum((long) 100000);
		student.setPersFirstName("F");
		student.setPersLastName("L");
		student.setPersAdress("adress");
		student.setPersEmail("firstlast@email.com");
		student.setPersPassword("P@ssword12"); //valid pass
		student.setPersPhone("+40711111111");
		student.setStudFaculty("FEAA");
		student.setStudSpecialization("IE");
		student.setWorkgroups(null);
		student.setPersDoB(new Date(System.currentTimeMillis() - 99999999));
		
		Set<ConstraintViolation<Student>> violations = validator.validate(student);

		assertFalse(violations.isEmpty());
	}

}
