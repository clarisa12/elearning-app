package com.msd.elearningapp.loaddatabase;

import java.time.LocalDate;
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

			log.info("Preloading " + repository.save(new Administrator((long) 100001, "AdmFirstName2", "AdmLastName1",
					new Date(1988 - 12 - 12), "admin1@email.com", "adminpass1@A", "0711111111","admin 1 adress", (long) 1,
					"Admin1 title", "Admin1 department")));
			log.info("Preloading " + repository.save(new Administrator((long) 100002, "AdmFirstName1", "AdmLastName1",
					new Date(1988 - 12 - 12), "admin1@email.com", "adminpass1@A", "0711111111","admin 1 adress", (long) 1,
					"Admin1 title", "Admin1 department")));
			log.info("Preloading " + repository.save(new Administrator((long) 100003, "AdmFirstName3", "AdmLastName1",
					new Date(1988 - 12 - 12), "admin1@email.com", "adminpass1@A", "0711111111","admin 1 adress", (long) 1,
					"Admin1 title", "Admin1 department")));
		};
	}
	
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

}