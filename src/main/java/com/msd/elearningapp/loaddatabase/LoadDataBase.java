package com.msd.elearningapp.loaddatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.msd.elearningapp.domain.Documentation;
import com.msd.elearningapp.domain.Student;
import com.msd.elearningapp.repository.DocumentationRepository;
import com.msd.elearningapp.repository.StudentRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabaseDocumentation(DocumentationRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Documentation("random", "obs", (long)2)));
      log.info("Preloading " + repository.save(new Documentation("random", "obs2", (long)3)));
    };
  }
  
  @Bean
  CommandLineRunner initDatabaseStudent(StudentRepository repository) {

    return args -> {
      
      log.info("Preloading " + repository.save(new Student((long) 123456789, "FirstName", "LastName", null, "flname@provider.com", null,"Adress1", "+4071111111", "FEAA", "IE", (long)123)));
    
    };
}
  }