package com.didorg.hibernatespringdatajpa.persintance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.didorg.hibernatespringdatajpa.persintance.domain.Student;

public interface IStudentRepository extends JpaRepository<Student, Long>{

}
