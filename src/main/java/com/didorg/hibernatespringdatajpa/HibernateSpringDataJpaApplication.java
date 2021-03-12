package com.didorg.hibernatespringdatajpa;

import com.didorg.hibernatespringdatajpa.persintance.domain.Course;
import com.didorg.hibernatespringdatajpa.persintance.domain.Tuition;
import com.didorg.hibernatespringdatajpa.persintance.domain.University;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.didorg.hibernatespringdatajpa.persintance.domain.Student;
import com.didorg.hibernatespringdatajpa.persintance.repository.IStudentRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HibernateSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateSpringDataJpaApplication.class, args);
	}

}
