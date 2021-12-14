package com.msd.elearningapp.loaddatabase;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.msd.elearningapp.domain.*;
import com.msd.elearningapp.repository.*;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabaseAdministrator(AdministratorRepository repository) {

		return args -> {

			log.info("Preloading " + repository.save(new Administrator((long) 100001, "AdmFirstName1", "AdmLastName1",
					new Date(1988 - 12 - 12), "admin1@email.com", "0711111111", "admin 1 adress", (long) 1,
					"Admin1 title", "Admin1 department")));
			log.info("Preloading " + repository.save(new Administrator((long) 100002, "AdmFirstName2", "AdmLastName2",
					new Date(1988 - 12 - 12), "admin2@email.com", "0711111112", "admin 2 adress", (long) 2,
					"Admin2 title", "Admin2 department")));
			log.info("Preloading " + repository.save(new Administrator((long) 100003, "AdmFirstName3", "AdmLastName3",
					new Date(1988 - 12 - 12), "admin3@email.com", "0711111113", "admin 3 adress", (long) 3,
					"Admin3 title", "Admin3 department")));
		};
	}
/*
	@Bean
	CommandLineRunner initDatabaseAssignment(AssignmentRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Assignment((long) 110, "Assignment1", new Date(2022 - 12 - 12),
					new Date(2022 - 12 - 13), AssignmentState.PROPOSED, null, null)));
			log.info("Preloading " + repository.save(new Assignment((long) 120, "Assignment2", new Date(2022 - 12 - 12),
					new Date(2022 - 12 - 13), AssignmentState.PROPOSED, null, null)));
			log.info("Preloading " + repository.save(new Assignment((long) 130, "Assignment3", new Date(2022 - 12 - 12),
					new Date(2022 - 12 - 13), AssignmentState.PROPOSED, null, null)));
		};
	}
*/
	@Bean
	CommandLineRunner initDatabaseDocumentation(DocumentationRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Documentation((long) 1, "TestDoc1", "TestDocObs1")));
			log.info("Preloading " + repository.save(new Documentation((long) 2, "TestDoc2", "TestDocObs2")));
			log.info("Preloading " + repository.save(new Documentation((long) 3, "TestDoc3", "TestDocObs3")));
		};
	}

	@Bean
	CommandLineRunner initDatabaseGrade(GradeRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Grade((long) 1, (double) 10, new Date(2021 - 12 - 12))));
			log.info("Preloading " + repository.save(new Grade((long) 2, (double) 10, new Date(2021 - 12 - 12))));
			log.info("Preloading " + repository.save(new Grade((long) 3, (double) 10, new Date(2021 - 12 - 12))));
		};
	}

	/*
	@Bean
	CommandLineRunner initDatabaseMeeting(MeetingsRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Meetings((long) 1, "Meeting 1 body", new Date(2021 - 12 - 12),
					new Date(2021 - 12 - 12), null, null, "Meeting 1 obs", "Meeting 1 topic", MeetingState.PROPOSED)));
			log.info("Preloading " + repository.save(new Meetings((long) 2, "Meeting 2 body", new Date(2021 - 12 - 12),
					new Date(2021 - 12 - 12), null, null, "Meeting 2 obs", "Meeting 2 topic", MeetingState.PROPOSED)));
			log.info("Preloading " + repository.save(new Meetings((long) 3, "Meeting 3 body", new Date(2021 - 12 - 12),
					new Date(2021 - 12 - 12), null, null, "Meeting 3 obs", "Meeting 3 topic", MeetingState.PROPOSED)));
		};
	}
*/
	@Bean
	CommandLineRunner initDatabaseNotification(NotificationRepository repository) {

		return args -> {
			log.info("Preloading "
					+ repository.save(new Notification((long) 1, new Date(2021 - 12 - 12), "Notif 1 text")));
			log.info("Preloading "
					+ repository.save(new Notification((long) 2, new Date(2021 - 12 - 12), "Notif 2 text")));
			log.info("Preloading "
					+ repository.save(new Notification((long) 3, new Date(2021 - 12 - 12), "Notif 3 text")));
		};
	}

	@Bean
	CommandLineRunner initDatabaseProfessor(ProfessorRepository repository) {

		return args -> {

			log.info("Preloading " + repository.save(new Professor((long) 100004, "ProfFirstName1", "ProfLastName1",
					new Date(1988 - 12 - 12), "prof1@email.com", "0711111114", "prof 1 adress", (long) 1, "Prof1 title",
					"Prof1 department", "Prof1 faculty")));
			log.info("Preloading " + repository.save(new Professor((long) 100005, "ProfFirstName2", "ProfLastName2",
					new Date(1988 - 12 - 12), "prof2@email.com", "0711111115", "prof 2 adress", (long) 2, "Prof2 title",
					"Prof2 department", "Prof2 faculty")));
			log.info("Preloading " + repository.save(new Professor((long) 100006, "ProfFirstName3", "ProfLastName3",
					new Date(1988 - 12 - 12), "prof3@email.com", "0711111116", "prof 3 adress", (long) 3, "Prof3 title",
					"Prof3 department", "Prof3 faculty")));
		};
	}

	@Bean
	CommandLineRunner initDatabaseStudent(StudentRepository repository) {

		return args -> {

			log.info("Preloading " + repository.save(new Student((long) 100007, "StudFirstName1", "StudLastName1",
					new Date(1999 - 12 - 12), "prof1@email.com", "0711111117", "stud 1 adress", (long) 1,
					"Stud1 faculty", "Stud1 specialization")));
			log.info("Preloading " + repository.save(new Student((long) 100008, "StudFirstName2", "StudLastName2",
					new Date(1999 - 12 - 12), "Stud2@email.com", "0711111118", "stud 2 adress", (long) 2,
					"Stud2 faculty", "Stud1 specialization")));
			log.info("Preloading " + repository.save(new Student((long) 100009, "StudFirstName3", "StudLastName3",
					new Date(1999 - 12 - 12), "Stud3@email.com", "0711111119", "stud 3 adress", (long) 3,
					"Stud3 faculty", "Stud1 specialization")));
		};
	}
	/*
	@Bean
	CommandLineRunner initDatabaseTask(TaskRepository repository) {

		return args -> {

			log.info("Preloading " + repository.save(new Task((long)1, new Date(2021 - 12 - 12), new Date(2021 - 12 - 12), "Task1 desc", null, null, TaskState.PROPOSED)));
			log.info("Preloading " + repository.save(new Task((long)2, new Date(2021 - 12 - 12), new Date(2021 - 12 - 12), "Task2 desc", null, null, TaskState.PROPOSED)));
			log.info("Preloading " + repository.save(new Task((long)3, new Date(2021 - 12 - 12), new Date(2021 - 12 - 12), "Task2 desc", null, null, TaskState.PROPOSED)));
		};
	}
	*/
	/*
	@Bean
	CommandLineRunner initDatabaseWorkgroup(WorkgroupsRepository repository) {

		return args -> {

			log.info("Preloading " + repository.save(new Workgroups((long)1, "Workgroup1 name", null, null)));
			log.info("Preloading " + repository.save(new Workgroups((long)2, "Workgroup2 name", null, null)));
			log.info("Preloading " + repository.save(new Workgroups((long)3, "Workgroup3 name", null, null)));
			};
	}
	*/
}