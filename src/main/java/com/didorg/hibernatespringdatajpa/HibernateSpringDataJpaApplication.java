package com.didorg.hibernatespringdatajpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.didorg.hibernatespringdatajpa.persintance.domain.Student;
import com.didorg.hibernatespringdatajpa.persintance.repository.IStudentRepository;

@SpringBootApplication
public class HibernateSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateSpringDataJpaApplication.class, args);
	}
	
    @Bean
    CommandLineRunner commandLineRunner(IStudentRepository studentRepository){
        return args -> {
            Student maria = new Student("Maria", "Jones", "mari@gmail.edu", 25);
            studentRepository.save(maria);
        };
    }

}
