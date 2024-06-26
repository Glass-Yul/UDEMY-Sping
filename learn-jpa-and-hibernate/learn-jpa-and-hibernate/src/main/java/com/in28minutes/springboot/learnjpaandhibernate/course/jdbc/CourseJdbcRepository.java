package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;


@Repository
public class CourseJdbcRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private static String INSERT_QUERY =
			"""
				insert into course (id, name, author)
				values (?, ?, ?);
			""";
	
	private static String DELETE_QUERY =
			"""
				delete from course 
				where id=?;
			""";
	
	private static String SELECT_QUERY =
			"""
				select * from course 
				where id=?;
			""";
	
	public void insert(Course course) {
		jdbc.update(INSERT_QUERY, 
				course.getId(), course.getName(), course.getauthor());
	}
	
	public void deleteById(long id) {
		jdbc.update(DELETE_QUERY, id);
		
	}
	
	public Course findById(long id) {
		return jdbc.queryForObject(SELECT_QUERY, 
						new BeanPropertyRowMapper<>(Course.class), id);
		// ResultSet을 Bean에 매핑 한 후 id도 입력값으로 보냄
	}
	
}
