package com.in28minutes.springboot.learnjpaandhibernate.course;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import com.in28minutes.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa.CourseSpringDataRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner{

//	@Autowired
//	private CourseJdbcRepository repository;
	
//	@Autowired
//	private CourseJpaRepository repository;
	
	@Autowired
	private CourseSpringDataRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course(1,"Lesrn AWS Now!", "in28minutes"));
		repository.save(new Course(2,"Lesrn SpringDataJPA Now!", "in28minutes"));
		repository.save(new Course(3,"Lesrn DevOps Now!", "in28minutes"));
		repository.save(new Course(4,"Lesrn Spring Now!", "in28minutes"));
		
		repository.deleteById(1l);
		
		System.out.println(repository.findById(2l));
		System.out.println(repository.findById(3l));
		
	
	}

}
