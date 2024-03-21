package com.in28minutes.springboot.learnjpaandhibernate.course.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJpaRepository {

	//@Autowired
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Course course) {
		em.merge(course);
	}
	
	public Course findById(long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(long id) {
		Course findCourse =  em.find(Course.class, id);
		em.remove(findCourse);
	}
	
	
}
